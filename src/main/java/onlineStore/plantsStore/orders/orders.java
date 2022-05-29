package onlineStore.plantsStore.orders;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class orders {
    @EmbeddedId
    private orderIdentity id;
    private LocalDateTime orderDate;
    private int quantity;
    private String state;



//
//    @ManyToOne
//    @JoinColumn(name = "userId")
//    private users user;
//
//    @ManyToOne
//    @JoinColumn(name = "productId")
//    private onlineStore.plantsStore.products.product product;




    @Override
    public String toString() {
        return "orders{" +
                "id=" + id +
                ", orderedAt=" + orderDate +
                ", quantity=" + quantity +
                ", state='" + state + '\'' +
                '}';
    }


}
