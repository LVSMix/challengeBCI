package com.bci.application.port;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
public class PhoneRequest {
    public String number;
    public String citycode;
    public String contrycode;
}
