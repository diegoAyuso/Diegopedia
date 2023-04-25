package com.diego.ApiRestDiegoPedia.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.diego.ApiRestDiegoPedia.Models.Contribucion;
import com.diego.ApiRestDiegoPedia.Models.Usuario;

public interface UsuarioRepository  extends JpaRepository<Usuario, Integer> {
	List<Usuario> findByNombreContaining(String nombreCoincidencia);

	List<Usuario> findByPasswordContaining(String passwordCoincidencia);
	List<Usuario> findByNombre(String nombre);

	List<Usuario> findByPassword(String password);

}