package com.diego.SpringMVCDiegoPedia.Controllers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.diego.SpringMVCDiegoPedia.Models.Cientifico;
import com.diego.SpringMVCDiegoPedia.Models.Contribucion;
import com.diego.SpringMVCDiegoPedia.Models.RelacionCientificoContribucion;
import com.diego.SpringMVCDiegoPedia.Repositories.CientificoRepository;
import com.diego.SpringMVCDiegoPedia.Repositories.ContribucionRepository;
import com.diego.SpringMVCDiegoPedia.Repositories.RelacionCientificoContribucionRepository;

@Controller
public class RelacionCientificoContribucionController {
	@Autowired
	ContribucionRepository contribucionRepository;
	@Autowired
	CientificoRepository cientificoRepository;
	@Autowired
	RelacionCientificoContribucionRepository relacionRepository;

	@GetMapping("/relacionesCientificoContribucion")
	public String getAll(Model model, @Param("campoBuscar") String campoBuscar,
			@Param("tipoComparador") String tipoComparador) {
		try {
			List<RelacionCientificoContribucion> relacionesCientificoContribucion = new ArrayList<RelacionCientificoContribucion>();
			if (campoBuscar == null) {
				relacionRepository.findAll().forEach(relacionesCientificoContribucion::add);
			} else if (tipoComparador.equals("nombreContribucion")) {
				contribucionRepository.findByNombreContaining(campoBuscar).forEach(
						e -> relacionRepository.findByContribucion(e).forEach(relacionesCientificoContribucion::add));
				model.addAttribute("campoBuscar", campoBuscar);
				model.addAttribute("parametroBusquedaSeleccionado", "nombreContribucion");

			} else if (tipoComparador.equals("apellidosCientifico")) {
				cientificoRepository.findByApellidosContaining(campoBuscar).forEach(
						e -> relacionRepository.findByCientifico(e).forEach(relacionesCientificoContribucion::add));
				model.addAttribute("campoBuscar", campoBuscar);
				model.addAttribute("parametroBusquedaSeleccionado", "apellidosCientifico");

			} else if (tipoComparador.equals("epoca")) {
				relacionRepository.findByEpocaContaining(campoBuscar).forEach(relacionesCientificoContribucion::add);
				model.addAttribute("campoBuscar", campoBuscar);
				model.addAttribute("parametroBusquedaSeleccionado", "epoca");
			} else if (tipoComparador.equals("aportacion")) {
				relacionRepository.findByAportacionContaining(campoBuscar)
						.forEach(relacionesCientificoContribucion::add);
				model.addAttribute("campoBuscar", campoBuscar);
				model.addAttribute("parametroBusquedaSeleccionado", "aportacion");
			}
			// Dado que ordena por fkContribución por defecto, nos aseguremos que lo ordene
			// por su Id
			relacionesCientificoContribucion.sort(Comparator.comparing(RelacionCientificoContribucion::getId));
			model.addAttribute("relacionesCientificoContribucion", relacionesCientificoContribucion);
		} catch (Exception e) {
			model.addAttribute("message", e.getMessage());
		}
		return "relacionesCientificoContribucion";
	}

	@GetMapping("/relacionesCientificoContribucion/new")
	public String add(Model model) {
		RelacionCientificoContribucion relacionCientificoContribucion = new RelacionCientificoContribucion();
		model.addAttribute("buttonEdit", "Añadir relación");
		model.addAttribute("relacionCientificoContribucion", relacionCientificoContribucion);
		List<Cientifico> cientificosOrdenados = cientificoRepository.findAll();
		cientificosOrdenados.sort(Comparator.comparing(Cientifico::getApellidos));
		List<Contribucion> contribucionesOrdenadas = contribucionRepository.findAll();
		contribucionesOrdenadas.sort(Comparator.comparing(Contribucion::getNombre));
		model.addAttribute("cientificos", cientificosOrdenados);
		model.addAttribute("contribuciones", contribucionesOrdenadas);
		model.addAttribute("aportaciones", relacionRepository.findAportacionesRelacion());
		return "guardarCientificoContribucion";
	}

	@PostMapping("/relacionesCientificoContribucion/save")
	public String guardar(Model model, RelacionCientificoContribucion relacion, RedirectAttributes redirectAttributes) {
		try {
			relacionRepository.save(relacion);
			redirectAttributes.addFlashAttribute("message", "Relacion guardada");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("message", e.getMessage());
		}
		return "redirect:/relacionesCientificoContribucion";
	}

