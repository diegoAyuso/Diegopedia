package com.diego.ApiRestDiegoPedia.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.diego.ApiRestDiegoPedia.Models.Contribucion;
import com.diego.ApiRestDiegoPedia.Models.RelacionCientificoContribucion;
import com.diego.ApiRestDiegoPedia.Models.RelacionUsuarioContribucion;
import com.diego.ApiRestDiegoPedia.Models.Usuario;

public interface RelacionUsuarioContribucionRepository  extends JpaRepository<RelacionUsuarioContribucion, Integer> {

	List<RelacionUsuarioContribucion> findByUsuario(Usuario usuario);
	List<RelacionUsuarioContribucion> findByContribucion(Contribucion contribucion);
	// Relaciones por usuario
	@Query(value = "select * from usuario_contribucion where fkUsuario in (select id from usuarios where nombre=?1)", nativeQuery = true)
	List<RelacionUsuarioContribucion> findRelacionesUsuarioContribucionByNombreUsuario(String nombreUsuario);
	// Relaciones por contribucion
	@Query(value = "select * from usuario_contribucion where fkContribucion in (select id from contribuciones where nombre like %?1%)", nativeQuery = true)
	List<RelacionUsuarioContribucion> findRelacionesUsuarioContribucionByNombreContribucion(String nombreContribucion);
}