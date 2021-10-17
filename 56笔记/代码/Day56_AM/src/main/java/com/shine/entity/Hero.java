package com.shine.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hero {
    private Integer id;
    private String username;
    private Integer age;
    private String addr;
    private String info;
}
