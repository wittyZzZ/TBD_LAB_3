package com.tbd_grupo_8.lab_1.controllers;

import com.tbd_grupo_8.lab_1.config.JwtUtil;
import com.tbd_grupo_8.lab_1.dto.LoginDto;
import com.tbd_grupo_8.lab_1.dto.RegisterDto;
import com.tbd_grupo_8.lab_1.entities.Cliente;
import com.tbd_grupo_8.lab_1.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final ClienteService clienteService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, ClienteService clienteService, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.clienteService = clienteService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody LoginDto loginDto) {
        try { // Intentar autenticar al usuario
            UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());
            Authentication authentication = authenticationManager.authenticate(login);

            // Obtener el cliente y crear el token con toda su información
            Cliente cliente = clienteService.getByUsername(loginDto.getUsername());
            String jwt = this.jwtUtil.create(cliente); // Modificamos el método `create` para recibir un objeto Cliente

            return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION,jwt).build();
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/register")
    public Object register(@RequestBody RegisterDto registerDto) {
        if (clienteService.getByUsername(registerDto.getUsername()) != null || clienteService.getByEmail(registerDto.getEmail()) != null) {
            // Devolver un error con estado 409 (CONFLICT) si el usuario ya existe
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Ya existe un usuario con ese nombre o correo");
        } else {
            Cliente newCliente = new Cliente();
            newCliente.setUsername(registerDto.getUsername());
            newCliente.setContrasena(passwordEncoder.encode(registerDto.getPassword())); // Encriptar la contraseña
            newCliente.setRol(registerDto.getRol());
            newCliente.setEmail(registerDto.getEmail());
            newCliente.setTelefono(registerDto.getTelefono());
            newCliente.setDireccion(registerDto.getDireccion());
            clienteService.saveCliente(newCliente);
            return ResponseEntity.ok().build();
        }
    }
}
