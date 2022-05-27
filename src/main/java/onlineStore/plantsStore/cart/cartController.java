package onlineStore.plantsStore.cart;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import onlineStore.plantsStore.products.product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    @PostMapping(path = "user/cart/{productID}/{quantity}")
    public ResponseEntity<?> addItemToCart(@RequestHeader(name="Authorization") String token1, @PathVariable long productID, @PathVariable int quantity){
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        String token = token1.substring("Bearer ".length());
        DecodedJWT decodedJWT= verifier.verify(token);
        String username = decodedJWT.getSubject();

        cartService.addToCart(username,productID,quantity);
 //       cartService.addToCart(username,productID,quantity);
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "user/cart/deleteOneCartItem/{productID}")
    public ResponseEntity<?> deleteOneCartItem(@RequestHeader(name="Authorization") String token1, @PathVariable long productID){
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        String token = token1.substring("Bearer ".length());
        DecodedJWT decodedJWT= verifier.verify(token);
        String username = decodedJWT.getSubject();
        cartService.deleteOneCartItem(username,productID);
        return ResponseEntity.ok().build();
    }
    @PostMapping(path = "user/cart/deleteAllCartItem")
    public ResponseEntity<?> deleteAllCartItem(@RequestHeader(name="Authorization") String token1){
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        String token = token1.substring("Bearer ".length());
        DecodedJWT decodedJWT= verifier.verify(token);
        String username = decodedJWT.getSubject();

        cartService.deleteAllCartItem(username);
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "user/cartExactQuantity/{productID}/{quantity}")
    public ResponseEntity<?> addItemToCartExactQuantity(@RequestHeader(name="Authorization") String token1, @PathVariable long productID, @PathVariable int quantity){
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        String token = token1.substring("Bearer ".length());
        DecodedJWT decodedJWT= verifier.verify(token);
        String username = decodedJWT.getSubject();

        cartService.addToCartExactQuantity(username,productID,quantity);
        //       cartService.addToCart(username,productID,quantity);
        return ResponseEntity.ok().build();
    }

    //the return type will be changed according to the front end
    @GetMapping(path = "user/cart/getCartItemsForUser")
    public ResponseEntity<List<cart>> getCartItemsForUser(@RequestHeader(name="Authorization") String token1){
        //@RequestHeader(name="Authorization") String token
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        String token = token1.substring("Bearer ".length());
        DecodedJWT decodedJWT= verifier.verify(token);
        String username = decodedJWT.getSubject();

        return ResponseEntity.ok().body(cartService.getCartItemsForUser(username));
    }
    @GetMapping(path = "user/cart/getCartSize")
    public ResponseEntity<Integer> getCartSize(@RequestHeader(name="Authorization") String token1){
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        String token = token1.substring("Bearer ".length());
        DecodedJWT decodedJWT= verifier.verify(token);
        String username = decodedJWT.getSubject();

        return ResponseEntity.ok().body(cartService.getCartSize(username));
    }

}
