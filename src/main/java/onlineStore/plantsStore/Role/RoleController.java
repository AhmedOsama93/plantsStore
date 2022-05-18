package onlineStore.plantsStore.Role;

import onlineStore.plantsStore.users.users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class RoleController {


    @Autowired
    RoleService roleService;
//    @PostMapping(value = "/addRole/{roleName}")
//    void addRole(@PathVariable String roleName){
//        roleService.addNewRole(roleName);
//    }

    @PostMapping(path="api/role/addNewRole")
    public ResponseEntity<Role> addRole(@RequestBody Role role){
        URI uri= URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/role/addNewRole").toUriString());
        return ResponseEntity.created(uri).body(roleService.addNewRole(role));
    }
}
