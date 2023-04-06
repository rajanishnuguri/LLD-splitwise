package com.rajanish.splitwise.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
@Entity
@Table(name="sp_users",
        uniqueConstraints={
                @UniqueConstraint(columnNames = "phone_number"),
                @UniqueConstraint(columnNames = "user_name")
        }
)
public class Users extends BaseModel{
    @Column(name="user_name")
    private String userName;
    @Column(name="phone_number")
    private String phoneNumber;
    private String hashedPassword;

    @Override
    public String toString() {
        return "Id: "+this.getId()+" userName="+this.getUserName()+" phoneNumber="+this.getPhoneNumber()
                +" CreatedDate="+this.getCreatedDate();
    }
}
