package com.shine.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * user
 * @author 
 */
@Data
public class User implements Serializable {
    private Integer userid;

    private String username;

    private String password;

    private String address;

    private String phone;

    private static final long serialVersionUID = 1L;
}