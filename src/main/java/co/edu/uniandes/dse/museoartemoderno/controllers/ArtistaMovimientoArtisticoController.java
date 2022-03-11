package co.edu.uniandes.dse.museoartemoderno.controllers;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uniandes.dse.museoartemoderno.dto.MovimientoArtisticoDTO;
import co.edu.uniandes.dse.museoartemoderno.dto.MovimientoArtisticoDetailDTO;
import co.edu.uniandes.dse.museoartemoderno.entities.MovimientoArtisticoEntity;
import co.edu.uniandes.dse.museoartemoderno.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.museoartemoderno.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.museoartemoderno.services.ArtistaMovimientoArtisticoService;

@RestController
@RequestMapping("/artistas")
public class ArtistaMovimientoArtisticoController {

	@Autowired
	private ArtistaMovimientoArtisticoService artistaMovimientoService;

	@Autowired
	private ModelMapper modelMapper;

	@PostMapping(value = "/{artistaId}/movimientoartisticos/{movimientoId}")
	@ResponseStatus(code = HttpStatus.OK)
	public MovimientoArtisticoDetailDTO addMovimientoArtistico(@PathVariable("artistaId") Long artistaId, @PathVariable("movimientoId") Long movimientoId) throws EntityNotFoundException {
		MovimientoArtisticoEntity movimientoArtisticoEntity = artistaMovimientoService.addMovimientoArtistico(artistaId, movimientoId);
		return modelMapper.map(movimientoArtisticoEntity, MovimientoArtisticoDetailDTO.class);
	}

	@GetMapping(value = "/{artistaId}/movimientoartisticos/{movimientoId}")
	@ResponseStatus(code = HttpStatus.OK)
	public MovimientoArtisticoDetailDTO getMovimientoArtistico(@PathVariable("movimientoId") Long authorId, @PathVariable("artistaId") Long bookId) throws EntityNotFoundException, IllegalOperationException {
		MovimientoArtisticoEntity movimientoArtisticoEntity = artistaMovimientoService.getMovimientoArtistico(bookId, authorId);
		return modelMapper.map(movimientoArtisticoEntity, MovimientoArtisticoDetailDTO.class);
	}

	@PutMapping(value = "/{artistaId}/movimientoartisticos")
	@ResponseStatus(code = HttpStatus.OK)
	public List<MovimientoArtisticoDetailDTO> addMovimientosArtisticos(@PathVariable("artistaId") Long artistaId, @RequestBody List<MovimientoArtisticoDTO> movimientos) throws EntityNotFoundException {
		List<MovimientoArtisticoEntity> entities = modelMapper.map(movimientos, new TypeToken<List<MovimientoArtisticoEntity>>() {
		}.getType());
		List<MovimientoArtisticoEntity> movimientosList = artistaMovimientoService.replaceMovimientosArtisticos(artistaId, entities);
		return modelMapper.map(movimientosList, new TypeToken<List<MovimientoArtisticoDetailDTO>>() {
		}.getType());
	}

	@DeleteMapping(value = "/{artistaId}/movimientoartisticos/{movimientoId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void removeMovimientoArtistico(@PathVariable("movimientoId") Long movimientoId, @PathVariable("artistaId") Long artistaId) throws EntityNotFoundException {
		artistaMovimientoService.removeMovimientoArtistico(artistaId, movimientoId);
	}
}
