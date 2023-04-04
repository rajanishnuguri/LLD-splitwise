package com.rajanish.splitwise.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name="sp_groups")
public class Groups extends BaseModel{
    private String name;
    @ManyToOne
    private Users createdBy;
    @ManyToMany
    private List<Users> participants;
    @ManyToMany
    private List<Users> admins;
    @OneToMany
    private List<Expenses> expenses;
}
