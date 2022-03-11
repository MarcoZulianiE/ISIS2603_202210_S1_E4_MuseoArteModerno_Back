package co.edu.uniandes.dse.museoartemoderno.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.museoartemoderno.exceptions.ErrorMessage;
import co.edu.uniandes.dse.museoartemoderno.repositories.MovimientoArtisticoRepository;
import co.edu.uniandes.dse.museoartemoderno.repositories.ArtistaRepository;
import co.edu.uniandes.dse.museoartemoderno.entities.MovimientoArtisticoEntity;
import co.edu.uniandes.dse.museoartemoderno.entities.ArtistaEntity;
import co.edu.uniandes.dse.museoartemoderno.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.museoartemoderno.exceptions.IllegalOperationException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ArtistaMovimientoArtisticoService {

	@Autowired
	private MovimientoArtisticoRepository movimientoArtisticoRepository;

	@Autowired
	private ArtistaRepository artistaRepository;
	
	/**
	 * Asocia un movimiento artistico al artista cuyo id es dado por parametro
	 * @param artistaId - Id del artista a asociar
	 * @param museoId - Id del movimiento artistico a asociar
	 * @return - instancia de movimiento artistico que fue asociado al artista
	 * @throws EntityNotFoundException - Exception que se lanza si no se encuentra la entidad
	 */
	@Transactional
	public MovimientoArtisticoEntity addMovimientoArtistico(Long artistaId, Long movimientoArtisticoId) throws EntityNotFoundException {
		log.info("Inicia proceso de asociarle un movimiento artistico al artista con id: " + artistaId);
		Optional<ArtistaEntity> artistaEntity = artistaRepository.findById(artistaId);
		Optional<MovimientoArtisticoEntity> movimientoArtisticoEntity = movimientoArtisticoRepository.findById(movimientoArtisticoId);

		if (artistaEntity.isEmpty())
			throw new EntityNotFoundException(ErrorMessage.ARTISTA_NOT_FOUND);

		if (movimientoArtisticoEntity.isEmpty())
			throw new EntityNotFoundException(ErrorMessage.MOVIMIENTO_ARTISTICO_NOT_FOUND);

		movimientoArtisticoEntity.get().getArtistas().add(artistaEntity.get());
		log.info("Termina proceso de asociarle un movimiento artistico al artista con id: " + artistaId);
		return movimientoArtisticoEntity.get();
	}
	
	/**
	 * Devuelve una lista de todos los movimientos artisticos asociados con el artista
	 * @param artistaId - Id del artista a buscar en los movientos artisticos
	 * @return - Lista de movimientos artisticos asociados al artista
	 * @throws EntityNotFoundException - Exception que se lanza si no se encuentra la entidad
	 */
	@Transactional
	public List<MovimientoArtisticoEntity> getMovimientosArtisticos(Long artistaId) throws EntityNotFoundException {
		log.info("Inicia proceso de consultar todos los movimientos artisticos del autor con id: " + artistaId);
		Optional<ArtistaEntity> artistaEntity = artistaRepository.findById(artistaId);
		if (artistaEntity.isEmpty())
			throw new EntityNotFoundException(ErrorMessage.ARTISTA_NOT_FOUND);

		List<MovimientoArtisticoEntity> movimientosArtisticos = movimientoArtisticoRepository.findAll();
		List<MovimientoArtisticoEntity> movimientosArtisticosList = new ArrayList<>();

		for (MovimientoArtisticoEntity movimientoArtistico : movimientosArtisticos) {
			if (movimientoArtistico.getArtistas().contains(artistaEntity.get())) {
				movimientosArtisticosList.add(movimientoArtistico);
			}
		}
		log.info("Termina proceso de consultar todos los movimientos artisticos del autor con id: " + artistaId);
		return movimientosArtisticosList;
	}
	
	/**
	 * Obtiene un movimiento artistico con id especifico asociado a un artista dado su id
	 * @param artistaId - Id de un artista
	 * @param museoId - Id de un movimiento artistico
	 * @return el movimiento artistico del artista
	 * @throws EntityNotFoundException - Exception que se lanza si no se encuentra la entidad
	 * @throws IllegalOperationException - Exception que se lanza si no se cumple alguna regla de negocio
	 */
	@Transactional
	public MovimientoArtisticoEntity getMovimientoArtistico(Long artistaId, Long movimientoArtisticoId) throws EntityNotFoundException, IllegalOperationException {
		log.info("Inicia proceso de consultar el movimiento artistico con id: " + artistaId + ", del artista con id: " + movimientoArtisticoId);
		Optional<ArtistaEntity> artistaEntity = artistaRepository.findById(artistaId);
		Optional<MovimientoArtisticoEntity> movimientoArtisticoEntity = movimientoArtisticoRepository.findById(movimientoArtisticoId);

		if (artistaEntity.isEmpty())
			throw new EntityNotFoundException(ErrorMessage.ARTISTA_NOT_FOUND);

		if (movimientoArtisticoEntity.isEmpty())
			throw new EntityNotFoundException(ErrorMessage.MOVIMIENTO_ARTISTICO_NOT_FOUND);

		log.info("Termina proceso de consultar el movimiento artistico con id: " + artistaId + ", del artista con id: " + movimientoArtisticoId);
		if (movimientoArtisticoEntity.get().getArtistas().contains(artistaEntity.get()))
			return movimientoArtisticoEntity.get();

		throw new IllegalOperationException("El Movimiento artistico no esta asociado con el Artista");
	}
	
	/**
	 * 
	 * @param bookId
	 * @param list
	 * @return
	 * @throws EntityNotFoundException
	 */
	@Transactional
    public List<MovimientoArtisticoEntity> replaceMovimientosArtisticos(Long artistaId, List<MovimientoArtisticoEntity> list) throws EntityNotFoundException {
            log.info("Inicia proceso de reemplazar los autores del libro con id: ", artistaId);
            Optional<ArtistaEntity> artistaEntity = artistaRepository.findById(artistaId);
            if (artistaEntity.isEmpty())
                    throw new EntityNotFoundException(ErrorMessage.ARTISTA_NOT_FOUND);

            for (MovimientoArtisticoEntity author : list) {
                    Optional<MovimientoArtisticoEntity> movimientoArtisticoEntity = movimientoArtisticoRepository.findById(author.getId());
                    if (movimientoArtisticoEntity.isEmpty())
                            throw new EntityNotFoundException(ErrorMessage.MOVIMIENTO_ARTISTICO_NOT_FOUND);

                    if (!artistaEntity.get().getMovimientos().contains(movimientoArtisticoEntity.get()))
                    	artistaEntity.get().getMovimientos().add(movimientoArtisticoEntity.get());
            }
            log.info("Termina proceso de reemplazar los autores del libro con id: ", artistaId);
            return getMovimientosArtisticos(artistaId);
    }
	
	/**
	 * Elimina un movimiento artistico asociado al artista
	 * @param artistaId - Id del artista del cual se quiere eliminar el museo
	 * @param museoId - Id del movimiento artistico a eliminar del artista
	 * @throws EntityNotFoundException - Exception que se lanza si no se encuentra la entidad
	 */
	@Transactional
	public void removeMovimientoArtistico(Long artistaId, Long movimientoArtisticoId) throws EntityNotFoundException {
		log.info("Inicia proceso de borrar un movimiento artistico del artista con id: " + artistaId);
		Optional<ArtistaEntity> artistaEntity = artistaRepository.findById(artistaId);
		if (artistaEntity.isEmpty())
			throw new EntityNotFoundException(ErrorMessage.ARTISTA_NOT_FOUND);

		Optional<MovimientoArtisticoEntity> movimientoArtisticoEntity = movimientoArtisticoRepository.findById(movimientoArtisticoId);
		if (movimientoArtisticoEntity.isEmpty())
			throw new EntityNotFoundException(ErrorMessage.MOVIMIENTO_ARTISTICO_NOT_FOUND);

		movimientoArtisticoEntity.get().getArtistas().remove(artistaEntity.get());
		log.info("Finaliza proceso de borrar un movimiento artistico del artista con id: " + artistaId);
	}
	
}
