package com.luisa.gestao_vagas.modules.company.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luisa.gestao_vagas.modules.company.entities.JobEntity;
import com.luisa.gestao_vagas.modules.company.useCases.CreateCompanyUseCase;
import com.luisa.gestao_vagas.modules.company.useCases.CreateJobUseCase;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/job")
public class JobController {
    
    @Autowired
    private CreateJobUseCase createJobUseCase;

    public ResponseEntity<Object> createJob(@Valid @RequestBody JobEntity jobEntity){
        try{
            var result = this.createJobUseCase.createJob(jobEntity);
            return ResponseEntity.ok().body(result);
        } catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
}
