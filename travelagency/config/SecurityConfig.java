package com.bergeskar.travelagency.config;


import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.keycloak.adapters.springsecurity.management.HttpSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;
import java.util.List;


@KeycloakConfiguration
public class SecurityConfig extends KeycloakWebSecurityConfigurerAdapter {


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth){
        SimpleAuthorityMapper grantedAuthorityMapper = new SimpleAuthorityMapper();
        KeycloakAuthenticationProvider keycloakAuthenticationProvider = keycloakAuthenticationProvider();
        keycloakAuthenticationProvider.setGrantedAuthoritiesMapper(grantedAuthorityMapper);
        auth.authenticationProvider(keycloakAuthenticationProvider);
    }


    @Bean
    @Override
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
    }


    @Bean
    @Override
    @ConditionalOnMissingBean(HttpSessionManager.class)
    protected HttpSessionManager httpSessionManager() {
        return new HttpSessionManager();
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/tw-db/**");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);

        http.csrf().disable();
        http

                .authorizeRequests()
                .antMatchers("/api/v2/trips").hasAnyRole("user", "admin")
                .antMatchers("/api/v2/booktrip").hasRole("user")
                .antMatchers("/api/v2/updatetrip").hasAnyRole("user", "admin")
                .antMatchers("/api/v2/mytrips").hasRole("user")
                .antMatchers("/api/v2/exchange").hasRole("user")
                .antMatchers("/api/v2/customers").hasRole("admin")
                .antMatchers("/api/v2/adddestination").hasRole("admin")
                .antMatchers("/api/v2/deletedestination").hasRole("admin")
                .antMatchers("/api/v2/updatedestination").hasRole("admin")
                .antMatchers("/api/v2/cancelbooking").hasRole("admin")
                .antMatchers("/api/v2/addcustomer").hasRole("admin")
                .antMatchers("/api/v2/alldestinations").hasRole("admin")
                .antMatchers("/api/v2/bookingsbydestination").hasRole("admin")
                .antMatchers("/api/v2/destination/{id}").hasRole("admin")


                .and().formLogin();

        http.csrf().disable().cors().configurationSource(request -> corsFilter());
        http.headers().frameOptions().disable();
    }

    @Bean
    public CorsConfiguration corsFilter() {

        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);

        config.addAllowedOrigin("http://127.0.0.1:5501/");
        config.addAllowedOrigin("http://localhost:5501/");

        config.addAllowedOrigin("http://localhost:3000/");
        config.addAllowedOrigin("http://127.0.0.1:3000/");


        config.setAllowedMethods(Arrays.asList("POST", "OPTIONS", "GET", "DELETE", "PUT"));
        config.setAllowedHeaders(Arrays.asList("X-Requested-With", "Origin", "Content-Type", "Accept", "Authorization"));
        config.setExposedHeaders(List.of("Authorization"));
        return config;
    }

}
