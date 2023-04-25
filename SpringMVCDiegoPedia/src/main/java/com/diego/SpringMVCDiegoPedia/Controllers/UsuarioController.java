package com.diego.SpringMVCDiegoPedia.Controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.diego.SpringMVCDiegoPedia.Models.Usuario;
import com.diego.SpringMVCDiegoPedia.Repositories.UsuarioRepository;


@Controller
public class UsuarioController {
	@Autowired
	UsuarioRepository usuarioRepository;

	@GetMapping("/usuarios")
	public String getAll(Model model, @Param("campoBuscar") String campoBuscar,
			@Param("tipoComparador") String tipoComparador) {
		try {
			List<Usuario> usuarios = new ArrayList<Usuario>();
			if (campoBuscar == null) {
				usuarioRepository.findAll().forEach(usuarios::add);
			} else if (tipoComparador.equals("nombre")) {
				usuarioRepository.findByNombreContaining(campoBuscar).forEach(usuarios::add);
				model.addAttribute("campoBuscar", campoBuscar);
				model.addAttribute("parametroBusquedaSeleccionado", "nombre");

			} else if (tipoComparador.equals("password")) {
				usuarioRepository.findByPasswordContaining(campoBuscar).forEach(usuarios::add);
				model.addAttribute("campoBuscar", campoBuscar);
				model.addAttribute("parametroBusquedaSeleccionado", "apellidos");

			}
			model.addAttribute("usuarios", usuarios);
		} catch (Exception e) {
			model.addAttribute("message", e.getMessage());
		}
		return "usuarios";
	}

	@GetMapping("/usuarios/{id}")
	public String edit(@PathVariable("id") int id, Model model, RedirectAttributes redirectAttributes) {
		try {
			Usuario usuario = usuarioRepository.findById(id).get();
			model.addAttribute("usuario", usuario);
			model.addAttribute("pageTitle", "Editar Usuario (ID: " + id + ")");
			model.addAttribute("buttonEdit", "Editar Usuario");
			return "guardarUsuario";
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("message", e.getMessage());
		}
		return "guardarUsuario";
	}

	@GetMapping("/usuarios/new")
	public String add(Model model) {
		Usuario usuario = new Usuario();
		model.addAttribute("usuario", usuario);
		model.addAttribute("pageTitle", "Crear usuario");
		model.addAttribute("buttonEdit", "Añadir usuario");
		return "guardarUsuario";
	}

	@PostMapping("/usuarios/save")
	public String guardar(Usuario usuario, RedirectAttributes redirectAttributes) {
		try {
			usuarioRepository.save(usuario);
			redirectAttributes.addFlashAttribute("message", "Usuario guardado o modificado");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("message", e.getMessage());
		}
		return "redirect:/usuarios";
	}

	@GetMapping("/usuarios/goToDelete/{id}")
	public String goToDelete(@PathVariable("id") int id, Model model, RedirectAttributes redirectAttributes) {
		try {
			Usuario usuario = usuarioRepository.findById(id).get();
			model.addAttribute("usuario", usuario);
			return "eliminarUsuario";
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("message", e.getMessage());
		}
		return "eliminarUsuario";
	}

	@GetMapping("/usuarios/delete/{id}")
	public String delete(@PathVariable("id") int id, Model model, RedirectAttributes redirectAttributes) {
		try {
			usuarioRepository.deleteById(id);
			redirectAttributes.addFlashAttribute("message", "Usuario eliminado.");
		}catch (Exception e) {
			redirectAttributes.addFlashAttribute("message", e.getMessage());
		}
		return "redirect:/usuarios";
	}
}

