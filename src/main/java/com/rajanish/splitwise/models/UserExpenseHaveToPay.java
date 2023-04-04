
package com.rajanish.splitwise.models;

        import jakarta.persistence.Entity;
        import jakarta.persistence.ManyToOne;
        import lombok.Getter;
        import lombok.Setter;



@Getter
@Setter
@Entity
public class UserExpenseHaveToPay extends BaseModel{
    @ManyToOne
    private Users User;
    @ManyToOne
    private Expenses expense;

    private double amount;

}
