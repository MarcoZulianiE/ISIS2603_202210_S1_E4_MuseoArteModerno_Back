package co.edu.uniandes.dse.museoartemoderno.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.museoartemoderno.repositories.PaisRepository;
import co.edu.uniandes.dse.museoartemoderno.repositories.ArtistaRepository;
import co.edu.uniandes.dse.museoartemoderno.entities.PaisEntity;
import co.edu.uniandes.dse.museoartemoderno.entities.ArtistaEntity;
import co.edu.uniandes.dse.museoartemoderno.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.museoartemoderno.exceptions.IllegalOperationException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ArtistaPaisService {

	@Autowired
	private PaisRepository paisRepository;

	@Autowired
	private ArtistaRepository artistaRepository;
	
	/**
	 * Asocia un Pais de Nacimiento al artista cuyo id es dado por parametro
	 * @param artistaId - Id del artista a asociar
	 * @param paisNacimientoId - Id del Pais a asociar
	 * @return - instancia de pais que fue asociada al artista
	 * @throws EntityNotFoundException - Exception que se lanza si no se encuentra la entidad
	 */
	@Transactional
	public PaisEntity addLugarNacimiento(Long artistaId, Long paisNacimientoId) throws EntityNotFoundException {
		log.info("Inicia proceso de asociarle un pais de Nacimiento al artista con id: " + artistaId);
		Optional<ArtistaEntity> artistaEntity = artistaRepository.findById(artistaId);
		Optional<PaisEntity> paisEntity = paisRepository.findById(paisNacimientoId);

		if (artistaEntity.isEmpty())
			throw new EntityNotFoundException("ARTISTA NOT FOUND");

		if (paisEntity.isEmpty())
			throw new EntityNotFoundException("PAIS NOT FOUND");

		paisEntity.get().getArtistasNacimiento().add(artistaEntity.get());
		log.info("Termina proceso de asociarle un pais de Nacimiento al artista con id: " + artistaId);
		return paisEntity.get();
	}
	
	/**
	 * Asocia un pais de Fallecimiento al artista cuyo id es dado por parametro
	 * @param artistaId - Id del artista a asociar
	 * @param paisId - Id de la pais a asociar
	 * @return - instancia de pais que fue asociada al artista
	 * @throws EntityNotFoundException - Exception que se lanza si no se encuentra la entidad
	 */
	@Transactional
	public PaisEntity addLugarFallecimiento(Long artistaId, Long fechaFallecimientoId) throws EntityNotFoundException {
		log.info("Inicia proceso de asociarle una fecha de Fallecimiento al artista con id: " + artistaId);
		Optional<ArtistaEntity> artistaEntity = artistaRepository.findById(artistaId);
		Optional<PaisEntity> paisEntity = paisRepository.findById(fechaFallecimientoId);

		if (artistaEntity.isEmpty())
			throw new EntityNotFoundException("ARTISTA NOT FOUND");

		if (paisEntity.isEmpty())
			throw new EntityNotFoundException("FECHA NOT FOUND");

		paisEntity.get().getArtistasFallecimiento().add(artistaEntity.get());
		log.info("Termina proceso de asociarle una fecha de Fallecimiento al artista con id: " + artistaId);
		return paisEntity.get();
	}
	
	/**
	 * Devuelve el pais de Nacimiento asociado con el artista cuyo id llega por parametro
	 * @param artistaId - Id del artista del que se desea conocer el pais de Nacimiento
	 * @return - El pais de Nacimiento solicitado
	 * @throws EntityNotFoundException - Exception que se lanza si no se encuentra la entidad
	 */
	@Transactional
	public PaisEntity getLugarNacimiento(Long artistaId) throws EntityNotFoundException {
		log.info("Inicia proceso de consultar el pais de Nacimiento del artista con id: " + artistaId);
		Optional<ArtistaEntity> artistaEntity = artistaRepository.findById(artistaId);
		if (artistaEntity.isEmpty())
			throw new EntityNotFoundException("ARTISTA NOT FOUND");

		PaisEntity paisEntity = artistaEntity.get().getLugarNacimiento();

		if (paisEntity == null)
			throw new EntityNotFoundException("PAIS NOT FOUND");

		log.info("Termina proceso de consultar el pais de Nacimiento del artista con id: " + artistaId);
		return paisEntity;
	}
	
	/**
	 * Devuelve el pais de Fallecimiento asociado con el artista cuyo id llega por parametro
	 * @param artistaId - Id del artista del que se desea conocer el pais de Fallecimiento
	 * @return - El pais de Fallecimiento solicitado
	 * @throws EntityNotFoundException - Exception que se lanza si no se encuentra la entidad
	 */
	@Transactional
	public PaisEntity getLugarFallecimiento(Long artistaId) throws EntityNotFoundException {
		log.info("Inicia proceso de consultar el pais de Fallecimiento del artista con id: " + artistaId);
		Optional<ArtistaEntity> artistaEntity = artistaRepository.findById(artistaId);
		if (artistaEntity.isEmpty())
			throw new EntityNotFoundException("ARTISTA NOT FOUND");

		PaisEntity paisEntity = artistaEntity.get().getLugarFallecimiento();

		if (paisEntity == null)
			throw new EntityNotFoundException("PAIS NOT FOUND");

		log.info("Termina proceso de consultar el pais de Fallecimiento del artista con id: " + artistaId);
		return paisEntity;
	}
	
	/**
	 * Remplaza el pais de Nacimiento del artista por una nueva
	 * @param artistaId - Id del artistat del cual se quiere modificar el pais de Nacimiento
	 * @param paisId - Id del nuevo pais de Nacimiento por el cual se quiere remplazar
	 * @return - Nuevo pais de Nacimiento asociado al artista
	 * @throws EntityNotFoundException - Exception que se lanza si no se encuentra la entidad
	 */
	@Transactional
	public PaisEntity replaceLugarNacimiento(Long artistaId, Long paisId) throws EntityNotFoundException {
		log.info("Inicia proceso de actualizar el pais de Nacimiento del artista con id:" + artistaId);
		Optional<PaisEntity> paisEntity = paisRepository.findById(paisId);
		if (paisEntity.isEmpty())
			throw new EntityNotFoundException("PAIS NOT FOUND");

		Optional<ArtistaEntity> artistaEntity = artistaRepository.findById(artistaId);
		if (artistaEntity.isEmpty())
			throw new EntityNotFoundException("ARTISTA NOT FOUND");

		artistaEntity.get().setLugarNacimiento(paisEntity.get());
		log.info("Termina proceso de actualizar el pais de Nacimiento del artista con id:" + artistaId);
		return paisEntity.get();
	}
	
	/**
	 * Remplaza el pais de Fallecimiento del artista por una nueva
	 * @param artistaId - Id del artistat del cual se quiere modificar la fecha de Fallecimiento
	 * @param paisId - Id de la nueva fecha de Fallecimiento por la cual se quiere remplazar
	 * @return - Nueva fecha de Fallecimiento asociada al artista
	 * @throws EntityNotFoundException - Exception que se lanza si no se encuentra la entidad
	 */
	@Transactional
	public PaisEntity replaceLugarFallecimiento(Long artistaId, Long paisId) throws EntityNotFoundException {
		log.info("Inicia proceso de actualizar el pais de Fallecimiento del artista con id: " + artistaId);
		Optional<PaisEntity> paisEntity = paisRepository.findById(paisId);
		if (paisEntity.isEmpty())
			throw new EntityNotFoundException("PAIS NOT FOUND");

		Optional<ArtistaEntity> artistaEntity = artistaRepository.findById(artistaId);
		if (artistaEntity.isEmpty())
			throw new EntityNotFoundException("ARTISTA NOT FOUND");

		artistaEntity.get().setLugarFallecimiento(paisEntity.get());
		log.info("Termina proceso de actualizar el pais de Fallecimiento del artista con id: " + artistaId);
		return paisEntity.get();
	}
	
	/**
	 * Elimina el pais de Nacimiento asociado a un artista
	 * @param artistaId - Id del artista del cual se quiere eliminar el pais de Nacimiento
	 * @throws EntityNotFoundException - Exception que se lanza si no se encuentra la entidad
	 */
	@Transactional
	public void removeLugarNacimiento(Long artistaId) throws EntityNotFoundException {
		log.info("Inicia proceso de borrar el pais de Nacimiento del artista con id: ", artistaId);
		Optional<ArtistaEntity> artistaEntity = artistaRepository.findById(artistaId);
		if (artistaEntity.isEmpty())
			throw new EntityNotFoundException("ARTISTA NOT FOUND");

		if (artistaEntity.get().getLugarNacimiento() == null) {
			throw new EntityNotFoundException("El autor no tiene lugar de Nacimiento");
		}
		Optional<PaisEntity> paisEntity = paisRepository.findById(artistaEntity.get().getLugarNacimiento().getId());

		paisEntity.ifPresent(pais -> {
			artistaEntity.get().setLugarNacimiento(null);
			pais.getArtistasNacimiento().remove(artistaEntity.get());
		});

		log.info("Termina proceso de borrar el pais de Nacimiento del artista con id: ", artistaId);
	}
	
	/**
	 * Elimina la fecha de Fallecimiento asociada a un artista
	 * @param artistaId - Id del artista del cual se quiere eliminar la fecha de Fallecimiento
	 * @throws EntityNotFoundException - Exception que se lanza si no se encuentra la entidad
	 */
	@Transactional
	public void removeLugarFallecimiento(Long artistaId) throws EntityNotFoundException {
		log.info("Inicia proceso de borrar el pais de Fallecimiento del artista con id: ", artistaId);
		Optional<ArtistaEntity> artistaEntity = artistaRepository.findById(artistaId);
		if (artistaEntity.isEmpty())
			throw new EntityNotFoundException("ARTISTA NOT FOUND");

		if (artistaEntity.get().getLugarFallecimiento() == null) {
			throw new EntityNotFoundException("El autor no tiene fecha de Fallecimiento");
		}
		Optional<PaisEntity> paisEntity = paisRepository.findById(artistaEntity.get().getLugarFallecimiento().getId());

		paisEntity.ifPresent(pais -> {
			artistaEntity.get().setLugarFallecimiento(null);
			pais.getArtistasFallecimiento().remove(artistaEntity.get());
		});

		log.info("Termina proceso de borrar el pais de Fallecimiento del artista con id: ", artistaId);
	}	
}