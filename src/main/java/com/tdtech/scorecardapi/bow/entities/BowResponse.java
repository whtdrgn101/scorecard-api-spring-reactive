package com.tdtech.scorecardapi.bow.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BowResponse {
    private String bowType;
    private String name;
    private String manufacturer;
    private String model;
    private Double ataLength;
    private Double drawWeight;
    private Double braceHeight;

    public BowResponse(BowDto bow) {
        this.bowType = bow.getBowType();
        this.name = bow.getName();
        this.manufacturer = bow.getManufacturer();
        this.model = bow.getModel();
        this.drawWeight = bow.getDrawWeight();
        this.ataLength = bow.getAtaLength();
        this.braceHeight = bow.getBraceHeight();
    }
}
