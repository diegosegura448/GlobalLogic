package com.globalL.userServices.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionErrorDto {
    @JsonProperty(value = "timestamp")
    private Date timestamp;
    @JsonProperty(value = "codigo")
    private int codigo;
    @JsonProperty(value = "detail")
    private String detail;
}
