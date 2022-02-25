package co.edu.uniandes.dse.museoartemoderno.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.ManyToMany;

import lombok.Getter;
import lombok.Setter;
import uk.co.jemos.podam.common.PodamExclude;

@Entity
@Getter
@Setter
public class MuseoEntity extends BaseEntity {
	
	private String nombre;
	
	private String direccion;
	
	private ArrayList<String> salasExposicion;
	
	private Integer totalObrasExhibidas;
	
	
	@PodamExclude
	@ManyToMany(mappedBy = "museos")
	private ArrayList<ArtistaEntity> artistas;
	
	
	@PodamExclude
	@OneToMany(mappedBy = "museo")
	private ArrayList<ObraEntity> obras;
	
	
	@PodamExclude
	@ManyToMany(mappedBy = "museos")
	private ArrayList<MovimientoArtisticoEntity> movimientos;
	
	
	@PodamExclude
	@ManyToOne
	private ArrayList<MovimientoArtisticoEntity> ubicacion;
	
	
}
