package com.shine.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Passport {
    private int id;
    private String nationality;
    private Date expire;
    private int passengerId;

    private Passenger passenger;

    @Override
    public String toString() {
        return "Passport{" +
                "id=" + id +
                ", nationality='" + nationality + '\'' +
                ", expire=" + expire +
                ", passengerId=" + passengerId +
                '}';
    }
}
