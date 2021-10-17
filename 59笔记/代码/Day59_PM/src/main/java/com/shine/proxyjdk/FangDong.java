package com.shine.proxyjdk;

public class FangDong implements ZuFang {
    @Override
    public Integer chuZu(Integer money) {
        System.out.println("房东收到房租:" + money);
        return money;
    }
}
