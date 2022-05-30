package onlineStore.plantsStore.orders;

import onlineStore.plantsStore.cart.cart;
import onlineStore.plantsStore.cart.cartIdentity;
import onlineStore.plantsStore.cart.cartRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
public class orderConfig {
//    @Bean
//    CommandLineRunner orderCommandLineRunner( orderRepository orderRepository){
//
//        return args -> {
//            orders o = new orders(
//                    new orderIdentity("admin@gmail.com",2,3)
//                    , LocalDateTime.now()
//                    ,5
//                    ,"delivered"
//
//            );
//            orderRepository.saveAll(List.of(o));
//        };
//    }
}
