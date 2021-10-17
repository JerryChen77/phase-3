package com.shine.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MyLoginInterceptor implements HandlerInterceptor {
    /**
     * 请求到达之前处理
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        // System.out.println("MyLoginInterceptor === preHandle ===》");
        // 判断session中的数据是否合法
        if (session.getAttribute("isLogin") != null){
            // 验证成功,放行,执行后面的handler
            return true;
        }
        // 重定向到登录页面
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return false;
    }

    /**
     * 请求结束之后处理
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // System.out.println("MyLoginInterceptor === postHandle ===》");
    }

    /**
     * 视图加载完成之后处理
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // System.out.println("MyLoginInterceptor === afterCompletion ===》");
    }
}
