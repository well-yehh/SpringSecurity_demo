package dear.darling.springsecurity_demo.repo;

import dear.darling.springsecurity_demo.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<Users, Integer> {

    Users findByUsername(String name);
}

//plain(password) -> cipher (encrypted password)
//plain -> hash1 -> hash2...( we can convert plain to hash but cant get plain again from hash)
//using Bcrypt password generator
