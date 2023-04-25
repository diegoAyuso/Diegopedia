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

import com.diego.ApiRestDiegoPedia.Models.Contribucion;
import com.diego.ApiRestDiegoPedia.Models.RelacionCientificoContribucion;
import com.diego.ApiRestDiegoPedia.Repositories.CientificoRepository;
import com.diego.ApiRestDiegoPedia.Repositories.ContribucionRepository;
import com.diego.ApiRestDiegoPedia.Repositories.RelacionCientificoContribucionRepository;

@CrossOrigin
@RestController
@RequestMapping("/diegopedia")
public class RelacionCientificoContribucionController {
	@Autowired
	CientificoRepository cientificoRepository;
	@Autowired
	ContribucionRepository contribucionRepository;
	@Autowired
	RelacionCientificoContribucionRepository relacionRepository;

	@GetMapping("/relacionesCientificoContribucion")
	public ResponseEntity<List<RelacionCientificoContribucion>> getAll() {
		try {
			List<RelacionCientificoContribucion> relaciones = relacionRepository.findAll();
			if (relaciones.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(relaciones, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/relacionesCientificoContribucion/{id}")
	public ResponseEntity<RelacionCientificoContribucion> getById(@PathVariable("id") int id) {
		Optional<RelacionCientificoContribucion> relacion = relacionRepository.findById(id);
		if (relacion.isPresent()) {
			return new ResponseEntity<>(relacion.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/relacionesCientificoContribucion")
	public ResponseEntity<RelacionCientificoContribucion> add(@RequestBody RelacionCientificoContribucion relacion) {
		try {
			// Se fijará solo en los ids del científico y de la contribución
			if (!relacion.getEpoca().matches("^[0-9]{4}$") || !relacion.getAportacion()
					.matches("^[a-zA-Z\\s\\-ÁÉÍÓÚáéíóúÀÈÌÒÙàèìòÄËÏÖÜäëïöüÂÊÎÔÛâêîôûÑñ]+$")) {
				return new ResponseEntity<>(HttpStatus.CONFLICT);
			}
			if (cientificoRepository.findById(relacion.getCientifico().getId()).isEmpty()) {
				// No existe el cientifico con misma id que el científico que se ha pasado por
				// la relación
				// Con esto cribamos para que el científico deba existir con anterioridad en
				// cientificos
				return new ResponseEntity<>(HttpStatus.CONFLICT);
			}
			if (contribucionRepository.findById(relacion.getContribucion().getId()).isEmpty()) {
				// No existe la contribución con misma id que la contribución que se ha pasado
				// por la relación
				// Con esto cribamos para que la contribución deba existir con anterioridad en
				// contribuciones
				return new ResponseEntity<>(HttpStatus.CONFLICT);
			}
			RelacionCientificoContribucion relacionNueva = relacion;
			relacionNueva.setId(0);
			relacionNueva = relacionRepository.save(relacionNueva);
			return new ResponseEntity<>(relacionNueva, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/relacionesCientificoContribucion/{id}")
	public ResponseEntity<RelacionCientificoContribucion> update(@PathVariable("id") int id,
			@RequestBody RelacionCientificoContribucion relacion) {
		try {
			// Se fijará solo en los ids del científico y de la contribución
			if (!relacion.getEpoca().matches("^[0-9]{4}$") || !relacion.getAportacion()
					.matches("^[a-zA-Z\\s\\-ÁÉÍÓÚáéíóúÀÈÌÒÙàèìòÄËÏÖÜäëïöüÂÊÎÔÛâêîôûÑñ]+$")) {
				return new ResponseEntity<>(HttpStatus.CONFLICT);
			}
			if (cientificoRepository.findById(relacion.getCientifico().getId()).isEmpty()) {
				// No existe el cientifico con misma id que el científico que se ha pasado por
				// la relación
				// Con esto cribamos para que el científico deba existir con anterioridad en
				// cientificos
				return new ResponseEntity<>(HttpStatus.CONFLICT);
			}
			if (contribucionRepository.findById(relacion.getContribucion().getId()).isEmpty()) {
				// No existe la contribución con misma id que la contribución que se ha pasado
				// por la relación
				// Con esto cribamos para que la contribución deba existir con anterioridad en
				// contribuciones
				return new ResponseEntity<>(HttpStatus.CONFLICT);
			}
			Optional<RelacionCientificoContribucion> relacionNueva = relacionRepository.findById(id);
			if (relacionNueva.isPresent()) {
				RelacionCientificoContribucion updateRelacion = relacionNueva.get().setRelacion(relacion);
				updateRelacion = relacionRepository.save(updateRelacion);
				return new ResponseEntity<>(updateRelacion, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/relacionesCientificoContribucion/{id}")
	public ResponseEntity<Integer> delete(@PathVariable("id") int id) {
		try {
			if (relacionRepository.findById(id).isEmpty()) {
				return new ResponseEntity<>(id,HttpStatus.NOT_FOUND);
			} else {
				relacionRepository.deleteById(id);
				return new ResponseEntity<>(id,HttpStatus.OK);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(500,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	/* Búsqueda de relaciones cientifico contribución  */
	// relaciones cientifico contribución con nombre de contribucion nombreContribucion
	@GetMapping("/relacionesCientificoContribucion/busquedaPropia/nombreContribucion/{nombreContribucion}")
	public ResponseEntity<List<RelacionCientificoContribucion>> getAllRelacionesCientificoContribucionByNombreContribucion(
			@PathVariable(value = "nombreContribucion") String nombreContribucion) {
		try {
			Optional<List<RelacionCientificoContribucion>> relacionesCientificoContribucion = Optional
					.of(relacionRepository.findRelacionesCientificoContribucionByNombreContribucion(nombreContribucion));
			if (relacionesCientificoContribucion.isPresent()) {
				return new ResponseEntity<>(relacionesCientificoContribucion.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// relaciones cientifico contribución con apellidos científico: apellidosCientifico
	@GetMapping("/relacionesCientificoContribucion/busquedaPropia/apellidosCientifico/{apellidosCientifico}")
	public ResponseEntity<List<RelacionCientificoContribucion>> getAllRelacionesCientificoContribucionByApellidosCientifico(
			@PathVariable(value = "apellidosCientifico") String apellidosCientifico) {
		try {
			Optional<List<RelacionCientificoContribucion>> relacionesCientificoContribucion = Optional
					.of(relacionRepository.findRelacionesCientificoContribucionByApellidosCientifico(apellidosCientifico));
			if (relacionesCientificoContribucion.isPresent()) {
				return new ResponseEntity<>(relacionesCientificoContribucion.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// relaciones cientifico contribución con época: epoca
	@GetMapping("/relacionesCientificoContribucion/busquedaPropia/epoca/{epoca}")
	public ResponseEntity<List<RelacionCientificoContribucion>> getAllRelacionesCientificoContribucionByEpoca(
			@PathVariable(value = "epoca") String epoca) {
		try {
			Optional<List<RelacionCientificoContribucion>> relacionesCientificoContribucion = Optional
					.of(relacionRepository.findRelacionesCientificoContribucionByEpoca(epoca));
			if (relacionesCientificoContribucion.isPresent()) {
				return new ResponseEntity<>(relacionesCientificoContribucion.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// relaciones cientifico contribución con aportación: aportación
	@GetMapping("/relacionesCientificoContribucion/busquedaPropia/aportacion/{aportacion}")
	public ResponseEntity<List<RelacionCientificoContribucion>> getAllRelacionesCientificoContribucionByAportacion(
			@PathVariable(value = "aportacion") String aportacion) {
		try {
			Optional<List<RelacionCientificoContribucion>> relacionesCientificoContribucion = Optional
					.of(relacionRepository.findRelacionesCientificoContribucionByAportacion(aportacion));
			if (relacionesCientificoContribucion.isPresent()) {
				return new ResponseEntity<>(relacionesCientificoContribucion.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}