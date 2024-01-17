package br.com.yanvelasco.gestao_vagas.modules.candidate.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.UUID;

public record CreateCandidateDTO(
        UUID id,

        @Schema(example = "Yan Velasco", requiredMode = Schema.RequiredMode.REQUIRED)
        String name,
        @Schema(example = "Yan", requiredMode = Schema.RequiredMode.REQUIRED)
        String username,

        @Schema(example = "yancarlosduartevelasco@gmail.com", requiredMode = Schema.RequiredMode.REQUIRED)
        String email,

        @Schema(example = "12345678", minLength = 8, requiredMode = Schema.RequiredMode.REQUIRED)
        String password,

        @Schema(example = "Bom com programação", requiredMode = Schema.RequiredMode.REQUIRED)
        String description,

        @Schema(example = "Curriculum Yan", requiredMode = Schema.RequiredMode.REQUIRED)
        String curriculum,

        LocalDateTime createdAt,

        LocalDateTime updatedAt) {
}
