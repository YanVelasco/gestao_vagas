package br.com.yanvelasco.gestao_vagas.modules.company.usecases;

import br.com.yanvelasco.gestao_vagas.modules.company.entities.JobEntity;
import br.com.yanvelasco.gestao_vagas.modules.company.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateJobUseCase {

    @Autowired
    private JobRepository jobRepository;

    @SuppressWarnings("null")
    public JobEntity execute(JobEntity jobEntity){
        return jobRepository.save(jobEntity);
    }

}
