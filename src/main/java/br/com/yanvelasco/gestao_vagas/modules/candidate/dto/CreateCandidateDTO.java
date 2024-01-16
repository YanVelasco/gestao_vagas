package br.com.yanvelasco.gestao_vagas.modules.candidate.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record CreateCandidateDTO(
        UUID id,
        String name,
        String username,
        String email,
        String password,
        String description,
        String curriculum,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {
}
