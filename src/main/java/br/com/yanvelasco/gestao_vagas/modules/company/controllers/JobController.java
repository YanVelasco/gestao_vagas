package br.com.yanvelasco.gestao_vagas.modules.company.controllers;

import br.com.yanvelasco.gestao_vagas.modules.company.dto.CreateJobDTO;
import br.com.yanvelasco.gestao_vagas.modules.company.entities.JobEntity;
import br.com.yanvelasco.gestao_vagas.modules.company.useCases.CreateJobUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    private CreateJobUseCase createJobUseCase;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody @Valid CreateJobDTO createJobDTO, HttpServletRequest httpServletRequest){
        System.out.println(createJobDTO);
        try {
            var companyId = httpServletRequest.getAttribute("company_id");
            //jobEntity.setCompanyId(UUID.fromString(companyId.toString()));

            var jobEntity = JobEntity.builder()
                    .benefits(createJobDTO.benefits())
                    .companyId(UUID.fromString(companyId.toString()))
                    .description(createJobDTO.description())
                    .level(createJobDTO.level()).build();

            var result = createJobUseCase.execute(jobEntity);
            return ResponseEntity.ok().body(result);
        }catch (Exception exception){
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

}
