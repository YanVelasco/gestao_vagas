package br.com.yanvelasco.gestao_vagas.modules.company.controllers;

import br.com.yanvelasco.gestao_vagas.modules.company.dto.CreateJobDTO;
import br.com.yanvelasco.gestao_vagas.modules.company.entities.JobEntity;
import br.com.yanvelasco.gestao_vagas.modules.company.usecases.CreateJobUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/company/job")
public class JobController {

    @Autowired
    private CreateJobUseCase createJobUseCase;

    @PostMapping
    @PreAuthorize("hasRole('COMPANY')")
    @Tag(name = "Vagas", description = "Informações das Vagas")
    @Operation(summary = "Cadastro de Vagas", description = "Essa função é resposável por cadastrar vagas")
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = JobEntity.class))
    })
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<Object> create(@RequestBody @Valid CreateJobDTO createJobDTO,
            HttpServletRequest httpServletRequest) {
        try {
            var companyId = httpServletRequest.getAttribute("company_id");

            var jobEntity = JobEntity.builder()
                    .benefits(createJobDTO.benefits())
                    .companyId(UUID.fromString(companyId.toString()))
                    .description(createJobDTO.description())
                    .level(createJobDTO.level())
                    .build();

            var result = createJobUseCase.execute(jobEntity);
            return ResponseEntity.ok().body(result);
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

}
