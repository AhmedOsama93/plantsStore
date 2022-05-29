package onlineStore.plantsStore.orders;


import onlineStore.plantsStore.cart.cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface orderRepository extends JpaRepository<orders, orderIdentity> {
    @Query("select o from orders o where o.id.username=?1 and o.id.productID=?2 and o.id.orderNo=?3")
    orders findOrderByUserAndProduct(String  username, long productId,int orderNo);

    @Query("select o from orders o where o.id.username=?1 ")
    List<orders> findOrderByUsername(String  username);

}
