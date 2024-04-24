package com.example.demoecommerce.config;

import com.cloudinary.Api;
import com.example.demoecommerce.filter.AuthEntryPointJwt;
import com.example.demoecommerce.filter.AuthTokenFilter;
import com.example.demoecommerce.service.user_detail.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig{
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    @Bean
//    public static PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception{
//        http
////                .csrf(csrf ->csrf.ignoringRequestMatchers("/api/ignorable/**"))
//                .csrf(AbstractHttpConfigurer::disable)
//                .authorizeRequests(auth -> auth
//                        .requestMatchers("/admin/**").hasRole("ADMIN")
//                        .anyRequest().permitAll())
//                .formLogin(form -> form
//                        .loginPage("/login")
//                        .defaultSuccessUrl("/home"))
//                .logout(logout -> logout
//                        .logoutUrl("/logout")
//                        .logoutSuccessUrl("/home")) ;
//
//        return http.build();
//    }
//
//    protected void configure (AuthenticationManagerBuilder auth) throws Exception{
//        auth.userDetailsService(userDetailsService);
//    }

    @Autowired
    private AuthEntryPointJwt authEntryPointJwt;
    @Autowired
    private UserDetailsService userDetailsService;

    // Mã hóa mật khẩu
    @Bean
    public static BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(encoder());
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .authenticationEntryPoint(authEntryPointJwt)
                )

                .addFilterBefore(authTokenFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(authorize -> authorize

                        .requestMatchers(new AntPathRequestMatcher("/api/admin/**"))
                        .hasAnyRole("ADMIN")

                        .requestMatchers(new AntPathRequestMatcher("/api/all/**"))
                        .hasAnyRole("ADMIN", "USER")

                        .requestMatchers(new AntPathRequestMatcher("/api/no-auth/**"))
                        .permitAll().anyRequest().authenticated())

                .csrf(AbstractHttpConfigurer::disable)
                .authenticationProvider(authenticationProvider())
                .cors(cors -> cors
                        .configurationSource(request -> {
                            CorsConfiguration configuration = new CorsConfiguration();
                            configuration.setAllowedOrigins(List.of("*"));
                            configuration.setAllowedMethods(List.of("*"));
                            configuration.setAllowedHeaders(List.of("*"));
                            return configuration;
                        })
                );
        return http.build();
    }

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
//    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailService();
    }

    @Bean
    public AuthTokenFilter authTokenFilter() {
        return new AuthTokenFilter();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(encoder());
        return daoAuthenticationProvider;
    }

    // cái này để cho phép port bên react của chị có thể tuỳ biến truy cập vào spring security nhé
    // chỉ cần sửa trong allowed origin theo port react của chị là được
    @Bean
    public FilterRegistrationBean corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:3000");
        config.addAllowedHeader("*");
        config.setAllowedMethods(Arrays.asList(Api.HttpMethod.GET.name(), Api.HttpMethod.POST.name(), Api.HttpMethod.PUT.name(),
                Api.HttpMethod.DELETE.name()));
        config.setMaxAge(3600L);
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(-102);
        return bean;
    }

}
