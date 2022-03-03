package com.zhang.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Assign {
    private int id;
    private String driverUsername;
    private String driverIdCard;
    private String carName;
    private String carLicenseLateNumber;
    private Double profit;
}
