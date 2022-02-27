package co.edu.uniandes.dse.museoartemoderno.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.museoartemoderno.repositories.PaisRepository;
import co.edu.uniandes.dse.museoartemoderno.repositories.ArtistaRepository;
import co.edu.uniandes.dse.museoartemoderno.entities.PaisEntity;
import co.edu.uniandes.dse.museoartemoderno.entities.ArtistaEntity;
import co.edu.uniandes.dse.museoartemoderno.exceptions.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ArtistaPaisService {

	@Autowired
	private PaisRepository paisRepository;

	@Autowired
	private ArtistaRepository artistaRepository;
	
	/**
	 * Remplazar el pais de un artista.
	 *
	 * @param artistaId      id del artista que se quiere actualizar.
	 * @param paisId El id del pais que se será del artista.
	 * @return el nuevo artista.
	 */

	@Transactional
	public ArtistaEntity replaceLugarNacimiento(Long artistaId, Long paisId) throws EntityNotFoundException {
		log.info("Inicia proceso de actualizar el artista con id: ", artistaId);
		Optional<ArtistaEntity> artistaEntity = artistaRepository.findById(artistaId);
		if (artistaEntity.isEmpty())
			throw new EntityNotFoundException("ARTISTA NOT FOUND");

		Optional<PaisEntity> paisEntity = paisRepository.findById(paisId);
		if (paisEntity.isEmpty())
			throw new EntityNotFoundException("PAIS NACIMIENTO NOT FOUND");

		artistaEntity.get().setLugarNacimiento(paisEntity.get());
		log.info("Termina proceso de actualizar el artista con id: ", artistaId);

		return artistaEntity.get();
	}
	
	/**
	 * Remplazar el pais de un artista.
	 *
	 * @param artistaId      id del artista que se quiere actualizar.
	 * @param paisId El id del pais que se será del artista.
	 * @return el nuevo artista.
	 */

	@Transactional
	public ArtistaEntity replaceLugarFallecimiento(Long artistaId, Long paisId) throws EntityNotFoundException {
		log.info("Inicia proceso de actualizar el artista con id: ", artistaId);
		Optional<ArtistaEntity> artistaEntity = artistaRepository.findById(artistaId);
		if (artistaEntity.isEmpty())
			throw new EntityNotFoundException("ARTISTA NOT FOUND");

		Optional<PaisEntity> paisEntity = paisRepository.findById(paisId);
		if (paisEntity.isEmpty())
			throw new EntityNotFoundException("PAIS NACIMIENTO NOT FOUND");

		artistaEntity.get().setLugarFallecimiento(paisEntity.get());
		log.info("Termina proceso de actualizar el artista con id: ", artistaId);

		return artistaEntity.get();
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
			throw new EntityNotFoundException("PAIS NACIMIENTO NOT FOUND");
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
			throw new EntityNotFoundException("PAIS FALLECIMIENTO NOT FOUND");
		}
		Optional<PaisEntity> paisEntity = paisRepository.findById(artistaEntity.get().getLugarFallecimiento().getId());

		paisEntity.ifPresent(pais -> {
			artistaEntity.get().setLugarFallecimiento(null);
			pais.getArtistasFallecimiento().remove(artistaEntity.get());
		});

		log.info("Termina proceso de borrar el pais de Fallecimiento del artista con id: ", artistaId);
	}	
}