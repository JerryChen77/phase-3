package com.shine.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.shine.serializer.MySerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User2 {
    private Integer id;
    // 属性别名
    @JsonProperty("userName")
    private String username;

    // 忽略指定项
    @JsonIgnore
    private String password;

    // 自定义格式化
    @JsonSerialize(using = MySerializer.class)
    private Double salary;

    // 格式化
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date registerTime;

    // 忽略空
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<String> hobby;
}
