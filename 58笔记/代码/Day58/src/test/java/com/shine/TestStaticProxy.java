package com.shine;

import com.shine.proxystatic.ZhongJie;
import org.junit.Test;

public class TestStaticProxy {
    @Test
    public void testZu(){
        ZhongJie zhongJie = new ZhongJie();
        Integer zu = zhongJie.chuZu(3600);
    }

}
