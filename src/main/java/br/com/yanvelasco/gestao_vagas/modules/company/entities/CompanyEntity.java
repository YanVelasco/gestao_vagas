package br.com.yanvelasco.gestao_vagas.modules.company.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "company")
@Table(name = "company")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "O campo [username] não deve estar em branco")
    @Pattern(regexp = "\\S+", message = "O campo [username] não deve conter espaços em branco")
    private String username;

    @Email(message = "O campo [email] deve conter um email válido")
    private String email;

    @Length(min = 8, message = "O campo [password] deve ter no minímo 8 caracteres")
    private String password;

    @NotBlank(message = "O campo [website] não deve estar em branco")
    private String website;

    @NotBlank(message = "O campo [nome] não deve estar em branco")
    private String name;

    @NotBlank(message = "O campo [description] não deve estar em branco")
    private String description;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

}