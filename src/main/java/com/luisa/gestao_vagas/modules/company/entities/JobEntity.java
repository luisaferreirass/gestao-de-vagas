package com.luisa.gestao_vagas.modules.company.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity(name = "job")
public class JobEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    private UUID id;
    private String description;
    private String benefits;

    @NotBlank(message= "Esse cmapo é obrigatório")
    private String level;

    // partimos da tabela que já criamos para aquela que já está criada
    @ManyToOne() // podemos ter muitos jobs para uma company
    @JoinColumn(name="company_id", insertable = false, updatable = false) // anotation de relacionamento
    // quando for inserir um job e não tiver um id vai dar erro
    // vai verificar se essa entidade existe
    private CompanyEntity companyEntity;
    // Define que a coluna company_id na tabela de Job será a chave estrangeira (Foreign Key) que referencia a tabela Company.
    // Permite acessar diretamente os dados da empresa associada a um job.


    @Column(name= "company_id")
    private UUID company_id;
    // foreign key (chave primária de uma outra tabela)

    @CreationTimestamp
    private LocalDateTime createdAt;
}
