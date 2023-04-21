/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sistema.examenes.ropositorios;

import com.sistema.examenes.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author rafhael
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    public Usuario findByUsername(String name);
}
