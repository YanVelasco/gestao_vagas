package br.com.yanvelasco.gestao_vagas.modules.candidate.controllers;

import br.com.yanvelasco.gestao_vagas.exceptions.UserAlreadyExists;
import br.com.yanvelasco.gestao_vagas.modules.candidate.entity.CandidateEntity;
import br.com.yanvelasco.gestao_vagas.modules.candidate.repository.CandidadeRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CandidadeRepository candidadeRepository;

    @PostMapping
    public CandidateEntity create(@RequestBody @Valid CandidateEntity candidateEntity){
        System.out.println(candidateEntity);

        candidadeRepository.findByUsernameOrEmail(candidateEntity.getUsername(), candidateEntity.getEmail())
                .ifPresent(user ->{
                    throw new UserAlreadyExists("Usuário já existe");
                });

        return candidadeRepository.save(candidateEntity);
    }

}
