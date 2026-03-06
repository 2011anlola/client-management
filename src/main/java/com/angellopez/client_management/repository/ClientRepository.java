package com.angellopez.client_management.repository;

import com.angellopez.client_management.entity.Client;
import com.angellopez.client_management.entity.ClientStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {

    List<Client> findByStatus(ClientStatus status);

    List<Client> findByCountry(String country);

    List<Client> findByStatusAndCountry(ClientStatus status, String country);
}