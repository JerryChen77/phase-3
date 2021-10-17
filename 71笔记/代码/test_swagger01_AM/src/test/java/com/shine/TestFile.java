package com.shine;

import com.alibaba.fastjson.JSON;
import com.shine.entity.JsonRootBean;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;

@SpringBootTest
public class TestFile {

    @Test
    public void paresJson(){
        BufferedReader br = null;
        try {
            // 字符缓冲流
            br = new BufferedReader(new InputStreamReader(TestFile.class.getClassLoader().getResourceAsStream("data.txt")));
            char[] c=new char[1024];
            String s = null;
            // 读取json数据
            while ((s=br.readLine()) != null){
                System.out.println(s);
                // 把json字符串转换成对象
                JsonRootBean jsonRootBean = JSON.parseObject(s, JsonRootBean.class);
                System.out.println(jsonRootBean);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
            //JSON.parseObject(msg, InfoBead.class);
        System.out.println("OVER");
    }
}
