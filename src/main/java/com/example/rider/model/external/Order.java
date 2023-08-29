package com.example.rider.model.external;

import com.example.rider.model.Rider;

import javax.persistence.*;
import java.util.Date;
import java.util.function.Consumer;

@Entity
@Table(name = "orders")
public class Order {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    @Column
    private String items;

    @Column
    private String customerDetails;

    @Column(name = "customer_location_x")
    private Double customerLocationX;

    @Column(name = "customer_location_y")
    private Double customerLocationY;

    @Column
    private String restaurantDetails;

    @Column(name = "restaurant_location_x")
    private Double restaurantLocationX;

    @Column(name = "restaurant_location_y")
    private Double restaurantLocationY;

    @Column
    private String status;

    @Column
    private Date placedAt;

    @Column
    private Date deliveredAt;

    @ManyToOne
    @JoinColumn(name = "rider_id")
    private Rider rider;

    @Column
    private Double billAmt;

    @Column
    private String paymentMode;

    public Order() {
        int nitem = 1+(int)(Math.random()*4);
        this.items = "";
        this.billAmt = 0.0;
        while (nitem-- > 0) {
            int q = (int)(Math.random()*5);
            double p = Math.random()*500;
            this.items += (getRandomName()+" x "+q+", ");
            this.billAmt += p*q;
        }
        this.items = this.items.substring(0,this.items.length()-2);
        this.billAmt *= (1+Math.random()*0.5);
        double det = Math.random();
        this.customerDetails = (det<0.5?"MR. ":det<0.75?"MS. ":"MRS. ")+getRandomName();
        this.customerLocationX = Math.random();
        this.customerLocationY = Math.random();
        this.restaurantDetails = "RESTAURANT "+getRandomName();
        this.restaurantLocationX = Math.random();
        this.restaurantLocationY = Math.random();
        this.status = "placed";
        this.placedAt = new Date();
    }

    public Long getId() {
        return id;
    }

    public String getCustomerDetails() {
        return customerDetails;
    }

    public String getRestaurantDetails() {
        return restaurantDetails;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getPlacedAt() {
        return placedAt;
    }

    public Date getDeliveredAt() {
        return deliveredAt;
    }

    public void setDeliveredAt(Date deliveredAt) {
        this.deliveredAt = deliveredAt;
    }

    public Rider getRider() {
        return rider;
    }

    public void setRider(Rider rider) {
        this.rider = rider;
    }

    public Double getBillAmt() {
        return billAmt;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getItems() {
        return items;
    }

    public Double[] getRestaurantLocation() {
        return new Double[]{this.restaurantLocationX,this.restaurantLocationY};
    }

    public Double[] getCustomerLocation() {
        return new Double[]{this.customerLocationX, this.customerLocationY};
    }

    private String getRandomName() {
        String s = "";
        int n = 5 + (int) (Math.random() * 5);
        while (n-->0)
            s += (char) (65 + (int) (Math.random() * 26));
        return s;
    }
}
