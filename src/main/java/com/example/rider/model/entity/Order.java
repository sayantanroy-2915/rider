package com.example.rider.model.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Class to represent Orders <br>
 * Properties: id, items, restaurantName, restaurantAddressLine, restaurantLocationX, restaurantLocationY,
 * customerName, customerAddressLine, customerLocationX, customerLocationY, city, placedAt, pickedUpAt,
 * deliveredAt, billAmt, paymentMode
 *
 */
@Entity
@Table(name = "orders")
public class Order {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    private String items;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "customer_address_line")
    private String customerAddressLine;

    @Column(name = "customer_location_x")
    private Double customerLocationX;

    @Column(name = "customer_location_y")
    private Double customerLocationY;

    @Column(name = "restaurant_name")
    private String restaurantName;

    @Column(name = "restaurant_address_line")
    private String restaurantAddressLine;

    @Column(name = "restaurant_location_x")
    private Double restaurantLocationX;

    @Column(name = "restaurant_location_y")
    private Double restaurantLocationY;

    @Column
    private String city;

    @Column
    private String status;

	@Column(name = "bill_amt")
    private Double billAmt;

    @Column(name = "payment_mode")
    private String paymentMode;

    @JoinColumn(name = "rider_id")
    private Long riderId;

    @Column(name = "placed_at")
    private Date placedAt;

    @Column(name = "picked_up_at")
    private Date pickedUpAt;

    @Column(name = "delivered_at")
    private Date deliveredAt;

    public Order() {}

    public Order(String items, String customerName, String customerAddressLine, Double customerLocationX, Double customerLocationY, String restaurantName, String restaurantAddressLine, String city, Double restaurantLocationX, Double restaurantLocationY, Double billAmt, String paymentMode) {
        this.items = items;
        this.customerName = customerName;
        this.customerAddressLine = customerAddressLine;
        this.customerLocationX = customerLocationX;
        this.customerLocationY = customerLocationY;
        this.restaurantName = restaurantName;
        this.restaurantAddressLine = restaurantAddressLine;
        this.city = city;
        this.restaurantLocationX = restaurantLocationX;
        this.restaurantLocationY = restaurantLocationY;
        this.status = "placed";
        this.billAmt = billAmt;
        this.paymentMode = paymentMode;
        this.riderId = null;
        this.placedAt = new Date();
        this.pickedUpAt = null;
        this.deliveredAt = null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddressLine() {
        return customerAddressLine;
    }

    public void setCustomerAddressLine(String customerAddressLine) {
        this.customerAddressLine = customerAddressLine;
    }

    public Double getCustomerLocationX() {
        return customerLocationX;
    }

    public void setCustomerLocationX(Double customerLocationX) {
        this.customerLocationX = customerLocationX;
    }

    public Double getCustomerLocationY() {
        return customerLocationY;
    }

    public void setCustomerLocationY(Double customerLocationY) {
        this.customerLocationY = customerLocationY;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantAddressLine() {
        return restaurantAddressLine;
    }

    public void setRestaurantAddressLine(String restaurantAddressLine) {
        this.restaurantAddressLine = restaurantAddressLine;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Double getRestaurantLocationX() {
        return restaurantLocationX;
    }

    public void setRestaurantLocationX(Double restaurantLocationX) {
        this.restaurantLocationX = restaurantLocationX;
    }

    public Double getRestaurantLocationY() {
        return restaurantLocationY;
    }

    public void setRestaurantLocationY(Double restaurantLocationY) {
        this.restaurantLocationY = restaurantLocationY;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getBillAmt() {
        return billAmt;
    }

    public void setBillAmt(Double billAmt) {
        this.billAmt = billAmt;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public Long getRiderId() {
        return riderId;
    }

    public void setRiderId(Long riderId) {
        this.riderId = riderId;
    }

    public Date getPlacedAt() {
        return placedAt;
    }

    public void setPlacedAt(Date placedAt) {
        this.placedAt = placedAt;
    }

    public Date getPickedUpAt() {
        return pickedUpAt;
    }

    public void setPickedUpAt(Date pickedUpAt) {
        this.pickedUpAt = pickedUpAt;
    }

    public Date getDeliveredAt() {
        return deliveredAt;
    }

    public void setDeliveredAt(Date deliveredAt) {
        this.deliveredAt = deliveredAt;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", items='" + items + '\'' +
                ", customerName='" + customerName + '\'' +
                ", CustomerAddressLine='" + customerAddressLine + '\'' +
                ", customerLocationX=" + customerLocationX +
                ", customerLocationY=" + customerLocationY +
                ", restaurantName='" + restaurantName + '\'' +
                ", restaurantAddressLine='" + restaurantAddressLine + '\'' +
                ", city='" + city + '\'' +
                ", restaurantLocationX=" + restaurantLocationX +
                ", restaurantLocationY=" + restaurantLocationY +
                ", status='" + status + '\'' +
                ", billAmt=" + billAmt +
                ", paymentMode='" + paymentMode + '\'' +
                ", riderId=" + riderId +
                ", placedAt=" + placedAt +
                ", pickedUpAt=" + pickedUpAt +
                ", deliveredAt=" + deliveredAt +
                '}';
    }
}
