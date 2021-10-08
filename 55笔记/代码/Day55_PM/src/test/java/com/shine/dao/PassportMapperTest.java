package com.shine.dao;

import com.shine.entity.Passport;
import com.shine.utils.MyBatisUtils;
import org.junit.Test;

public class PassportMapperTest {
    @Test
    public void getPassport(){
        PassportMapper passportMapper = MyBatisUtils.getMapper(PassportMapper.class);

        Passport passport = passportMapper.findPassportWithPassengerById(2);
        System.out.println(passport);
        System.out.println(passport.getPassenger());

        MyBatisUtils.closeSession();
    }
}
