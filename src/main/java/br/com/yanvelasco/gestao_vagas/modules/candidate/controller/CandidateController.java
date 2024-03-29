package br.com.yanvelasco.gestao_vagas.modules.candidate.controller;

import br.com.yanvelasco.gestao_vagas.modules.candidate.dto.CreateCandidateDTO;
import br.com.yanvelasco.gestao_vagas.modules.candidate.dto.ProfileCandidateResponseDTO;
import br.com.yanvelasco.gestao_vagas.modules.candidate.entity.CandidateEntity;
import br.com.yanvelasco.gestao_vagas.modules.candidate.usecases.ApplyJobCandidateUseCase;
import br.com.yanvelasco.gestao_vagas.modules.candidate.usecases.CreateCandidateUseCase;
import br.com.yanvelasco.gestao_vagas.modules.candidate.usecases.ListAllJobsByFilterUseCase;
import br.com.yanvelasco.gestao_vagas.modules.candidate.usecases.ProfileCandidateUseCase;
import br.com.yanvelasco.gestao_vagas.modules.company.entities.JobEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/candidate")
@Tag(name = "Candidato", description = "Informações do candidato")
public class CandidateController {

    @Autowired
    private CreateCandidateUseCase createCandidateUseCase;

    @Autowired
    private ProfileCandidateUseCase profileCandidateUseCase;

    @Autowired
    private ListAllJobsByFilterUseCase listAllJobsByFilterUseCase;

    @Autowired
    private ApplyJobCandidateUseCase applyJobCandidateUseCase;

    @PostMapping
    @Operation(summary = "Cadastro de Candidato", description = "Essa função é responsável por cadastrar candidatos")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(array = @ArraySchema(schema = @Schema(implementation = CandidateEntity.class)))
            }),
            @ApiResponse(responseCode = "400", description = "Usuário já existe")
    })
    public ResponseEntity<Object> create(@RequestBody CreateCandidateDTO createCandidateDTO) {
        try {
            var result = createCandidateUseCase.execute(createCandidateDTO);
            return ResponseEntity.ok().body(result);
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @GetMapping
    @PreAuthorize("hasRole('CANDIDATE')")
    @Operation(summary = "Perfil do Candidato", description = "Essa função é responsável por buscar as informações do candidato")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(array = @ArraySchema(schema = @Schema(implementation = ProfileCandidateResponseDTO.class)))
            }),
            @ApiResponse(responseCode = "400", description = "Usuário não encontrado")
    })
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<Object> get(HttpServletRequest httpServletRequest) {
        var idCandidate = httpServletRequest.getAttribute("candidate_id");
        try {
            var profile = profileCandidateUseCase
                    .execute(UUID.fromString(idCandidate.toString()));
            return ResponseEntity.ok().body(profile);
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @GetMapping("/job")
    @PreAuthorize("hasRole('CANDIDATE')")
    @Operation(summary = "Listagem de vagas disponíveis", description = "Essa função é responsável por lista de vagas.")
    @ApiResponse(responseCode = "200", content = {
            @Content(array = @ArraySchema(schema = @Schema(implementation = JobEntity.class)))
    })
    @SecurityRequirement(name = "jwt_auth")
    public List<JobEntity> findJobByDescription(@RequestParam String description) {
        return listAllJobsByFilterUseCase.execute(description);
    }

    @PostMapping("/job/apply")
    @PreAuthorize("hasRole('CANDIDATE')")
    @SecurityRequirement(name = "jwt_auth")
    @Operation(summary = "Inscrição do candidato para uma vaga", description = "Essa função é responsável por realizar a inscrição do candidato em uma vaga.")
    public ResponseEntity<Object> applyJob(HttpServletRequest httpServletRequest, @RequestBody UUID idJob){
        var idCandidate = httpServletRequest.getAttribute("candidate_id");
        try {
            var result = applyJobCandidateUseCase.execute(UUID.fromString(idCandidate.toString()), idJob);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

}
