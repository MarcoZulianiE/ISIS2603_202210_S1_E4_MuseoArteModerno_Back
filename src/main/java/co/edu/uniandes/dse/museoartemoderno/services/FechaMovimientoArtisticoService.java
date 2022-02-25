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
import co.edu.uniandes.dse.museoartemoderno.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.museoartemoderno.repositories.FechaRepository;
import co.edu.uniandes.dse.museoartemoderno.repositories.MovimientoArtisticoRepository;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service


public class FechaMovimientoArtisticoService {

	@Autowired
	public MovimientoArtisticoRepository movimientoartisticoRepository;
	@Autowired
	public FechaRepository fechaRepository;
	@Transactional
	public MovimientoArtisticoEntity addFecha(long fechaId,long movimientoId) throws EntityNotFoundException{
		log.info("inicio proceso de asociar fecha a un movimiento");
		Optional<FechaEntity> fechaEntity = fechaRepository.findById(fechaId);
		Optional<MovimientoArtisticoEntity> movimientoEntity = movimientoartisticoRepository.findById(movimientoId);
        if (movimientoEntity.isEmpty())
                throw new EntityNotFoundException("MOVIMIENTO_NOT_FOUND");
        fechaEntity.get().getMovimientoArtisticos().add(movimientoEntity.get());
        log.info("Termina proceso de asociarle la fecha a un movimiento");
        return movimientoEntity.get();
	}
	@Transactional
    public List<MovimientoArtisticoEntity> getMovimientos(Long fechaId) throws EntityNotFoundException {
        log.info("inicio busqueda");
        Optional<FechaEntity> fechaEntity = fechaRepository.findById(fechaId);
        if (fechaEntity.isEmpty())
            throw new EntityNotFoundException("FECHA_NOT_FOUND");
        log.info("fin");

		return fechaEntity.get().getMovimientoArtisticos()
;

		
	}
	@Transactional
    public MovimientoArtisticoEntity getMovimiento(Long bookId, Long authorId)
                    throws EntityNotFoundException, IllegalOperationException {
            log.info("inicio");
            Optional<MovimientoArtisticoEntity> movimientoEntity = movimientoartisticoRepository.findById(authorId);
            Optional<FechaEntity> fechaEntity = fechaRepository.findById(bookId);

            if (movimientoEntity.isEmpty())
                    throw new EntityNotFoundException("AUTHOR_NOT_FOUND");

            if (fechaEntity.isEmpty())
                    throw new EntityNotFoundException("BOOK_NOT_FOUND");
            log.info("fin");
            if (fechaEntity.get().getMovimientoArtisticos().contains(movimientoEntity.get()))
                    return movimientoEntity.get();

            throw new IllegalOperationException("Operación Ilegal");
}
}
