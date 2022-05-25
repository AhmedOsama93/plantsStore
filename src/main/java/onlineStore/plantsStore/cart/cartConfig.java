package onlineStore.plantsStore.cart;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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
//    @Bean
//    public WebMvcConfigurer corsConfigurer3() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/api/**");
//            }
//        };
//    }
}
