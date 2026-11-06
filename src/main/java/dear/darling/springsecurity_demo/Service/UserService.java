package dear.darling.springsecurity_demo.Service;

import dear.darling.springsecurity_demo.Model.Users;
import dear.darling.springsecurity_demo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo repo;

    //its an object of Bcrypt providing by spring security by-default
    private BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder(12);

    public Users register(Users user){
        //to encrypt password we use bcrypt library
        user.setPassword(bcrypt.encode(user.getPassword()));

        return repo.save(user);
    }
}
