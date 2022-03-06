package co.edu.uniandes.dse.museoartemoderno.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovimientoArtisticoDTO 
{
	private Long id;
	private String nombre;
	private Date fechaApogeo;
}
