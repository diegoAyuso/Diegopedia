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
@Table(name = "usuario_cientifico")
public class RelacionUsuarioCientifico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	// Para evitar un serializado infinito, conseguir que se serialice y evitar
	// error por lazy
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fkCientifico", nullable = false)
	private Cientifico cientifico;

	// Para evitar un serializado infinito, conseguir que se serialice y evitar
	// error por lazy
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fkUsuario", nullable = false)
	private Usuario usuario;

	public RelacionUsuarioCientifico() {
	}

	public RelacionUsuarioCientifico(int id, Usuario usuario, Cientifico cientifico) {
		this.id = id;
		this.usuario = usuario;
		this.cientifico = cientifico;
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

	public Cientifico getCientifico() {
		return cientifico;
	}

	public void setCientifico(Cientifico cientifico) {
		this.cientifico = cientifico;
	}

	@Override
	public String toString() {
		return "RelacionCientificosContribuciones [id=" + id + ", usuario=" + usuario + ", cientifico="
				+ cientifico + "]";
	}

	public RelacionUsuarioCientifico setRelacion(RelacionUsuarioCientifico relacion) {
		return new RelacionUsuarioCientifico(this.getId(), relacion.getUsuario(), relacion.getCientifico());
	}

}
