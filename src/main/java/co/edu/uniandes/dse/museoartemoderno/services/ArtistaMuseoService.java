package co.edu.uniandes.dse.museoartemoderno.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.museoartemoderno.repositories.MuseoRepository;
import co.edu.uniandes.dse.museoartemoderno.repositories.ArtistaRepository;
import co.edu.uniandes.dse.museoartemoderno.entities.MuseoEntity;
import co.edu.uniandes.dse.museoartemoderno.entities.ArtistaEntity;
import co.edu.uniandes.dse.museoartemoderno.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.museoartemoderno.exceptions.IllegalOperationException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ArtistaMuseoService {

	@Autowired
	private MuseoRepository museoRepository;

	@Autowired
	private ArtistaRepository artistaRepository;
	
	/**
	 * Asocia un museo al artista cuyo id es dado por parametro
	 * @param artistaId - Id del artista a asociar
	 * @param museoId - Id del museo a asociar
	 * @return - instancia de museo que fue asociada al artista
	 * @throws EntityNotFoundException - Exception que se lanza si no se encuentra la entidad
	 */
	@Transactional
	public MuseoEntity addMuseo(Long artistaId, Long museoId) throws EntityNotFoundException {
		log.info("Inicia proceso de asociarle un museo al artista con id: " + artistaId);
		Optional<ArtistaEntity> artistaEntity = artistaRepository.findById(artistaId);
		Optional<MuseoEntity> museoEntity = museoRepository.findById(museoId);

		if (artistaEntity.isEmpty())
			throw new EntityNotFoundException("ARTISTA NOT FOUND");

		if (museoEntity.isEmpty())
			throw new EntityNotFoundException("MUSEO NOT FOUND");

		museoEntity.get().getArtistas().add(artistaEntity.get());
		log.info("Termina proceso de asociarle un museo al artista con id: ", artistaId);
		return museoEntity.get();
	}
	
	/**
	 * Devuelve una lista de todos los museos asociados con el artista cuyo id llega por parametro
	 * @param artistaId - Id del artista a buscar en los museos
	 * @return - Lista de museos asociados al artista
	 * @throws EntityNotFoundException - Exception que se lanza si no se encuentra la entidad
	 */
	@Transactional
	public List<MuseoEntity> getBooks(Long artistaId) throws EntityNotFoundException {
		log.info("Inicia proceso de consultar todos los libros del autor con id: " + artistaId);
		Optional<ArtistaEntity> artistaEntity = artistaRepository.findById(artistaId);
		if (artistaEntity.isEmpty())
			throw new EntityNotFoundException("ARTISTA NOT FOUND");

		List<MuseoEntity> museos = museoRepository.findAll();
		List<MuseoEntity> museosList = new ArrayList<>();

		for (MuseoEntity museo : museos) {
			if (museo.getArtistas().contains(artistaEntity.get())) {
				museosList.add(museo);
			}
		}
		log.info("Termina proceso de consultar todos los libros del autor con id: " + artistaId);
		return museosList;
	}
	
	/**
	 * Obtiene un museo con id especifico asociado a un artista dado su id
	 * @param artistaId - Id de un artista
	 * @param museoId - Id de un museo
	 * @return el museo del artista
	 * @throws EntityNotFoundException - Exception que se lanza si no se encuentra la entidad
	 * @throws IllegalOperationException - Exception que se lanza si no se cumple alguna regla de negocio
	 */
	@Transactional
	public MuseoEntity getMuseo(Long artistaId, Long museoId) throws EntityNotFoundException, IllegalOperationException {
		log.info("Inicia proceso de consultar el libro con id: " + artistaId + ", del artista con id: " + museoId);
		Optional<ArtistaEntity> artistaEntity = artistaRepository.findById(artistaId);
		Optional<MuseoEntity> museoEntity = museoRepository.findById(museoId);

		if (artistaEntity.isEmpty())
			throw new EntityNotFoundException("ARTISTA NOT FOUND");

		if (museoEntity.isEmpty())
			throw new EntityNotFoundException("MUSEO NOT FOUND");

		log.info("Termina proceso de consultar el libro con id: " + artistaId + ", del artista con id: " + museoId);
		if (museoEntity.get().getArtistas().contains(artistaEntity.get()))
			return museoEntity.get();

		throw new IllegalOperationException("El Museo no esta asociado con el Artista");
	}
	
	/**
	 * Remplaza los museos asociados a un artista cuyo id es dado por parametro
	 * @param artistaId - Id del artista
	 * @param museos - Instancias de museos a vincular con el artista
	 * @return - Lista de nuevos museos asociados al artista
	 * @throws EntityNotFoundException - Exception que se lanza si no se encuentra la entidad
	 */
	@Transactional
	public List<MuseoEntity> addMuseos(Long artistaId, List<MuseoEntity> museos) throws EntityNotFoundException {
		log.info("Inicia proceso de reemplazar los museos asociados al artista con id: " + artistaId);
		Optional<ArtistaEntity> artistaEntity = artistaRepository.findById(artistaId);
		if (artistaEntity.isEmpty())
			throw new EntityNotFoundException("ARTISTA NOT FOUND");

		for (MuseoEntity museo : museos) {
			Optional<MuseoEntity> museoEntity = museoRepository.findById(museo.getId());
			if (museoEntity.isEmpty())
				throw new EntityNotFoundException("MUSEO NOT FOUND");

			if (!museoEntity.get().getArtistas().contains(artistaEntity.get()))
				museoEntity.get().getArtistas().add(artistaEntity.get());
		}
		log.info("Finaliza proceso de reemplazar los museos asociados al artista con id: " + artistaId);
		return museos;
	}
	
	/**
	 * Elimina un museo asociado al artista
	 * @param artistaId - Id del artista del cual se quiere eliminar el museo
	 * @param museoId - Id del museo a eliminar del artista
	 * @throws EntityNotFoundException - Exception que se lanza si no se encuentra la entidad
	 */
	@Transactional
	public void removeMuseo(Long artistaId, Long museoId) throws EntityNotFoundException {
		log.info("Inicia proceso de borrar un museo del artista con id: " + artistaId);
		Optional<ArtistaEntity> artistaEntity = artistaRepository.findById(artistaId);
		if (artistaEntity.isEmpty())
			throw new EntityNotFoundException("ARTISTA NOT FOUND");

		Optional<MuseoEntity> museoEntity = museoRepository.findById(museoId);
		if (museoEntity.isEmpty())
			throw new EntityNotFoundException("MUSEO NOT FOUND");

		museoEntity.get().getArtistas().remove(artistaEntity.get());
		log.info("Finaliza proceso de borrar un libro del author con id: " + artistaId);
	}
	
}