package dear.darling.springsecurity_demo.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;


//If we are implimenting this then make sure secure whole thing by our own spring will use it as it security barrier from now


@Configuration // For making to configure our own security chain and not using default chain by spring security
@EnableWebSecurity // it let the spring know we are using our own security chain
public class Security_config {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        return http
                .csrf(customizer -> customizer.disable())
                .authorizeHttpRequests(request -> request.anyRequest().authenticated())
                .formLogin(Customizer.withDefaults()) //Login form implemented
                .httpBasic(Customizer.withDefaults()) // for using it in PostMan

                //Making the http request stateless , (csrf disabling)
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build(); //return the object of security filter chain




    }

}
