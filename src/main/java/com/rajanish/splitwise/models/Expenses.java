package com.rajanish.splitwise.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;


import java.util.List;

@Getter
@Setter
@Entity
public class Expenses extends BaseModel{
    private String description;
    @ManyToOne
    private Currrency baseCurrency;

    @ManyToOne
    private Users userCreatedBy;

    @ManyToMany(fetch= FetchType.EAGER)
    private List<Users> participants;
}
