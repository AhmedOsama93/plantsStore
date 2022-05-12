package onlineStore.plantsStore.cart;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class cartConfig {
    @Bean
    CommandLineRunner commandLineRunner3(cartRepository cartRepository){

        return args -> {
            cart c = new cart(
                    new cartIdentity(5,2)
                    ,5
            );
            cartRepository.saveAll(List.of(c));
        };
    }

}
