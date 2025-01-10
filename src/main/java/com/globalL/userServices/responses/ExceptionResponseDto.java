package com.globalL.userServices.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponseDto<T> implements Serializable {


        private static final long serialVersionUID = -8097821240224209802L;

        @JsonProperty(value = "error")
        private ExceptionErrorDto error;

}
