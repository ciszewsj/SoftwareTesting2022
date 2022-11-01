package com.example.testowanieoprogramowania.data;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "order_table")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Status status;

    private Date paid;

    @OneToOne
    private User user;

    @ManyToMany
    private List<Product> products = new ArrayList<>();

    public enum Status {
        NOT_PAID, NEW, PROCESSED, SENT, DELIVERED
    }
}
