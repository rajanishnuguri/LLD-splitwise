package com.rajanish.splitwise.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
@Entity
@Table(name="sp_users")
public class Users extends BaseModel{
    private String userName;
    private String phoneNumber;
    private String hashedPassword;

    @Override
    public String toString() {
        return "Id: "+this.getId()+" userName="+this.getUserName()+" phoneNumber="+this.getPhoneNumber()
                +" CreatedDate="+this.getCreatedDate();
    }
}
