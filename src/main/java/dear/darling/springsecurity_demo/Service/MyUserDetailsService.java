package dear.darling.springsecurity_demo.Service;

import dear.darling.springsecurity_demo.Model.UserPrincipal;
import dear.darling.springsecurity_demo.Model.Users;
import dear.darling.springsecurity_demo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo repo;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
       Users user = repo.findByUsername(name);

        System.out.println("Searching username: " + name);


        System.out.println("User found: " + (user != null));

       if(user == null){
           System.out.println("User not found");
           throw new UsernameNotFoundException("User not found");
       }

        return new UserPrincipal(user);
    }
}
