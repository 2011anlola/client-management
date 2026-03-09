package com.angellopez.client_management.dto;

import com.angellopez.client_management.entity.ClientStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

/**
 * Data Transfer Object for client creation and update requests.
 */
@Data
public class ClientRequestDTO {

    @NotBlank(message = "Name is required")
    private String name;

    @Email(message = "Invalid email format")
    private String email;

    @Pattern(regexp = "^\\+?[0-9\\-]{7,15}$", message = "Phone number invalid")
    private String phone;

    private String address;
    private String country;
    private ClientStatus status;
}