package com.angellopez.client_management.repository;

import com.angellopez.client_management.entity.Client;
import com.angellopez.client_management.entity.ClientStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Page<Client> findByStatus(ClientStatus status, Pageable pageable);

    Page<Client> findByCountry(String country, Pageable pageable);

    Page<Client> findByStatusAndCountry(ClientStatus status, String country, Pageable pageable);
}
