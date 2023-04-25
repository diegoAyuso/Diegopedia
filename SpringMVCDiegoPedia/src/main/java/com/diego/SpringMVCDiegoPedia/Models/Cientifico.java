package com.diego.SpringMVCDiegoPedia.Models;

import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "cientificos")
public class Cientifico {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "apellidos")
	private String apellidos;

	@Column(name = "nacionalidad")
	private String nacionalidad;

	@Column(name = "fechaNacimiento")
	private String fechaNacimiento;

	@Column(name = "fechaDefuncion", nullable = true)
	private String fechaDefuncion;

	@Column(name = "enlaceMasInformacion")
	private String enlaceMasInformacion;

	@Column(name = "enlaceFoto")
	private String enlaceFoto;

	@OneToMany(mappedBy = "id", fetch = FetchType.LAZY)
	Set<RelacionCientificoContribucion> registros;

	public Cientifico() {
	}

	public Cientifico(int id, String nombre, String apellidos, String nacionalidad, String fechaNacimiento,
			String fechaDefuncion, String enlaceMasInformacion, String enlaceFoto,
			Set<RelacionCientificoContribucion> registros) {
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.nacionalidad = nacionalidad;
		this.fechaNacimiento = fechaNacimiento;
		this.fechaDefuncion = fechaDefuncion;
		this.registros = registros;
		this.enlaceMasInformacion = enlaceMasInformacion;
		this.enlaceFoto = enlaceFoto;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getFechaDefuncion() {
		return fechaDefuncion;
	}

	public void setFechaDefuncion(String fechaDefuncion) {
		this.fechaDefuncion = fechaDefuncion;
	}

	public Set<RelacionCientificoContribucion> getRegistros() {
		return registros;
	}

	public void setRegistros(Set<RelacionCientificoContribucion> registros) {
		this.registros = registros;
	}

	public String getEnlaceMasInformacion() {
		return enlaceMasInformacion;
	}

	public void setEnlaceMasInformacion(String enlaceMasInformacion) {
		this.enlaceMasInformacion = enlaceMasInformacion;
	}

	public String getEnlaceFoto() {
		return enlaceFoto;
	}

	public void setEnlaceFoto(String enlaceFoto) {
		this.enlaceFoto = enlaceFoto;
	}

	@Override
	public String toString() {
		return "Cientifico [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", nacionalidad="
				+ nacionalidad + ", fechaNacimiento=" + fechaNacimiento + ", fechaDefuncion=" + fechaDefuncion
				+ ", enlaceMasInformacion=" + enlaceMasInformacion + ", enlaceFoto=" + enlaceFoto + "]";
	}

	public Cientifico setCientifico(Cientifico cientifico) {
		return new Cientifico(this.getId(), cientifico.getNombre(), cientifico.getApellidos(),
				cientifico.getNacionalidad(), cientifico.getFechaNacimiento(), cientifico.getFechaDefuncion(),
				cientifico.getEnlaceMasInformacion(), cientifico.getEnlaceFoto(), cientifico.getRegistros());
	}
}