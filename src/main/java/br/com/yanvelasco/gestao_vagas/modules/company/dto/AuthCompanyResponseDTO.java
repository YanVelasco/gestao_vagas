package br.com.yanvelasco.gestao_vagas.modules.company.dto;

import lombok.Builder;
import java.time.Instant;

@Builder
public record AuthCompanyResponseDTO(Instant expires_in, String acces_token) {
}
