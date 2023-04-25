package com.diego.SpringMVCDiegoPedia.Controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.diego.SpringMVCDiegoPedia.Models.Cientifico;
import com.diego.SpringMVCDiegoPedia.Repositories.CientificoRepository;
import com.diego.SpringMVCDiegoPedia.Repositories.ContribucionRepository;

@Controller
public class CientificoController {
	@Autowired
	CientificoRepository cientificoRepository;
	@Autowired
	ContribucionRepository contribucionRepository;

	@GetMapping("/cientificos")
	public String getAll(Model model, @Param("campoBuscar") String campoBuscar,
			@Param("tipoComparador") String tipoComparador) {
		try {
			List<Cientifico> cientificos = new ArrayList<Cientifico>();
			if (campoBuscar == null) {
				cientificoRepository.findAll().forEach(cientificos::add);
			} else if (tipoComparador.equals("nombre")) {
				cientificoRepository.findByNombreContaining(campoBuscar).forEach(cientificos::add);
				model.addAttribute("campoBuscar", campoBuscar);
				model.addAttribute("parametroBusquedaSeleccionado", "nombre");

			} else if (tipoComparador.equals("apellidos")) {
				cientificoRepository.findByApellidosContaining(campoBuscar).forEach(cientificos::add);
				model.addAttribute("campoBuscar", campoBuscar);
				model.addAttribute("parametroBusquedaSeleccionado", "apellidos");

			} else if (tipoComparador.equals("nacionalidad")) {
				cientificoRepository.findByNacionalidadContaining(campoBuscar).forEach(cientificos::add);
				model.addAttribute("campoBuscar", campoBuscar);
				model.addAttribute("parametroBusquedaSeleccionado", "nacionalidad");

			} else if (tipoComparador.equals("fechaNacimiento")) {
				cientificoRepository.findByFechaNacimientoContaining(campoBuscar).forEach(cientificos::add);
				model.addAttribute("campoBuscar", campoBuscar);
				model.addAttribute("parametroBusquedaSeleccionado", "fechaNacimiento");

			} else if (tipoComparador.equals("fechaDefuncion")) {
				cientificoRepository.findByFechaDefuncionContaining(campoBuscar).forEach(cientificos::add);
				model.addAttribute("campoBuscar", campoBuscar);
				model.addAttribute("parametroBusquedaSeleccionado", "fechaDefuncion");

			} else if (tipoComparador.equals("enlaceMasInformacion")) {
				cientificoRepository.findByEnlaceMasInformacionContaining(campoBuscar).forEach(cientificos::add);
				model.addAttribute("campoBuscar", campoBuscar);
				model.addAttribute("parametroBusquedaSeleccionado", "enlaceMasInformacion");

			} else if (tipoComparador.equals("enlaceFoto")) {
				cientificoRepository.findByEnlaceFotoContaining(campoBuscar).forEach(cientificos::add);
				model.addAttribute("campoBuscar", campoBuscar);
				model.addAttribute("parametroBusquedaSeleccionado", "enlaceFoto");

			}
			model.addAttribute("cientificos", cientificos);
		} catch (Exception e) {
			model.addAttribute("message", e.getMessage());
		}
		return "cientificos";
	}

	@GetMapping("/cientificos/new")
	public String add(Model model) {
		Cientifico cientifico = new Cientifico();
		model.addAttribute("cientifico", cientifico);
		model.addAttribute("pageTitle", "Crear científico");
		model.addAttribute("buttonEdit", "Añadir Científico");
		model.addAttribute("nacionalidades", cientificoRepository.findNacionalidadesCientificos());
		return "guardarCientifico";
	}

	@PostMapping("/cientificos/save")
	public String guardar(Cientifico cientifico, RedirectAttributes redirectAttributes) {
		try {
			cientificoRepository.save(cientifico);
			if (cientifico.getFechaDefuncion().equals("")) {
				cientifico.setFechaDefuncion(null);
				cientificoRepository.save(cientifico);
			}
			redirectAttributes.addFlashAttribute("message", "Científico guardado o modificado");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("message", e.getMessage());
		}
		return "redirect:/cientificos";
	}

	@GetMapping("/cientificos/{id}")
	public String edit(@PathVariable("id") int id, Model model, RedirectAttributes redirectAttributes) {
		try {
			Cientifico cientifico = cientificoRepository.findById(id).get();
			model.addAttribute("cientifico", cientifico);
			model.addAttribute("pageTitle", "Editar Científico (ID: " + id + ")");
			model.addAttribute("buttonEdit", "Editar Científico");
			model.addAttribute("nacionalidades", cientificoRepository.findNacionalidadesCientificos());
			return "guardarCientifico";
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("message", e.getMessage());
		}
		return "guardarCientifico";
	}

	@GetMapping("/cientificos/goToDelete/{id}")
	public String goToDelete(@PathVariable("id") int id, Model model, RedirectAttributes redirectAttributes) {
		try {
			Cientifico cientifico = cientificoRepository.findById(id).get();
			model.addAttribute("cientifico", cientifico);
			return "eliminarCientifico";
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("message", e.getMessage());
		}
		return "eliminarCientifico";
	}

	@GetMapping("/cientificos/delete/{id}")
	public String delete(@PathVariable("id") int id, Model model, RedirectAttributes redirectAttributes) {
		try {
			cientificoRepository.deleteById(id);
			redirectAttributes.addFlashAttribute("message", "Científico eliminado.");
		} catch (DataIntegrityViolationException e) {
			redirectAttributes.addFlashAttribute("message",
					"No se puede eliminar el científico porque está formando parte de una relación.");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("message", e.getMessage());
		}
		return "redirect:/cientificos";
	}
}
