package com.shine.proxystatic;

public class ZhongJie implements ZuFang {
    FangDong fangDong = new FangDong();
    @Override
    public Integer chuZu(Integer money) {
        System.out.println("发广告");
        System.out.println("带看房");
        System.out.println("签合同");
        fangDong.chuZu(money-400);
        System.out.println("中介收房租===》" + money);
        System.out.println("修电器...");
        return money;
    }
}
