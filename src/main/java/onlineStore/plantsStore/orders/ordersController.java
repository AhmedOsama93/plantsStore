package onlineStore.plantsStore.orders;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import onlineStore.plantsStore.users.users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Controller
@RestController
public class ordersController {
    private final orderService orderService1;

    @Autowired
    public ordersController(orderService orderService) {
        this.orderService1 = orderService;
    }

    @GetMapping(path = "admin/getProductSoldCount")
    private ResponseEntity<Integer>getProductSoldCount(){
        return ResponseEntity.ok().body(orderService1.getProductSoldCount());
    }
    @GetMapping(path = "admin/getTotalIncome")
    private ResponseEntity<Integer>getTotalIncome(){
        return ResponseEntity.ok().body(orderService1.getTotalIncome());
    }

    @PostMapping(path = "user/order")
    public ResponseEntity<?> order( @RequestHeader(name="Authorization") String token1){

        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        String token = token1.substring("Bearer ".length());
        DecodedJWT decodedJWT= verifier.verify(token);
        String username = decodedJWT.getSubject();

        orderService1.order(username);
        return ResponseEntity.ok().build();
    }
    @PostMapping(path = "user/getAllOrdersForuser")
    public ResponseEntity<List<orders>>getAllOrdersForAdmin(@RequestHeader(name="Authorization") String token1){

        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        String token = token1.substring("Bearer ".length());
        DecodedJWT decodedJWT= verifier.verify(token);
        String username = decodedJWT.getSubject();

        return ResponseEntity.ok().body(orderService1.getAllOrdersForUser(username));
    }
    @PostMapping(path = "user/orderOneCartItem/{productID}")
    public ResponseEntity<?> order( @RequestHeader(name="Authorization") String token1,@PathVariable long productID){

        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        String token = token1.substring("Bearer ".length());
        DecodedJWT decodedJWT= verifier.verify(token);
        String username = decodedJWT.getSubject();
        orderService1.orderCartItem(username,productID);
        return ResponseEntity.ok().build();
    }
}

