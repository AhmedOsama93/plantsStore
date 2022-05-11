package onlineStore.plantsStore.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class cartController {
    private final cartService cartService;

    @Autowired
    public cartController(cartService cartService){
        this.cartService = cartService;
    }
    @PostMapping(path = "api/cart/{userID}/{productID}/{quantity}")
    public void addItemToCart(@PathVariable long userID,@PathVariable long productID,@PathVariable int quantity){
        cartService.addToCart(userID,productID,quantity);
    }
}
