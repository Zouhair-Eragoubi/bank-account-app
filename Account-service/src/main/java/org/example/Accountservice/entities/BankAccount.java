package org.example.Accountservice.entities;

import jakarta.persistence.*;
import lombok.*;
import org.example.Accountservice.enums.AccountType;
import org.example.Accountservice.model.Customer;

import java.util.Date;
@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class BankAccount {
    @Id
    private String accountId;
    private double balance;
    private Date createAt;
    private String currency;
    @Enumerated(EnumType.STRING)
    private AccountType type;
    @Transient
    private Customer customer;
    private Long customerId;

}
