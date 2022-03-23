package co.edu.uniandes.dse.museoartemoderno.dto;

import java.util.ArrayList;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaisDetailDTO extends PaisDTO{
	private List<CiudadDTO> ciudades = new ArrayList<>();
	private List<MovimientoArtisticoDTO> movimientos = new ArrayList<>();
	private List<MuseoDTO> museos = new ArrayList<>();
	private ArtistaDTO artistasFallecimiento;
	private ArtistaDTO artistasNacimiento;

}
