package com.example.rider.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders")
public class Order {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    @Column
    private Long customerId;

    @Column
    private Long restaurantId;

    @Column
    private String status;

    @Column
    private Date createdAt;

    @Column
    private Date acceptedAt;

    @Column
    private Date completedAt;

    @Column
    private Double totalAmt;

    @Column
    private Double paidAmt;

    @Column
    private String paymentMode;
}
