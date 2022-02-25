package co.edu.uniandes.dse.museoartemoderno.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.museoartemoderno.entities.ArtistaEntity;
import co.edu.uniandes.dse.museoartemoderno.entities.MovimientoArtisticoEntity;
import co.edu.uniandes.dse.museoartemoderno.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.museoartemoderno.repositories.ArtistaRepository;
import co.edu.uniandes.dse.museoartemoderno.repositories.MovimientoArtisticoRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MovimientoArtisticoArtistaService 
{
	@Autowired
	private MovimientoArtisticoRepository movimientoArtisticoRepository;

	@Autowired
	private ArtistaRepository artistaRepository;
	


	/**
	 * Asocia un artista a un movimiento artistico cuyo Id es dado por parametro
	 * @param movimientoId - Id del movimiento al que se le asociara el artista
	 * @param artistaID - Id del artistsa que se asociara
	 * @return - instancia del artista que fue asociado
	 * @throws EntityNotFoundException si no se encuentra alguna o cualquier entidad
	 */
	public ArtistaEntity addArtista(Long movimientoId, Long artistaId) throws EntityNotFoundException
	{
		log.info("Inicia proceso de asociar al movimiento "+movimientoId+" el artista "+artistaId);
		Optional<MovimientoArtisticoEntity> movimientoEntity = movimientoArtisticoRepository.findById(movimientoId);
		Optional<ArtistaEntity> artistaEntity = artistaRepository.findById(artistaId);
		if(movimientoEntity.isEmpty())
		{
			throw new EntityNotFoundException("Movimiento Artistico not found");
		}
		if(artistaEntity.isEmpty())
		{
			throw new EntityNotFoundException("Artista not found");
		}

		artistaEntity.get().getMovimientos().add(movimientoEntity.get());
		log.info("Termina proceso de asociar al movimiento "+movimientoId+" el artista "+artistaId);
		return artistaEntity.get();
	}
}
