package com.globalL.userServices.responses;

import com.globalL.userServices.requests.TelefonoDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
public class LoginResponse {

    private UUID id;
    private Date created;
    private Date lastLogin;
    private String token;
    private boolean isActive;
    private String name;
    private String password;
    private List<TelefonoDto> phones;
}
