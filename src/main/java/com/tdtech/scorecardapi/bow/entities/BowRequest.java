package com.tdtech.scorecardapi.bow.entities;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BowRequest {
    private String bowType;
    private String name;
    private String manufacturer;
    private String model;
    private double drawWeight;
    private double ataLength;
    private double braceHeight;
}
