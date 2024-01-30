package br.com.yanvelasco.gestao_vagas.modules.candidate.usecases;

import br.com.yanvelasco.gestao_vagas.modules.company.entities.JobEntity;
import br.com.yanvelasco.gestao_vagas.modules.company.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListAllJobsByFilterUseCase {

    @Autowired
    private JobRepository jobRepository;

    public List<JobEntity> execute(String description){
        return jobRepository.findByDescriptionContainingIgnoreCase(description);
    }

}
