package com.szymon.application.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Address {

    @Id
    @Column(name = "emp_id", unique = true, nullable = false)
    private long id;

    @Column(name = "address_line1")
    private String addressLine1;

    @Column(name = "zipcode")
    private String zipcode;

    @Column(name = "city")
    private String city;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Employee employee;

    @Override
    public String toString() {
        return "AddressLine1= " + addressLine1 + ", City=" + city
                + ", Zipcode=" + zipcode;
    }
}
