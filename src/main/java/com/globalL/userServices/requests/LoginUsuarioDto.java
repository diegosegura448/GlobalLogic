package com.globalL.userServices.requests;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LoginUsuarioDto {

    @NotBlank(message="El e-mail es requerido.")
    @Pattern(regexp = "^[^@]+@[^@]+\\.[a-zA-Z]{2,}$", message = "El e-mail errado.")
    private String email;
    @NotBlank(message="El password es requerido.")
    @Pattern(regexp = "^(?=[^A-Z]*[A-Z][^A-Z]*$)(?=[^0-9]*\\d[^0-9]*\\d[^0-9]*$)[a-zA-Z0-9]{8,12}$", message = "El password errado.")
    private String password;
}
