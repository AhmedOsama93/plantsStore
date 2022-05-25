package onlineStore.plantsStore.users;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class userConfig   {

    @Bean
    CommandLineRunner commandLineRunner(usersRepository repository){
        return args->{
            users abdo = new users(
                    1,
                    "abdo",
                    "admin",
                    "admin@gmail.com",
                    "123",
                    true,
                    true
            );
            repository.saveAll(List.of(abdo));
        };
    }
//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/api/**");
//            }
//        };
//    }
}
