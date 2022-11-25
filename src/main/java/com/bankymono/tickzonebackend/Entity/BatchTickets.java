package com.bankymono.tickzonebackend.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Setter
@Getter
@RequiredArgsConstructor
public class BatchTickets {
    private String name;
    private int noOfTickets;
    private String price;
    private String info;
}
