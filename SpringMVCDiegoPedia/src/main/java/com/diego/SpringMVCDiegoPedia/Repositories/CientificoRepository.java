package com.diego.SpringMVCDiegoPedia.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.diego.SpringMVCDiegoPedia.Models.Cientifico;
import com.diego.SpringMVCDiegoPedia.Models.Contribucion;

public interface CientificoRepository extends JpaRepository<Cientifico, Integer> {
	List<Cientifico> findByNombreContaining(String nombreCoincidencia);

	List<Cientifico> findByApellidosContaining(String apellidosCoincidencia);

	List<Cientifico> findByNacionalidadContaining(String nacionalidadCoincidencia);

	List<Cientifico> findByFechaNacimientoContaining(String fechaNacimientoCoincidencia);

	List<Cientifico> findByFechaDefuncionContaining(String fechaDefuncionCoincidencia);

	List<Cientifico> findByEnlaceMasInformacionContaining(String enlaceMasInformacionCoincidencia);

	List<Cientifico> findByEnlaceFotoContaining(String enlaceFotoCoincidencia);

	@Query(value = "select nacionalidad from cientificos group by nacionalidad", nativeQuery = true)
	List<String> findNacionalidadesCientificos();

	/*
	 * Búsquedas personalizadas por campo de entidad ajena con entidades
	 * relacionadas
	 */
	// Cientificos que contribuyeron a la contribución con nombre:
	// nombreContribucion
	@Query(value = "select * from cientificos where id in (select fkCientifico from cientifico_contribucion where fkContribucion in (select id from contribuciones where nombre like %?1%))", nativeQuery = true)
	List<Cientifico> findCientificosByNombreContribucion(String nombreContribucion);

	// Cientificos que contribuyeron a la contribución con campo: campoContribucion
	@Query(value = "select * from cientificos where id in (select fkCientifico from cientifico_contribucion where fkContribucion in (select id from contribuciones where campo like %?1%))", nativeQuery = true)
	List<Cientifico> findCientificosByCampoContribucion(String campoContribucion);

	// Cientificos que contribuyeron a la contribución con rama: ramaContribucion
	@Query(value = "select * from cientificos where id in (select fkCientifico from cientifico_contribucion where fkContribucion in (select id from contribuciones where rama like %?1%))", nativeQuery = true)
	List<Cientifico> findCientificosByRamaContribucion(String ramaContribucion);

	// Cientificos que contribuyeron a la contribución con nombre:
	// enlaceMasInformacion
	@Query(value = "select * from cientificos where id in (select fkCientifico from cientifico_contribucion where fkContribucion in (select id from contribuciones where enlaceMasInformacion like %?1%))", nativeQuery = true)
	List<Cientifico> findCientificosByEnlaceMasInformacionContribucion(String enlaceMasInformacion);

	/*
	 * Búsquedas personalizadas por campo de entidad propia con entidades
	 * relacionadas
	 */
	// Cientificos que contribuyeron a una o varias contribuciones con nombre:
	// nombreCientifico
	@Query(value = "select * from cientificos where id in (select fkCientifico from cientifico_contribucion)and nombre like %?1%", nativeQuery = true)
	List<Cientifico> findCientificosByNombreCientificoRelacionado(String nombreCientifico);

	// Cientificos que contribuyeron a una o varias contribuciones con apellidos:
	// apellidosCientifico
	@Query(value = "select * from cientificos where id in (select fkCientifico from cientifico_contribucion)and apellidos like %?1%", nativeQuery = true)
	List<Cientifico> findCientificosByApellidosCientificoRelacionado(String apellidosCientifico);

	// Cientificos que contribuyeron a una o varias contribuciones con nacionalidad:
	// nacionalidadCientifico
	@Query(value = "select * from cientificos where id in (select fkCientifico from cientifico_contribucion)and nacionalidad like %?1%", nativeQuery = true)
	List<Cientifico> findCientificosByNacionalidadCientificoRelacionado(String nacionalidadCientifico);

	// Cientificos que contribuyeron a una o varias contribuciones con
	// fechaNacimiento: fechaNacimientoCientifico
	@Query(value = "select * from cientificos where id in (select fkCientifico from cientifico_contribucion)and fechaNacimiento like %?1%", nativeQuery = true)
	List<Cientifico> findCientificosByFechaNacimientoCientificoRelacionado(String fechaNacimientoCientifico);

	// Cientificos que contribuyeron a una o varias contribuciones con
	// fechaDefuncion: fechaDefuncionCientifico
	@Query(value = "select * from cientificos where id in (select fkCientifico from cientifico_contribucion)and fechaDefuncion like %?1%", nativeQuery = true)
	List<Cientifico> findCientificosByFechaDefuncionCientificoRelacionado(String fechaDefuncionCientifico);

	// Cientificos que contribuyeron a una o varias contribuciones con
	// enlaceMasInformacion: enlaceMasInformacionCientifico
	@Query(value = "select * from cientificos where id in (select fkCientifico from cientifico_contribucion)and enlaceMasInformacion like %?1%", nativeQuery = true)
	List<Cientifico> findCientificosByEnlaceMasInformacionCientificoRelacionado(String enlaceMasInformacionCientifico);

	// Cientificos que contribuyeron a una o varias contribuciones con enlaceFoto:
	// enlaceFotoCientifico
	@Query(value = "select * from cientificos where id in (select fkCientifico from cientifico_contribucion)and enlaceFoto like %?1%", nativeQuery = true)
	List<Cientifico> findCientificosByEnlaceFotoCientificoRelacionado(String enlaceFotoCientifico);

}
