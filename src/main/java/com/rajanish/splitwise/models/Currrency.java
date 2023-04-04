package com.rajanish.splitwise.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class Currrency extends BaseModel{
    private String baseCurrency;
}