	@GetMapping("/relacionesCientificoContribucion/{id}")
	public String edit(@PathVariable("id") int id, Model model, RedirectAttributes redirectAttributes) {
		try {
			RelacionCientificoContribucion relacionCientificoContribucion = relacionRepository.findById(id).get();
			model.addAttribute("nombreContribucionReferida",
					relacionCientificoContribucion.getContribucion().getNombre());
			model.addAttribute("apellidosCientificoReferido",
					relacionCientificoContribucion.getCientifico().getApellidos());
			List<Cientifico> cientificosOrdenados = cientificoRepository.findAll();
			cientificosOrdenados.sort(Comparator.comparing(Cientifico::getApellidos));
			List<Contribucion> contribucionesOrdenadas = contribucionRepository.findAll();
			contribucionesOrdenadas.sort(Comparator.comparing(Contribucion::getNombre));
			model.addAttribute("cientificos", cientificosOrdenados);
			model.addAttribute("contribuciones", contribucionesOrdenadas);
			model.addAttribute("relacionCientificoContribucion", relacionCientificoContribucion);
			model.addAttribute("pageTitle", "Editar Relacion (ID: " + id + ")");
			model.addAttribute("buttonEdit", "Editar relación");
			model.addAttribute("aportaciones", relacionRepository.findAportacionesRelacion());
			return "guardarCientificoContribucion";
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("message", e.getMessage());
		}
		return "guardarCientificoContribucion";
	}

	@GetMapping("/relacionesCientificoContribucion/goToDelete/{id}")
	public String goToDelete(@PathVariable("id") int id, Model model, RedirectAttributes redirectAttributes) {
		try {
			RelacionCientificoContribucion relacionCientificoContribucion = relacionRepository.findById(id).get();
			model.addAttribute("relacionCientificoContribucion", relacionCientificoContribucion);
			return "eliminarRelacionCientificoContribucion";
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("message", e.getMessage());
		}
		return "eliminarRelacionCientificoContribucion";
	}

	@GetMapping("/relacionesCientificoContribucion/delete/{id}")
	public String delete(@PathVariable("id") int id, Model model, RedirectAttributes redirectAttributes) {
		try {
			relacionRepository.deleteById(id);
			redirectAttributes.addFlashAttribute("message", "Relación eliminada.");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("message", e.getMessage());
		}
		return "redirect:/relacionesCientificoContribucion";
	}

