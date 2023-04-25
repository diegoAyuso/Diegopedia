package com.diego.ApiRestDiegoPedia.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.diego.ApiRestDiegoPedia.Models.Cientifico;
import com.diego.ApiRestDiegoPedia.Models.Contribucion;
import com.diego.ApiRestDiegoPedia.Models.RelacionCientificoContribucion;

public interface RelacionCientificoContribucionRepository
		extends JpaRepository<RelacionCientificoContribucion, Integer> {
	List<RelacionCientificoContribucion> findByContribucion(Contribucion contribucion);

	List<RelacionCientificoContribucion> findByCientifico(Cientifico cientifico);

	List<RelacionCientificoContribucion> findByEpocaContaining(String epocaCoincidencia);

	List<RelacionCientificoContribucion> findByAportacionContaining(String aportacionCoincidencia);

	@Query(value = "select aportacion from cientifico_contribucion group by aportacion", nativeQuery = true)
	List<String> findAportacionesRelacion();
	
	/*
	 * Búsquedas personalizadas por campo de entidad propia con entidades
	 * relacionadas
	 */
	// Relaciones por nombreContribucion
	@Query(value = "select * from cientifico_contribucion where fkContribucion in (select id from contribuciones where nombre like %?1%)", nativeQuery = true)
	List<RelacionCientificoContribucion> findRelacionesCientificoContribucionByNombreContribucion(String nombreContribucion);

	// Relaciones por apellidosCientifico
	@Query(value = "select * from cientifico_contribucion where fkCientifico in (select id from cientificos where apellidos like %?1%)", nativeQuery = true)
	List<RelacionCientificoContribucion> findRelacionesCientificoContribucionByApellidosCientifico(String apellidosCientifico);

	// Relaciones por epoca
	@Query(value = "select * from cientifico_contribucion where epoca like %?1%", nativeQuery = true)
	List<RelacionCientificoContribucion> findRelacionesCientificoContribucionByEpoca(String epoca);

	// Relaciones por aportacion
	@Query(value = "select * from cientifico_contribucion where aportacion like %?1%", nativeQuery = true)
	List<RelacionCientificoContribucion> findRelacionesCientificoContribucionByAportacion(String aportacion);

	
}