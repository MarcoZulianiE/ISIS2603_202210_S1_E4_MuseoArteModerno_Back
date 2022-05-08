package co.edu.uniandes.dse.museoartemoderno.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CiudadDTO {
	private String nombre;
	private PaisDTO pais;
	private Long id;
    private String coordenadasCiudad;
}
