package onlineStore.plantsStore.orders;


import onlineStore.plantsStore.cart.cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface orderRepository extends JpaRepository<orders, orderIdentity> {
    @Query("select o from orders o where o.id.userId=?1 and o.id.productID=?2 and o.id.orderNo=?3")
    cart findcartByUserAndProduct(long  userId, long productId,int orderNo);
}
