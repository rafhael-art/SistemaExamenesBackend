/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sistema.examenes.impl;

import com.sistema.examenes.entidades.Usuario;
import com.sistema.examenes.entidades.UsuarioRol;
import com.sistema.examenes.ropositorios.RolRepository;
import com.sistema.examenes.ropositorios.UsuarioRepository;
import com.sistema.examenes.servicios.UsuarioService;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author rafhael
 */
@Service
public class UsuarioServiceImpl implements UsuarioService{
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private RolRepository rolRepository;

    @Override
    public Usuario guardarUsuario(Usuario usuario, Set<UsuarioRol> usuarioRoles) throws Exception {
        Usuario usuarioLocal =usuarioRepository.findByUsername(usuario.getUsername());
        
        if (usuarioLocal != null) {
            System.out.println("Usuario ya Existe");
            throw new Exception("El Usuario ya está presente");
        }else{
            for(UsuarioRol usuarioRol : usuarioRoles){
                rolRepository.save(usuarioRol.getRol());
            }
            usuario.getUsuarioRoles().addAll(usuarioRoles);
            usuarioLocal = usuarioRepository.save(usuario);
        }
        return usuarioLocal;
    }

    @Override
    public Usuario obtenerUsuario(String username) {
       return usuarioRepository.findByUsername(username);
    }

    @Override
    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}
