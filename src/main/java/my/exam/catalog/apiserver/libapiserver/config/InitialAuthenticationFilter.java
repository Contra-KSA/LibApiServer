package my.exam.catalog.apiserver.libapiserver.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import my.exam.catalog.apiserver.libapiserver.dto.UserDTO;
import my.exam.catalog.apiserver.libapiserver.entity.UsernamePasswordAuthentication;
import my.exam.catalog.apiserver.libapiserver.service.HeaderValues;
import my.exam.catalog.apiserver.libapiserver.service.JwtServiceImpl;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class InitialAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtServiceImpl jwtService;
    @Autowired
    private UsernamePasswordAuthenticationProvider authenticationProvider;

    public InitialAuthenticationFilter(JwtServiceImpl jwtService,
            UsernamePasswordAuthenticationProvider authenticationProvider) {
        this.jwtService = jwtService;
        this.authenticationProvider = authenticationProvider;
    }

    @Autowired
    public InitialAuthenticationFilter(JwtServiceImpl jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException {
        if (request.getHeader(HeaderValues.AUTHORIZATION) == null) {
            StringBuilder sb = new StringBuilder();
            BufferedReader br = request.getReader();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            String bodyJson = sb.toString();
            if (!bodyJson.isEmpty()) {
                ObjectMapper mapper = new ObjectMapper();
                UserDTO userDto = mapper.readValue(bodyJson, UserDTO.class);
                String username = userDto.getUsername();
                String password = userDto.getPassword();
                try {
                    Authentication authentication = new UsernamePasswordAuthentication(username, password);
                    authentication = authenticationProvider.authenticate(authentication);
                    String jwt = jwtService.generatedJwt(authentication);
                    response.setHeader(HeaderValues.AUTHORIZATION, HeaderValues.BEARER + jwt);
                } catch (BadCredentialsException | ObjectNotFoundException e) {
                    logger.error(e.getMessage());
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                }
            }else{
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return !request.getServletPath().equals("/login");
    }
}