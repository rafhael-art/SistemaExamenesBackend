/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sistema.examenes.servicios;

import com.sistema.examenes.entidades.Usuario;
import com.sistema.examenes.entidades.UsuarioRol;
import java.util.Set;

/**
 *
 * @author rafhael
 */
public interface UsuarioService {
    public Usuario guardarUsuario(Usuario usuario, Set<UsuarioRol> usuarioRoles) throws Exception;
    public Usuario obtenerUsuario(String username);
    public void eliminarUsuario (Long id);
}
