package com.tangdabao.springcloud.zuul.filter;

import com.alibaba.fastjson.JSONObject;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.tangdabao.springcloud.zuul.config.OauthConfig;
import com.tangdabao.springcloud.zuul.utils.BaseResultVo;
import com.tangdabao.springcloud.zuul.utils.HttpUtil;
import com.tangdabao.springcloud.zuul.utils.ResultEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 请求地址 授权检查过滤器
 */
@Component
public class AuthFilter extends ZuulFilter {
    @Autowired
    private OauthConfig oauthConfig;

    private Logger logger=LoggerFactory.getLogger(this.getClass());

    /**
     * PRE: 该类型的filters在Request routing到源web-service之前执行。用来实现Authentication、选择源服务地址等
     * ROUTING：该类型的filters用于把Request routing到源web-service，源web-service是实现业务逻辑的服务。这里使用HttpClient请求web-service。
     * POST：该类型的filters在ROUTING返回Response后执行。用来实现对Response结果进行修改，收集统计数据以及把Response传输会客户端。
     * ERROR：上面三个过程中任何一个出现错误都交由ERROR类型的filters进行处理。
     */
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        if(!oauthConfig.isEnabled() || oauthConfig.getAuthenticated_list()==null)
            return null;

        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();


        String accessToken=request.getParameter("access_token");
        String clientId=request.getParameter("client_id");


        //检测需要授权的url资源
        AntPathMatcher matcher=new AntPathMatcher();

        try{
            for(String uri:oauthConfig.getAuthenticated_list()){
                if(!matcher.match(uri, request.getRequestURI().toString()))
                    continue;

                if(accessToken==null || clientId==null){
                    responseError(ctx, ResultEnum.ERROR_PARAM,"参数不完整");
                }

                //从auth服务器里面获取token信息
                String jsonResult=HttpUtil.sendPost(oauthConfig.getCheck_token_url(), "token="+accessToken);
                Map result=(Map)JSONObject.parse(jsonResult);

                //normal:{"aud":["oauth-resource-test-1"],"user_name":"user","scope":["app"],"exp":1503393541,"authorities":["ROLE_USER","ROLE_ACTUATOR"],"client_id":"oauth-client"}
                //error:{"error":"invalid_token","error_description":"Token was not recognised"}
                if(result==null){
                    responseError(ctx,ResultEnum.SYSTEM_ERROR,"无法解析返回结果信息");
                }

                if(result.containsKey("error")){
                    responseError(ctx,ResultEnum.SYSTEM_ERROR,result.get("error_description").toString());
                }
                //校验token的合法性
                if(!result.get("client_id").equals(clientId)){
                    responseError(ctx,ResultEnum.SYSTEM_ERROR,"非法token,参数不匹配");
                }

                break;
            }
        }catch(Exception e){
            logger.error(e.getMessage(),e);
            responseError(ctx,ResultEnum.SYSTEM_ERROR,null);
        }

        return null;
    }

    private void responseError(RequestContext ctx,ResultEnum status,String msg){
        BaseResultVo<Void> result=new BaseResultVo<Void>(status);
        if(msg!=null)
            result.setMsg(msg);

        ctx.setSendZuulResponse(false);
        try {
            ctx.getResponse().setHeader("Content-type", "application/json");
            String jsonStr=JSONObject.toJSONString(result);
            logger.info("oauth 授权验证:"+jsonStr);
            ctx.getResponse().getWriter().write(jsonStr);
        }catch (Exception e){
            logger.error("授权检查后失败，写入错误信息失败！",e);
        }

    }
}
