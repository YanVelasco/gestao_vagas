package br.com.yanvelasco.gestao_vagas.modules.candidate.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "candidate")
@Table(name = "candidates")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CandidateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Schema(example = "Yan Velasco")
    @NotBlank(message = "O campo [nome] não deve estar em branco")
    private String name;

    @NotBlank(message = "O campo [username] não deve estar em branco")
    @Pattern(regexp = "\\S+", message = "O campo [username] não deve conter espaços em branco")
    @Schema(example = "Yan")
    private String username;

    @Schema(example = "yancarlosduartevelasco@gmail.com")
    @Email(message = "O campo [email] deve conter um email válido")
    private String email;

    @Schema(example = "12345678")
    @Length(min = 8, message = "O campo [password] deve ter no minímo 8 caracteres")
    private String password;

    @Schema(example = "Bom com programação", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "O campo [description] não deve estar em branco")
    private String description;

    @Schema(example = "Curriculum Yan")
    @NotBlank(message = "O campo [curriculum] não deve estar em branco")
    private String curriculum;

    @CreationTimestamp
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}