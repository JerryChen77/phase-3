package com.shine.controller;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RequestMapping("/upload01")
@Controller
public class UploadController01 {

    /**
     * 获取文件名和文件类型
     * @param multipartFile
     * @return
     */
    @RequestMapping("/up01")
    public String up01(MultipartFile multipartFile){
        String filename = multipartFile.getOriginalFilename();
        System.out.println("filename==>" + filename);

        String contentType = multipartFile.getContentType();
        System.out.println("contentType==>" + contentType);

        return "index";
    }

    @RequestMapping("/up02")
    public String up02(MultipartFile multipartFile, HttpSession session) throws IOException {
        // 获取需要存入的路径
        String realPath = session.getServletContext().getRealPath("/files");

        // 获取原始文件名
        String filename = multipartFile.getOriginalFilename();
        // 存储文件的时候文件名需要怎么操作?   保证文件名的唯一性
        // 生成唯一的文件名
        String uniqueName = UUID.randomUUID().toString();

        // 拼接文件的后缀名
        filename = uniqueName + "-" + filename;
        System.out.println(filename);

        // 把文件写入服务器
        multipartFile.transferTo(new File(realPath + "\\" + filename));

        return "index";
    }
}
