/*
package fr.umontpellier.jeeS.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import fr.umontpellier.jeeS.service.ValidTokenService;
import jakarta.servlet.http.HttpServletResponse;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	/*
	//@Autowired
    //private UserDetailsService userDetailsService;
	private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    
    public SecurityConfig(UserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    //vaovao
    
    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = 
            http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
        return authenticationManagerBuilder.build();
    }
    /*
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }
	
    */
	/*
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		//http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
		http
	        .authorizeHttpRequests()
	            .requestMatchers("/login", "/ajoutUser", "/ajoutUtilisateur", "/error").permitAll()
	            .requestMatchers("/administrateur/**").hasRole("ADMINISTRATEUR")
	            .requestMatchers("/gestionnaire/**").hasRole("GESTIONNAIRE")
	            .requestMatchers("/etudiant/**").hasRole("ETUDIANT")
	            .anyRequest().authenticated()
	        .and()
	        .formLogin()
	            .loginPage("/login")
	            .defaultSuccessUrl("/")
	            .permitAll()
	        .and()
	        .logout(logout -> logout
	                .logoutUrl("/logout")
	                .logoutSuccessUrl("/login?logout")
	                .invalidateHttpSession(true)
	                .deleteCookies("JSESSIONID")
	            );/*
            .and()
            .headers()
                .cacheControl().disable()
	        .and()
	        .csrf()
	            .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
	           
	    return http.build();
	}
	*/
/*
	private final JwtRequestFilter jwtRequestFilter;
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ValidTokenService validTokenService;  

    public SecurityConfig(JwtRequestFilter jwtRequestFilter, 
                          UserDetailsService userDetailsService, 
                          BCryptPasswordEncoder bCryptPasswordEncoder, 
                          ValidTokenService validTokenService) {  
        this.jwtRequestFilter = jwtRequestFilter;
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.validTokenService = validTokenService;  
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = 
            http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
        return authenticationManagerBuilder.build();
    }
*/
    /*
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
        	.and()
        	.authorizeRequests()
	            .requestMatchers("/login", "/ajoutUser", "/ajoutUtilisateur", "/error").permitAll()
	            .requestMatchers("/administrateur/**").hasRole("ADMINISTRATEUR")
	            .requestMatchers("/gestionnaire/**").hasRole("GESTIONNAIRE")
	            .requestMatchers("/etudiant/**").hasRole("ETUDIANT")
	            .anyRequest().authenticated()
	        .and()
	        	.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
    */
/*
    @Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		//http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
		http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
			.and()
	        .authorizeHttpRequests()
	            .requestMatchers("/auth/login","/login", "/ajoutUser", "/ajoutUtilisateur", "/error").permitAll()
	            .requestMatchers("/administrateur/**").hasRole("ADMINISTRATEUR")
	            .requestMatchers("/gestionnaire/**").hasRole("GESTIONNAIRE")
	            .requestMatchers("/etudiant/**").hasRole("ETUDIANT")
	            .anyRequest().authenticated()
	        .and()
	        .formLogin()
	            .loginPage("/auth/login")
	            .defaultSuccessUrl("/")
	            .permitAll()
	        .and()
	        .logout()
	        .logoutUrl("/logout")
            .logoutSuccessHandler((request, response, authentication) -> {
                String token = request.getHeader("Authorization");
                if (token != null && token.startsWith("Bearer ")) {
                    validTokenService.invalidateToken(token.substring(7));  // Utilisation de l'instance
                }
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().write("Logout successful");
            })
            .invalidateHttpSession(true)
            .clearAuthentication(true)
            .deleteCookies("JSESSIONID", "jwtToken");
           http
            .headers()
                .cacheControl().disable()
	        .and()
	        	.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	           
	    return http.build();
	}
    
}
*/