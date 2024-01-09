package br.com.yanvelasco.gestao_vagas.modules.candidate.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.UUID;

@Data
public class CandidateEntity {

    private UUID id;
    @NotBlank(message = "O campo [nome] não deve estar em branco")
    private String name;
    @NotBlank(message = "O campo [username] não deve estar em branco")
    @Pattern(regexp = "\\S+", message = "O campo [username] não deve conter espaços em branco")
    private String username;
    @Email(message = "O campo [email] deve conter um email válido")
    private String email;
    @Length(min = 8, message = "O campo [password] deve ter no minímo 8 caracteres")
    private String password;
    @NotBlank(message = "O campo [username] não deve estar em branco")
    private String description;
    @NotBlank(message = "O campo [username] não deve estar em branco")
    private String curriculum;

}
