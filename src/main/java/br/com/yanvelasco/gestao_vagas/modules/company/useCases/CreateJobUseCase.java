package br.com.yanvelasco.gestao_vagas.modules.company.usecases;

import br.com.yanvelasco.gestao_vagas.exceptions.NotFound;
import br.com.yanvelasco.gestao_vagas.modules.company.entities.JobEntity;
import br.com.yanvelasco.gestao_vagas.modules.company.repositories.CompanyRepository;
import br.com.yanvelasco.gestao_vagas.modules.company.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateJobUseCase {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private CompanyRepository companyRepository;

    public JobEntity execute(JobEntity jobEntity){
        companyRepository.findById(jobEntity.getCompanyId()).orElseThrow(
            () -> new NotFound("Empresa não encontrada")
        );
        return jobRepository.save(jobEntity);
    }

}
