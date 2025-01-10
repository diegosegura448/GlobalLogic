package com.globalL.userServices.entities;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Table(name="telefonos")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Telefono {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int phoneId;
    @Column(nullable = false)
    private UUID user;
    @Column(nullable = false)
    private long number;
    @Column(nullable = false)
    private int cityCode;
    @Column(nullable = false)
    private int countryCode;
}
