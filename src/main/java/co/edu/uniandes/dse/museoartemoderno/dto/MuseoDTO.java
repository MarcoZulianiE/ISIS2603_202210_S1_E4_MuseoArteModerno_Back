package co.edu.uniandes.dse.museoartemoderno.dto;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MuseoDTO {

	private String nombre;
	
	private String direccion;
	
	private List<String> salasExposicion = new ArrayList<>();
	
	private Integer totalObrasExhibidas;

}