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

import com.diego.ApiRestDiegoPedia.Models.RelacionCientificoContribucion;
import com.diego.ApiRestDiegoPedia.Models.RelacionUsuarioCientifico;
import com.diego.ApiRestDiegoPedia.Models.RelacionUsuarioContribucion;
import com.diego.ApiRestDiegoPedia.Repositories.CientificoRepository;
import com.diego.ApiRestDiegoPedia.Repositories.RelacionUsuarioCientificoRepository;
import com.diego.ApiRestDiegoPedia.Repositories.UsuarioRepository;

@CrossOrigin
@RestController
@RequestMapping("/diegopedia")
public class RelacionUsuarioCientificoController {
	@Autowired
	CientificoRepository cientificoRepository;
	@Autowired
	UsuarioRepository usuarioRepository;
	@Autowired
	RelacionUsuarioCientificoRepository relacionRepository;

	@GetMapping("/relacionesUsuarioCientifico")
	public ResponseEntity<List<RelacionUsuarioCientifico>> getAll() {
		try {
			List<RelacionUsuarioCientifico> relaciones = relacionRepository.findAll();
			if (relaciones.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(relaciones, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/relacionesUsuarioCientifico/{id}")
	public ResponseEntity<RelacionUsuarioCientifico> getById(@PathVariable(value="id") int id) {
		Optional<RelacionUsuarioCientifico> relacion = relacionRepository.findById(id);
		if (relacion.isPresent()) {
			return new ResponseEntity<>(relacion.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/relacionesUsuarioCientifico")
	public ResponseEntity<RelacionUsuarioCientifico> add(@RequestBody RelacionUsuarioCientifico relacion) {
		try {
			if (cientificoRepository.findById(relacion.getCientifico().getId()).isEmpty()) {
				// No existe el cientifico con misma id que el científico que se ha pasado por
				// la relación
				// Con esto cribamos para que el científico deba existir con anterioridad en
				// cientificos
				return new ResponseEntity<>(HttpStatus.CONFLICT);
			}
			if (usuarioRepository.findById(relacion.getUsuario().getId()).isEmpty()) {
				// No existe el usuario con misma id que el usuario que se ha pasado
				// por la relación
				// Con esto cribamos para que el usuario deba existir con anterioridad en
				// usuarios
				return new ResponseEntity<>(HttpStatus.CONFLICT);
			}
			RelacionUsuarioCientifico relacionNueva = relacion;
			relacionNueva.setId(0);
			relacionNueva = relacionRepository.save(relacionNueva);
			return new ResponseEntity<>(relacionNueva, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/relacionesUsuarioCientifico/{id}")
	public ResponseEntity<RelacionUsuarioCientifico> update(@PathVariable(value="id") int id,
			@RequestBody RelacionUsuarioCientifico relacion) {
		try {

			if (cientificoRepository.findById(relacion.getCientifico().getId()).isEmpty()) {
				// No existe el cientifico con misma id que el científico que se ha pasado por
				// la relación
				// Con esto cribamos para que el científico deba existir con anterioridad en
				// cientificos
				return new ResponseEntity<>(HttpStatus.CONFLICT);
			}
			if (usuarioRepository.findById(relacion.getUsuario().getId()).isEmpty()) {
				// No existe el usuario con misma id que el usuario que se ha pasado
				// por la relación
				// Con esto cribamos para que el usuario deba existir con anterioridad en
				// usuarios
				return new ResponseEntity<>(HttpStatus.CONFLICT);
			}
			Optional<RelacionUsuarioCientifico> relacionNueva = relacionRepository.findById(id);
			if (relacionNueva.isPresent()) {
				RelacionUsuarioCientifico updateRelacion = relacionNueva.get().setRelacion(relacion);
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

	@DeleteMapping("/relacionesUsuarioCientifico/{id}")
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

	@GetMapping("/relacionesUsuarioCientifico/busquedaPropia/cientifico/{apellidosCientifico}")
	public ResponseEntity<List<RelacionUsuarioCientifico>> getAllByApellidosCientifico(@PathVariable(value="apellidosCientifico") String apellidosCientifico) {
		try {
			Optional<List<RelacionUsuarioCientifico>> relaciones = Optional.of(relacionRepository.findRelacionesUsuarioCientificoByApellidosCientifico(apellidosCientifico));
			if (relaciones.isPresent()) {
				return new ResponseEntity<>(relaciones.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}	
	@GetMapping("/relacionesUsuarioCientifico/busquedaPropia/usuario/{nombreUsuario}")
	public ResponseEntity<List<RelacionUsuarioCientifico>> getAllByNombreUsuario(@PathVariable(value="nombreUsuario") String nombreUsuario) {
		try {
			Optional<List<RelacionUsuarioCientifico>> relaciones = Optional.of(relacionRepository.findRelacionesUsuarioCientificoByNombreUsuario(nombreUsuario));
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
	