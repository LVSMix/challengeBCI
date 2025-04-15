package com.bci.application.port;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    public UUID id;
    public LocalDateTime created;
    public LocalDateTime modified;
    public LocalDateTime lastLogin;
    public String token;
    @JsonProperty("isActive")
    public boolean isActive;
}
