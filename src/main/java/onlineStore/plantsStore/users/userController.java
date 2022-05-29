package onlineStore.plantsStore.users;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

//@Controller
@RestController
@RequiredArgsConstructor
public class userController {
    private final userService userService;

    //    *********************************************************************************************
    //                                            api test
    //    *********************************************************************************************

//    @GetMapping(path="api/home")
//    public String ex1(){
//        return "wellcome";
//    }//this return the wellcome.html in resources/templates

    //    *********************************************************************************************

    @GetMapping(path="admin/getUsers")
    public ResponseEntity<List<users>> getUsers(){

        return ResponseEntity.ok().body(userService.getUsers());
    }
    @GetMapping(path="admin/getUsersCount")
    public ResponseEntity<Integer> getUsersCount(){

        return ResponseEntity.ok().body(userService.getUsersCount());
    }

    @PostMapping(path="visitor/registerNewUser")
    public ResponseEntity<?> registerNewUser(@RequestParam users user){
        userService.addNewUser(user);
        return ResponseEntity.ok().build();
    }
    @PostMapping(path="admin/addUserForAdmin")
    public ResponseEntity<?> addUserForAdmin( users user){
        userService.addNewUserForAdmin(user);
        return ResponseEntity.ok().build();
    }//change it
    @GetMapping(path = "visitor/isEmailTaken/{email}")
    public ResponseEntity<Boolean>isEmailTaken(@PathVariable String email){
        return ResponseEntity.ok().body(userService.isEmailTaken(email));
    }
    @PostMapping(path="visitor/enterVerifyCode/{verifyCode}")
    public ResponseEntity<?> enterVerifyCode(@PathVariable String verifyCode){
        userService.verifyCode(verifyCode);
        return ResponseEntity.ok().build();
    }
    @PostMapping(path="visitor/forgetPasswordRequest/{email}")
    public ResponseEntity<?>forgetPasswordRequest(@PathVariable String email){
        userService.forgetPasswordRequest(email);
        return ResponseEntity.ok().build();
    }
    @PostMapping(path="visitor/changeForgetPassword")
    public ResponseEntity<?>forgetPasswordRequest(@RequestBody forgetPasswordForm forgetPasswordForm){
        userService.changeForgetPassword(forgetPasswordForm.getCode(),forgetPasswordForm.getNewPassword1(),forgetPasswordForm.getNewPassword2());
        return ResponseEntity.ok().build();
    }
    @GetMapping(path = "/user/getMyData")
    public ResponseEntity<users>getUserData(@RequestHeader(name="Authorization") String token1){
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        String token = token1.substring("Bearer ".length());
        DecodedJWT decodedJWT= verifier.verify(token);
        String username = decodedJWT.getSubject();

        return ResponseEntity.ok().body(userService.getUserData(username));
    }
    @GetMapping(path = "/admin/getUserData/{username}")
    public ResponseEntity<users>getUserDataForAdmin(@PathVariable String username){
        return ResponseEntity.ok().body(userService.getUserData(username));
    }
    @GetMapping(path = "/visitor/isAdmin")
    public ResponseEntity<Boolean>isAdmin(@RequestHeader(name="Authorization") String token1){
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        String token = token1.substring("Bearer ".length());
        DecodedJWT decodedJWT= verifier.verify(token);
        String username = decodedJWT.getSubject();
        return ResponseEntity.ok().body(userService.isAdmin(username));
    }
    @GetMapping(path = "/visitor/isUser")
    public ResponseEntity<Boolean>isUser(@RequestHeader(name="Authorization") String token1){
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        String token = token1.substring("Bearer ".length());
        DecodedJWT decodedJWT= verifier.verify(token);
        String username = decodedJWT.getSubject();
        return ResponseEntity.ok().body(userService.isUser(username));
    }
    @PostMapping(path = "/admin/addRoleToUser")
    public ResponseEntity<?>addRoleToUser( RoleToUserForm form){
        userService.addRoleToUser(form.getEmail(), form.getRoleName());
        return ResponseEntity.ok().build();
    }


    @PostMapping("admin/suspend/{email}")
    public ResponseEntity<?> suspend (@PathVariable String email){
        userService.suspendUser(email);
        return ResponseEntity.ok().build();
    }
    @PostMapping("admin/activateUser/{email}")
    public ResponseEntity<?> activateUser (@PathVariable String email){
        userService.activateUser(email);
        return ResponseEntity.ok().build();
    }


    @PostMapping(path="admin/editUserForAdmin")
    public ResponseEntity<?> editUserForAdmins(@RequestBody users user){
        userService.editUserForAdmins(user);
        return ResponseEntity.ok().build();
    }
    @PostMapping(path="user/editUserForUser")
    public ResponseEntity<?> editUserForUser(@RequestHeader(name="Authorization") String token1,users users){
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        String token = token1.substring("Bearer ".length());
        DecodedJWT decodedJWT= verifier.verify(token);
        String username = decodedJWT.getSubject();
        userService.editUserForUser(username,users);
        return ResponseEntity.ok().build();
    }

}
@Data
class forgetPasswordForm{
    private String code;
    private String newPassword1;
    private String newPassword2;
}
@Data
class RoleToUserForm{
    private String email;
    private String roleName;
}