package com.angellopez.client_management.dto;

import com.angellopez.client_management.entity.ClientStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
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
