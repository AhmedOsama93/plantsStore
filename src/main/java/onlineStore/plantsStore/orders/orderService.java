package onlineStore.plantsStore.orders;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import onlineStore.plantsStore.cart.cart;
import onlineStore.plantsStore.products.product;
import onlineStore.plantsStore.users.users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
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
    public List<Integer>getinOutDooRCount(){
        List<orders> orders =orderRepository.findAll();
        int in=0,out=0;
        for (int i = 0; i < orders.size(); i++) {
            long productId=orders.get(i).getId().getProductID();
            product p1 = productRepository.findproductsByID(productId);
            if(p1!=null){
                if(p1.isIndoor())
                    in++;
                else
                    out++;
            }
        }
        List<Integer> output=new ArrayList<>();
        output.add(in);
        output.add(out);
        return output;

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
            orders o = new orders(oi, LocalDateTime.now(), LocalDateTime.now().getDayOfMonth(),cartList.get(i).getQuantity(),"Ordered");
            orders.add(o);
        }
        return orders;
    }
    public List<OrdersDone> CustomersOfOrders(){
        List<OrdersDone>ords = new ArrayList<OrdersDone>();
        List<orders>orders = orderRepository.findAll();
        for (int i = 0; i < orders.size(); i++) {
            users user ;
            user = usersRepository.findusersByEmail(orders.get(i).getId().getUsername());
            long o =orders.get(i).getId().getProductID();
            OrdersDone ordersDone = new OrdersDone();
            ordersDone.Pname = productRepository.getById(o).getName();
            ordersDone.fname = user.getfName();
            ordersDone.orderDate= orders.get(i).getOrderDate();
            ordersDone.quantity = orders.get(i).getQuantity();
            ordersDone.price =productRepository.getById(o).getPrice();
            ords.add(ordersDone);
        }
        return ords;
    }

    public List<dayStat> getMonthSelling(){
        LocalDateTime lastMonth = LocalDateTime.now().minus (30, ChronoUnit.DAYS);
        List<orders>allorders= orderRepository.findLastMonthOrders(lastMonth);
        int dayCount = orderRepository.findLastMonthOrdersDayCount(lastMonth).size();

        List<Integer> days = new ArrayList<>();
        List<Double> daysProfit = new ArrayList<>();
        List<dayStat> finalStat = new ArrayList<>();
        for (int i = 0; i < allorders.size(); i++) {
            if(!days.contains(allorders.get(i).getDayOfMonth())){
                days.add(allorders.get(i).getDayOfMonth());
                daysProfit.add(0.0);
                finalStat.add(new dayStat());
            }
        }
        for (int i = 0; i <allorders.size(); i++) {
            int dayIndex = days.indexOf(allorders.get(i).getDayOfMonth());
            double price = productRepository.getById(allorders.get(i).getId().getProductID()).getPrice();
            daysProfit.set(dayIndex,daysProfit.get(dayIndex)+allorders.get(i).getQuantity() * price);
        }
        for (int i = 0; i < dayCount; i++) {
            finalStat.get(i).day=days.get(i);
            finalStat.get(i).profit=daysProfit.get(i);
        }
        return finalStat;
    }

}
@Data
class dayStat{
    int day;
    double profit;
}