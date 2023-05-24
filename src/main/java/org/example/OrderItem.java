package org.example;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
@Getter
@Setter
@Entity
@Table(name="ORDER_ITEM")
public class OrderItem {
    @Id @GeneratedValue
    @Column(name="ORDER_ITEM_ID")
    private Long id;
    @ManyToOne
    @Column(name="ITEM_ID")
    private Item item;
    @ManyToOne
    @JoinColumn(name="ORDER_ID")
    private Order order;

    private int orderPrice;
    private int count;
}
