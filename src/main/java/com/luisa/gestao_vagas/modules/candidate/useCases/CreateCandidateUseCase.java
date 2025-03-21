package com.luisa.gestao_vagas.modules.candidate.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luisa.gestao_vagas.exceptions.UserFoundException;
import com.luisa.gestao_vagas.modules.candidate.CadidateEntity;
import com.luisa.gestao_vagas.modules.candidate.CandidateRepository;

@Service // camada de serviço, ou seja, regra de negócio
public class CreateCandidateUseCase {
    @Autowired
    private CandidateRepository candidateRepository;

    public CadidateEntity execute(CadidateEntity cadidateEntity){
        this.candidateRepository.findByUsernameOrEmail(cadidateEntity.getUsername(), cadidateEntity.getEmail()).ifPresent((user) -> {
                throw new UserFoundException();
        });
        // Se ifPresent, vai rodar a função 
        return this.candidateRepository.save(cadidateEntity);
        // vai retornar todas as informações do candidate
    }
}
