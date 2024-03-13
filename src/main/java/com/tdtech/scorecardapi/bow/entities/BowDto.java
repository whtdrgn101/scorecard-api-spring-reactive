package com.tdtech.scorecardapi.bow.entities;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BowDto {
    private String bowType;
    private String name;
    private String manufacturer;
    private String model;
    private double drawWeight;
    private double ataLength;
    private double braceHeight;

    public BowDto(BowRequest bow) {
        this.name = bow.getName();
        this.bowType = bow.getBowType();
        this.manufacturer = bow.getManufacturer();
        this.model = bow.getModel();
        this.drawWeight = bow.getDrawWeight();
        this.ataLength = bow.getAtaLength();
        this.braceHeight = bow.getBraceHeight();
    }
}
