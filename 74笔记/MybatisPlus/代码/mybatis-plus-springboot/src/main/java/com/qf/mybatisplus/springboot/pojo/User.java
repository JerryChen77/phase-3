package com.qf.mybatisplus.springboot.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author ghy
 * @version 1.0
 * @date 2021-07-30
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("tb_user")  //指定映射的表名
public class User implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;
    private String userName;

    @TableField(select = false)
    private String password;
    private String name;
    private Integer age;


    //private String email;

    @TableField("email")   //查询email字段时，封装给mail属性
    private String mail;

    //指定该属性不映射数据库字段名
    @TableField(exist = false)
    private String address;


}
