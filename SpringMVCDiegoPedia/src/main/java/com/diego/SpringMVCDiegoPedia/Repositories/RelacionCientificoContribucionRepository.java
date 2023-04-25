package com.diego.SpringMVCDiegoPedia.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.diego.SpringMVCDiegoPedia.Models.Cientifico;
import com.diego.SpringMVCDiegoPedia.Models.Contribucion;
import com.diego.SpringMVCDiegoPedia.Models.RelacionCientificoContribucion;

public interface RelacionCientificoContribucionRepository
		extends JpaRepository<RelacionCientificoContribucion, Integer> {
	List<RelacionCientificoContribucion> findByContribucion(Contribucion contribucion);

	List<RelacionCientificoContribucion> findByCientifico(Cientifico cientifico);

	List<RelacionCientificoContribucion> findByEpocaContaining(String epocaCoincidencia);

	List<RelacionCientificoContribucion> findByAportacionContaining(String aportacionCoincidencia);

	@Query(value = "select aportacion from cientifico_contribucion group by aportacion", nativeQuery = true)
	List<String> findAportacionesRelacion();
}