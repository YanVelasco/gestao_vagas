package br.com.yanvelasco.gestao_vagas.modules.candidate.dto;

import lombok.Builder;

@Builder
public record AuthCandidateResponseDTO(String acces_token, Long expires_in ) {
}
