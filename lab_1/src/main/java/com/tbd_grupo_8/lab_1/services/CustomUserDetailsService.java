package com.tbd_grupo_8.lab_1.services;

import com.tbd_grupo_8.lab_1.entities.Cliente;
import com.tbd_grupo_8.lab_1.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Cliente cliente = clienteRepository.findByUsername(username);
        if (cliente == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return User.builder()
                .username(cliente.getUsername())
                .password(cliente.getContrasena()) // Asegúrate de que la contraseña esté encriptada
                .roles(cliente.getRol())
                .build();
    }
}