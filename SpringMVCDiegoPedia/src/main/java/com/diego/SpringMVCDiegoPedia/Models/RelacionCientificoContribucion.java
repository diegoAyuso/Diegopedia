package com.diego.SpringMVCDiegoPedia.Models;

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
@Table(name = "cientifico_contribucion")
public class RelacionCientificoContribucion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fkContribucion", nullable = false)
	private Contribucion contribucion;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fkCientifico", nullable = false)
	private Cientifico cientifico;

	@Column(name = "epoca")
	private String epoca;

	@Column(name = "aportacion")
	private String aportacion;

	public RelacionCientificoContribucion() {
	}

	public RelacionCientificoContribucion(int id, Contribucion contribucion, Cientifico cientifico, String epoca,
			String aportacion) {
		this.id = id;
		this.contribucion = contribucion;
		this.cientifico = cientifico;
		this.epoca = epoca;
		this.aportacion = aportacion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Contribucion getContribucion() {
		return contribucion;
	}

	public void setContribucion(Contribucion contribucion) {
		this.contribucion = contribucion;
	}

	public Cientifico getCientifico() {
		return cientifico;
	}

	public void setCientifico(Cientifico cientifico) {
		this.cientifico = cientifico;
	}

	public String getEpoca() {
		return epoca;
	}

	public void setEpoca(String epoca) {
		this.epoca = epoca;
	}

	public String getAportacion() {
		return aportacion;
	}

	public void setAportacion(String aportacion) {
		this.aportacion = aportacion;
	}

	@Override
	public String toString() {
		return "RelacionCientificosContribuciones [id=" + id + ", contribucion=" + contribucion + ", cientifico="
				+ cientifico + ", epoca=" + epoca + ", aportacion=" + aportacion + "]";
	}

}
