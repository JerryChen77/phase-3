package com.mybatis.mybatisplus.vo;

import lombok.Data;

import java.io.Serializable;


@Data
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
