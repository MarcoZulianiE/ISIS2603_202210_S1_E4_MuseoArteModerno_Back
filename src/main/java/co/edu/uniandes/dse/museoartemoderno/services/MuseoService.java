package co.edu.uniandes.dse.museoartemoderno.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.museoartemoderno.entities.MuseoEntity;
import co.edu.uniandes.dse.museoartemoderno.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.museoartemoderno.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.museoartemoderno.repositories.MuseoRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MuseoService {

    @Autowired
    MuseoRepository museoRepository;
    
    
	/**
	 * Guarda un nuevo museo en la base de datos si cumple con las reglas de negocio
	 * @param MuseoEntity - Entidad de la cual se verificaran las reglas de negocio
	 * @return - La entidad de museo para guardar
	 * @throws EntityNotFoundException - Exception que se lanza si no se encuentra la entidad
	 * @throws IllegalOperationException - Exception que se lanza si no se cumple alguna regla de negocio
	 */
	@Transactional
	public MuseoEntity createMuseo(MuseoEntity museoEntity) throws EntityNotFoundException, IllegalOperationException {
		log.info("Inicia el proceso de creación del museo");
		
	
		log.info("Termina el proceso de creación del museo");
		return museoRepository.save(museoEntity);
	}
    
    
	/**
	 * @return Lista de todas las entidades de tipo Museo
	 */
	@Transactional
    public List<MuseoEntity> getMuseos() {
    	log.info("Inicia proceso de consulta de todos los museos");
    	return museoRepository.findAll();
    }
	
	
	/**
	 * Encuentra en la base de datos el museo con un id especifico
	 * @param museoId - Id del museo que se quiere obtener
	 * @return - El museo con el id dado por parametro
	 * @throws EntityNotFoundException - Exception que se lanza si no se encuentra la entidad
	 */
	@Transactional
	public MuseoEntity getMuseo(Long museoId) throws EntityNotFoundException {
		log.info("Inicia proceso de consultar el museo con id: " + museoId);
		Optional<MuseoEntity> museoEntity = museoRepository.findById(museoId);
		
		//TODO
		if (museoEntity.isEmpty())
			throw new EntityNotFoundException("MUSEO NOT FOUND");
		
		log.info("Termina proceso de consultar el museo con id: " + museoId);
		return museoEntity.get();
	}
	
	/**
	 * Actualizar un museo dado su Id
	 * @param museoId - Id del museo que se quiere actualizar
	 * @param museo - entidad con los cambios
	 * @return - entidad actualizada
	 * @throws EntityNotFoundException - Exception que se lanza si no se encuentra la entidad
	 * @throws IllegalOperationException - Exception que se lanza si no se cumple alguna regla de negocio
	 */
	@Transactional
	public MuseoEntity updateMuseo(Long museoId, MuseoEntity museo)
			throws EntityNotFoundException, IllegalOperationException {
		log.info("Inicia proceso de actualizar el museo con id: " + museoId);
		Optional<MuseoEntity> museoEntity = museoRepository.findById(museoId);
		
		//TODO
		if (museoEntity.isEmpty())
			throw new EntityNotFoundException("MUSEO NOT FOUND");

		museo.setId(museoId);
		log.info("Termina proceso de actualizar el museo con id: " + museoId);
		return museoRepository.save(museo);
	}
	
	/**
	 * Eliminar un museo dado su Id 
	 * @param museoId - Id del museo que se quiere eliminar
	 * @throws EntityNotFoundException - Exception que se lanza si no se encuentra la entidad
	 * @throws IllegalOperationException - Exception que se lanza si no se cumple alguna regla de negocio
	 */
	@Transactional
	public void deleteMuseo(Long museoId) throws EntityNotFoundException, IllegalOperationException {
		log.info("Inicia proceso de borrar el museo con id: ", museoId);
		Optional<MuseoEntity> museoEntity = museoRepository.findById(museoId);
		
		//TODO
		if (museoEntity.isEmpty())
			throw new EntityNotFoundException("MUSEO NOT FOUND");
		
		museoRepository.deleteById(museoId);
		log.info("Termina proceso de borrar el museo con id: " + museoId);
	}
	
	
}
