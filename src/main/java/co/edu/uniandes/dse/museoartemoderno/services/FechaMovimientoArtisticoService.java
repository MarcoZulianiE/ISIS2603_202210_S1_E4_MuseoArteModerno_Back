package co.edu.uniandes.dse.museoartemoderno.services;

import java.util.List;
import java.util.Optional;

import org.modelmapper.spi.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.museoartemoderno.entities.FechaEntity;
import co.edu.uniandes.dse.museoartemoderno.entities.MovimientoArtisticoEntity;
import co.edu.uniandes.dse.museoartemoderno.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.museoartemoderno.repositories.FechaRepository;
import co.edu.uniandes.dse.museoartemoderno.repositories.MovimientoArtisticoRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j

public class FechaMovimientoArtisticoService {

	@Autowired
	public MovimientoArtisticoRepository movimientoartisticoRepository;
	@Autowired
	public FechaRepository fechaRepository;
	@Transactional
	public MovimientoArtisticoEntity addFecha(long fechaId,long movimientoId) throws EntityNotFoundException{
		log.info("inicio proceso de asociar fecha a un movimiento");
		Optional<MovimientoArtisticoEntity> movimientoEntity = movimientoartisticoRepository.findById(movimientoId);
        if (movimientoEntity.isEmpty())
                throw new EntityNotFoundException(ErrorMessage.MOVIMIENTO_NOT_FOUND);
        FechaEntity.get().getMovimientos().add(movimientoEntity.get());
        log.info("Termina proceso de asociarle la fecha a un movimiento");
        return movimientoEntity.get();
	}
	@Transactional
    public List<MovimientoArtisticoEntity> getMovimientos(Long fechaId) throws EntityNotFoundException {
		return null;
		
	}
}
