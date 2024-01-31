package br.com.yanvelasco.gestao_vagas.modules.candidate.usecases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.yanvelasco.gestao_vagas.exceptions.NotFound;
import br.com.yanvelasco.gestao_vagas.modules.candidate.repository.ApplyJobRepository;
import br.com.yanvelasco.gestao_vagas.modules.candidate.repository.CandidadeRepository;
import br.com.yanvelasco.gestao_vagas.modules.company.repositories.JobRepository;

@Service
public class ApplyJobCandidateUseCase {

    @Autowired
    private CandidadeRepository candidadeRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ApplyJobRepository applyJobRepository;

    // ID do Candidato e ID da Vaga
    public void execute(UUID idCandidate, UUID idJob) {
        candidadeRepository.findById(idCandidate).orElseThrow(() -> {
            throw new NotFound("Usuário não encontrado");
        });

        jobRepository.findById(idJob).orElseThrow(() -> {
            throw new NotFound("Vaga não encontrada");
        });
    }

}
