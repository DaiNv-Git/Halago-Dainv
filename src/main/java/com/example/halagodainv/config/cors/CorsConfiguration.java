package com.example.halagodainv.config.cors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.Filter;
import java.io.IOException;
import java.util.List;

@Component
public class CorsConfiguration extends OncePerRequestFilter implements Filter{

    @Value("#{'${request.origin.allow}'.split(',')}")
    private List<String> origins;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
//        String origin = request.getHeader(HttpHeaders.ORIGIN);
//        System.out.println(origins);
//        if (origins.contains(origin)) {
//            response.setHeader("Access-Control-Allow-Origin", origin);
//            System.out.println("=============="+origin);
//        } else {
//            response.setHeader("Access-Control-Allow-Origin", origins.get(0));
//            response.setHeader("Access-Control-Allow-Origin", origins.get(1));
//            response.setHeader("Access-Control-Allow-Origin", origins.get(2));
//            response.setHeader("Access-Control-Allow-Origin", origins.get(3));
//            response.setHeader("Access-Control-Allow-Origin", origins.get(4));
//        }
        response.setHeader("Access-Control-Allow-Origin","*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
//        response.setHeader("Access-Control-Allow-Headers", "X-Requested-With,Content-Type,Content-Range,Range,Authorization,Origin,Accept,Access-Control-Request-Method,Access-Control-Request-Headers,X-Requested-With,x-customer-header-1,x-customer-header-2");
        response.setHeader("Access-Control-Allow-Headers", "*");

        if ("OPTIONS".equals(request.getMethod()))
            response.setStatus(HttpServletResponse.SC_OK);
        else
            chain.doFilter(request, response);

    }
}
