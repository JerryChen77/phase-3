package com.mybatis.mybatisplus.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * @author Cjl
 * @date 2021/8/3 19:06
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("tb_site")
@ToString
public class Site {
    @TableId(type = IdType.AUTO)
    private Integer siteId;
    private String siteName;
    private Integer userId;

    @TableField(select = false)
    private Integer deviceId;
    @TableField(select = false)
    private String deviceName;
    @TableField(select = false)
    private Integer deviceStatus;
    @TableField(select = false)
    private String deviceType;

}
