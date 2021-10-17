package com.shine.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.shine.serializer.MySerializable;
import com.shine.serializer.MySerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User3 {
    private Integer id;

    // 属性别名
    @JSONField(name="userName")
    private String username;

    // 忽略属性
    @JSONField(serialize = false)
    private String password;

    // 自定义格式化
    @JSONField(serializeUsing = MySerializable.class)
    private Double salary;

    // 日期格式化
    @JSONField(format="yyyy/MM/dd")
    private Date registerTime;

    // 优化null值
    @JSONField(serialzeFeatures = SerializerFeature.WriteMapNullValue)
    private List<String> hobby;
}
