package com.diego.SpringMVCDiegoPedia.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diego.SpringMVCDiegoPedia.Models.Usuario;


public interface UsuarioRepository  extends JpaRepository<Usuario, Integer> {
	List<Usuario> findByNombreContaining(String nombreCoincidencia);

	List<Usuario> findByPasswordContaining(String passwordCoincidencia);
	List<Usuario> findByNombre(String nombre);

	List<Usuario> findByPassword(String password);

}