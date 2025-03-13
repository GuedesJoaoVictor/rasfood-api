package com.csi.api.rasfood.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "final_price")
    private BigDecimal finalPrice = BigDecimal.ZERO;
    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
    @ManyToOne(fetch = FetchType.LAZY)
    private Client client;
    //JoinTable(name = "order_menu", joinColumns = @JoinColumn(name = "order_id"), inverseJoinColumns = @JoinColumn(name = "menu_id"))
    /*
     * ALL = Realiza todas as operações em cascata
     * DETACH = Operacao detach executada no pai e no filho
     * MERGE = Salva pai e filho, podende já haver a entidade gerenciada
     * PERSIST = Cria pai e filho
     * REFRESH = Atualiza entidade com operacoes do banco
     * REMOVE = Propaga remocao entre pai e filho
     * */
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderMenu> orderMenuList = new ArrayList<OrderMenu>();

    public Order() {}

    public Order(Client client) {
        this.client = client;
    }

    public void setOrder(OrderMenu orderMenu) {
        orderMenu.setOrder(this);
        this.orderMenuList.add(orderMenu);
        this.finalPrice = finalPrice.add(
                orderMenu.getRegisterValue().multiply(
                        BigDecimal.valueOf(
                                orderMenu.getQuantity()
                        )
                )
        );
    }

    public List<OrderMenu> getOrderMenuList() {
        return orderMenuList;
    }

    public void setOrderMenuList(List<OrderMenu> orderMenuList) {
        this.orderMenuList = orderMenuList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(BigDecimal finalPrice) {
        this.finalPrice = finalPrice;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", finalPrice=" + finalPrice +
                ", createdAt=" + createdAt +
                ", client=" + client +
                ", orderMenuList=" + orderMenuList +
                '}';
    }
}
