package co.edu.uniandes.dse.museoartemoderno.services;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.museoartemoderno.entities.MovimientoArtisticoEntity;
import co.edu.uniandes.dse.museoartemoderno.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.museoartemoderno.repositories.MovimientoArtisticoRepository;

@Service
public class MovimientoArtisticoEntityService {

	@Autowired
	MovimientoArtisticoRepository movimientoArtisticoRepository;
	
	/**
	 *Da todos los movimientos artisticos 
	 * @return lista con todos los movimientos artisticos
	 */
	@Transactional
	public List<MovimientoArtisticoEntity> getMovimientosArtisticos()
	{
		return movimientoArtisticoRepository.findAll();
	}
	
	/**
	 * Da un movimiento artistico a partir de su Id
	 * @param pId - Id del movimiento que se quiere buscar
	 * @return El movimiento artistico con el Id buscado
	 * @throws EntityNotFoundException si no se encuentra la entidad buscada
	 */
	@Transactional
	public MovimientoArtisticoEntity getMovimientoArtistico(Long pId) throws EntityNotFoundException
	{
		Optional<MovimientoArtisticoEntity> movimientoBuscado = movimientoArtisticoRepository.findById(pId);
		if(movimientoBuscado.isEmpty())
		{
			throw new EntityNotFoundException("Movimiento Artistico no encontrado");
		}
		return movimientoBuscado.get();
	}
	
}
