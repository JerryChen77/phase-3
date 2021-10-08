package com.mybatis.mybatisplus.vo;

import lombok.Data;
import org.springframework.stereotype.Component;
import java.io.Serializable;

/**
 * 结果集，实现Serializable接口，用于网络传输，到前端页面
 */
@Data
@Component
public class ResultVO implements Serializable {

    private Boolean success;
    private String message;
    private Object data;

    public static ResultVO ok(String message, Object data){
        ResultVO vo = new ResultVO();
        vo.setSuccess(true);
        vo.setMessage(message);
        vo.setData(data);
        return vo;
    }

    public static ResultVO ok(String message){
        ResultVO vo = new ResultVO();
        vo.setSuccess(true);
        vo.setMessage(message);
        return vo;
    }

    public static ResultVO error(String message, Object data){
        ResultVO vo = new ResultVO();
        vo.setSuccess(false);
        vo.setMessage(message);
        vo.setData(data);
        return vo;
    }

    public static ResultVO error(String message){
        ResultVO vo = new ResultVO();
        vo.setSuccess(false);
        vo.setMessage(message);
        return vo;
    }

}