	@GetMapping("/relacionesCientificoContribucion/consultas")
	public String getConsultas(Model model, @Param("campoBuscar") String campoBuscar,
			@Param("tipoDeConsultas") String tipoDeConsultas,
			@Param("tipoDeRegistrosObtenidos") String tipoDeRegistrosObtenidos,
			@Param("tipoComparadorCampos") String tipoComparadorCampos) {
		try {
			List<Contribucion> contribucionesRelacionadas = new ArrayList<Contribucion>();
			List<Cientifico> cientificosRelacionados = new ArrayList<Cientifico>();
			boolean campoAjeno = false;
			if (tipoDeConsultas == null || tipoDeConsultas.equals("regresar")) {
				model.addAttribute("tituloBusqueda", "Consultas personalizadas");
				model.addAttribute("message", "Elija el tipo de consulta a realizar");
				model.addAttribute("cientificosRelacionados", cientificosRelacionados);
				model.addAttribute("contribucionesRelacionadas", contribucionesRelacionadas);
			} else {
				if (tipoDeConsultas.equals("consultasPorCampoAjeno")) {
					model.addAttribute("tituloBusqueda", "Consultas personalizadas por búsqueda de campo ajeno");
					model.addAttribute("tipoDeConsultas", "consultasPorCampoAjeno");
					model.addAttribute("message",
							"Adjunte parámetros de búsqueda para mostrar registros. El valor del campo que rellene será el valor de referencia para encontrar coincidencias con aquellos valores de los campos de la entidad que esté relacionada con la entidad elegida en el primer desplegable.");
					campoAjeno = true;
				} else if (tipoDeConsultas.equals("consultasPorCampoPropioRelacionadas")) {
					model.addAttribute("tituloBusqueda", "Consultas personalizadas por búsqueda de campo propio");
					model.addAttribute("tipoDeConsultas", "consultasPorCampoPropioRelacionadas");
					model.addAttribute("message",
							"Adjunte parámetros de búsqueda para mostrar registros. El valor del campo que rellene será el valor de referencia para encontrar coincidencias con aquellos valores de los campos de la entidad elegida en el primer desplegable.");
					campoAjeno = false;
				}
			}
			if (tipoDeRegistrosObtenidos != null) {
				model.addAttribute("campoBuscar", campoBuscar);
				if (campoAjeno) {
					if (tipoDeRegistrosObtenidos.equals("cientificos")) {
						// Identificador clave ajena, debido al intercambio, aquí tendríamos que mostrar
						// contribuciones en lugar de científicos
						model.addAttribute("tablaBusquedaSeleccionada", "cientificos");
						if (tipoComparadorCampos.equals("nombre")) {
							model.addAttribute("contribucionesRelacionadas",
									contribucionRepository.findContribucionesByNombreCientifico(campoBuscar));
							model.addAttribute("cientificosRelacionados", cientificosRelacionados);
							model.addAttribute("parametroBusquedaSeleccionado", "nombre");
						} else if (tipoComparadorCampos.equals("apellidos")) {
							model.addAttribute("contribucionesRelacionadas",
									contribucionRepository.findContribucionesByApellidosCientifico(campoBuscar));
							model.addAttribute("cientificosRelacionados", cientificosRelacionados);
							model.addAttribute("parametroBusquedaSeleccionado", "apellidos");
						} else if (tipoComparadorCampos.equals("nacionalidad")) {
							model.addAttribute("contribucionesRelacionadas",
									contribucionRepository.findContribucionesByNacionalidadCientifico(campoBuscar));
							model.addAttribute("cientificosRelacionados", cientificosRelacionados);
							model.addAttribute("parametroBusquedaSeleccionado", "nacionalidad");
						} else if (tipoComparadorCampos.equals("fechaNacimiento")) {
							model.addAttribute("contribucionesRelacionadas",
									contribucionRepository.findContribucionesByFechaNacimientoCientifico(campoBuscar));
							model.addAttribute("cientificosRelacionados", cientificosRelacionados);
							model.addAttribute("parametroBusquedaSeleccionado", "fechaNacimiento");
						} else if (tipoComparadorCampos.equals("fechaDefuncion")) {
							model.addAttribute("contribucionesRelacionadas",
									contribucionRepository.findContribucionesByFechaDefuncionCientifico(campoBuscar));
							model.addAttribute("cientificosRelacionados", cientificosRelacionados);
							model.addAttribute("parametroBusquedaSeleccionado", "fechaDefuncion");
						} else if (tipoComparadorCampos.equals("enlaceFoto")) {
							model.addAttribute("contribucionesRelacionadas", contribucionRepository
									.findContribucionesByEnlaceMasInformacionCientifico(campoBuscar));
							model.addAttribute("cientificosRelacionados", cientificosRelacionados);
							model.addAttribute("parametroBusquedaSeleccionado", "enlaceFoto");
						} else if (tipoComparadorCampos.equals("enlaceMasInformacion")) {
							model.addAttribute("contribucionesRelacionadas",
									contribucionRepository.findContribucionesByEnlaceFotoCientifico(campoBuscar));
							model.addAttribute("cientificosRelacionados", cientificosRelacionados);
							model.addAttribute("parametroBusquedaSeleccionado", "enlaceMasInformacion");
						}
					} else if (tipoDeRegistrosObtenidos.equals("contribuciones")) {
						// Identificador clave ajena, debido al intercambio, aquí tendríamos que mostrar
						// cientificos en lugar de contribuciones
						model.addAttribute("tablaBusquedaSeleccionada", "contribuciones");
						if (tipoComparadorCampos.equals("nombre")) {
							model.addAttribute("cientificosRelacionados",
									cientificoRepository.findCientificosByNombreContribucion(campoBuscar));
							model.addAttribute("contribucionesRelacionadas", contribucionesRelacionadas);
							model.addAttribute("parametroBusquedaSeleccionado", "nombre");
						} else if (tipoComparadorCampos.equals("campo")) {
							model.addAttribute("cientificosRelacionados",
									cientificoRepository.findCientificosByCampoContribucion(campoBuscar));
							model.addAttribute("contribucionesRelacionadas", contribucionesRelacionadas);
							model.addAttribute("parametroBusquedaSeleccionado", "campo");
						} else if (tipoComparadorCampos.equals("rama")) {
							model.addAttribute("cientificosRelacionados",
									cientificoRepository.findCientificosByRamaContribucion(campoBuscar));
							model.addAttribute("contribucionesRelacionadas", contribucionesRelacionadas);
							model.addAttribute("parametroBusquedaSeleccionado", "rama");
						} else if (tipoComparadorCampos.equals("enlaceMasInformacion")) {
							model.addAttribute("cientificosRelacionados", cientificoRepository
									.findCientificosByEnlaceMasInformacionContribucion(campoBuscar));
							model.addAttribute("contribucionesRelacionadas", contribucionesRelacionadas);
							model.addAttribute("parametroBusquedaSeleccionado", "enlaceMasInformacion");
						}
					}
				} else {
					if (tipoDeRegistrosObtenidos.equals("cientificos")) {
						model.addAttribute("tablaBusquedaSeleccionada", "cientificos");
						if (tipoComparadorCampos.equals("nombre")) {
							model.addAttribute("cientificosRelacionados",
									cientificoRepository.findCientificosByNombreCientificoRelacionado(campoBuscar));
							model.addAttribute("contribucionesRelacionadas", contribucionesRelacionadas);
							model.addAttribute("parametroBusquedaSeleccionado", "nombre");
						} else if (tipoComparadorCampos.equals("apellidos")) {
							model.addAttribute("cientificosRelacionados",
									cientificoRepository.findCientificosByApellidosCientificoRelacionado(campoBuscar));
							model.addAttribute("contribucionesRelacionadas", contribucionesRelacionadas);
							model.addAttribute("parametroBusquedaSeleccionado", "apellidos");
						} else if (tipoComparadorCampos.equals("nacionalidad")) {
							model.addAttribute("cientificosRelacionados", cientificoRepository
									.findCientificosByNacionalidadCientificoRelacionado(campoBuscar));
							model.addAttribute("contribucionesRelacionadas", contribucionesRelacionadas);
							model.addAttribute("parametroBusquedaSeleccionado", "nacionalidad");
						} else if (tipoComparadorCampos.equals("fechaNacimiento")) {
							model.addAttribute("cientificosRelacionados", cientificoRepository
									.findCientificosByFechaNacimientoCientificoRelacionado(campoBuscar));
							model.addAttribute("contribucionesRelacionadas", contribucionesRelacionadas);
							model.addAttribute("parametroBusquedaSeleccionado", "fechaNacimiento");
						} else if (tipoComparadorCampos.equals("fechaDefuncion")) {
							model.addAttribute("cientificosRelacionados", cientificoRepository
									.findCientificosByFechaDefuncionCientificoRelacionado(campoBuscar));
							model.addAttribute("contribucionesRelacionadas", contribucionesRelacionadas);
							model.addAttribute("parametroBusquedaSeleccionado", "fechaDefuncion");
						} else if (tipoComparadorCampos.equals("enlaceFoto")) {
							model.addAttribute("cientificosRelacionados",
									cientificoRepository.findCientificosByEnlaceFotoCientificoRelacionado(campoBuscar));
							model.addAttribute("contribucionesRelacionadas", contribucionesRelacionadas);
							model.addAttribute("parametroBusquedaSeleccionado", "enlaceFoto");
						} else if (tipoComparadorCampos.equals("enlaceMasInformacion")) {
							model.addAttribute("cientificosRelacionados", cientificoRepository
									.findCientificosByEnlaceMasInformacionCientificoRelacionado(campoBuscar));
							model.addAttribute("contribucionesRelacionadas", contribucionesRelacionadas);
							model.addAttribute("parametroBusquedaSeleccionado", "enlaceMasInformacion");
						}
					} else if (tipoDeRegistrosObtenidos.equals("contribuciones")) {
						model.addAttribute("tablaBusquedaSeleccionada", "contribuciones");
						if (tipoComparadorCampos.equals("nombre")) {
							model.addAttribute("contribucionesRelacionadas", contribucionRepository
									.findContribucionesByNombreContribucionRelacionada(campoBuscar));
							model.addAttribute("cientificosRelacionados", cientificosRelacionados);
							model.addAttribute("parametroBusquedaSeleccionado", "nombre");
						} else if (tipoComparadorCampos.equals("campo")) {
							model.addAttribute("contribucionesRelacionadas", contribucionRepository
									.findContribucionesByCampoContribucionRelacionada(campoBuscar));
							model.addAttribute("cientificosRelacionados", cientificosRelacionados);
							model.addAttribute("parametroBusquedaSeleccionado", "campo");
						} else if (tipoComparadorCampos.equals("rama")) {
							model.addAttribute("contribucionesRelacionadas", contribucionRepository
									.findContribucionesByRamaContribucionRelacionada(campoBuscar));
							model.addAttribute("cientificosRelacionados", cientificosRelacionados);
							model.addAttribute("parametroBusquedaSeleccionado", "rama");
						} else if (tipoComparadorCampos.equals("enlaceMasInformacion")) {
							model.addAttribute("contribucionesRelacionadas", contribucionRepository
									.findContribucionesByEnlaceMasInformacionContribucionRelacionada(campoBuscar));
							model.addAttribute("cientificosRelacionados", cientificosRelacionados);
							model.addAttribute("parametroBusquedaSeleccionado", "enlaceMasInformacion");
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", e.getMessage());
		}

		return "relacionesCientificoContribucionConsultas";
	}

}
