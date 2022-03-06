package co.edu.uniandes.dse.museoartemoderno.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uniandes.dse.museoartemoderno.dto.ArtistaDTO;
import co.edu.uniandes.dse.museoartemoderno.entities.ArtistaEntity;
import co.edu.uniandes.dse.museoartemoderno.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.museoartemoderno.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.museoartemoderno.services.ArtistaService;

@RestController
@RequestMapping("/artistas")
public class ArtistaController {
	
	@Autowired
	private ArtistaService artistaService;
	
	@Autowired
	private ModelMapper modelMapper;

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ArtistaDTO create(@RequestBody ArtistaDTO artistaDTO) throws IllegalOperationException, EntityNotFoundException {
		ArtistaEntity artistaEntity = artistaService.createArtista(modelMapper.map(artistaDTO, ArtistaEntity.class));
		return modelMapper.map(artistaEntity, ArtistaDTO.class);	
	}
}
