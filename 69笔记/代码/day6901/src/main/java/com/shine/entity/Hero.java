package com.shine.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * hero
 * @author 
 */
@Data
public class Hero implements Serializable {
    private Integer id;

    private String username;

    private String gender;

    private Integer age;

    private String addr;

    private String info;

    private static final long serialVersionUID = 1L;
}