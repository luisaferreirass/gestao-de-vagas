package com.luisa.gestao_vagas.security;

import java.io.IOException;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.luisa.gestao_vagas.providers.JWTProvider;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter{
    // disponibiliza o método do filtro

    @Autowired
    private JWTProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

                String header = request.getHeader("Authorization");

                if(header != null){
                    var subjectToken = this.jwtProvider.validateToken(header);
                    if(subjectToken.isEmpty()){
                        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                        return;
                    }
                    request.setAttribute("company_id", subjectToken); // estamos inserindo o company id dentro do request
                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(subjectToken, null, Collections.emptyList());
                    // é usado para dizer ao Spring: “Esse usuário está autenticado (ou tentando se autenticar)”.
                    SecurityContextHolder.getContext().setAuthentication(auth); // registra o usuário autenticado no contexto de segurança atual.
                    // Para que o spring consiga ter todas as informações do usuário para ver se ele tem autorização ou não
                } 

                filterChain.doFilter(request, response);
        
        throw new UnsupportedOperationException("Unimplemented method 'doFilterInternal'");

        // antes da requisição ir pro controller, passa pelo filtro e se precisar de requisição
        // verifica se o token é válido e se for passa pro controller

    }

}
