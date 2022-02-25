package co.edu.uniandes.dse.museoartemoderno.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;
import uk.co.jemos.podam.common.PodamExclude;

@Entity
@Getter
@Setter
public class PaisEntity extends BaseEntity{
	private String nombrePais;
	private String coordenadasPais;
	
	@PodamExclude
	@OneToMany(mappedBy = "pais")
	private List<CiudadEntity> pais = new ArrayList<>();
	
	@PodamExclude
	@OneToMany(mappedBy = "lugarFallecimiento")
	private List<ArtistaEntity> Artistafallecimiento = new ArrayList<>();
	
	@PodamExclude
	@OneToMany(mappedBy = "lugarNacimiento")
	private List<ArtistaEntity> Artistanacimiento = new ArrayList<>();
	
	@PodamExclude
	@OneToMany(mappedBy = "ubicacion")
	private List<MuseoEntity> ubicacion = new ArrayList<>();
	
	@PodamExclude
	@OneToMany(mappedBy = "lugarOrigen")
	private List<MovimientoArtisticoEntity> lugarOrigen = new ArrayList<>();

}
