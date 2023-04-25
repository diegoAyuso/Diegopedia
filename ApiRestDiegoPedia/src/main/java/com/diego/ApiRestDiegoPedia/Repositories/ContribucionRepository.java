package com.diego.ApiRestDiegoPedia.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.diego.ApiRestDiegoPedia.Models.Contribucion;

public interface ContribucionRepository extends JpaRepository<Contribucion, Integer> {
	List<Contribucion> findByNombreContaining(String nombreCoincidencia);

	List<Contribucion> findByCampoContaining(String campoCoincidencia);

	List<Contribucion> findByRamaContaining(String ramaCoincidencia);

	List<Contribucion> findByEnlaceMasInformacionContaining(String enlaceMasInformacion);

	@Query(value = "select * from contribuciones where id in (select fkContribucion from cientifico_contribucion where fkCientifico=?1)", nativeQuery = true)
	List<Contribucion> findContribucionesByCientificosId(int CientificoId);

	@Query(value = "select campo from contribuciones group by campo", nativeQuery = true)
	List<String> findCamposContribuciones();

	@Query(value = "select rama from contribuciones group by rama", nativeQuery = true)
	List<String> findRamasContribuciones();

	/*
	 * Búsquedas personalizadas por campo de entidad ajena con entidades
	 * relacionadas
	 */
	// Contribuciones del científico con nombre: nombreCientifico
	@Query(value = "select * from contribuciones where id in (select fkContribucion from cientifico_contribucion where fkCientifico in (select id from cientificos where nombre like %?1%))", nativeQuery = true)
	List<Contribucion> findContribucionesByNombreCientifico(String nombreCientifico);

	// Contribuciones del científico con apellidos: apellidosCientifico
	@Query(value = "select * from contribuciones where id in (select fkContribucion from cientifico_contribucion where fkCientifico in (select id from cientificos where apellidos like %?1%))", nativeQuery = true)
	List<Contribucion> findContribucionesByApellidosCientifico(String apellidosCientifico);

	// Contribuciones del científico con nacionalidad: nacionalidadCientifico
	@Query(value = "select * from contribuciones where id in (select fkContribucion from cientifico_contribucion where fkCientifico in (select id from cientificos where nacionalidad like %?1%))", nativeQuery = true)
	List<Contribucion> findContribucionesByNacionalidadCientifico(String nacionalidadCientifico);

	// Contribuciones del científico con fechaNacimiento: fechaNacimientoCientifico
	@Query(value = "select * from contribuciones where id in (select fkContribucion from cientifico_contribucion where fkCientifico in (select id from cientificos where fechaNacimiento like %?1%))", nativeQuery = true)
	List<Contribucion> findContribucionesByFechaNacimientoCientifico(String fechaNacimientoCientifico);

	// Contribuciones del científico con fechaDefuncion: fechaDefuncionCientifico
	@Query(value = "select * from contribuciones where id in (select fkContribucion from cientifico_contribucion where fkCientifico in (select id from cientificos where fechaDefuncion like %?1%))", nativeQuery = true)
	List<Contribucion> findContribucionesByFechaDefuncionCientifico(String fechaDefuncionCientifico);

	// Contribuciones de los científicos vivos
	@Query(value = "select * from contribuciones where id in (select fkContribucion from cientifico_contribucion where fkCientifico in (select id from cientificos where fechaDefuncion=null))", nativeQuery = true)
	List<Contribucion> findContribucionesByFechaDefuncionCientificoNull();

	// Contribuciones del científico con enlaceMasInformacion: enlaceMasInformacion
	@Query(value = "select * from contribuciones where id in (select fkContribucion from cientifico_contribucion where fkCientifico in (select id from cientificos where enlaceMasInformacion like %?1%))", nativeQuery = true)
	List<Contribucion> findContribucionesByEnlaceMasInformacionCientifico(String enlaceMasInformacion);

	// Contribuciones del científico con enlaceFoto: enlaceFotoCientifico
	@Query(value = "select * from contribuciones where id in (select fkContribucion from cientifico_contribucion where fkCientifico in (select id from cientificos where enlaceFoto like %?1%))", nativeQuery = true)
	List<Contribucion> findContribucionesByEnlaceFotoCientifico(String enlaceFotoCientifico);

	/*
	 * Búsquedas personalizadas por campo de entidad propia con entidades
	 * relacionadas
	 */

	// Contribuciones de uno o varios cientificos con nombre: nombreContribucion
	@Query(value = "select * from contribuciones where id in (select fkContribucion from cientifico_contribucion) and nombre like %?1%", nativeQuery = true)
	List<Contribucion> findContribucionesByNombreContribucionRelacionada(String nombreContribucion);

	// Contribuciones de uno o varios cientificos con campo: campoContribucion
	@Query(value = "select * from contribuciones where id in (select fkContribucion from cientifico_contribucion)and campo like %?1%", nativeQuery = true)
	List<Contribucion> findContribucionesByCampoContribucionRelacionada(String campoContribucion);

	// Contribuciones de uno o varios cientificos con nombre: ramaContribucion
	@Query(value = "select * from contribuciones where id in (select fkContribucion from cientifico_contribucion)and rama like %?1%", nativeQuery = true)
	List<Contribucion> findContribucionesByRamaContribucionRelacionada(String ramaContribucion);

	// Contribuciones de uno o varios cientificos con nombre:
	// enlaceMasInformacionContribucion
	@Query(value = "select * from contribuciones where id in (select fkContribucion from cientifico_contribucion)and enlaceMasInformacion like %?1%", nativeQuery = true)
	List<Contribucion> findContribucionesByEnlaceMasInformacionContribucionRelacionada(
			String enlaceMasInformacionContribucion);
	// Contribuciones del científico con fechaDefuncion: fechaDefuncionCientifico
}
