package onlineStore.plantsStore.orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class orderService {
    private final onlineStore.plantsStore.cart.cartRepository cartRepository;
    private final orderRepository orderRepository;
    @Autowired
    public orderService(onlineStore.plantsStore.cart.cartRepository cartRepository, orderRepository orderRepository){
        this.cartRepository = cartRepository;
        this.orderRepository = orderRepository;
    }

    public  void order(String username){

    }
}
