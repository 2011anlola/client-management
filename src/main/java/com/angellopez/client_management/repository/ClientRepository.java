package com.angellopez.client_management.repository;

import com.angellopez.client_management.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
