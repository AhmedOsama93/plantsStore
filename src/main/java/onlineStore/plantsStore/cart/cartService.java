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

    public void addToCart(String username,long productId, int quantity){
        cart c1 = cartRepository.findcartByUserAndProduct(username,productId);
        if(c1==null){
            cartIdentity id1=new cartIdentity(username,productId);
            c1 = new cart(id1,quantity);
        }
        else {
            c1.setQuantity(c1.getQuantity()+quantity);
        }
        cartRepository.save(c1);
    }
    public void deleteOneCartItem(String username,long productId){
        cart c1 = cartRepository.findcartByUserAndProduct(username,productId);
        cartRepository.delete(c1);
    }
    public void deleteAllCartItem(String username){
        List<cart> c1 = cartRepository.findcartByUser(username);
        cartRepository.deleteAll(c1);
    }
    public void addToCartExactQuantity(String username,long productId, int quantity){
        cart c1 = cartRepository.findcartByUserAndProduct(username,productId);
        if(c1==null){
            cartIdentity id1=new cartIdentity(username,productId);
            c1 = new cart(id1,quantity);
        }
        else {
            c1.setQuantity(quantity);
        }
        cartRepository.save(c1);
    }
    public List<cart> getCartItemsForUser(String username){
        return cartRepository.findcartByUser(username);
    }
    public Integer getCartSize(String username){
        return cartRepository.findcartByUser(username).size();
    }
}
