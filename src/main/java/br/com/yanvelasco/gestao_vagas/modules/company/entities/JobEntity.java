package br.com.yanvelasco.gestao_vagas.modules.company.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "job")
@Table(name = "job")
public class JobEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "O campo [description] não deve estar em branco")
    private String description;

    @NotBlank(message = "O campo [benefits] não deve estar em branco")
    private String benefits;

    @NotBlank(message = "O campo [level] não deve estar em branco")
    private String level;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private CompanyEntity company;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

}