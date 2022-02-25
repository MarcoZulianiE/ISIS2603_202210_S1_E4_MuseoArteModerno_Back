package co.edu.uniandes.dse.museoartemoderno.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.museoartemoderno.repositories.MuseoRepository;
import co.edu.uniandes.dse.museoartemoderno.repositories.PaisRepository;
import co.edu.uniandes.dse.museoartemoderno.entities.MuseoEntity;
import co.edu.uniandes.dse.museoartemoderno.entities.PaisEntity;
import co.edu.uniandes.dse.museoartemoderno.exceptions.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MuseoPaisService {

	@Autowired
	private PaisRepository PaisRepository;

	@Autowired
	private MuseoRepository MuseoRepository;
	
	/**
	 * Asocia una Pais al Museo cuyo id es dado por parametro
	 * @param MuseoId - Id del Museo a asociar
	 * @param PaisId - Id de la Pais a asociar
	 * @return - instancia de Pais que fue asociada al Museo
	 * @throws EntityNotFoundException - Exception que se lanza si no se encuentra la entidad
	 */
	@Transactional
	public PaisEntity addPais(Long MuseoId, Long PaisId) throws EntityNotFoundException {
		log.info("Inicia proceso de asociarle una Pais de  al Museo con id: " + MuseoId);
		Optional<MuseoEntity> MuseoEntity = MuseoRepository.findById(MuseoId);
		Optional<PaisEntity> PaisEntity = PaisRepository.findById(PaisId);

		if (MuseoEntity.isEmpty())
			throw new EntityNotFoundException("Museo NOT FOUND");

		if (PaisEntity.isEmpty())
			throw new EntityNotFoundException("Pais NOT FOUND");

		PaisEntity.get().getMuseos().add(MuseoEntity.get());
		log.info("Termina proceso de asociarle una Pais de  al Museo con id: " + MuseoId);
		return PaisEntity.get();
	}
	
	
	/**
	 * Devuelve el Pais asociado con el Museo cuyo id llega por parametro
	 * @param MuseoId - Id del Museo del que se desea conocer el Pais
	 * @return El Pais solicitado
	 * @throws EntityNotFoundException - Exception que se lanza si no se encuentra la entidad
	 */
	@Transactional
	public PaisEntity getPais(Long MuseoId) throws EntityNotFoundException {
		log.info("Inicia proceso de consultar la Pais de  del Museo con id: " + MuseoId);
		Optional<MuseoEntity> MuseoEntity = MuseoRepository.findById(MuseoId);
		if (MuseoEntity.isEmpty())
			throw new EntityNotFoundException("Museo NOT FOUND");

		PaisEntity PaisEntity = MuseoEntity.get().getPais();

		if (PaisEntity == null)
			throw new EntityNotFoundException("Pais NOT FOUND");

		log.info("Termina proceso de consultar la Pais de  del Museo con id: " + MuseoId);
		return PaisEntity;
	}
	
	
	/**
	 * Remplaza el Pais del Museo por una nuevo
	 * @param MuseoId - Id del Museo del cual se quiere modificar el Pais 
	 * @param PaisId - Id del nuevo Pais por el cual se quiere remplazar
	 * @return - Nuevo Pais asociado al Museo
	 * @throws EntityNotFoundException - Exception que se lanza si no se encuentra la entidad
	 */
	@Transactional
	public PaisEntity replacePais(Long MuseoId, Long PaisId) throws EntityNotFoundException {
		log.info("Inicia proceso de actualizar la Pais de  del Museo con id:" + MuseoId);
		Optional<PaisEntity> PaisEntity = PaisRepository.findById(PaisId);
		if (PaisEntity.isEmpty())
			throw new EntityNotFoundException("Pais NOT FOUND");

		Optional<MuseoEntity> MuseoEntity = MuseoRepository.findById(MuseoId);
		if (MuseoEntity.isEmpty())
			throw new EntityNotFoundException("Museo NOT FOUND");

		MuseoEntity.get().setPais(PaisEntity.get());
		log.info("Termina proceso de actualizar la Pais de  del Museo con id:" + MuseoId);
		return PaisEntity.get();
	}
	
	
	/**
	 * Elimina el Pais asociado a un Museo
	 * @param MuseoId - Id del Museo del cual se quiere eliminar el Pais
	 * @throws EntityNotFoundException - Exception que se lanza si no se encuentra la entidad
	 */
	@Transactional
	public void removePais(Long MuseoId) throws EntityNotFoundException {
		log.info("Inicia proceso de borrar la Pais de  del Museo con id: ", MuseoId);
		Optional<MuseoEntity> MuseoEntity = MuseoRepository.findById(MuseoId);
		if (MuseoEntity.isEmpty())
			throw new EntityNotFoundException("Museo NOT FOUND");

		if (MuseoEntity.get().getPais() == null) {
			throw new EntityNotFoundException("El autor no tiene Pais de ");
		}
		Optional<PaisEntity> PaisEntity = PaisRepository.findById(MuseoEntity.get().getPais().getId());

		PaisEntity.ifPresent(Pais -> {
			MuseoEntity.get().setPais(null);
			Pais.getMuseos().remove(MuseoEntity.get());
		});

		log.info("Termina proceso de borrar el Pais del Museo con id: ", MuseoId);
	}
	

}
