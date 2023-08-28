package com.example.rider.model.external;

import javax.persistence.*;
import java.awt.geom.Point2D;
import java.util.Date;

@Entity
@Table(name = "orders")
public class Order {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    @Column
    private String customerDetails;

    @Column
    private Point2D customerLocation;

    @Column
    private String restaurantDetails;

    @Column
    private Point2D restaurantLocation;

    @Column
    private String status;

    @GeneratedValue(strategy = GenerationType.AUTO)
    private Date placedAt;

    @Column
    private Date deliveredAt;

    @Column
    private Double billAmt;

    @Column
    private String paymentMode;

    public Long getId() {
        return id;
    }


}
