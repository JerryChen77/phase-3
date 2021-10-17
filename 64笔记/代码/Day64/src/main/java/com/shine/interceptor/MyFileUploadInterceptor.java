package com.shine.interceptor;

import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 如果文件超出1MB，报异常，异常被自定义的异常解析器处理，重定向到uploadError页面，提示上传文件过大
 */
public class MyFileUploadInterceptor implements HandlerInterceptor {

    private Long maxContentLength;

    public Long getMaxContentLength() {
        return maxContentLength;
    }

    public void setMaxContentLength(Long maxContentLength) {
        this.maxContentLength = maxContentLength;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 判断文件大小是否合适
        ServletRequestContext servletRequestContext = new ServletRequestContext(request);

        // 获取上传文件大小
        long len = servletRequestContext.contentLength();

        // 如果文件超出范围，抛出异常
        if (len > maxContentLength){
            throw new MaxUploadSizeExceededException(maxContentLength);
        }
        return true;
    }
}
