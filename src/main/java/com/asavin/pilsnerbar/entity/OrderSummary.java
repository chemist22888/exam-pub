package com.asavin.pilsnerbar.entity;

import com.asavin.pilsnerbar.json.OrderView;
import com.fasterxml.jackson.annotation.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderSummary {
    @JsonView(OrderView.SummaryAllView.class)
    @JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="name")
    @JsonIdentityReference(alwaysAsId=true) // otherwise first ref as POJO, others as id
    Product product;
    @JsonView(OrderView.SummaryProductView.class)
    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
    @JsonIdentityReference(alwaysAsId=true) // otherwise first ref as POJO, others as id
    User user;

    public OrderSummary(User user, Long amount, Double summaryPrice) {
        this.user = user;
        this.amount = amount;
        this.summaryPrice = summaryPrice;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    Long amount;
    Double summaryPrice;

    public OrderSummary() {
    }

    public OrderSummary(Product product, Long amount, Double summaryPrice) {
        this.product = product;
        this.amount = amount;
        this.summaryPrice = summaryPrice;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Double getSummaryPrice() {
        return summaryPrice;
    }

    public void setSummaryPrice(Double summaryPrice) {
        this.summaryPrice = summaryPrice;
    }
}
