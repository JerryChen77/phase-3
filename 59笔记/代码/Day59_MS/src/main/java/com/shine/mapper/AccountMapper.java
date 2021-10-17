package com.shine.mapper;

import com.shine.entity.Account;

/**
 * 数据访问层接口
 */
public interface AccountMapper {
    Account findByUsername(String username);

    Integer updateAccount(Account account);

}
