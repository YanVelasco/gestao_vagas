package br.com.yanvelasco.gestao_vagas.modules.candidate.useCases;

import br.com.yanvelasco.gestao_vagas.modules.candidate.dto.ProfileCandidateResponseDTO;
import br.com.yanvelasco.gestao_vagas.modules.candidate.repository.CandidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProfileCandidateUseCase {

    @Autowired
    private CandidadeRepository candidadeRepository;

    public ProfileCandidateResponseDTO execute(UUID idCandidate){
        var candidate = candidadeRepository.findById(idCandidate).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        var candidateDTO = ProfileCandidateResponseDTO.builder()
                .username(candidate.getUsername())
                .description(candidate.getEmail())
                .email(candidate.getEmail())
                .build();

        return candidateDTO;
    }


}
