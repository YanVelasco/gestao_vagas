package br.com.yanvelasco.gestao_vagas.modules.candidate.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record ProfileCandidateResponseDTO(
        @Schema(example = "Yan Carlos Duarte Velasco")
        String name,
        @Schema(example = "Yan")
        String username,
        @Schema(example = "Melhor programador")
        String description,
        @Schema(example = "yancarlosduartevelasco@gmail.com")
        String email) {
}
