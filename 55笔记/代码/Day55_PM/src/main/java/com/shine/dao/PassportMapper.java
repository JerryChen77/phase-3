package com.shine.dao;

import com.shine.entity.Passport;

public interface PassportMapper {
    Passport findPassportWithPassengerById(int id);
}
