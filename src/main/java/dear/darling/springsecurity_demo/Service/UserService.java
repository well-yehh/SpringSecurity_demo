package dear.darling.springsecurity_demo.Service;

import dear.darling.springsecurity_demo.Model.Users;
import dear.darling.springsecurity_demo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo repo;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JWTservice jwtservice;

    //it's an object of Bcrypt providing by spring security by-default
    private BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder(12);

    public Users register(Users user){
        //to encrypt password we use bcrypt library
        user.setPassword(bcrypt.encode(user.getPassword()));

        return repo.save(user);
    }

    public String verify(Users user) {
        Authentication authentication =
                authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        if(authentication.isAuthenticated()) {
            return jwtservice.generateToken(user.getUsername());
        }

        return "fail";
    }
}
