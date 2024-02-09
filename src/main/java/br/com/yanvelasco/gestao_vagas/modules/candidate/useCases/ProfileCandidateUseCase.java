package br.com.yanvelasco.gestao_vagas.modules.candidate.usecases;

import br.com.yanvelasco.gestao_vagas.exceptions.NotFound;
import br.com.yanvelasco.gestao_vagas.modules.candidate.dto.ProfileCandidateResponseDTO;
import br.com.yanvelasco.gestao_vagas.modules.candidate.repository.CandidadeRepository;
import lombok.NonNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProfileCandidateUseCase {

    @Autowired
    private CandidadeRepository candidadeRepository;

    public ProfileCandidateResponseDTO execute(@NonNull UUID idCandidate) {
        var candidate = candidadeRepository.findById(idCandidate)
                .orElseThrow(() -> new NotFound("Usuário não encontrado"));

        var candidateDTO = ProfileCandidateResponseDTO.builder()
                .name(candidate.getName())
                .username(candidate.getUsername())
                .description(candidate.getDescription())
                .email(candidate.getEmail())
                .build();

        return candidateDTO;
    }

}
