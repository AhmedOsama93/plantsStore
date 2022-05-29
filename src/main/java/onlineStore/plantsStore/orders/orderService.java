package onlineStore.plantsStore.orders;

import onlineStore.plantsStore.cart.cart;
import onlineStore.plantsStore.products.product;
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
    private final onlineStore.plantsStore.products.productRepository productRepository;
    @Autowired
    public orderService(onlineStore.plantsStore.cart.cartRepository cartRepository, orderRepository orderRepository, onlineStore.plantsStore.users.usersRepository usersRepository, onlineStore.plantsStore.products.productRepository productRepository){
        this.cartRepository = cartRepository;
        this.orderRepository = orderRepository;
        this.usersRepository = usersRepository;
        this.productRepository = productRepository;
    }
    public int getProductSoldCount(){
        List<orders> orders =orderRepository.findAll();
        int productSold=0;
        for (int i = 0; i < orders.size(); i++) {
            productSold+=orders.get(i).getQuantity();
        }
        return productSold;
    }
    public List<orders>getAllOrdersForUser(String username){
        return orderRepository.findOrderByUsername(username);
    }
    public int getTotalIncome(){
        List<orders> orders =orderRepository.findAll();
        int totalIncome=0;
        for (int i = 0; i < orders.size(); i++) {
            long productId = orders.get(i).getId().getProductID();
            totalIncome+=(orders.get(i).getQuantity() * productRepository.getById(productId).getPrice() );
        }
        return totalIncome;
    }
    public  void order(String username){

        List<cart> cartItems= new ArrayList<>();
        cartItems = cartRepository.findcartByUser(username);
        doOrder(username, cartItems);
    }
    public void orderCartItem(String username,long productID){
        List<cart> cartItems= new ArrayList<>();
        cartItems.add( cartRepository.findcartByUserAndProduct(username,productID));
        doOrder(username, cartItems);

    }

    private void doOrder(String username, List<cart> cartItems) {
        if(!isQuantityAvalable(cartItems)){
            throw new IllegalStateException("There Is No Available Quantity");
        }
        users u = usersRepository.findusersByEmail(username);
        List<orders> orders = cartToOrder(cartItems,u.NewOrderCount());
        orderRepository.saveAll(orders);
        decAvalable( cartItems);
        incProductNum(cartItems);
        usersRepository.save(u);
        cartRepository.deleteAll(cartItems);
    }

    public boolean isQuantityAvalable(List<cart> cartItems){
        for (int i = 0; i < cartItems.size(); i++) {
            product p = productRepository.getById(cartItems.get(i).getId().getProductID());
            if(p.getQuantityAvailable()<cartItems.get(i).getQuantity()){
                return false;
            }
        }
        return true;
    }
    public void decAvalable(List<cart> cartItems){
        for (int i = 0; i < cartItems.size(); i++) {
            product p = productRepository.getById(cartItems.get(i).getId().getProductID());
            p.decquantityAvailable(cartItems.get(i).getQuantity());
            productRepository.save(p);
        }
    }
    public void incProductNum(List<cart> cartItems){
        for (int i = 0; i < cartItems.size(); i++) {
            product p = productRepository.getById(cartItems.get(i).getId().getProductID());
            p.incNumOrderd(cartItems.get(i).getQuantity());
            productRepository.save(p);
        }
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
