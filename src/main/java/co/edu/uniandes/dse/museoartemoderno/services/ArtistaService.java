package co.edu.uniandes.dse.museoartemoderno.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.museoartemoderno.repositories.ArtistaRepository;
import co.edu.uniandes.dse.museoartemoderno.entities.ArtistaEntity;
import co.edu.uniandes.dse.museoartemoderno.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.museoartemoderno.exceptions.IllegalOperationException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ArtistaService {

	@Autowired
	ArtistaRepository artistaRepository;
	
	/**
	 * Guarda un nuevo artista en la base de datos si cumple con las reglas de negocio
	 * @param artistaEntity - Entidad de la cual se verificaran las reglas de negocio
	 * @return - La entidad de artista para guardar
	 * @throws EntityNotFoundException - Exception que se lanza si no se encuentra la entidad
	 * @throws IllegalOperationException - Exception que se lanza si no se cumple alguna regla de negocio
	 */
	@Transactional
	public ArtistaEntity createArtista(ArtistaEntity artistaEntity) throws EntityNotFoundException, IllegalOperationException {
		log.info("Inicia proceso de creación del artista");
		
			
		
		log.info("Termina proceso de creación del artista");
		return artistaRepository.save(artistaEntity);
	}
	
	/**
	 * @return Lista de todas las entidades de tipo Artista
	 */
	@Transactional
    public List<ArtistaEntity> getBooks() {
            log.info("Inicia proceso de consulta de todos los artistas");
            return artistaRepository.findAll();
    }
	
	/**
	 * Encuentra en la base de datos el artista con un id especifico
	 * @param artistaId - Id del artista que se quiere obtener
	 * @return - El artista con el id dado por parametro
	 * @throws EntityNotFoundException - Exception que se lanza si no se encuentra la entidad
	 */
	@Transactional
	public ArtistaEntity getArtista(Long artistaId) throws EntityNotFoundException {
		log.info("Inicia proceso de consultar el artista con id: " + artistaId);
		Optional<ArtistaEntity> artistaEntity = artistaRepository.findById(artistaId);
		
		//TODO
		if (artistaEntity.isEmpty())
			throw new EntityNotFoundException("ARTISTA NOT FOUND");
		
		log.info("Termina proceso de consultar el artista con id: " + artistaId);
		return artistaEntity.get();
	}
	
	/**
	 * Actualizar artista dado su Id
	 * @param artistaId - Id del artista que se quiere actualizar
	 * @param artista - entidad con los cambios
	 * @return - entidad actualizada
	 * @throws EntityNotFoundException - Exception que se lanza si no se encuentra la entidad
	 * @throws IllegalOperationException - Exception que se lanza si no se cumple alguna regla de negocio
	 */
	@Transactional
	public ArtistaEntity updateArtista(Long artistaId, ArtistaEntity artista)
			throws EntityNotFoundException, IllegalOperationException {
		log.info("Inicia proceso de actualizar el libro con id: " + artistaId);
		Optional<ArtistaEntity> artistaEntity = artistaRepository.findById(artistaId);
		
		//TODO
		if (artistaEntity.isEmpty())
			throw new EntityNotFoundException("ARTISTA NOT FOUND");

		artista.setId(artistaId);
		log.info("Termina proceso de actualizar el libro con id: " + artistaId);
		return artistaRepository.save(artista);
	}
	
	/**
	 * Eliminar artista dado su Id 
	 * @param artistaId - Id del artista que se quiere eliminar
	 * @throws EntityNotFoundException - Exception que se lanza si no se encuentra la entidad
	 * @throws IllegalOperationException - Exception que se lanza si no se cumple alguna regla de negocio
	 */
	@Transactional
	public void deleteArtista(Long artistaId) throws EntityNotFoundException, IllegalOperationException {
		log.info("Inicia proceso de borrar el libro con id: ", artistaId);
		Optional<ArtistaEntity> artistaEntity = artistaRepository.findById(artistaId);
		
		//TODO
		if (artistaEntity.isEmpty())
			throw new EntityNotFoundException("ARTISTA NOT FOUND");
		
		artistaRepository.deleteById(artistaId);
		log.info("Termina proceso de borrar el libro con id: " + artistaId);
	}

}