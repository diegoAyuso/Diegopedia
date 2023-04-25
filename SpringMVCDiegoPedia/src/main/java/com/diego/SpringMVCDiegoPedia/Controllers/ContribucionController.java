package com.diego.SpringMVCDiegoPedia.Controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.diego.SpringMVCDiegoPedia.Models.Cientifico;
import com.diego.SpringMVCDiegoPedia.Models.Contribucion;
import com.diego.SpringMVCDiegoPedia.Repositories.CientificoRepository;
import com.diego.SpringMVCDiegoPedia.Repositories.ContribucionRepository;

import jakarta.persistence.Persistence;

import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Controller
public class ContribucionController {
	@Autowired
	ContribucionRepository contribucionRepository;
	@Autowired
	CientificoRepository cientificoRepository;

	@GetMapping("/contribuciones")
	public String getAll(Model model, @Param("campoBuscar") String campoBuscar,
			@Param("tipoComparador") String tipoComparador) {
		try {
			List<Contribucion> contribuciones = new ArrayList<Contribucion>();
			if (campoBuscar == null) {
				contribucionRepository.findAll().forEach(contribuciones::add);
			} else if (tipoComparador.equals("nombre")) {
				contribucionRepository.findByNombreContaining(campoBuscar).forEach(contribuciones::add);
				model.addAttribute("campoBuscar", campoBuscar);
				model.addAttribute("parametroBusquedaSeleccionado", "nombre");

			} else if (tipoComparador.equals("campo")) {
				contribucionRepository.findByCampoContaining(campoBuscar).forEach(contribuciones::add);
				model.addAttribute("campoBuscar", campoBuscar);
				model.addAttribute("parametroBusquedaSeleccionado", "campo");

			} else if (tipoComparador.equals("rama")) {
				contribucionRepository.findByRamaContaining(campoBuscar).forEach(contribuciones::add);
				model.addAttribute("campoBuscar", campoBuscar);
				model.addAttribute("parametroBusquedaSeleccionado", "rama");

			} else if (tipoComparador.equals("enlaceMasInformacion")) {
				contribucionRepository.findByEnlaceMasInformacionContaining(campoBuscar).forEach(contribuciones::add);
				model.addAttribute("campoBuscar", campoBuscar);
				model.addAttribute("parametroBusquedaSeleccionado", "enlaceMasInformacion");

			}
			model.addAttribute("contribuciones", contribuciones);
		} catch (Exception e) {
			model.addAttribute("message", e.getMessage());
		}
		return "contribuciones";
	}

	@GetMapping("/contribuciones/new")
	public String add(Model model) {
		Contribucion contribucion = new Contribucion();
		model.addAttribute("contribucion", contribucion);
		model.addAttribute("pageTitle", "Crear contribucion");
		model.addAttribute("buttonEdit", "Añadir contribución");
		model.addAttribute("campos", contribucionRepository.findCamposContribuciones());
		model.addAttribute("ramas", contribucionRepository.findRamasContribuciones());
		return "guardarContribucion";
	}

	@PostMapping("/contribuciones/save")
	public String guardar(Contribucion contribucion, RedirectAttributes redirectAttributes) {
		try {
			contribucionRepository.save(contribucion);
			redirectAttributes.addFlashAttribute("message", "Contribución guardada o modificada");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("message", e.getMessage());
		}
		return "redirect:/contribuciones";
	}

	@GetMapping("/contribuciones/{id}")
	public String edit(@PathVariable("id") int id, Model model, RedirectAttributes redirectAttributes) {
		try {
			Contribucion contribucion = contribucionRepository.findById(id).get();
			model.addAttribute("contribucion", contribucion);
			model.addAttribute("pageTitle", "Editar Contribución (ID: " + id + ")");
			model.addAttribute("buttonEdit", "Editar contribución");
			model.addAttribute("campos", contribucionRepository.findCamposContribuciones());
			model.addAttribute("ramas", contribucionRepository.findRamasContribuciones());
			return "guardarContribucion";
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("message", e.getMessage());
		}
		return "guardarContribucion";
	}

	@GetMapping("/contribuciones/goToDelete/{id}")
	public String goToDelete(@PathVariable("id") int id, Model model, RedirectAttributes redirectAttributes) {
		try {
			Contribucion contribucion = contribucionRepository.findById(id).get();
			model.addAttribute("contribucion", contribucion);
			return "eliminarContribucion";
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("message", e.getMessage());
		}
		return "eliminarContribucion";
	}

	@GetMapping("/contribuciones/delete/{id}")
	public String delete(@PathVariable("id") int id, Model model, RedirectAttributes redirectAttributes) {
		try {
			contribucionRepository.deleteById(id);
			redirectAttributes.addFlashAttribute("message", "Contribución eliminada.");
		} catch (DataIntegrityViolationException e) {
			redirectAttributes.addFlashAttribute("message",
					"No se puede eliminar la contribución porque está formando parte de una relación.");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("message", e.getMessage());
		}
		return "redirect:/contribuciones";
	}

	@PostMapping("/contribuciones")
	public ResponseEntity<Contribucion> create(@RequestBody Contribucion contribucion) {
		try {
			Contribucion contribuciónNueva = contribucion;
			contribuciónNueva = contribucionRepository.save(contribuciónNueva);
			return new ResponseEntity<>(contribuciónNueva, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/contribuciones/{id}")
	public ResponseEntity<Contribucion> update(@PathVariable("id") int id, @RequestBody Contribucion contribucion) {
		try {
			Optional<Contribucion> contribuciónNueva = contribucionRepository.findById(id);
			if (contribuciónNueva.isPresent()) {
				Contribucion updateContribucion = contribuciónNueva.get().setContribucion(contribucion);
				updateContribucion = contribucionRepository.save(updateContribucion);
				return new ResponseEntity<>(updateContribucion, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/contribuciones/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable("id") int id) {
		try {
			contribucionRepository.deleteById(id);
			return ResponseEntity.ok(null);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
