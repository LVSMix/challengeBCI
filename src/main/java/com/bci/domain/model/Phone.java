package com.bci.domain.model;

import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Phone {
    @Id
    @GeneratedValue
    private Long id;
    private String number;
    private String cityCode;
    private String contryCode;

    public Phone(String number, String cityCode, String contryCode) {
       this.number = number;
       this.cityCode = cityCode;
       this.contryCode = contryCode;
    }
}
