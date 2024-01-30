package br.com.yanvelasco.gestao_vagas.modules.candidate.usecases;

import br.com.yanvelasco.gestao_vagas.exceptions.UserAlreadyExists;
import br.com.yanvelasco.gestao_vagas.modules.candidate.dto.CreateCandidateDTO;
import br.com.yanvelasco.gestao_vagas.modules.candidate.entity.CandidateEntity;
import br.com.yanvelasco.gestao_vagas.modules.candidate.repository.CandidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CreateCandidateUseCase {

    @Autowired
    private CandidadeRepository candidadeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CandidateEntity execute(CreateCandidateDTO createCandidateDTO) {
        var createCandidate = CandidateEntity.builder()
                .id(createCandidateDTO.id())
                .name(createCandidateDTO.name())
                .username(createCandidateDTO.username())
                .email(createCandidateDTO.email())
                .password(createCandidateDTO.password())
                .description(createCandidateDTO.description())
                .curriculum(createCandidateDTO.curriculum())
                .createdAt(LocalDateTime.now())
                .updatedAt(null)
                .build();

        candidadeRepository.findByUsernameOrEmail(createCandidate.getUsername(), createCandidate.getEmail())
                .ifPresent(user -> {
                    throw new UserAlreadyExists("O usuário já está cadastrado");
                });

        var password = passwordEncoder.encode(createCandidate.getPassword());
        createCandidate.setPassword(password);

        return candidadeRepository.save(createCandidate);
    }
}
