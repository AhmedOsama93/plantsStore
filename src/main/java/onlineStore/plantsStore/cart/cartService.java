package onlineStore.plantsStore.cart;

import onlineStore.plantsStore.products.product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class cartService {
    private final cartRepository cartRepository;

    @Autowired
    public cartService(cartRepository cartRepository){
        this.cartRepository=cartRepository;
    }
    public void addToCart(long userID,long productId, int quantity){
        cart c1 = cartRepository.findcartByUserAndProduct(userID,productId);
        if(c1==null){
            cartIdentity id1=new cartIdentity(userID,productId);
            c1 = new cart(id1,quantity);
        }
        else {
            c1.setQuantity(c1.getQuantity()+quantity);
        }
        cartRepository.save(c1);
    }
    public List<cartItem> getCartItemsForUser(long userId){
        return cartRepository.getCartItemsForUser(userId);
    }
}
