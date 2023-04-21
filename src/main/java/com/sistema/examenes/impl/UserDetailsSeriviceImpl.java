/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sistema.examenes.impl;

import com.sistema.examenes.entidades.Usuario;
import com.sistema.examenes.ropositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author rafhael
 */
@Service
public class UserDetailsSeriviceImpl implements UserDetailsService{

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = this.usuarioRepository.findByUsername(username);
        if(usuario == null){
            throw  new UsernameNotFoundException("Usuario no Encontrado");
        }
        return usuario;
    }
    
}
