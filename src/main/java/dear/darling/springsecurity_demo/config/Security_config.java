package dear.darling.springsecurity_demo.config;


import dear.darling.springsecurity_demo.Service.JWTservice;
import dear.darling.springsecurity_demo.Service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


//If we are implimenting this then make sure secure whole thing by our own spring will use it as it security barrier from now


@Configuration // For making to configure our own security chain and not using default chain by spring security
@EnableWebSecurity // it let the spring know we are using our own security chain
public class Security_config {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        return http
                .csrf(customizer -> customizer.disable())
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/api/register", "/api/login")
                        .permitAll()
                        .anyRequest()
                        .authenticated())
//                .formLogin(Customizer.withDefaults()) //Login form implemented
                .httpBasic(Customizer.withDefaults()) // for using it in PostMan

                //Making the http request stateless , (csrf disabling)
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build(); //return the object of security filter chain


    }

    //Autherising using DATABASE

    // [Un-authenticated object] --> [Authentication Provider] --> [Authenticated object]
    // Now i will use mine authentication provider not gonna use default one by spring security

    @Bean
    public AuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);
        provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());

        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{

       return config.getAuthenticationManager();
    }

}
