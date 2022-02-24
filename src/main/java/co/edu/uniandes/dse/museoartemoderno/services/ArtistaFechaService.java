package co.edu.uniandes.dse.museoartemoderno.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.museoartemoderno.repositories.FechaRepository;
import co.edu.uniandes.dse.museoartemoderno.repositories.ArtistaRepository;
import co.edu.uniandes.dse.museoartemoderno.entities.FechaEntity;
import co.edu.uniandes.dse.museoartemoderno.entities.ArtistaEntity;
import co.edu.uniandes.dse.museoartemoderno.exceptions.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ArtistaFechaService {

	@Autowired
	private FechaRepository fechaRepository;

	@Autowired
	private ArtistaRepository artistaRepository;
	
	/**
	 * Asocia una fecha de Nacimiento al artista cuyo id es dado por parametro
	 * @param artistaId - Id del artista a asociar
	 * @param fechaNacimientoId - Id de la fecha a asociar
	 * @return - instancia de fecha que fue asociada al artista
	 * @throws EntityNotFoundException - Exception que se lanza si no se encuentra la entidad
	 */
	@Transactional
	public FechaEntity addFechaNacimiento(Long artistaId, Long fechaNacimientoId) throws EntityNotFoundException {
		log.info("Inicia proceso de asociarle una fecha de Nacimiento al artista con id: " + artistaId);
		Optional<ArtistaEntity> artistaEntity = artistaRepository.findById(artistaId);
		Optional<FechaEntity> fechaEntity = fechaRepository.findById(fechaNacimientoId);

		if (artistaEntity.isEmpty())
			throw new EntityNotFoundException("ARTISTA NOT FOUND");

		if (fechaEntity.isEmpty())
			throw new EntityNotFoundException("FECHA NOT FOUND");

		fechaEntity.get().getArtistasNacimiento().add(artistaEntity.get());
		log.info("Termina proceso de asociarle una fecha de Nacimiento al artista con id: " + artistaId);
		return fechaEntity.get();
	}
	
	/**
	 * Asocia una fecha de Fallecimiento al artista cuyo id es dado por parametro
	 * @param artistaId - Id del artista a asociar
	 * @param fechaFallecimientoId - Id de la fecha a asociar
	 * @return - instancia de fecha que fue asociada al artista
	 * @throws EntityNotFoundException - Exception que se lanza si no se encuentra la entidad
	 */
	@Transactional
	public FechaEntity addFechaFallecimiento(Long artistaId, Long fechaFallecimientoId) throws EntityNotFoundException {
		log.info("Inicia proceso de asociarle una fecha de Fallecimiento al artista con id: " + artistaId);
		Optional<ArtistaEntity> artistaEntity = artistaRepository.findById(artistaId);
		Optional<FechaEntity> fechaEntity = fechaRepository.findById(fechaFallecimientoId);

		if (artistaEntity.isEmpty())
			throw new EntityNotFoundException("ARTISTA NOT FOUND");

		if (fechaEntity.isEmpty())
			throw new EntityNotFoundException("FECHA NOT FOUND");

		fechaEntity.get().getArtistasFallecimiento().add(artistaEntity.get());
		log.info("Termina proceso de asociarle una fecha de Fallecimiento al artista con id: " + artistaId);
		return fechaEntity.get();
	}
	
	/**
	 * Devuelve la fecha de Nacimiento asociada con el artista cuyo id llega por parametro
	 * @param artistaId - Id del artista del que se desea conocer la fecha de Nacimiento
	 * @return - La fecha de Nacimiento solicitada
	 * @throws EntityNotFoundException - Exception que se lanza si no se encuentra la entidad
	 */
	@Transactional
	public FechaEntity getFechaNacimiento(Long artistaId) throws EntityNotFoundException {
		log.info("Inicia proceso de consultar la fecha de Nacimiento del artista con id: " + artistaId);
		Optional<ArtistaEntity> artistaEntity = artistaRepository.findById(artistaId);
		if (artistaEntity.isEmpty())
			throw new EntityNotFoundException("ARTISTA NOT FOUND");

		FechaEntity fechaEntity = artistaEntity.get().getFechaNacimiento();

		if (fechaEntity == null)
			throw new EntityNotFoundException("FECHA NOT FOUND");

		log.info("Termina proceso de consultar la fecha de Nacimiento del artista con id: " + artistaId);
		return fechaEntity;
	}
	
	/**
	 * Devuelve la fecha de Fallecimiento asociada con el artista cuyo id llega por parametro
	 * @param artistaId - Id del artista del que se desea conocer la fecha de Fallecimiento
	 * @return - La fecha de Fallecimiento solicitada
	 * @throws EntityNotFoundException - Exception que se lanza si no se encuentra la entidad
	 */
	@Transactional
	public FechaEntity getFechaFallecimiento(Long artistaId) throws EntityNotFoundException {
		log.info("Inicia proceso de consultar la fecha de Fallecimiento del artista con id: " + artistaId);
		Optional<ArtistaEntity> artistaEntity = artistaRepository.findById(artistaId);
		if (artistaEntity.isEmpty())
			throw new EntityNotFoundException("ARTISTA NOT FOUND");

		FechaEntity fechaEntity = artistaEntity.get().getFechaFallecimiento();

		if (fechaEntity == null)
			throw new EntityNotFoundException("FECHA NOT FOUND");

		log.info("Termina proceso de consultar la fecha de Fallecimiento del artista con id: " + artistaId);
		return fechaEntity;
	}
	
	/**
	 * Remplaza la Fecha de Nacimiento del artista por una nueva
	 * @param artistaId - Id del artistat del cual se quiere modificar la fecha de Nacimiento
	 * @param fechaNacimientoId - Id de la nueva fecha de Nacimiento por la cual se quiere remplazar
	 * @return - Nueva fecha de Nacimiento asociada al artista
	 * @throws EntityNotFoundException - Exception que se lanza si no se encuentra la entidad
	 */
	@Transactional
	public FechaEntity replaceFechaNacimiento(Long artistaId, Long fechaNacimientoId) throws EntityNotFoundException {
		log.info("Inicia proceso de actualizar la fecha de Nacimiento del artista con id:" + artistaId);
		Optional<FechaEntity> fechaEntity = fechaRepository.findById(fechaNacimientoId);
		if (fechaEntity.isEmpty())
			throw new EntityNotFoundException("FECHA NOT FOUND");

		Optional<ArtistaEntity> artistaEntity = artistaRepository.findById(artistaId);
		if (artistaEntity.isEmpty())
			throw new EntityNotFoundException("ARTISTA NOT FOUND");

		artistaEntity.get().setFechaNacimiento(fechaEntity.get());
		log.info("Termina proceso de actualizar la fecha de Nacimiento del artista con id:" + artistaId);
		return fechaEntity.get();
	}
	
	/**
	 * Remplaza la Fecha de Fallecimiento del artista por una nueva
	 * @param artistaId - Id del artistat del cual se quiere modificar la fecha de Fallecimiento
	 * @param fechaFallecimientoId - Id de la nueva fecha de Fallecimiento por la cual se quiere remplazar
	 * @return - Nueva fecha de Fallecimiento asociada al artista
	 * @throws EntityNotFoundException - Exception que se lanza si no se encuentra la entidad
	 */
	@Transactional
	public FechaEntity replaceFechaFallecimiento(Long artistaId, Long fechaFallecimientoId) throws EntityNotFoundException {
		log.info("Inicia proceso de actualizar la fecha de Fallecimiento del artista con id: " + artistaId);
		Optional<FechaEntity> fechaEntity = fechaRepository.findById(fechaFallecimientoId);
		if (fechaEntity.isEmpty())
			throw new EntityNotFoundException("FECHA NOT FOUND");

		Optional<ArtistaEntity> artistaEntity = artistaRepository.findById(artistaId);
		if (artistaEntity.isEmpty())
			throw new EntityNotFoundException("ARTISTA NOT FOUND");

		artistaEntity.get().setFechaFallecimiento(fechaEntity.get());
		log.info("Termina proceso de actualizar la fecha de Fallecimiento del artista con id: " + artistaId);
		return fechaEntity.get();
	}
	
	/**
	 * Elimina la fecha de Nacimiento asociada a un artista
	 * @param artistaId - Id del artista del cual se quiere eliminar la fecha de Nacimiento
	 * @throws EntityNotFoundException - Exception que se lanza si no se encuentra la entidad
	 */
	@Transactional
	public void removeFechaNacimiento(Long artistaId) throws EntityNotFoundException {
		log.info("Inicia proceso de borrar la fecha de Nacimiento del artista con id: ", artistaId);
		Optional<ArtistaEntity> artistaEntity = artistaRepository.findById(artistaId);
		if (artistaEntity.isEmpty())
			throw new EntityNotFoundException("ARTISTA NOT FOUND");

		if (artistaEntity.get().getFechaNacimiento() == null) {
			throw new EntityNotFoundException("El autor no tiene fecha de Nacimiento");
		}
		Optional<FechaEntity> fechaEntity = fechaRepository.findById(artistaEntity.get().getFechaNacimiento().getId());

		fechaEntity.ifPresent(fecha -> {
			artistaEntity.get().setFechaNacimiento(null);
			fecha.getArtistasNacimiento().remove(artistaEntity.get());
		});

		log.info("Termina proceso de borrar la fecha de Nacimiento del artista con id: ", artistaId);
	}
	
	/**
	 * Elimina la fecha de Fallecimiento asociada a un artista
	 * @param artistaId - Id del artista del cual se quiere eliminar la fecha de Fallecimiento
	 * @throws EntityNotFoundException - Exception que se lanza si no se encuentra la entidad
	 */
	@Transactional
	public void removeFechaFallecimiento(Long artistaId) throws EntityNotFoundException {
		log.info("Inicia proceso de borrar la fecha de Fallecimiento del artista con id: ", artistaId);
		Optional<ArtistaEntity> artistaEntity = artistaRepository.findById(artistaId);
		if (artistaEntity.isEmpty())
			throw new EntityNotFoundException("ARTISTA NOT FOUND");

		if (artistaEntity.get().getFechaFallecimiento() == null) {
			throw new EntityNotFoundException("El autor no tiene fecha de Nacimiento");
		}
		Optional<FechaEntity> fechaEntity = fechaRepository.findById(artistaEntity.get().getFechaFallecimiento().getId());

		fechaEntity.ifPresent(fecha -> {
			artistaEntity.get().setFechaFallecimiento(null);
			fecha.getArtistasFallecimiento().remove(artistaEntity.get());
		});

		log.info("Termina proceso de borrar la fecha de Fallecimiento del artista con id: ", artistaId);
	}	
}