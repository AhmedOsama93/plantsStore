package onlineStore.plantsStore.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@Controller
public class pageController {
    @GetMapping(path="/")
    public String homePage(){
        return "home/index.html";
    }
    @GetMapping(path="/getAdminPage")
    public String adminPage(@RequestHeader(name="Authorization") String token1){
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        String token = token1.substring("Bearer ".length());
        DecodedJWT decodedJWT= verifier.verify(token);
        String username = decodedJWT.getSubject();
        //check if admin
        return "admin";
    }


}
