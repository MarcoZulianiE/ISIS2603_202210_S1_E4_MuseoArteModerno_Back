package co.edu.uniandes.dse.museoartemoderno.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;


import co.edu.uniandes.dse.museoartemoderno.repositories.FechaRepository;
import co.edu.uniandes.dse.museoartemoderno.repositories.ObraRepository;


import co.edu.uniandes.dse.museoartemoderno.entities.FechaEntity;
import co.edu.uniandes.dse.museoartemoderno.entities.ObraEntity;
import co.edu.uniandes.dse.museoartemoderno.exceptions.EntityNotFoundException;

@Slf4j
@Service
public class ObraFechaService {
	
	@Autowired
	private ObraRepository obraRepository;

	@Autowired
	private FechaRepository fechaRepository;
	
	
	/**
	 * Asocia una fecha existente a una Obra
	 *
	 * @param obraId Identificador de la instancia de obra
	 * @param artistaId   Identificador de la instancia de artista
	 * @return Instancia de ArtistaEntity que fue asociada a Obra
	 */

	@Transactional
	public FechaEntity addFecha(Long obraId, Long fechaId) throws EntityNotFoundException {
		log.info("Inicia proceso de asociar una fecha a la obra con id = {0}", obraId);
		Optional<ObraEntity> obraEntity = obraRepository.findById(obraId);
		Optional<FechaEntity> fechaEntity = fechaRepository.findById(fechaId);

		if (obraEntity.isEmpty())
			throw new EntityNotFoundException("Obra no encontrada.");

		if (fechaEntity.isEmpty())
			throw new EntityNotFoundException("Fecha no encontada.");
		
		(obraEntity.get()).setFechaPublicacion(fechaEntity.get());
		log.info("Termina proceso de asociar una fecha a la obra con id = {0}", obraId);
		return fechaEntity.get();
	}

	/**
	 * Obtiene la fecha de publicacion de una obra
	 *
	 * @param obraId Identificador de la instancia de obra
	 * @return Fecha asociada a la obra dado su id
	 */
	@Transactional
	public FechaEntity getFechaPublicacion(Long obraId) throws EntityNotFoundException {
		log.info("Inicia proceso de consultar la fecha de publicación la obra con id = {0}", obraId);
		Optional<ObraEntity> obraEntity = obraRepository.findById(obraId);
		if (obraEntity.isEmpty())
			throw new EntityNotFoundException("Ninguna obra fue encontrada con el id dado.");
		
		log.info("Termina proceso de consultar la fecha de publicación de la obra con id = {0}", obraId);
		return obraEntity.get().getFechaPublicacion();
	}


	/**
	 * Desasocia una Fecha existente de una Obra existente
	 * @param obraId Identificador de la instancia de obra
	 */
	@Transactional
	public void removerFechaPublicacion(Long obraId) throws EntityNotFoundException {
		log.info("Inicia proceso de borrar la fecha de publicación de la obra con id = {0}", obraId);
		Optional<ObraEntity> obraEntity = obraRepository.findById(obraId);
		if (obraEntity.isEmpty())
			throw new EntityNotFoundException("Ninguna obra fue encontrada con el id dado.");


		obraEntity.get().setFechaPublicacion(null);
		log.info("Finaliza proceso de borrar la fecha de publicación de la obra con id = {0}", obraId);
	}

}