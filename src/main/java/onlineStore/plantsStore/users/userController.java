package onlineStore.plantsStore.users;

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

@Controller
//@RestController
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

    @GetMapping(path="admin/user/getUsers")
    public ResponseEntity<List<users>> getUsers(){
      return ResponseEntity.ok().body(userService.getUsers());
    }

//    @GetMapping(path="api/user/getUsers")
//    public List<users> getUsers(){
//        return userService.getUsers();
//    }

    @PostMapping(path="visitor/registerNewUser")
    public ResponseEntity<users> registerNewUser(@RequestBody users user){
        URI uri= URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/user/registerNewUser").toUriString());
        return ResponseEntity.created(uri).body(userService.addNewUser(user));
    }//change it


    @PostMapping(path = "/admin/addRoleToUser")
    public ResponseEntity<?>addRoleToUser(@RequestBody RoleToUserForm form){
        userService.addRoleToUser(form.getEmail(), form.getRoleName());
        return ResponseEntity.ok().build();
    }


    @PostMapping("admin/suspend/{email}")
    public void suspend (@PathVariable String email){
        userService.suspendUser(email);
    }

    @PostMapping(path="admin/editUserForAdmin")
    public void editUserForAdmins(@RequestBody users user){
        userService.editUserForAdmins(user);
    }

}
@Data
class RoleToUserForm{
    private String email;
    private String roleName;
}