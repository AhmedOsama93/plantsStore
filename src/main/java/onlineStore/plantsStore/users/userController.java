package onlineStore.plantsStore.users;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class userController {
    private final userService userService;

    //    *********************************************************************************************
    //                                            api test
    //    *********************************************************************************************

    @GetMapping(path="api/home")
    public String ex1(){
        return "Hello World";
    }

    //    *********************************************************************************************

    @GetMapping(path="api/user/getUsers")
    public ResponseEntity<List<users>> getUsers(){
        return ResponseEntity.ok().body(userService.getUsers());
    }

//    @GetMapping(path="api/user/getUsers")
//    public List<users> getUsers(){
//        return userService.getUsers();
//    }

    @PostMapping(path="api/user/registerNewUser")
    public ResponseEntity<users> registerNewUser(@RequestBody users user){
        URI uri= URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/user/registerNewUser").toUriString());
        return ResponseEntity.created(uri).body(userService.addNewUser(user));
    }

    @PostMapping(path = "/roel/addRoleToUser")
    public ResponseEntity<?>addRoleToUser(@RequestBody RoleToUserForm form){
        userService.addRoleToUser(form.getEmail(), form.getRoleName());
        return ResponseEntity.ok().build();
    }


    @PostMapping("api/user/suspend/{email}")
    public void suspend (@PathVariable String email){
        userService.suspendUser(email);
    }

    @PostMapping(path="api/user/editUserForAdmin")
    public void editUserForAdmins(@RequestBody users user){
        userService.editUserForAdmins(user);
    }

}
@Data
class RoleToUserForm{
    private String email;
    private String roleName;
}