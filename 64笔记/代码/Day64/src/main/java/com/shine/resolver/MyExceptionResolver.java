package com.shine.resolver;

import com.shine.exception.MyException01;
import com.shine.exception.MyException02;
import com.shine.exception.MyException03;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 * 异常解析器
 */
public class MyExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ModelAndView modelAndView = new ModelAndView();

        // 判断异常的类型
        if (ex instanceof MyException01){
            modelAndView.setViewName("redirect:/error01.jsp");
        } else if (ex instanceof SQLException){
            modelAndView.setViewName("redirect:/error02.jsp");
        } else if (ex instanceof NullPointerException){
            modelAndView.setViewName("redirect:/error03.jsp");
        } else if (ex instanceof MaxUploadSizeExceededException){
            modelAndView.setViewName("redirect:/uploadError.jsp");
        }
        return modelAndView;
    }
}
