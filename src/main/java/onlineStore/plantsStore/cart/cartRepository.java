package onlineStore.plantsStore.cart;


import onlineStore.plantsStore.products.product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface cartRepository extends JpaRepository<cart,cartIdentity> {
    @Query("select c from cart c where c.id.userId=?1 and c.id.productID=?2 ")
    cart findcartByUserAndProduct(long  userId,long productId);

    @Query("select  new onlineStore.plantsStore.cart.cartItem (c.id.productID ,c.quantity) from cart c where c.id.userId=?1")
    List<cartItem> getCartItemsForUser(long  userId);

}
