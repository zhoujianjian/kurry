//package com.cancer.star.http.filter;
//
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.BufferedReader;
//import java.io.IOException;
//
///**
// * Created by zhoujian on 2018/7/5
// *
// * @Desc 类描述.
// */
////@Component
////@javax.servlet.annotation.WebFilter
//public class WebFilter implements Filter {
//
//    private static final Logger logger = LoggerFactory.getLogger(WebFilter.class);
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//
//    }
//
//    public static String getBodyString(BufferedReader br, String reqUrl) {
//        String inputLine;
//        StringBuilder sb = new StringBuilder();
//        try {
//            while ((inputLine = br.readLine()) != null) {
//                sb.append(inputLine);
//            }
//            br.close();
//        } catch (IOException e) {
//            logger.warn("requestUrl: " + reqUrl + ", Request error: "
//                    + e.getMessage() + "Request URI: ");
//        }
//        return sb.toString();
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        ServletRequest requestWrapper = new CommonHttpServletRequestWrapper((HttpServletRequest) servletRequest);
//
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//        String sign = request.getHeader("sign");
//        String sessionId = request.getHeader("userCookiesName");
//        String APPVersion=request.getHeader("APPVersion");
//        String uri = request.getRequestURI();
//        request.setAttribute("APPVersion",APPVersion);
//        String userCookiesName = request.getHeader("userCookiesName");
//        logger.info("uri:"+uri+";sign:"+sign+";userCookiesName:"+userCookiesName);
//
//        String reqData = getBodyString(requestWrapper.getReader(), uri);
//        logger.warn("CommonFilter读取body中的参数: " + reqData);
//
//
//
//        filterChain.doFilter(servletRequest,servletResponse);
//
//
//
//    }
//
//    @Override
//    public void destroy() {
//
//    }
//}
