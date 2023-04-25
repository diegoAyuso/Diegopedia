package com.diego.ApiRestDiegoPedia.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diego.ApiRestDiegoPedia.Models.Cientifico;
import com.diego.ApiRestDiegoPedia.Models.Contribucion;
import com.diego.ApiRestDiegoPedia.Repositories.CientificoRepository;
import com.diego.ApiRestDiegoPedia.Repositories.ContribucionRepository;

@CrossOrigin
@RestController
@RequestMapping("/diegopedia")
public class CientificoController {
	@Autowired
	CientificoRepository cientificoRepository;
	@Autowired
	ContribucionRepository contribucionRepository;

	@GetMapping("/cientificos")
	public ResponseEntity<List<Cientifico>> getAll() {
		try {
			List<Cientifico> cientificos = cientificoRepository.findAll();
			if (cientificos.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(cientificos, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/cientificos/{id}")
	public ResponseEntity<Cientifico> getById(@PathVariable("id") int id) {
		Optional<Cientifico> cientifico = cientificoRepository.findById(id);
		if (cientifico.isPresent()) {
			return new ResponseEntity<>(cientifico.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/cientificos")
	public ResponseEntity<Cientifico> add(@RequestBody Cientifico cientifico) {
		try {
			if (!cientifico.getFechaNacimiento().matches("^([0-2][0-9]|3[0-1])(/)(0[1-9]|1[0-2])\\2(\\d{4})$")
					|| (cientifico.getFechaDefuncion() != null && !cientifico.getFechaDefuncion()
							.matches("^([0-2][0-9]|3[0-1])(/)(0[1-9]|1[0-2])\\2(\\d{4})$"))) {
				return new ResponseEntity<>(HttpStatus.CONFLICT);
			}
			if (!cientifico.getNombre().matches("^[a-zA-Z\\s\\-ÁÉÍÓÚáéíóúÀÈÌÒÙàèìòÄËÏÖÜäëïöüÂÊÎÔÛâêîôûÑñ]+$")
					|| !cientifico.getApellidos().matches("^[a-zA-Z\\s\\-ÁÉÍÓÚáéíóúÀÈÌÒÙàèìòÄËÏÖÜäëïöüÂÊÎÔÛâêîôûÑñ]+$")
					|| !cientifico.getNacionalidad()
							.matches("^[a-zA-Z\\s\\-ÁÉÍÓÚáéíóúÀÈÌÒÙàèìòÄËÏÖÜäëïöüÂÊÎÔÛâêîôûÑñ]+$")) {
				return new ResponseEntity<>(HttpStatus.CONFLICT);
			}
			Cientifico cientificoNuevo = cientifico;
			cientificoNuevo.setId(0);
			cientificoNuevo = cientificoRepository.save(cientificoNuevo);
			return new ResponseEntity<>(cientificoNuevo, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/cientificos/{id}")
	public ResponseEntity<Cientifico> update(@PathVariable("id") int id, @RequestBody Cientifico cientifico) {
		try {
			if (!cientifico.getFechaNacimiento().matches("^([0-2][0-9]|3[0-1])(/)(0[1-9]|1[0-2])\\2(\\d{4})$")
					|| (cientifico.getFechaDefuncion() != null && !cientifico.getFechaDefuncion()
							.matches("^([0-2][0-9]|3[0-1])(/)(0[1-9]|1[0-2])\\2(\\d{4})$"))) {
				return new ResponseEntity<>(HttpStatus.CONFLICT);
			}
			if (!cientifico.getNombre().matches("^[a-zA-Z\\s\\-ÁÉÍÓÚáéíóúÀÈÌÒÙàèìòÄËÏÖÜäëïöüÂÊÎÔÛâêîôûÑñ]+$")
					|| !cientifico.getApellidos().matches("^[a-zA-Z\\s\\-ÁÉÍÓÚáéíóúÀÈÌÒÙàèìòÄËÏÖÜäëïöüÂÊÎÔÛâêîôûÑñ]+$")
					|| !cientifico.getNacionalidad()
							.matches("^[a-zA-Z\\s\\-ÁÉÍÓÚáéíóúÀÈÌÒÙàèìòÄËÏÖÜäëïöüÂÊÎÔÛâêîôûÑñ]+$")) {
				return new ResponseEntity<>(HttpStatus.CONFLICT);
			}
			Optional<Cientifico> cientificoNuevo = cientificoRepository.findById(id);
			if (cientificoNuevo.isPresent()) {
				Cientifico updateCientifico = cientificoNuevo.get().setCientifico(cientifico);
				updateCientifico = cientificoRepository.save(updateCientifico);
				return new ResponseEntity<>(updateCientifico, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/cientificos/{id}")
	public ResponseEntity<Integer> delete(@PathVariable("id") int id) {
		try {
			cientificoRepository.deleteById(id);
			return new ResponseEntity<>(id,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(500,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/cientificos/{idCientifico}/contribuciones")
	public ResponseEntity<List<Contribucion>> getAllContribucionesByIdCientifico(
			@PathVariable(value = "idCientifico") int idCientifico) {
		try {
			Optional<List<Contribucion>> contribuciones = Optional
					.of(contribucionRepository.findContribucionesByCientificosId(idCientifico));
			if (contribuciones.isPresent()) {
				return new ResponseEntity<>(contribuciones.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);

			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/* Búsqueda de científicos relacionados o sin relacionar */
	// Cientificos con nombre: nombreCientifico
	@GetMapping("/cientificos/busquedaPropia/nombre/{nombreCientifico}")
	public ResponseEntity<List<Cientifico>> getAllCientificosByNombre(
			@PathVariable(value = "nombreCientifico") String nombreCientifico) {
		try {
			Optional<List<Cientifico>> cientificos = Optional
					.of(cientificoRepository.findByNombreContaining(nombreCientifico));
			if (cientificos.isPresent()) {
				return new ResponseEntity<>(cientificos.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);

			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Cientificos con apellidos: apellidosCientifico
	@GetMapping("/cientificos/busquedaPropia/apellidos/{apellidosCientifico}")
	public ResponseEntity<List<Cientifico>> getAllCientificosByApellidos(
			@PathVariable(value = "apellidosCientifico") String apellidosCientifico) {
		try {
			Optional<List<Cientifico>> cientificos = Optional
					.of(cientificoRepository.findByApellidosContaining(apellidosCientifico));
			if (cientificos.isPresent()) {
				return new ResponseEntity<>(cientificos.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Cientificos con nacionalidad: nacionalidadCientifico
	@GetMapping("/cientificos/busquedaPropia/nacionalidad/{nacionalidadCientifico}")
	public ResponseEntity<List<Cientifico>> getAllCientificosByNacionalidad(
			@PathVariable(value = "nacionalidadCientifico") String nacionalidadCientifico) {
		try {
			Optional<List<Cientifico>> cientificos = Optional
					.of(cientificoRepository.findByNacionalidadContaining(nacionalidadCientifico));
			if (cientificos.isPresent()) {
				return new ResponseEntity<>(cientificos.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);

			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Cientificos con fechaNacimiento: fechaNacimiento
	@GetMapping("/cientificos/busquedaPropia/fechaNacimiento/{fechaNacimientoCientifico}")
	public ResponseEntity<List<Cientifico>> getAllCientificosByFechaNacimiento(
			@PathVariable(value = "fechaNacimientoCientifico") String fechaNacimientoCientifico) {
		try {
			Optional<List<Cientifico>> cientificos = Optional
					.of(cientificoRepository.findByFechaNacimientoContaining(fechaNacimientoCientifico));
			if (cientificos.isPresent()) {
				return new ResponseEntity<>(cientificos.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);

			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Cientificos con fechaDefuncion: fechaDefuncion
	@GetMapping("/cientificos/busquedaPropia//fechaDefuncion/{fechaDefuncionCientifico}")
	public ResponseEntity<List<Cientifico>> getAllCientificosByFechaDefuncion(
			@PathVariable(value = "fechaDefuncionCientifico") String fechaDefuncionCientifico) {
		try {
			Optional<List<Cientifico>> cientificos = Optional
					.of(cientificoRepository.findByFechaDefuncionContaining(fechaDefuncionCientifico));
			if (cientificos.isPresent()) {
				return new ResponseEntity<>(cientificos.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);

			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Cientificos vivos
	@GetMapping("/cientificos/busquedaPropia/vivos")
	public ResponseEntity<List<Cientifico>> getAllCientificosByFechaDefuncionNull(
			@PathVariable(value = "fechaDefuncionCientifico") String fechaDefuncionCientifico) {
		try {
			Optional<List<Cientifico>> cientificos = Optional.of(cientificoRepository.findByFechaDefuncionNull());
			if (cientificos.isPresent()) {
				return new ResponseEntity<>(cientificos.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);

			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Cientificos con enlaceMasInformacion: enlaceMasInformacionCientifico
	@GetMapping("/cientificos/busquedaPropia/enlaceMasInformacion/{enlaceMasInformacionCientifico}")
	public ResponseEntity<List<Cientifico>> getAllCientificosByEnlaceMasInformacion(
			@PathVariable(value = "enlaceMasInformacionCientifico") String enlaceMasInformacionCientifico) {
		try {
			Optional<List<Cientifico>> cientificos = Optional
					.of(cientificoRepository.findByEnlaceMasInformacionContaining(enlaceMasInformacionCientifico));
			if (cientificos.isPresent()) {
				return new ResponseEntity<>(cientificos.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);

			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Cientificos con enlaceFoto: enlaceFotoCientifico
	@GetMapping("/cientificos/busquedaPropia/enlaceFoto/{enlaceFotoCientifico}")
	public ResponseEntity<List<Cientifico>> getAllCientificosByEnlaceFoto(
			@PathVariable(value = "enlaceFotoCientifico") String enlaceFotoCientifico) {
		try {
			Optional<List<Cientifico>> cientificos = Optional
					.of(cientificoRepository.findByEnlaceFotoContaining(enlaceFotoCientifico));
			if (cientificos.isPresent()) {
				return new ResponseEntity<>(cientificos.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);

			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/*
	 * Búsquedas personalizadas por campo de entidad ajena con entidades
	 * relacionadas
	 */

	// Cientificos que contribuyeron a la contribución con nombre:
	// nombreContribucion
	@GetMapping("/cientificos/busquedaAjena/nombre/{nombreContribucion}")
	public ResponseEntity<List<Cientifico>> getAllCientificosByNombreContribucionCientificoRelacionado(
			@PathVariable(value = "nombreContribucion") String nombreContribucion) {
		try {
			Optional<List<Cientifico>> cientificos = Optional
					.of(cientificoRepository.findCientificosByNombreContribucion(nombreContribucion));
			if (cientificos.isPresent()) {
				return new ResponseEntity<>(cientificos.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);

			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Cientificos que contribuyeron a la contribución con campo: campoContribucion
	@GetMapping("/cientificos/busquedaAjena/campo/{campoContribucion}")
	public ResponseEntity<List<Cientifico>> getAllCientificosByCampoContribucionCientificoRelacionado(
			@PathVariable(value = "campoContribucion") String campoContribucion) {
		try {
			Optional<List<Cientifico>> cientificos = Optional
					.of(cientificoRepository.findCientificosByCampoContribucion(campoContribucion));
			if (cientificos.isPresent()) {
				return new ResponseEntity<>(cientificos.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);

			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Cientificos que contribuyeron a la contribución con rama: ramaContribucion
	@GetMapping("/cientificos/busquedaAjena/rama/{ramaContribucion}")
	public ResponseEntity<List<Cientifico>> getAllCientificosByRamaContribucionCientificoRelacionado(
			@PathVariable(value = "ramaContribucion") String ramaContribucion) {
		try {
			Optional<List<Cientifico>> cientificos = Optional
					.of(cientificoRepository.findCientificosByRamaContribucion(ramaContribucion));
			if (cientificos.isPresent()) {
				return new ResponseEntity<>(cientificos.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Cientificos que contribuyeron a la contribución con campo:
	// enlaceMasInformacion
	@GetMapping("/cientificos/busquedaAjena/enlaceMasInformacion/{enlaceMasInformacion}")
	public ResponseEntity<List<Cientifico>> getAllCientificosByEnlaceMasInformacionContribucionCientificoRelacionado(
			@PathVariable(value = "enlaceMasInformacion") String enlaceMasInformacion) {
		try {
			Optional<List<Cientifico>> cientificos = Optional
					.of(cientificoRepository.findCientificosByEnlaceMasInformacionContribucion(enlaceMasInformacion));
			if (cientificos.isPresent()) {
				return new ResponseEntity<>(cientificos.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);

			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/*
	 * Búsquedas personalizadas por campo de entidad propia con entidades
	 * relacionadas
	 */
	// Cientificos que contribuyeron a una o varias contribuciones con nombre:
	// nombreCientifico
	@GetMapping("cientificos/busquedaPropiaRelacionada/nombre/{nombreCientifico}")
	public ResponseEntity<List<Cientifico>> getAllCientificosByNombreCientificoRelacionado(
			@PathVariable(value = "nombreCientifico") String nombreCientifico) {
		try {
			Optional<List<Cientifico>> cientificos = Optional
					.of(cientificoRepository.findCientificosByNombreCientificoRelacionado(nombreCientifico));
			if (cientificos.isPresent()) {
				return new ResponseEntity<>(cientificos.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);

			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Cientificos que contribuyeron a una o varias contribuciones con apellidos:
	// apellidosCientifico
	@GetMapping("/cientificos/busquedaPropiaRelacionada/apellidos/{apellidosCientifico}")
	public ResponseEntity<List<Cientifico>> getAllCientificosByApellidosCientificoRelacionado(
			@PathVariable(value = "apellidosCientifico") String apellidosCientifico) {
		try {
			Optional<List<Cientifico>> cientificos = Optional
					.of(cientificoRepository.findCientificosByApellidosCientificoRelacionado(apellidosCientifico));
			if (cientificos.isPresent()) {
				return new ResponseEntity<>(cientificos.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);

			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Cientificos que contribuyeron a una o varias contribuciones con nacionalidad:
	// nacionalidadCientifico
	@GetMapping("/cientificos/busquedaPropiaRelacionada/nacionalidad/{nacionalidadCientifico}")
	public ResponseEntity<List<Cientifico>> getAllCientificosByNacionalidadCientificoRelacionado(
			@PathVariable(value = "nacionalidadCientifico") String nacionalidadCientifico) {
		try {
			Optional<List<Cientifico>> cientificos = Optional.of(
					cientificoRepository.findCientificosByNacionalidadCientificoRelacionado(nacionalidadCientifico));
			if (cientificos.isPresent()) {
				return new ResponseEntity<>(cientificos.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);

			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Cientificos que contribuyeron a una o varias contribuciones con
	// fechaNacimiento: fechaNacimiento
	@GetMapping("/cientificos/busquedaPropiaRelacionada/fechaNacimiento/{fechaNacimientoCientifico}")
	public ResponseEntity<List<Cientifico>> getAllCientificosByFechaNacimientoCientificoRelacionado(
			@PathVariable(value = "fechaNacimientoCientifico") String fechaNacimientoCientifico) {
		try {
			Optional<List<Cientifico>> cientificos = Optional.of(cientificoRepository
					.findCientificosByFechaNacimientoCientificoRelacionado(fechaNacimientoCientifico));
			if (cientificos.isPresent()) {
				return new ResponseEntity<>(cientificos.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);

			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Cientificos que contribuyeron a una o varias contribuciones con
	// fechaDefuncion: fechaDefuncion
	@GetMapping("/cientificos/busquedaPropiaRelacionada/fechaDefuncion/{fechaDefuncionCientifico}")
	public ResponseEntity<List<Cientifico>> getAllCientificosByFechaDefuncionCientificoRelacionado(
			@PathVariable(value = "fechaDefuncionCientifico") String fechaDefuncionCientifico) {
		try {
			Optional<List<Cientifico>> cientificos = Optional.of(cientificoRepository
					.findCientificosByFechaDefuncionCientificoRelacionado(fechaDefuncionCientifico));
			if (cientificos.isPresent()) {
				return new ResponseEntity<>(cientificos.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);

			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Cientificos que contribuyeron a una o varias contribuciones vivos
	@GetMapping("/cientificos/busquedaPropiaRelacionada/vivos")
	public ResponseEntity<List<Cientifico>> getAllCientificosByFechaDefuncionCientificoRelacionado() {
		try {
			Optional<List<Cientifico>> cientificos = Optional
					.of(cientificoRepository.findCientificosByFechaDefuncionNullCientificoRelacionado());
			if (cientificos.isPresent()) {
				return new ResponseEntity<>(cientificos.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);

			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Cientificos que contribuyeron a una o varias contribuciones con
	// enlaceMasInformacion: enlaceMasInformacionCientifico
	@GetMapping("/cientificos/busquedaPropiaRelacionada/enlaceMasInformacion/{enlaceMasInformacionCientifico}")
	public ResponseEntity<List<Cientifico>> getAllCientificosByEnlaceMasInformacionCientificoRelacionado(
			@PathVariable(value = "enlaceMasInformacionCientifico") String enlaceMasInformacionCientifico) {
		try {
			Optional<List<Cientifico>> cientificos = Optional.of(cientificoRepository
					.findCientificosByEnlaceMasInformacionCientificoRelacionado(enlaceMasInformacionCientifico));
			if (cientificos.isPresent()) {
				return new ResponseEntity<>(cientificos.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);

			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Cientificos que contribuyeron a una o varias contribuciones con enlaceFoto:
	// enlaceFotoCientifico
	@GetMapping("/cientificos/busquedaPropiaRelacionada/enlaceFoto/{enlaceFotoCientifico}")
	public ResponseEntity<List<Cientifico>> getAllCientificosByEnlaceFotoCientificoRelacionado(
			@PathVariable(value = "enlaceFotoCientifico") String enlaceFotoCientifico) {
		try {
			Optional<List<Cientifico>> cientificos = Optional
					.of(cientificoRepository.findCientificosByEnlaceFotoCientificoRelacionado(enlaceFotoCientifico));
			if (cientificos.isPresent()) {
				return new ResponseEntity<>(cientificos.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);

			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
