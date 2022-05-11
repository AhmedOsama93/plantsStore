package onlineStore.plantsStore.products;
import onlineStore.plantsStore.users.users;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class orders {
    @Id
    private long orderNumForUser;


    @ManyToOne
    @JoinColumn(name = "userId")
    private users user;

    @ManyToOne
    @JoinColumn(name = "productId")
    private product product;

    private LocalDateTime orderedAt;
    private int quantity;
    private String state;

    public orders() {

    }
    public orders(long orderNumForUser, users user, onlineStore.plantsStore.products.product product, LocalDateTime orderedAt, int quantity, String state) {
        this.orderNumForUser = orderNumForUser;
        this.user = user;
        this.product = product;
        this.orderedAt = orderedAt;
        this.quantity = quantity;
        this.state = state;
    }

    public void setOrderNumForUser(long orderNumForUser) {
        this.orderNumForUser = orderNumForUser;
    }

    public void setUser(users user) {
        this.user = user;
    }

    public void setProduct(onlineStore.plantsStore.products.product product) {
        this.product = product;
    }

    public void setOrderedAt(LocalDateTime orderedAt) {
        this.orderedAt = orderedAt;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setState(String state) {
        this.state = state;
    }

    public long getOrderNumForUser() {
        return orderNumForUser;
    }

    public users getUser() {
        return user;
    }

    public onlineStore.plantsStore.products.product getProduct() {
        return product;
    }

    public LocalDateTime getOrderedAt() {
        return orderedAt;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getState() {
        return state;
    }

    @Override
    public String toString() {
        return "orders{" +
                "orderNumForUser=" + orderNumForUser +
                ", user=" + user +
                ", product=" + product +
                ", orderedAt=" + orderedAt +
                ", quantity=" + quantity +
                ", state='" + state + '\'' +
                '}';
    }
}
