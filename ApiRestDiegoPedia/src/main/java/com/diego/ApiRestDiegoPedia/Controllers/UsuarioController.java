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
import com.diego.ApiRestDiegoPedia.Models.Usuario;
import com.diego.ApiRestDiegoPedia.Repositories.UsuarioRepository;

@CrossOrigin
@RestController
@RequestMapping("/diegopedia")
public class UsuarioController {
	@Autowired
	UsuarioRepository usuarioRepository;

	@GetMapping("/usuarios")
	public ResponseEntity<List<Usuario>> getAll() {
		try {
			List<Usuario> usuarios = usuarioRepository.findAll();
			if (usuarios.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(usuarios, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/usuarios/{id}")
	public ResponseEntity<Usuario> getById(@PathVariable(value="id") int id) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		if (usuario.isPresent()) {
			return new ResponseEntity<>(usuario.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/usuarios")
	public ResponseEntity<Usuario> add(@RequestBody Usuario usuario) {
		try {
			if (!usuario.getPassword().matches("(?=^.{8,30}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$")) {
				return new ResponseEntity<>(HttpStatus.CONFLICT);
			}
			if (usuarioRepository.findAll().stream().filter(user->user.getNombre().equals(usuario.getNombre())).toList().size()>0) {
				return new ResponseEntity<>(HttpStatus.CONFLICT);
			}
			Usuario usuarioNuevo = usuario;
			usuarioNuevo.setId(0);
			usuarioNuevo = usuarioRepository.save(usuarioNuevo);
			return new ResponseEntity<>(usuarioNuevo, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PutMapping("/usuarios/{id}")
	public ResponseEntity<Usuario> update(@PathVariable(value="id") int id, @RequestBody Usuario usuario) {
		try {
			if (!usuario.getPassword().matches("(?=^.{8,30}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$")) {
				return new ResponseEntity<>(HttpStatus.CONFLICT);
			}
			Optional<Usuario> usuarioNuevo = usuarioRepository.findById(id);
			if (usuarioNuevo.isPresent()) {
				Usuario updateUsuario = usuarioNuevo.get().setUsuario(usuario);
				updateUsuario = usuarioRepository.save(updateUsuario);
				return new ResponseEntity<>(updateUsuario, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/usuarios/{id}")
	public ResponseEntity<Integer> delete(@PathVariable(value="id") int id) {
		try {
			usuarioRepository.deleteById(id);
			return new ResponseEntity<>(id,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(500,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// Usuarios cuyo nombre contiene: nombreUsuario
	@GetMapping("/usuarios/busquedaPropia/nombre/{nombreUsuario}")
	public ResponseEntity<List<Usuario>> getAllUsuariosByNombre(
			@PathVariable(value = "nombreUsuario") String nombreUsuario) {
		try {
			Optional<List<Usuario>> usuarios = Optional
					.of(usuarioRepository.findByNombreContaining(nombreUsuario));
			if (usuarios.isPresent()) {
				return new ResponseEntity<>(usuarios.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);

			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Usuarios cuya password contiene: passwordUsuario
	@GetMapping("/usuarios/busquedaPropia/password/{passwordUsuario}")
	public ResponseEntity<List<Usuario>> getAllUsuariosByPassword(
			@PathVariable(value = "passwordUsuario") String passwordUsuario) {
		try {
			Optional<List<Usuario>> usuarios = Optional
					.of(usuarioRepository.findByPasswordContaining(passwordUsuario));
			if (usuarios.isPresent()) {
				return new ResponseEntity<>(usuarios.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);

			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	// Usuarios cuyo nombre contiene: nombreUsuario
		@GetMapping("/usuarios/busquedaPropia/nombreFull/{nombreUsuario}")
		public ResponseEntity<List<Usuario>> getAllUsuariosByNombreFull(
				@PathVariable(value = "nombreUsuario") String nombreUsuario) {
			try {
				Optional<List<Usuario>> usuarios = Optional
						.of(usuarioRepository.findByNombre(nombreUsuario));
				if (usuarios.isPresent()) {
					return new ResponseEntity<>(usuarios.get(), HttpStatus.OK);
				} else {
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);

				}
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		// Usuarios cuya password contiene: passwordUsuario
		@GetMapping("/usuarios/busquedaPropia/passwordFull/{passwordUsuario}")
		public ResponseEntity<List<Usuario>> getAllUsuariosByPasswordFull(
				@PathVariable(value = "passwordUsuario") String passwordUsuario) {
			try {
				Optional<List<Usuario>> usuarios = Optional
						.of(usuarioRepository.findByPassword(passwordUsuario));
				if (usuarios.isPresent()) {
					return new ResponseEntity<>(usuarios.get(), HttpStatus.OK);
				} else {
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);

				}
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
}