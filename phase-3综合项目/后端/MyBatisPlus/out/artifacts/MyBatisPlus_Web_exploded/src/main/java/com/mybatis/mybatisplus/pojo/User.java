package com.mybatis.mybatisplus.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Cjl
 * @date 2021/7/30 21:24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("tb_user")  //指定映射的表名
public class User implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String userName;
//    @TableField(select = false)
    private String password;
    private String name;
    private Integer age;
//    @TableField("email")
    private String email;
//    @TableField(exist = false)
    private Integer status;
    private Date registerTime;
    private Date updateTime;
}
