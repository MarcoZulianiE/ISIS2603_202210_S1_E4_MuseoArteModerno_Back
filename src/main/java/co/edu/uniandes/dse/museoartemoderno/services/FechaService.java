package co.edu.uniandes.dse.museoartemoderno.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.museoartemoderno.repositories.FechaRepository;
import co.edu.uniandes.dse.museoartemoderno.entities.FechaEntity;




@Service
public class FechaService {
    @Autowired
	FechaRepository fechaRepository;
    
    
    
    
}
