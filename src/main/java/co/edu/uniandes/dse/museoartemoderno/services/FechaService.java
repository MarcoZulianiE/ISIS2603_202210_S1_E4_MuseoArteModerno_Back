package co.edu.uniandes.dse.museoartemoderno.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.museoartemoderno.repositories.FechaRepository;
import co.edu.uniandes.dse.museoartemoderno.entities.FechaEntity;
import co.edu.uniandes.dse.museoartemoderno.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.museoartemoderno.exceptions.IllegalOperationException;
import lombok.extern.slf4j.Slf4j;



@Slf4j
@Service
public class FechaService {
    @Autowired
	FechaRepository fechaRepository;
    
    @Transactional
    public FechaEntity createFecha(FechaEntity fechaEntity) throws EntityNotFoundException, IllegalOperationException{
    	log.info("inicio creacion fecha");
        log.info("Termina proceso de creación de fecha");

    	return fechaRepository.save(fechaEntity);	
    }
    @Transactional
    public List<FechaEntity> getFechas() {
            log.info("Inicia proceso de consultar todas las fechas");
            return fechaRepository.findAll();
    }
    @Transactional
    public FechaEntity getFecha(Long fechaId) throws EntityNotFoundException{
    	log.info("inicio proceso busqueda fecha especificada");
    	Optional<FechaEntity> fechaEntity = fechaRepository.findById(fechaId);
    	if (fechaEntity.isEmpty())
            throw new EntityNotFoundException("FECHA_NO_ENCONTRADA");
    	log.info("fin proceso busqueda fecha especificada");

    	return FechaEntity.get();
    }
    @Transactional
    public void  deleteFecha(Long fechaId) throws EntityNotFoundException, IllegalOperationException{
    	log.info("inicio proceso borrado fecha especificada");

    	Optional<FechaEntity> fechaEntity = fechaRepository.findById(fechaId);
    	if (fechaEntity.isEmpty())
            throw new EntityNotFoundException("FECHA_NO_ENCONTRADA");
        List<ArtistaEntity> artistas = fechaEntity.get().getArtistas();
        if (!artistas.isEmpty())
            throw new IllegalOperationException("No se pudo borrar por que hay artistas con esa fecha");
    	log.info("fin proceso borrado fecha especificada");

        fechaRepository.deleteById(fechaId);

    }
    	
    }
     
    
    

