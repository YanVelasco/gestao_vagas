package br.com.yanvelasco.gestao_vagas.modules.company.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record CreateJobDTO(

        @Schema(example = "Vaga pra pessoa desenvolvedora Júnior", requiredMode = Schema.RequiredMode.REQUIRED)
        String description,

        @Schema(example = "GYMpass, VA, VR", requiredMode = Schema.RequiredMode.REQUIRED)
        String benefits,

        @Schema(example = "Júnior, Pleno, Sênior", requiredMode = Schema.RequiredMode.REQUIRED)
        String level) {
}