package com.angellopez.client_management.dto;

import com.angellopez.client_management.entity.ClientStatus;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Data Transfer Object for client response data.
 */
@Data
@Builder
@JsonPropertyOrder({ "id", "name", "email", "phone", "address", "country", "status", "createdAt", "updatedAt" })
public class ClientResponseDTO {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String country;
    private ClientStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}