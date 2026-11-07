package dear.darling.springsecurity_demo.Controller;

import dear.darling.springsecurity_demo.Model.Users;
import dear.darling.springsecurity_demo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class userController {

    @Autowired
    public UserRepo repo;

    @GetMapping("/users")
    public List<Users> getAllUsers(){
        return repo.findAll();
    }


    @PostMapping("/login")
    public String login(@RequestBody Users user){

        return service.verify(user);
    }

}
