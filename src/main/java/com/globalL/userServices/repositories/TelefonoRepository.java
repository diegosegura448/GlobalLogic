package com.globalL.userServices.repositories;

import com.globalL.userServices.entities.Telefono;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TelefonoRepository extends CrudRepository<Telefono, Integer> {
    List<Telefono> findByUser(UUID idUser);
}
