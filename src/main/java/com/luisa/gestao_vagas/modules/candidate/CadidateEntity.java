package com.luisa.gestao_vagas.modules.candidate;

import java.time.LocalDate;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;


@Data
@Entity(name= "candidate") 
public class CadidateEntity {
    // tudo que tiver ai dentro vai ser uma coluna no banco de dados
    @Id // estamos definindo a coluna id 
    @GeneratedValue(strategy = GenerationType.UUID) // estamos deixando a responsabilidade do incremento de id c ele
    private UUID id;
    private String name;

    @NotBlank()
    @Pattern(regexp = "\\S+", message = "O campo username não deve conter espaço")
    private String username;

    @Email(message = "O campo deve conter um e-mail válido")
    private String email;

    @Length(min = 10, max = 100)
    private String password;
    private String description;
    private String curriculum;

    @CreationTimestamp
    private LocalDate createdAt;

    
}
