package com.example.config;

import com.example.repository.BookRepository;
import com.example.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by user on 2017-03-10.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private ReaderRepository readerRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .antMatchers("/")
                .access("hasRole('READER')") //리더권한 요구
                .antMatchers("/**")
                .permitAll()
                .and()
                .formLogin()
                .loginPage("/login") //로그인 폼 경로 설정
                .failureUrl("/login?error=ture");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
       auth.userDetailsService(new UserDetailsService() {
           @Override
           public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
               return readerRepository.findOne(username);
           }
       }) ;
    }

}
