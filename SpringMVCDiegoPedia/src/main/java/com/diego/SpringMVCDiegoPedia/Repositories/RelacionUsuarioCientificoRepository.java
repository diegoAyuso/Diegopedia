package com.diego.SpringMVCDiegoPedia.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.diego.SpringMVCDiegoPedia.Models.Cientifico;
import com.diego.SpringMVCDiegoPedia.Models.RelacionUsuarioCientifico;
import com.diego.SpringMVCDiegoPedia.Models.Usuario;


public interface RelacionUsuarioCientificoRepository extends JpaRepository<RelacionUsuarioCientifico, Integer> {
	List<RelacionUsuarioCientifico> findByUsuario(Usuario usuario);

	List<RelacionUsuarioCientifico> findByCientifico(Cientifico cientifico);
	// Relaciones por usuario
	@Query(value = "select * from usuario_cientifico where fkUsuario in (select id from usuarios where nombre =?1)", nativeQuery = true)
	List<RelacionUsuarioCientifico> findRelacionesUsuarioCientificoByNombreUsuario(String nombreUsuario);
	// Relaciones por contribucion
	@Query(value = "select * from usuario_cientifico where fkCientifico in (select id from cientificos where apellidos like %?1%)", nativeQuery = true)
	List<RelacionUsuarioCientifico> findRelacionesUsuarioCientificoByApellidosCientifico(String apellidosCientifico);
}