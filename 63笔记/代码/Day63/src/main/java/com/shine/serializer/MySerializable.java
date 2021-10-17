package com.shine.serializer;


import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * 格式化小数输出
 */
public class MySerializable implements ObjectSerializer {

    @Override
    public void write(JSONSerializer jsonSerializer, Object object, Object o1, Type type, int i) throws IOException {
        Double value = (Double) object;     // salary属性值
        String text = value + "元";         // 在salary后拼接 “元”
        jsonSerializer.write(text);         // 输出拼接后的内容
    }
}
