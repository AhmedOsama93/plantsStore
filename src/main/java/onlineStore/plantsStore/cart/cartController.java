package onlineStore.plantsStore.cart;

import com.auth0.jwt.JWT;
import onlineStore.plantsStore.products.product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class cartController {
    private final cartService cartService;

    @Autowired
    public cartController(cartService cartService){

        this.cartService = cartService;
    }
    @PostMapping(path = "user/cart/{username}/{productID}/{quantity}")
    public void addItemToCart(@PathVariable String username,@PathVariable long productID,@PathVariable int quantity){
        cartService.addToCart(username,productID,quantity);
    }


    //the return type will be changed according to the front end
    @GetMapping(path = "user/cart/getCartItemsForUser/{username}")
    public List<cart> getCartItemsForUser(@PathVariable String username){
        //@RequestHeader(name="Authorization") String token
        System.out.println();
        return cartService.getCartItemsForUser(username);
    }
//    public String getUsernameFromToken(String token) {
//        Claims claims = JWT.parser().setSigningKey("secret").parseClaimsJws(token).getBody();
//        return claims.getSubject();
//    }
}
