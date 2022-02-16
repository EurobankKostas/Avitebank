package com.avitepa.foundation.bank.avitepabank.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "customer")
@Setter
@Getter
public class Customer{

    @Id
    @Column(name = "customer_id")
    private long customerId;

    @CreationTimestamp
    @Column(name = "registered_date" , updatable = false ,columnDefinition = "TIMESTAMP")
    private LocalDateTime dateTime;

    @Column(name = "lastLogin" , columnDefinition = "TIMESTAMP")
    @UpdateTimestamp
    private LocalDateTime lastEdit;

    @OneToOne(mappedBy = "customer" , cascade = CascadeType.MERGE)
    private CustomerPersonalDetails customerPersonalDetails;

    @OneToMany(cascade = CascadeType.ALL  , fetch = FetchType.LAZY ,mappedBy = "customer")
    private List<Account> accountList;

}
