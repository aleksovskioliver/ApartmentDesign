package mk.com.apartmentdesign.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final CustomEmailPasswordAuthenticationProvider authenticationProvider;

    public WebSecurityConfig (PasswordEncoder passwordEncoder, CustomEmailPasswordAuthenticationProvider authenticationProvider) {
        this.passwordEncoder = passwordEncoder;
        this.authenticationProvider = authenticationProvider;
    }

    @Override
    protected void configure (HttpSecurity http) throws Exception {
        http.csrf ().disable ()
                .authorizeRequests ()
                .antMatchers ("/","/home","/apartments/**","/designers/**","/register","/css/**","/js/**","/img/**","/fonts/**").permitAll ()
                .anyRequest ()
                .authenticated ()
                .and ()
                .formLogin ()
                .loginPage ("/login").permitAll ()
                .failureUrl ("/login?error=BadCredentials")
                .defaultSuccessUrl ("/home",true)
                .and ()
                .logout ()
                    .logoutUrl ("/logout")
                    .clearAuthentication (true)
                    .invalidateHttpSession (true)
                    .deleteCookies ("JSESSIONID")
                    .logoutSuccessUrl ("/login");
    }

    @Override
    protected void configure (AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider (authenticationProvider);
    }
}
