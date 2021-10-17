package com.shine.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

@RequestMapping("/down")
@Controller
public class DownloadController {

    /**
     * 下载文件
     * @param multipartFile
     * @return
     */
    @RequestMapping("/down01")
    public void down01(String filename, HttpSession session, HttpServletResponse response) throws IOException {
        // 下载目标文件的名字
        System.out.println("name:"+filename);

        // 获得要下载文件的绝对路径
        String path = session.getServletContext().getRealPath("/files");

        //文件的完整路径
        String real_path = path+"\\"+filename;

        // 设置响应头  告知浏览器，要以附件的形式保存内容   filename=浏览器显示的下载文件名
        response.setHeader("content-disposition","attachment;filename="+filename);

        // 读取目标文件，写出给客户端
        IOUtils.copy(new FileInputStream(real_path), response.getOutputStream());
    }

}
