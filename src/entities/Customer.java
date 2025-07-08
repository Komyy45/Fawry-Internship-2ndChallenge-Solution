package entities;

import value_objects.Address;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Customer {
    private final String id;
    private String name;
    private String email;
    private Address address;
    private BigDecimal balance;

    public Customer(String id,
                    String name,
                    String email,
                    Address address,
                    BigDecimal balance)
    {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
