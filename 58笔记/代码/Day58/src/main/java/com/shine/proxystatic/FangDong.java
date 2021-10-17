package com.shine.proxystatic;

public class FangDong implements ZuFang {
    @Override
    public Integer chuZu(Integer money) {
        System.out.println("房东收房租==>" + money);
        return money;
    }
}
