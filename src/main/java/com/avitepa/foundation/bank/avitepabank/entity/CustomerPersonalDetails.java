package com.avitepa.foundation.bank.avitepabank.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Setter
@Getter
public class CustomerPersonalDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "first_name" , nullable = false)
    private String firstName;

    @Column(name = "last_name" )
    private String lastName;

    @Column(name = "date_birth" )
    private LocalDateTime dateOfBirth;

    private String gender;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "mobile" )
    private String mobile;

    private String phone;

    @Column(name = "marital_status")
    private String maritalStatus;

    @JoinColumn(name = "customer_id" , referencedColumnName = "customer_id" ,unique = true)
    @OneToOne
    private Customer customer;
}
