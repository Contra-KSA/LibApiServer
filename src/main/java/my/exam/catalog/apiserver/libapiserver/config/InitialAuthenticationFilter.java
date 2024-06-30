package my.exam.catalog.apiserver.libapiserver.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import my.exam.catalog.apiserver.libapiserver.dto.UserDTO;
import my.exam.catalog.apiserver.libapiserver.entity.UsernamePasswordAuthentication;
import my.exam.catalog.apiserver.libapiserver.service.HeaderValues;
import my.exam.catalog.apiserver.libapiserver.service.JwtServiceImpl;
import org.hibernate.ObjectNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class InitialAuthenticationFilter extends OncePerRequestFilter {

    private JwtServiceImpl jwtService;
    private UsernamePasswordAuthenticationProvider authenticationProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException {
        if (request.getHeader("Authorization") == null) {
            String bodyJson = request.getReader().readLine();
            if (bodyJson != null) {
                ObjectMapper mapper = new ObjectMapper();
                UserDTO userDto = mapper.readValue(bodyJson, UserDTO.class);
                String username = userDto.getUsername();
                String password = userDto.getPassword();
                try {
                    Authentication authentication = new UsernamePasswordAuthentication(username, password);
                    authentication = authenticationProvider.authenticate(authentication);
                    String jwt = jwtService.generatedJwt(authentication);
                    response.setHeader("Authorization", HeaderValues.BEARER + jwt);
                } catch (BadCredentialsException | ObjectNotFoundException e) {
                    logger.error(e.getMessage());
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                }
            }
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return !request.getServletPath().equals("/login");
    }
}