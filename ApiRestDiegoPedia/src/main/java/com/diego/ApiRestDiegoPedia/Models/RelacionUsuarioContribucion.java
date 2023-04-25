package com.diego.ApiRestDiegoPedia.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario_contribucion")
public class RelacionUsuarioContribucion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	// Para evitar un serializado infinito, conseguir que se serialice y evitar
	// error por lazy
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fkContribucion", nullable = false)
	private Contribucion contribucion;

	// Para evitar un serializado infinito, conseguir que se serialice y evitar
	// error por lazy
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fkUsuario", nullable = false)
	private Usuario usuario;

	public RelacionUsuarioContribucion() {
	}

	public RelacionUsuarioContribucion(int id, Usuario usuario, Contribucion contribucion) {
		this.id = id;
		this.usuario = usuario;
		this.contribucion = contribucion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Contribucion getContribucion() {
		return contribucion;
	}

	public void setContribucion(Contribucion contribucion) {
		this.contribucion = contribucion;
	}

	@Override
	public String toString() {
		return "RelacionCientificosContribuciones [id=" + id + ", usuario=" + usuario + ", contribucion="
				+ contribucion + "]";
	}

	public RelacionUsuarioContribucion setRelacion(RelacionUsuarioContribucion relacion) {
		return new RelacionUsuarioContribucion(this.getId(), relacion.getUsuario(), relacion.getContribucion());
	}

}
