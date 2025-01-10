package com.globalL.userServices.responses;

import com.globalL.userServices.requests.TelefonoDto;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RegistroResponse {

    private UUID id;
    private String name;
    private String email;
    private String token;
    private Date created;
    private Date lastLogin;
    private boolean isActive;
    private List<TelefonoDto> phones;
}
