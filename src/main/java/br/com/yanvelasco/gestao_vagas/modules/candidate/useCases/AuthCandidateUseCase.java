package br.com.yanvelasco.gestao_vagas.modules.candidate.useCases;

import br.com.yanvelasco.gestao_vagas.modules.candidate.dto.AuthCandidateRequestDTO;
import br.com.yanvelasco.gestao_vagas.modules.candidate.dto.AuthCandidateResponseDTO;
import br.com.yanvelasco.gestao_vagas.modules.candidate.repository.CandidadeRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Service
public class AuthCandidateUseCase {

    @Value("${security.token.secret.candidate}")
    private String secretKey;

    @Autowired
    private CandidadeRepository candidadeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthCandidateRequestDTO execute(AuthCandidateRequestDTO authCandidateRequestDTO) throws AuthenticationException {
       var candidate = candidadeRepository.findByUsername(authCandidateRequestDTO.username())
               .orElseThrow(() -> new UsernameNotFoundException("Username/password incorreto."));

        var passwordMatches = passwordEncoder.matches(authCandidateRequestDTO.password(), candidate.getPassword());

        if (!passwordMatches){
            throw new AuthenticationException();
        }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        var token = JWT.create().withIssuer("Javagas")
                .withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
                .withSubject(candidate.getId().toString())
                .withClaim("roles", List.of("candidate"))
                .sign(algorithm);

        var authCandidateResponseDTO = AuthCandidateResponseDTO.builder().acces_token(token).build();

        return authCandidateRequestDTO;
    }
}