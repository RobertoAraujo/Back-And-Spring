//package io.github.robertoaraujo.security;
//

//import io.github.robertoaraujo.utils.JwtUtil;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
//
//    private JwtUtil jwtUtil;
//
//    private UsuarioService usuarioService;
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//        String token = request.getHeader("Authorization");
//
//        if (token != null && token.startsWith("Bearer ")) {
//            token = token.substring(7); // Remove o prefixo "Bearer "
//
//            try {
//                // Valida o token e extrai o username
//                String username = jwtUtil.extractUsername(token);
//
//                if (username != null && jwtUtil.validateToken(token, username)) {
//                    // Carrega os detalhes do usuário
//                    UserDetails userDetails = (UserDetails) usuarioService.loadUserByUsername(username);
//
//                    // Cria o token de autenticação
//                    return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//                } else {
//                    // Lógica para token inválido (pode lançar exceção)
//                    throw new AuthenticationException("Token JWT inválido") {};
//                }
//            } catch (Exception e) {
//                throw new AuthenticationException("Falha na autenticação com JWT: " + e.getMessage()) {};
//            }
//        }
//
//        // Se não houver token ou se o token for inválido, retornar null ou lançar exceção
//        throw new AuthenticationException("Token JWT não fornecido ou inválido") {};
//    }
//
//    @Override
//    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
//        SecurityContextHolder.getContext().setAuthentication(authResult);
//        chain.doFilter(request, response);
//    }
//}