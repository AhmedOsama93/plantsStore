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

    @GetMapping(path="api/home")
    public String ex1(){
        return "wellcome";
    }//this return the wellcome.html in resources/templates

    //    *********************************************************************************************

    @GetMapping(path="admin/getUsers")
    public ResponseEntity<List<users>> getUsers(){

        return ResponseEntity.ok().body(userService.getUsers());
    }

//    @GetMapping(path="api/user/getUsers")
//    public List<users> getUsers(){
//        return userService.getUsers();
//    }

    @PostMapping(path="visitor/registerNewUser")
    public ResponseEntity<?> registerNewUser(@RequestBody users user){
        userService.addNewUser(user);
        return ResponseEntity.ok().build();

    }//change it

    @PostMapping(path="visitor/enterVerifyCode/{verifyCode}")
    public ResponseEntity<?> enterVerifyCode(@PathVariable String verifyCode){
        userService.verifyCode(verifyCode);
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/admin/addRoleToUser")
    public ResponseEntity<?>addRoleToUser(@RequestBody RoleToUserForm form){
        userService.addRoleToUser(form.getEmail(), form.getRoleName());
        return ResponseEntity.ok().build();
    }


    @PostMapping("admin/suspend/{email}")
    public ResponseEntity<?> suspend (@PathVariable String email){
        userService.suspendUser(email);
        return ResponseEntity.ok().build();
    }

    @PostMapping(path="admin/editUserForAdmin")
    public ResponseEntity<?> editUserForAdmins(@RequestBody users user){
        userService.editUserForAdmins(user);
        return ResponseEntity.ok().build();
    }

}
@Data
class RoleToUserForm{
    private String email;
    private String roleName;
}