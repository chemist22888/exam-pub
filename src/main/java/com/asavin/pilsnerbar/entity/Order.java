package com.asavin.pilsnerbar.entity;

import com.asavin.pilsnerbar.json.OrderView;
import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "ordr")
public class Order {

    Long id;
//    @JsonView(OrderView.SummaryAllView.class)
    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
    @JsonIdentityReference(alwaysAsId=true) // otherwise first ref as POJO, others as id
    Product product;
    @JsonInclude(JsonInclude.Include.NON_NULL)
//    @JsonView(OrderView.SummaryProductView.class)
    @JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
    @JsonIdentityReference(alwaysAsId=true) // otherwise first ref as POJO, others as id
    User user;
//    @JsonView({OrderView.SummaryProductView.class,OrderView.SummaryAllView.class})
    Double price;

    public Order(Product product, User user, Double price) {
        this.product = product;
        this.user = user;
        this.price = price;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    public User getUser() {
        return user;
    }

    public void setUser(User fromUser) {
        this.user = fromUser;
    }
//
//    public User getToUser() {
//        return toUser;
//    }
//
//    public void setToUser(User toUser) {
//        this.toUser = toUser;
//    }

    public Order(Product product,  User user) {
        this.product = product;

        this.user = user;
    }

    public Order() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({OrderView.SummaryAllView.class})
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.EAGER)
//    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "product_id",referencedColumnName = "id")
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

}
