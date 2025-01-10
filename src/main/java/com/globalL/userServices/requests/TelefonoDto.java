package com.globalL.userServices.requests;

import lombok.*;

@Data
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TelefonoDto {
    private long number;
    private int cityCode;
    private int countryCode;
}
