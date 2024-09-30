//package io.github.robertoaraujo.security;
//
//import io.github.robertoaraujo.dominio.model.user.UsuarioEntity;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    private UsuarioRepository repository;
//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        UsuarioEntity usuario = repository.findByEmail(email)
//                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com email: " + email));
//        return new org.springframework.security.core.userdetails.User(usuario.getEmail(), usuario.getSenha(), new ArrayList<>());
//    }
//}