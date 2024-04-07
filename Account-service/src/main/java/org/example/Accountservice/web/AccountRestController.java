package org.example.Accountservice.web;

import org.example.Accountservice.client.CustomerRestClient;
import org.example.Accountservice.entities.BankAccount;
import org.example.Accountservice.enums.AccountType;
import org.example.Accountservice.model.Customer;
import org.example.Accountservice.repositories.BankAccountRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
public class AccountRestController {
    private BankAccountRepository bankAccountRepository;
    private CustomerRestClient customerRestClient;

    public AccountRestController(BankAccountRepository bankAccountRepository, CustomerRestClient customerRestClient) {
        this.bankAccountRepository = bankAccountRepository;
        this.customerRestClient = customerRestClient;
    }
    @GetMapping("/accounts")
    public List<BankAccount> accountList(){

        List<BankAccount> accountList = bankAccountRepository.findAll();
        accountList.forEach(a->{
            a.setCustomer(customerRestClient.findCustomerById(a.getCustomerId()));
        });
        return accountList;
    }
    @GetMapping("/accounts/{id}")
    public BankAccount bankAccountById(@PathVariable String id){

        BankAccount bankAccount = bankAccountRepository.findById(id).get();
        Customer customer=customerRestClient.findCustomerById(bankAccount.getCustomerId());
        bankAccount.setCustomer(customer);
        return bankAccount;
    }

    @GetMapping("/save")
    public void saveBankAccount(){
        customerRestClient.allCustomers().forEach(c->{
            BankAccount bankAccount=BankAccount.builder()
                    .accountId(UUID.randomUUID().toString())
                    .type(AccountType.SAVING_ACCOUNT)
                    .currency("MAD")
                    .balance(Math.random()*56789)
                    .customerId(c.getId())
                    .createAt(new Date())
                    .build();
            BankAccount bankAccount2=BankAccount.builder()
                    .accountId(UUID.randomUUID().toString())
                    .type(AccountType.CURRENT_ACCOUNT)
                    .currency("MAD")
                    .balance(Math.random()*90000)
                    .customerId(c.getId())
                    .createAt(new Date())
                    .build();
            bankAccountRepository.save(bankAccount);
            bankAccountRepository.save(bankAccount2);
        });
    }

}

