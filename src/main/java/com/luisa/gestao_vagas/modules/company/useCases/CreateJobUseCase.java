package com.luisa.gestao_vagas.modules.company.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luisa.gestao_vagas.modules.company.entities.JobEntity;
import com.luisa.gestao_vagas.modules.company.repositories.JobRepository;

@Service
public class CreateJobUseCase {

    @Autowired
    private JobRepository jobRepository;
    
    public JobEntity createJob(JobEntity jobEntity){
        return this.jobRepository.save(jobEntity);
    }

}
