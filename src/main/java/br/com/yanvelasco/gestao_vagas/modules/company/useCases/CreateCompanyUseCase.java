package br.com.yanvelasco.gestao_vagas.modules.company.useCases;

import br.com.yanvelasco.gestao_vagas.exceptions.UserAlreadyExists;
import br.com.yanvelasco.gestao_vagas.modules.company.entities.CompanyEntity;
import br.com.yanvelasco.gestao_vagas.modules.company.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCompanyUseCase {

    @Autowired
    private CompanyRepository companyRepository;

    public CompanyEntity execute(CompanyEntity companyEntity){
        companyRepository.findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getEmail())
                .ifPresent(company -> {
                    throw new UserAlreadyExists("A emporesa ja está cadastrada");
                });
        return  companyRepository.save(companyEntity);
    }
}