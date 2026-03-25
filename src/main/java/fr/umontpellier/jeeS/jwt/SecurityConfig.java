package fr.umontpellier.jeeS.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                		.requestMatchers("/login", "/error", "/ajoutUser","/ajoutUtilisateur", "/public/**").permitAll()
                        //.requestMatchers("/").hasAnyAuthority("ADMINISTRATEUR", "GESTIONNAIRE", "ETUDIANT") 
                        //.requestMatchers("/campus/**", "/batiments/**", "/salle/**", "/composante/**").hasAuthority("ADMINISTRATEUR")
                        //.requestMatchers("/campus/**", "/batiments/**", "/salle/**", "/composante/**").hasAuthority("GESTIONNAIRE")
                        //.requestMatchers("/campus/**", "/batiments/**", "/salle/**", "/composante/**").hasAuthority("ETUDIANT")
                        .anyRequest().authenticated()
                )
                .formLogin()
	                .loginPage("/login")
	                .defaultSuccessUrl("/", true)
                .and()
                .logout()
	                .logoutUrl("/logout")
	                .logoutSuccessUrl("/login?logout")
	                .invalidateHttpSession(true)
	                .deleteCookies("JSESSIONID")
	            .and()
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
