package com.luisa.gestao_vagas.modules.candidate;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<CadidateEntity, UUID> {
    // usamos uma interface pq o spring data jpa já tem uma facilidade para termos acesso a vários métodos do jpa
    // passamos a entidade e o tipo da chave primária (id)

    // método para validação, só por usarmos o findBy com os campos o spring já vai fazer a busca no banco de dados
    Optional<CadidateEntity> findByUsernameOrEmail(String username, String email);
    // Se o optional não tiver nulo, vai retornar true e se estiver vai retornar false (se usarmos o isPresent)
    // dentro de uma interface, todos os métodos são implicitamente públicos e abstratos
    // Quando você estende JpaRepository<CadidateEntity, UUID>, o Spring Data JPA cria automaticamente uma 
    // classe concreta com a implementação dos métodos CRUD
    // por isso que não precisamos crar uma outra classe para instanciar
}
