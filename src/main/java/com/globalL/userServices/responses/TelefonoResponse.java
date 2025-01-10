package com.globalL.userServices.responses;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TelefonoResponse {
    private long number;
    private int cityCode;
    private int countryCode;
}
