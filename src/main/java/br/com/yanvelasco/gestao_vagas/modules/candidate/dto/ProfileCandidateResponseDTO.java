package br.com.yanvelasco.gestao_vagas.modules.candidate.dto;

import lombok.Builder;

@Builder
public record ProfileCandidateResponseDTO(String username,String description, String email) {
}
