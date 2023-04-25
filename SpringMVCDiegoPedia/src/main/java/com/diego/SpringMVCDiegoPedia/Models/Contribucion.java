package com.diego.SpringMVCDiegoPedia.Models;

import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "contribuciones")
public class Contribucion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "campo")
	private String campo;

	@Column(name = "rama")
	private String rama;

	@Column(name = "enlaceMasInformacion")
	private String enlaceMasInformacion;

	@OneToMany(mappedBy = "id", fetch = FetchType.LAZY)
	Set<RelacionCientificoContribucion> registros;

	public Contribucion() {

	}

	public Contribucion(int id, String nombre, String campo, String rama, String enlaceMasInformacion,
			Set<RelacionCientificoContribucion> registros) {
		this.id = id;
		this.nombre = nombre;
		this.campo = campo;
		this.rama = rama;
		this.enlaceMasInformacion = enlaceMasInformacion;
		this.registros = registros;
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

	public String getCampo() {
		return campo;
	}

	public void setCampo(String campo) {
		this.campo = campo;
	}

	public String getRama() {
		return rama;
	}

	public void setRama(String rama) {
		this.rama = rama;
	}

	public String getEnlaceMasInformacion() {
		return enlaceMasInformacion;
	}

	public void setEnlaceMasInformacion(String enlaceMasInformacion) {
		this.enlaceMasInformacion = enlaceMasInformacion;
	}

	public Set<RelacionCientificoContribucion> getRegistros() {
		return registros;
	}

	public void setRegistros(Set<RelacionCientificoContribucion> registros) {
		this.registros = registros;
	}

	@Override
	public String toString() {
		return "Contribucion [id=" + id + ", nombre=" + nombre + ", campo=" + campo + ", rama=" + rama
				+ ", enlaceMasInformacion=" + enlaceMasInformacion + "]";
	}

	public Contribucion setContribucion(Contribucion contribucion) {
		return new Contribucion(this.getId(), contribucion.getNombre(), contribucion.getCampo(), contribucion.getRama(),
				contribucion.getEnlaceMasInformacion(), contribucion.getRegistros());
	}
}