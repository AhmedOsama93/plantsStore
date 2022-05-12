package onlineStore.plantsStore.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class userController {
    private final userService userService;

    @Autowired
    public userController(onlineStore.plantsStore.users.userService userService) {
        this.userService = userService;
    }
    //    *********************************************************************************************
    //                                            api test
    //    *********************************************************************************************

    @GetMapping(path="api/home")
    public String ex1(){
        return "Hello World";
    }

    //    *********************************************************************************************

    @GetMapping(path="api/user/getUsers")
    public List<users> getUsers(){
        return userService.getUsers();
    }

    @PostMapping(path="api/user/registerNewUser")
    public void registerNewUser(@RequestBody users user){
        userService.addNewUser(user);
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
