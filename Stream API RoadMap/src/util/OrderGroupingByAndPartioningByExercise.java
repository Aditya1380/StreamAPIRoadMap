package util;

import java.time.LocalDate;

public class OrderGroupingByAndPartioningByExercise {
	private String orderId;
    private LocalDate orderDate;
    private double amount;

    public OrderGroupingByAndPartioningByExercise(String orderId, LocalDate orderDate, double amount) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.amount = amount;
    }

    public LocalDate getOrderDate() { return orderDate; }
    public double getAmount() { return amount; }
}
