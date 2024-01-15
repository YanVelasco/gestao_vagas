package br.com.yanvelasco.gestao_vagas.modules.candidate.controller;

import br.com.yanvelasco.gestao_vagas.modules.candidate.entity.CandidateEntity;
import br.com.yanvelasco.gestao_vagas.modules.candidate.useCases.CreateCandidateUseCase;
import br.com.yanvelasco.gestao_vagas.modules.candidate.useCases.ProfileCandidateUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.events.Event;

import java.util.UUID;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CreateCandidateUseCase createCandidateUseCase;

    @Autowired
    private ProfileCandidateUseCase profileCandidateUseCase;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody @Valid CandidateEntity candidateEntity){
        System.out.println(candidateEntity);
        try {
            var result = createCandidateUseCase.execute(candidateEntity);
            return ResponseEntity.ok().body(result);
        } catch (Exception exception){
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @GetMapping
    @PreAuthorize("hasRole('CANDIDATE')")
    public ResponseEntity<Object> get(HttpServletRequest httpServletRequest){
        var idCandidate = httpServletRequest.getAttribute("candidate_id");
       try {
           var profile =  profileCandidateUseCase
                   .execute(UUID.fromString(idCandidate.toString()));
           return ResponseEntity.ok().body(profile);
       } catch (Exception exception){
           return ResponseEntity.badRequest().body(exception.getMessage());
       }
    }

}
