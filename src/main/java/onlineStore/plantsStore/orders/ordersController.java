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

//@Controller
@RestController
public class ordersController {
    private final orderService orderService1;

    @Autowired
    public ordersController(orderService orderService) {
        this.orderService1 = orderService;
    }



    @PostMapping(path = "user/order")
    public ResponseEntity<?> order( @RequestHeader(name="Authorization") String token1){
        //@RequestHeader(name="Authorization") String token, -> parameter

        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        String token = token1.substring("Bearer ".length());
        DecodedJWT decodedJWT= verifier.verify(token);
        String username = decodedJWT.getSubject();

        orderService1.order(username);
        return ResponseEntity.ok().build();
    }
}

