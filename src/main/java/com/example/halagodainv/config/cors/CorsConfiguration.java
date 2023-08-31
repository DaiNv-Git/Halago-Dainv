package com.example.halagodainv.config.cors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class CorsConfiguration extends OncePerRequestFilter {

    @Value("#{'${request.origin.allow}'.split(',')}")
    private List<String> origins = new ArrayList<>();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String origin = request.getHeader(HttpHeaders.ORIGIN);
        System.out.println(origins);
        if (origins.contains(origin)) {
            response.setHeader("Access-Control-Allow-Origin", origin);
        } else {
                response.setHeader("Access-Control-Allow-Origin", origins.get(0));
        }
//        response.setHeader("Access-Control-Allow-Origin", origin);
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, x-customer-header-1, x-customer-header-2, mixed-content");

        if ("OPTIONS".equals(request.getMethod()))
            response.setStatus(HttpServletResponse.SC_OK);
        else
            chain.doFilter(request, response);

    }
}
