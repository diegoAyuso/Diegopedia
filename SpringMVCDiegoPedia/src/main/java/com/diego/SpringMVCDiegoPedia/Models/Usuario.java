package com.diego.SpringMVCDiegoPedia.Models;

import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "password")
	private String password;

	@OneToMany(mappedBy = "id", fetch = FetchType.LAZY)
	Set<RelacionUsuarioCientifico> registros;

	public Usuario() {
	}

	public Usuario(int id, String nombre, String password,
			Set<RelacionUsuarioCientifico> registros) {
		this.id = id;
		this.nombre = nombre;
		this.password = password;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public Set<RelacionUsuarioCientifico> getRegistros() {
		return registros;
	}

	public void setRegistros(Set<RelacionUsuarioCientifico> registros) {
		this.registros = registros;
	}


	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", password=" + password + "]";
	}

	public Usuario setUsuario(Usuario usuario) {
		return new Usuario(this.getId(), usuario.getNombre(), usuario.getPassword(), usuario.getRegistros());
	}
}