package br.com.yanvelasco.gestao_vagas.modules.company.useCases;

import br.com.yanvelasco.gestao_vagas.modules.company.dto.AuthCompanyDTO;
import br.com.yanvelasco.gestao_vagas.modules.company.repositories.CompanyRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;

@Service
public class AuthCompanyUseCase {

    @Value("${security.token.secret}")
    private String secretKey;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String execute(AuthCompanyDTO authCompanyDTO) throws AuthenticationException {

        var company = companyRepository.findByUsername(authCompanyDTO.username()).orElseThrow(() ->{
                    throw new UsernameNotFoundException("Company não encontrada");
               }
        );

        var passwordMatches = passwordEncoder.matches(authCompanyDTO.password(), company.getPassword());

        if (!passwordMatches){
            throw new AuthenticationException();
        }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

         var token = JWT.create().withIssuer("Javagas")
                .withSubject(company.getId().toString())
                .sign(algorithm);

         return token;
    }
}
