package com.asavin.pilsnerbar.entity;

import com.asavin.pilsnerbar.json.OrderView;
import com.fasterxml.jackson.annotation.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "ordr")
public class Order {

    Long id;
    @JsonView(OrderView.SummaryAllView.class)
    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="name")
    @JsonIdentityReference(alwaysAsId=true) // otherwise first ref as POJO, others as id
    Product product;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonView(OrderView.SummaryProductView.class)
    @JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="user")
    @JsonIdentityReference(alwaysAsId=true) // otherwise first ref as POJO, others as id
    User fromUser;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    public User getFromUser() {
        return fromUser;
    }

    public void setFromUser(User fromUser) {
        this.fromUser = fromUser;
    }
//
//    public User getToUser() {
//        return toUser;
//    }
//
//    public void setToUser(User toUser) {
//        this.toUser = toUser;
//    }

    public Order(Product product,  User fromUser) {
        this.product = product;

        this.fromUser = fromUser;
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

    @ManyToOne
//    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "product_id",referencedColumnName = "id")
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

}
