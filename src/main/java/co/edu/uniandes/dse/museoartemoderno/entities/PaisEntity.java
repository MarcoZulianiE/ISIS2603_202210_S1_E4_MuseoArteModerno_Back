package co.edu.uniandes.dse.museoartemoderno.entities;

import java.util.ArrayList;
import java.util.List;


import javax.persistence.OneToMany;

import co.edu.uniandes.dse.bookstore.entities.BookEntity;
import uk.co.jemos.podam.common.PodamExclude;

public class PaisEntity {
	private String nombrePais;
	
	@PodamExclude
	@OneToMany(mappedBy = "pais")
	private List<CiudadEntity> pais = new ArrayList<>();
	
	@PodamExclude
	@OneToMany(mappedBy = "lugarFallecimiento")
	private List<ArtistaEntity> lugarFallecimiento = new ArrayList<>();
	
	@PodamExclude
	@OneToMany(mappedBy = "lugarNacimiento")
	private List<ArtistaEntity> lugarNacimiento = new ArrayList<>();
	
	@PodamExclude
	@OneToMany(mappedBy = "ubicacion")
	private List<MuseoEntity> ubicacion = new ArrayList<>();
	
	@PodamExclude
	@OneToMany(mappedBy = "lugarOrigen")
	private List<MovimientoArtisticoEntity> lugarOrigen = new ArrayList<>();

}
