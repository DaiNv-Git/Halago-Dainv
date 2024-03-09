package com.example.halagodainv.sercurity;

import com.example.halagodainv.config.cors.CorsConfiguration;
import com.example.halagodainv.config.filter.JwtAuthenticationEntryPoint;
import com.example.halagodainv.config.filter.JwtAuthenticationFilter;
import com.example.halagodainv.service.auth.UserServiceConfig;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@SecurityRequirement(name = "Bearer Authentication")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CorsConfiguration corsFilter;
    @Autowired
    private UserServiceConfig userServiceConfig;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userServiceConfig).passwordEncoder(passwordEncoder());
    }

    @Autowired
    JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public JwtAuthenticationFilter jwtAuthenticationFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable();
        http.authorizeRequests()
                .antMatchers("/api/login").permitAll()
                .antMatchers("/api/login-campaign").permitAll()
                .antMatchers("/api/refresh-token").permitAll()
                .antMatchers("/api/forgot_password").permitAll()
                .antMatchers("/contact-customer/add").permitAll()
                .antMatchers("/contact-customer/add-free-consul").permitAll()
                .antMatchers("/solution/review").permitAll()
                .antMatchers("/solution/live-stream").permitAll()
                .antMatchers("/homepage/partner").permitAll()
                .antMatchers("/homepage").permitAll()
                .antMatchers("/kob-celeb").permitAll()
                .antMatchers("/story").permitAll()
                .antMatchers("/view-brand").permitAll()
                .antMatchers("/news/view").permitAll()
                .antMatchers("/news/view/hot-new").permitAll()
                .antMatchers("/news/view/topic").permitAll()
                .antMatchers("/news/view-new-hot-topic").permitAll()
                .antMatchers("/news/view/tags").permitAll()
                .antMatchers("/news/view-detail").permitAll()
                .antMatchers("/news/view/set-hot").permitAll()
                .antMatchers("/news/view/set-not-hot").permitAll()
                .antMatchers("/api/images/get/{fileName}").permitAll()
                .antMatchers("/api/insert-influencer").permitAll()
                .antMatchers("/brand-give").permitAll()
                .antMatchers("/brand/add-brand").permitAll()
                .antMatchers("/influencer/add").permitAll()
                .antMatchers("/brand/add-contact").permitAll()
                .antMatchers("/nano-campaign").permitAll()
                .antMatchers("/manage-kol").permitAll()
                .antMatchers("/campaign/campaign-user-list").permitAll()
                .antMatchers("/media").permitAll()
                .antMatchers("/media-update-block-hide").permitAll()
                .antMatchers("/img-list").permitAll()
                .antMatchers("/influence-marketing/getAll").permitAll()
                .antMatchers("/influencer/**").permitAll()
                .antMatchers("/campaign/delete/influ").permitAll()
                .antMatchers("/api/check_code").permitAll()
                .antMatchers("/api/change_password").permitAll()
//                .antMatchers("/**").permitAll()
                .anyRequest().authenticated();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint);
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(corsFilter, ChannelProcessingFilter.class);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/swagger-ui/index.html",
                "/webjars/**");
    }
}
