package dear.darling.springsecurity_demo.Controller;

import dear.darling.springsecurity_demo.Model.Users;
import dear.darling.springsecurity_demo.Service.UserService;
import dear.darling.springsecurity_demo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class userController {

    @Autowired
    private UserService service;

    @PostMapping("/register")
    public Users register(@RequestBody Users user){
        return service.register(user);
    }

    @Autowired
    public UserRepo repo;

    @GetMapping("/users")
    public List<Users> getAllUsers(){
        return repo.findAll();
    }

}
