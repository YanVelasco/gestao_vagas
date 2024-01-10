package br.com.yanvelasco.gestao_vagas.modules.candidate.useCases;

import br.com.yanvelasco.gestao_vagas.exceptions.UserAlreadyExists;
import br.com.yanvelasco.gestao_vagas.modules.candidate.entity.CandidateEntity;
import br.com.yanvelasco.gestao_vagas.modules.candidate.repository.CandidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCandidateUseCase {

    @Autowired
    private CandidadeRepository candidadeRepository;

    public CandidateEntity excute(CandidateEntity candidateEntity){
        candidadeRepository.findByUsernameOrEmail(candidateEntity.getUsername(), candidateEntity.getEmail())
                .ifPresent(user ->{
                    throw new UserAlreadyExists("Usuário já existe");
                });
        return candidadeRepository.save(candidateEntity);
    }

}
