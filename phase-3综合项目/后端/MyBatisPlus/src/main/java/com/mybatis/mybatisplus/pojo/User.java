package com.mybatis.mybatisplus.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
@ToString
public class User implements Serializable {
    //指定主键
    @TableId(type = IdType.AUTO)
    private Long userId;
    private String userName;
//    @TableField(select = false)
    private String password;
//    private String name;
//    private Integer age;
//    @TableField("email")
//    private String email;
//    @TableField(exist = false)
//    private Integer status;
//    private Date registerTime;
//    private Date updateTime;
    //分支合并完成
}
