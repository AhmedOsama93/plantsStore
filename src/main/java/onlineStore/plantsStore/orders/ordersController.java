package onlineStore.plantsStore.orders;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    @GetMapping(path = "admin/getInOutDoorCount")
    private ResponseEntity<List<Integer>>getinOutDooRCount(){
        return ResponseEntity.ok().body(orderService1.getinOutDooRCount());
    }
    @GetMapping(path = "admin/getTotalIncome")
    private ResponseEntity<Integer>getTotalIncome(){
        return ResponseEntity.ok().body(orderService1.getTotalIncome());
    }
    @GetMapping(path = "admin/customerOfOrders")
    public ResponseEntity<List<OrdersDone>> CustomersOfOrders() {
            return ResponseEntity.ok().body(orderService1.CustomersOfOrders());
    }
    @GetMapping(path = "admin/aboutOrders")
    public ResponseEntity<List<Double>> aboutOrders() {
        return ResponseEntity.ok().body(orderService1.aboutOrders());
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
    @GetMapping(path = "user/getOrderDate/{productId}/{orderNo}")
    private ResponseEntity<String>getOrderDate(@RequestHeader(name="Authorization") String token1, @PathVariable long productId, @PathVariable int orderNo){
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        String token = token1.substring("Bearer ".length());
        DecodedJWT decodedJWT= verifier.verify(token);
        String username = decodedJWT.getSubject();
        return ResponseEntity.ok().body(orderService1.getOrderDate(username, productId,orderNo));
    }
    @GetMapping(path = "admin/getMonthSelling")
    public ResponseEntity<List<dayStat>> getMonthSelling(){
        return ResponseEntity.ok().body(orderService1.getMonthSelling());
    }
}