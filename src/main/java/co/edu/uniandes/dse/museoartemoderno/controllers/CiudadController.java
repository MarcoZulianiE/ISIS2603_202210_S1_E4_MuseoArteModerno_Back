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
import org.springframework.beans.factory.annotation.Autowired;
import co.edu.uniandes.dse.museoartemoderno.services.CiudadService;
@RestController
@RequestMapping("/ciudades")
public class CiudadController {
    @Autowired
	private CiudadService ciudadService;
    @Autowired
    private ModelMapper modelMapper;
    
    
    
    
    
    
    
    
    
    
}


