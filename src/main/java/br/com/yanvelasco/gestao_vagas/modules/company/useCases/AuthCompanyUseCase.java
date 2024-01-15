package br.com.yanvelasco.gestao_vagas.modules.company.useCases;

import br.com.yanvelasco.gestao_vagas.modules.company.dto.AuthCompanyDTO;
import br.com.yanvelasco.gestao_vagas.modules.company.dto.AuthCompanyResponseDTO;
import br.com.yanvelasco.gestao_vagas.modules.company.repositories.CompanyRepository;
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
public class AuthCompanyUseCase {

    @Value("${security.token.secret}")
    private String secretKey;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthCompanyResponseDTO execute(AuthCompanyDTO authCompanyDTO) throws AuthenticationException {

        var company = companyRepository.findByUsername(authCompanyDTO.username()).orElseThrow(() ->{
                    throw new UsernameNotFoundException("Username/password incorreto.");
               }
        );

        var passwordMatches = passwordEncoder.matches(authCompanyDTO.password(), company.getPassword());

        if (!passwordMatches){
            throw new AuthenticationException();
        }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        var expiresIn = Instant.now().plus(Duration.ofHours(2));

         var token = JWT.create().withIssuer("Javagas")
                 .withExpiresAt(expiresIn)
                 .withSubject(company.getId().toString())
                 .withClaim("roles", List.of("COMPANY"))
                 .sign(algorithm);

        var authCompanyResponseDTO = AuthCompanyResponseDTO.builder()
                .acces_token(token)
                .expires_in(expiresIn)
                .build()
                ;

         return authCompanyResponseDTO;
    }
}
