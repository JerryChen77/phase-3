package com.shine.service.impl;

import com.shine.service.AccountService;

public class AccountServiceImpl implements AccountService {
    @Override
    public Integer transfer(Integer money, String fromAccount, String toAccount) {
        if (money < 0){
            throw new RuntimeException("转账金额不能为负数...");
        }
        return 1;
    }
}
