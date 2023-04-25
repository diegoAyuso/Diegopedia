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

import com.diego.ApiRestDiegoPedia.Models.RelacionUsuarioContribucion;
import com.diego.ApiRestDiegoPedia.Repositories.ContribucionRepository;
import com.diego.ApiRestDiegoPedia.Repositories.RelacionUsuarioContribucionRepository;
import com.diego.ApiRestDiegoPedia.Repositories.UsuarioRepository;

@CrossOrigin
@RestController
@RequestMapping("/diegopedia")
public class RelacionUsuarioContribucionController {
	@Autowired
	ContribucionRepository contribucionRepository;
	@Autowired
	UsuarioRepository usuarioRepository;
	@Autowired
	RelacionUsuarioContribucionRepository relacionRepository;

	@GetMapping("/relacionesUsuarioContribucion")
	public ResponseEntity<List<RelacionUsuarioContribucion>> getAll() {
		try {
			List<RelacionUsuarioContribucion> relaciones = relacionRepository.findAll();
			if (relaciones.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(relaciones, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/relacionesUsuarioContribucion/{id}")
	public ResponseEntity<RelacionUsuarioContribucion> getById(@PathVariable(value="id") int id) {
		Optional<RelacionUsuarioContribucion> relacion = relacionRepository.findById(id);
		if (relacion.isPresent()) {
			return new ResponseEntity<>(relacion.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/relacionesUsuarioContribucion")
	public ResponseEntity<RelacionUsuarioContribucion> add(@RequestBody RelacionUsuarioContribucion relacion) {
		try {
			if (contribucionRepository.findById(relacion.getContribucion().getId()).isEmpty()) {
				// No existe la contribución con misma id que la contribución  que se ha pasado por
				// la relación
				// Con esto cribamos para que la contribución  deba existir con anterioridad en
				// contribuciones
				return new ResponseEntity<>(HttpStatus.CONFLICT);
			}
			if (usuarioRepository.findById(relacion.getUsuario().getId()).isEmpty()) {
				// No existe el usuario con misma id que el usuario que se ha pasado
				// por la relación
				// Con esto cribamos para que el usuario deba existir con anterioridad en
				// usuarios
				return new ResponseEntity<>(HttpStatus.CONFLICT);
			}
			RelacionUsuarioContribucion relacionNueva = relacion;
			relacionNueva.setId(0);
			relacionNueva = relacionRepository.save(relacionNueva);
			return new ResponseEntity<>(relacionNueva, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/relacionesUsuarioContribucion/{id}")
	public ResponseEntity<RelacionUsuarioContribucion> update(@PathVariable(value="id") int id,
			@RequestBody RelacionUsuarioContribucion relacion) {
		try {
			if (contribucionRepository.findById(relacion.getContribucion().getId()).isEmpty()) {
				// No existe la contribución con misma id que la contribución  que se ha pasado por
				// la relación
				// Con esto cribamos para que la contribución  deba existir con anterioridad en
				// contribuciones
				return new ResponseEntity<>(HttpStatus.CONFLICT);
			}
			if (usuarioRepository.findById(relacion.getUsuario().getId()).isEmpty()) {
				// No existe el usuario con misma id que el usuario que se ha pasado
				// por la relación
				// Con esto cribamos para que el usuario deba existir con anterioridad en
				// usuarios
				return new ResponseEntity<>(HttpStatus.CONFLICT);
			}
			Optional<RelacionUsuarioContribucion> relacionNueva = relacionRepository.findById(id);
			if (relacionNueva.isPresent()) {
				RelacionUsuarioContribucion updateRelacion = relacionNueva.get().setRelacion(relacion);
				updateRelacion = relacionRepository.save(updateRelacion);
				return new ResponseEntity<>(updateRelacion, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/relacionesUsuarioContribucion/{id}")
	public ResponseEntity<Integer> delete(@PathVariable(value="id") int id) {
		try {
			if (relacionRepository.findById(id).isEmpty()) {
				return new ResponseEntity<>(id,HttpStatus.NOT_FOUND);
			} else {
				relacionRepository.deleteById(id);
				return new ResponseEntity<>(id,HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(500,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/relacionesUsuarioContribucion/busquedaPropia/contribucion/{nombreContribucion}")
	public ResponseEntity<List<RelacionUsuarioContribucion>> getAllByNombreContribucion(@PathVariable(value="nombreContribucion") String nombreContribucion) {
		try {
			Optional<List<RelacionUsuarioContribucion>> relaciones = Optional.of(relacionRepository.findRelacionesUsuarioContribucionByNombreContribucion(nombreContribucion));
			if (relaciones.isPresent()) {
				return new ResponseEntity<>(relaciones.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}	
	@GetMapping("/relacionesUsuarioContribucion/busquedaPropia/usuario/{nombreUsuario}")
	public ResponseEntity<List<RelacionUsuarioContribucion>> getAllByNombreUsuario(@PathVariable(value="nombreUsuario") String nombreUsuario) {
		try {
			Optional<List<RelacionUsuarioContribucion>> relaciones = Optional.of(relacionRepository.findRelacionesUsuarioContribucionByNombreUsuario(nombreUsuario));
			if (relaciones.isPresent()) {
				return new ResponseEntity<>(relaciones.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
	