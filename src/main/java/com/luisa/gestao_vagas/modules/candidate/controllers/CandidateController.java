package com.luisa.gestao_vagas.modules.candidate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luisa.gestao_vagas.exceptions.UserFoundException;
import com.luisa.gestao_vagas.modules.candidate.CadidateEntity;
import com.luisa.gestao_vagas.modules.candidate.CandidateRepository;
import com.luisa.gestao_vagas.modules.candidate.useCases.CreateCandidateUseCase;

import jakarta.validation.Valid;

@RestController
@RequestMapping
public class CandidateController {

    @Autowired
    private CreateCandidateUseCase createCandidateUseCase;


    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody CadidateEntity cadidateEntity){
        try{
            var result = this.createCandidateUseCase.execute(cadidateEntity);
            // método que retorna uma entidade ou uma exceção
            return ResponseEntity.ok().body(result);
            // o ok retorna o status_code 200

        } catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
            // estamos tratando o erro que pode ser lançado
        }
        
        
        
    }   

}
