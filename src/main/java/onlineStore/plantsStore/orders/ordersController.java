package onlineStore.plantsStore.orders;


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



    @PostMapping(path = "user/order/{username}")
    public ResponseEntity<?> order( @PathVariable String username){
        //@RequestHeader(name="Authorization") String token, -> parameter
        orderService1.order(username);
        return ResponseEntity.ok().build();
    }
}

