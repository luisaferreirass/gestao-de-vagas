package com.luisa.gestao_vagas.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration // classe de configuração que eu quero que o spring gerencie
public class SecurityConfig {
    
    @Autowired
    private SecurityFilter securityFilter;

    // método para gerenciar minhas requisições
    @Bean // método que está sobreescrevendo as configurações iniciais
    // método que está sendo utilizado para definir algum objeto já gerenciado pelo spring
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        // o SecurityFilterChain define uma cadeia de filtros para gerenciar a segurança das requisições HTTP
        // desabilitar o spring security, para que possamos fazer requisições 
        http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> { // Configura as permissões de acesso para diferentes rotas
                // estamos permiitindo requests para a rota company e candidate, mas para
                // qualquer outro request será necessário
                auth.requestMatchers("/candidate/").permitAll()
                .requestMatchers("/company/").permitAll()
                .requestMatchers("/auth/company").permitAll()
                .requestMatchers("/auth/candidate").permitAll();
                auth.anyRequest().authenticated();
            })
            .addFilterBefore(securityFilter, BasicAuthenticationFilter.class)
            // criar um filtro para ser chamado em todas as requisições

        
        ;
        return http.build();
        // Construir e retornar a configuração do Spring Security
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    } // O Spring Security usará esse PasswordEncoder para criptografar senhas antes de armazená-las no banco de dados.


}
