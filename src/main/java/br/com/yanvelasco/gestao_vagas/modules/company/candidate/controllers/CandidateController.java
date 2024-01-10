package br.com.yanvelasco.gestao_vagas.modules.company.candidate.controllers;

import br.com.yanvelasco.gestao_vagas.modules.company.candidate.entity.CandidateEntity;
import br.com.yanvelasco.gestao_vagas.modules.company.candidate.useCases.CreateCandidateUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CreateCandidateUseCase createCandidateUseCase;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody @Valid CandidateEntity candidateEntity){
        System.out.println(candidateEntity);
        try {
            var result = createCandidateUseCase.excute(candidateEntity);
            return ResponseEntity.ok().body(result);
        } catch (Exception exception){
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

}
