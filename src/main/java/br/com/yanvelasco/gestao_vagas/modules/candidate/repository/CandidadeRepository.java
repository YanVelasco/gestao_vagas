package br.com.yanvelasco.gestao_vagas.modules.candidate.repository;

import br.com.yanvelasco.gestao_vagas.modules.candidate.entity.CandidateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CandidadeRepository extends JpaRepository<CandidateEntity, UUID> {
}