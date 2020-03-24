package com.ken.mall.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 日志记录拦截器
 * com.xfbetter.web.interceptor
 * author Daniel
 * 2017/12/20.
 */
@Component
public class LogInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(LogInterceptor.class);

    /** 默认忽略参数 */
    private static final String[] DEFAULT_IGNORE_PARAMETERS = new String[] { "password", "rePassword","tradePassword"};

    private String[] ignoreParameters = DEFAULT_IGNORE_PARAMETERS;

    private static AntPathMatcher antPathMatcher = new AntPathMatcher();

    //@Autowired
    //private LogConfigService logConfigService;

    //@Autowired
    //private OperationLogService logService;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        /*//日志配置
        List<LogConfig> logConfigs = logConfigService.getConfigs();

        if(logConfigs == null){
            return;
        }
        //请求方式
        String requestMethod = request.getMethod().toUpperCase();
        //请求url
        String path = request.getServletPath();


        for(LogConfig logConfig:logConfigs){
            if(!StringUtils.equals(requestMethod,logConfig.getMethod())){
                return;
            }
            if (antPathMatcher.match(logConfig.getUrlPattern(), path)) {
                //取出当前操作人
                String username = (String)SecurityUtils.getSubject().getPrincipal();
                String operation = logConfig.getOperation();
                String ip = IpUtils.getIpAddr(request);
                String content = (String) request.getAttribute(MallConstants.LOG_CONTENT_REQUEST_KEY);
                request.removeAttribute(MallConstants.LOG_CONTENT_REQUEST_KEY);
                StringBuffer parameter = new StringBuffer();
                Map<String, String[]> parameterMap = request.getParameterMap();
                if (parameterMap != null) {
                    for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
                        String parameterName = entry.getKey();
                        if (!ArrayUtils.contains(ignoreParameters, parameterName)) {
                            String[] parameterValues = entry.getValue();
                            if (parameterValues != null) {
                                for (String parameterValue : parameterValues) {
                                    parameter.append(parameterName + " = " + parameterValue + "\n");
                                }
                            }
                        }
                    }
                }
                OperationLog log = new OperationLog();
                log.setOperation(operation);
                log.setOperator(username);
                log.setIp(ip);
                log.setContent(content);
                log.setParameter(parameter.toString());
                try {
                    this.logService.save(log);
                }catch (Exception ex){
                    logger.error("日志写入错误,reason:{}",ex.getMessage());
                }
                break;
            }
        }*/

    }
}
