package br.com.yanvelasco.gestao_vagas.modules.company.controllers;

import br.com.yanvelasco.gestao_vagas.modules.company.entities.JobEntity;
import br.com.yanvelasco.gestao_vagas.modules.company.useCases.CreateJobUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    private CreateJobUseCase createJobUseCase;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody @Valid JobEntity jobEntity){
        System.out.println(jobEntity);
        try {
            var result = createJobUseCase.execute(jobEntity);
            return ResponseEntity.ok().body(result);
        }catch (Exception exception){
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

}
