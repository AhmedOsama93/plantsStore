package onlineStore.plantsStore.orders;

import onlineStore.plantsStore.cart.cart;
import onlineStore.plantsStore.users.users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class orderService {
    private final onlineStore.plantsStore.cart.cartRepository cartRepository;
    private final orderRepository orderRepository;
    private final onlineStore.plantsStore.users.usersRepository usersRepository;
    @Autowired
    public orderService(onlineStore.plantsStore.cart.cartRepository cartRepository, orderRepository orderRepository, onlineStore.plantsStore.users.usersRepository usersRepository){
        this.cartRepository = cartRepository;
        this.orderRepository = orderRepository;
        this.usersRepository = usersRepository;
    }

    public  void order(String username){

        List<cart> cartItems= new ArrayList<>();
        cartItems = cartRepository.findcartByUser(username);
        users u = usersRepository.findusersByEmail(username);
        List<orders> orders = cartToOrder(cartItems,u.NewOrderCount());
        orderRepository.saveAll(orders);
        usersRepository.save(u);
        cartRepository.deleteAll(cartItems);
    }
    public List<orders> cartToOrder(List<cart> cartList,int orderNo){
        List<orders> orders = new ArrayList<>();
        for (int i = 0; i < cartList.size(); i++) {
            orderIdentity oi = new orderIdentity(cartList.get(i).getId().getUsername(),
                    cartList.get(i).getId().getProductID(),orderNo
                    );
            orders o = new orders(oi, LocalDateTime.now(),cartList.get(i).getQuantity(),"Ordered");
            orders.add(o);
        }
        return orders;
    }
}
