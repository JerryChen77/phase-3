package com.shine.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * User类
 *  对应数据库中的user表
 */
@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "用户实体类",description = "User类，用户实体了，对应数据库中的user表")
public class User {

    @ApiModelProperty(name = "id",value = "用户id,主键，唯一，自增",example = "10010")
    private Integer id;

    @ApiModelProperty(name = "username",value = "用户名字,非主键，不唯一，不自增",example = "zhangsan")
    private String username;
    private String gender;
    private String addr;
    private String info;
}
