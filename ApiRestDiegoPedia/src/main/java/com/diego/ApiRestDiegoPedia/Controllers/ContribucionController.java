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
public class ContribucionController {
	@Autowired
	ContribucionRepository contribucionRepository;
	@Autowired
	CientificoRepository cientificoRepository;

	@GetMapping("/contribuciones")
	public ResponseEntity<List<Contribucion>> getAll() {
		try {
			List<Contribucion> contribuciones = contribucionRepository.findAll();
			if (contribuciones.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(contribuciones, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/contribuciones/{id}")
	public ResponseEntity<Contribucion> getById(@PathVariable("id") int id) {
		Optional<Contribucion> contribucion = contribucionRepository.findById(id);
		if (contribucion.isPresent()) {
			return new ResponseEntity<>(contribucion.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/contribuciones")
	public ResponseEntity<Contribucion> create(@RequestBody Contribucion contribucion) {
		try {
			if (!contribucion.getNombre().matches("^[a-zA-Z\\s\\-ÁÉÍÓÚáéíóúÀÈÌÒÙàèìòÄËÏÖÜäëïöüÂÊÎÔÛâêîôûÑñ]+$")
					|| !contribucion.getCampo().matches("^[a-zA-Z\\s\\-ÁÉÍÓÚáéíóúÀÈÌÒÙàèìòÄËÏÖÜäëïöüÂÊÎÔÛâêîôûÑñ]+$")
					|| !contribucion.getRama().matches("^[a-zA-Z\\s\\-ÁÉÍÓÚáéíóúÀÈÌÒÙàèìòÄËÏÖÜäëïöüÂÊÎÔÛâêîôûÑñ]+$")) {
				return new ResponseEntity<>(HttpStatus.CONFLICT);
			}
			;
			Contribucion contribucionNueva = contribucion;
			contribucionNueva.setId(0);
			contribucionNueva = contribucionRepository.save(contribucionNueva);
			return new ResponseEntity<>(contribucionNueva, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/contribuciones/{id}")
	public ResponseEntity<Contribucion> update(@PathVariable("id") int id, @RequestBody Contribucion contribucion) {
		try {
			if (!contribucion.getNombre().matches("^[a-zA-Z\\s\\-ÁÉÍÓÚáéíóúÀÈÌÒÙàèìòÄËÏÖÜäëïöüÂÊÎÔÛâêîôûÑñ]+$")
					|| !contribucion.getCampo().matches("^[a-zA-Z\\s\\-ÁÉÍÓÚáéíóúÀÈÌÒÙàèìòÄËÏÖÜäëïöüÂÊÎÔÛâêîôûÑñ]+$")
					|| !contribucion.getRama().matches("^[a-zA-Z\\s\\-ÁÉÍÓÚáéíóúÀÈÌÒÙàèìòÄËÏÖÜäëïöüÂÊÎÔÛâêîôûÑñ]+$")) {
				return new ResponseEntity<>(HttpStatus.CONFLICT);
			}
			Optional<Contribucion> contribuciónNueva = contribucionRepository.findById(id);
			if (contribuciónNueva.isPresent()) {
				Contribucion updateContribucion = contribuciónNueva.get().setContribucion(contribucion);
				updateContribucion = contribucionRepository.save(updateContribucion);
				return new ResponseEntity<>(updateContribucion, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/contribuciones/{id}")
	public ResponseEntity<Integer> delete(@PathVariable("id") int id) {
		try {
			contribucionRepository.deleteById(id);
			return new ResponseEntity<>(id,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(500,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/contribuciones/{idContribucion}/cientificos")
	public ResponseEntity<List<Cientifico>> getAllTagsByTutorialId(
			@PathVariable(value = "idContribucion") Integer idContribucion) {
		try {
			Optional<List<Cientifico>> cientificos = Optional
					.of(cientificoRepository.findCientificosByContribucionesId(idContribucion));
			if (cientificos.isPresent()) {
				return new ResponseEntity<>(cientificos.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);

			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/* Búsqueda de contribuciones relacionadas o sin relacionar */
	// Contribuciones con nombre: nombreContribucion
	@GetMapping("/contribuciones/busquedaPropia/nombre/{nombreContribucion}")
	public ResponseEntity<List<Contribucion>> getAllContribucionesByNombre(
			@PathVariable(value = "nombreContribucion") String nombreContribucion) {
		try {
			Optional<List<Contribucion>> contribuciones = Optional
					.of(contribucionRepository.findByNombreContaining(nombreContribucion));
			if (contribuciones.isPresent()) {
				return new ResponseEntity<>(contribuciones.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);

			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Contribuciones con campo: campoContribucion
	@GetMapping("/contribuciones/busquedaPropia/campo/{campoContribucion}")
	public ResponseEntity<List<Contribucion>> getAllContribucionesByCampo(
			@PathVariable(value = "campoContribucion") String campoContribucion) {
		try {
			Optional<List<Contribucion>> contribuciones = Optional
					.of(contribucionRepository.findByCampoContaining(campoContribucion));
			if (contribuciones.isPresent()) {
				return new ResponseEntity<>(contribuciones.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);

			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Contribuciones con rama: ramaContribucion
	@GetMapping("/contribuciones/busquedaPropia/rama/{ramaContribucion}")
	public ResponseEntity<List<Contribucion>> getAllContribucionesByRama(
			@PathVariable(value = "ramaContribucion") String ramaContribucion) {
		try {
			Optional<List<Contribucion>> contribuciones = Optional
					.of(contribucionRepository.findByRamaContaining(ramaContribucion));
			if (contribuciones.isPresent()) {
				return new ResponseEntity<>(contribuciones.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);

			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Contribuciones con enlaceMasInformacion: enlaceMasInformacion
	@GetMapping("/contribuciones/busquedaPropia/enlaceMasInformacion/{enlaceMasInformacionContribucion}")
	public ResponseEntity<List<Contribucion>> getAllContribucionesByEnlaceMasInformacion(
			@PathVariable(value = "enlaceMasInformacionContribucion") String enlaceMasInformacionContribucion) {
		try {
			Optional<List<Contribucion>> contribuciones = Optional
					.of(contribucionRepository.findByEnlaceMasInformacionContaining(enlaceMasInformacionContribucion));
			if (contribuciones.isPresent()) {
				return new ResponseEntity<>(contribuciones.get(), HttpStatus.OK);
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

	// Contribuciones relacionadas por cientificos con campo nombre:
	// nombreCientifico
	@GetMapping("/contribuciones/busquedaAjena/nombre/{nombreCientifico}")
	public ResponseEntity<List<Contribucion>> getAllCientificosByNombreCientificoRelacionado(
			@PathVariable(value = "nombreCientifico") String nombreCientifico) {
		try {
			Optional<List<Contribucion>> contribuciones = Optional
					.of(contribucionRepository.findContribucionesByNombreCientifico(nombreCientifico));
			if (contribuciones.isPresent()) {
				return new ResponseEntity<>(contribuciones.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);

			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Contribuciones relacionadas por cientificos con campo apellidos:
	// apellidosCientifico
	@GetMapping("/contribuciones/busquedaAjena/apellidos/{apellidosCientifico}")
	public ResponseEntity<List<Contribucion>> getAllCientificosByApellidosCientificoRelacionado(
			@PathVariable(value = "apellidosCientifico") String apellidosCientifico) {
		try {
			Optional<List<Contribucion>> contribuciones = Optional
					.of(contribucionRepository.findContribucionesByApellidosCientifico(apellidosCientifico));
			if (contribuciones.isPresent()) {
				return new ResponseEntity<>(contribuciones.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);

			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Contribuciones relacionadas por cientificos con campo nacionalidad:
	// nacionalidadCientifico
	@GetMapping("/contribuciones/busquedaAjena/nacionalidad/{nacionalidadCientifico}")
	public ResponseEntity<List<Contribucion>> getAllCientificosByNacionalidadCientificoRelacionado(
			@PathVariable(value = "nacionalidadCientifico") String nacionalidadCientifico) {
		try {
			Optional<List<Contribucion>> contribuciones = Optional
					.of(contribucionRepository.findContribucionesByNacionalidadCientifico(nacionalidadCientifico));
			if (contribuciones.isPresent()) {
				return new ResponseEntity<>(contribuciones.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Contribuciones relacionadas por cientificos con campo fechaNacimiento:
	// fechaNacimiento
	@GetMapping("/contribuciones/busquedaAjena/fechaNacimiento/{fechaNacimientoCientifico}")
	public ResponseEntity<List<Contribucion>> getAllCientificosByFechaNacimientoCientificoRelacionado(
			@PathVariable(value = "fechaNacimientoCientifico") String fechaNacimientoCientifico) {
		try {
			Optional<List<Contribucion>> contribuciones = Optional.of(
					contribucionRepository.findContribucionesByFechaNacimientoCientifico(fechaNacimientoCientifico));
			if (contribuciones.isPresent()) {
				return new ResponseEntity<>(contribuciones.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);

			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Contribuciones relacionadas por cientificos con campo fechaDefuncion:
	// fechaDefuncion
	@GetMapping("/contribuciones/busquedaAjena/fechaDefuncion/{fechaDefuncionCientifico}")
	public ResponseEntity<List<Contribucion>> getAllCientificosByFechaDefuncionCientificoRelacionado(
			@PathVariable(value = "fechaDefuncionCientifico") String fechaDefuncionCientifico) {
		try {
			Optional<List<Contribucion>> contribuciones = Optional
					.of(contribucionRepository.findContribucionesByFechaDefuncionCientifico(fechaDefuncionCientifico));
			if (contribuciones.isPresent()) {
				return new ResponseEntity<>(contribuciones.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);

			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Contribuciones relacionadas por cientificos vivos
	@GetMapping("/contribuciones/busquedaAjena/vivos")
	public ResponseEntity<List<Contribucion>> getAllCientificosByFechaDefuncionCientificoRelacionado() {
		try {
			Optional<List<Contribucion>> contribuciones = Optional
					.of(contribucionRepository.findContribucionesByFechaDefuncionCientificoNull());
			if (contribuciones.isPresent()) {
				return new ResponseEntity<>(contribuciones.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);

			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Contribuciones relacionadas por cientificos con campo enlaceMasInformacion:
	// enlaceMasInformacionCientifico
	@GetMapping("/contribuciones/busquedaAjena/enlaceMasInformacion/{enlaceMasInformacionCientifico}")
	public ResponseEntity<List<Contribucion>> getAllCientificosByEnlaceMasInformacionCientificoRelacionado(
			@PathVariable(value = "enlaceMasInformacionCientifico") String enlaceMasInformacionCientifico) {
		try {
			Optional<List<Contribucion>> contribuciones = Optional.of(contribucionRepository
					.findContribucionesByEnlaceMasInformacionCientifico(enlaceMasInformacionCientifico));
			if (contribuciones.isPresent()) {
				return new ResponseEntity<>(contribuciones.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);

			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Contribuciones relacionadas por cientificos con campo enlaceFoto:
	// enlaceFotoCientifico
	@GetMapping("/contribuciones/busquedaAjena/enlaceFoto/{enlaceFotoCientifico}")
	public ResponseEntity<List<Contribucion>> getAllCientificosByEnlaceFotoCientificoRelacionado(
			@PathVariable(value = "enlaceFotoCientifico") String enlaceFotoCientifico) {
		try {
			Optional<List<Contribucion>> contribuciones = Optional
					.of(contribucionRepository.findContribucionesByEnlaceFotoCientifico(enlaceFotoCientifico));
			if (contribuciones.isPresent()) {
				return new ResponseEntity<>(contribuciones.get(), HttpStatus.OK);
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
	// Contribuciones relacionadas con nombre: nombreContribucion
	@GetMapping("/contribuciones/busquedaPropiaRelacionada/nombre/{nombreContribucion}")
	public ResponseEntity<List<Contribucion>> getAllContribucionesByNombreContribucionRelacionada(
			@PathVariable(value = "nombreContribucion") String nombreContribucion) {
		try {
			Optional<List<Contribucion>> contribuciones = Optional
					.of(contribucionRepository.findContribucionesByNombreContribucionRelacionada(nombreContribucion));
			if (contribuciones.isPresent()) {
				return new ResponseEntity<>(contribuciones.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);

			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Contribuciones relacionadas con campo: campoContribucion
	@GetMapping("/contribuciones/busquedaPropiaRelacionada/campo/{campoContribucion}")
	public ResponseEntity<List<Contribucion>> getAllContribucionesByCampoContribucionRelacionada(
			@PathVariable(value = "campoContribucion") String campoContribucion) {
		try {
			Optional<List<Contribucion>> contribuciones = Optional
					.of(contribucionRepository.findContribucionesByCampoContribucionRelacionada(campoContribucion));
			if (contribuciones.isPresent()) {
				return new ResponseEntity<>(contribuciones.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);

			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Contribuciones relacionadas con rama: ramaContribucion
	@GetMapping("/contribuciones/busquedaPropiaRelacionada/rama/{ramaContribucion}")
	public ResponseEntity<List<Contribucion>> getAllContribucionesByRamaContribucionRelacionada(
			@PathVariable(value = "ramaContribucion") String ramaContribucion) {
		try {
			Optional<List<Contribucion>> contribuciones = Optional
					.of(contribucionRepository.findContribucionesByRamaContribucionRelacionada(ramaContribucion));
			if (contribuciones.isPresent()) {
				return new ResponseEntity<>(contribuciones.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);

			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Contribuciones relacionadas con enlaceMasInformacion: enlaceMasInformacion
	@GetMapping("/contribuciones/busquedaPropiaRelacionada/enlaceMasInformacion/{enlaceMasInformacionContribucion}")
	public ResponseEntity<List<Contribucion>> getAllContribucionesByEnlaceMasInformacionContribucionRelacionada(
			@PathVariable(value = "enlaceMasInformacionContribucion") String enlaceMasInformacionContribucion) {
		try {
			Optional<List<Contribucion>> contribuciones = Optional.of(contribucionRepository
					.findContribucionesByEnlaceMasInformacionContribucionRelacionada(enlaceMasInformacionContribucion));
			if (contribuciones.isPresent()) {
				return new ResponseEntity<>(contribuciones.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);

			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
