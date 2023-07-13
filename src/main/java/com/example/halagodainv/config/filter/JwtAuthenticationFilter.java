package com.example.halagodainv.config.filter;

import com.example.halagodainv.service.auth.UserServiceConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtToken token;
    @Autowired
    private UserServiceConfig userServiceConfig;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //kiem tra duong dan dang nhap
        try {
            // Lấy jwt từ request
            String authenticationHeader = getJwtFromRequest(request);
            if (StringUtils.hasText(authenticationHeader) && token.validateToken(authenticationHeader)) {
                String email = token.getUserNameFromJWT(authenticationHeader);
//                UserDetails userDetails = userServiceConfig.loadUserByUsername(email);
                UserDetails userDetails;
                if (!ObjectUtils.isEmpty(userServiceConfig.loadUserByUsername(email))) {
                    userDetails = userServiceConfig.loadUserByUsername(email);
                    UsernamePasswordAuthenticationToken
                            usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
        } catch (Exception e) {
            log.error("Error logging in : {} " + e.getMessage());
            response.setHeader("api/login", HttpStatus.BAD_REQUEST.name());
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.setContentType(APPLICATION_JSON_VALUE);
            Map<String, String> error = new HashMap<>();
            error.put("message", "user login or password is not correct");
            new ObjectMapper().writeValue(response.getOutputStream(), error);
        }
        filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        // Kiểm tra xem header Authorization có chứa thông tin jwt không
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return "Authorization is not exit";
    }
}
