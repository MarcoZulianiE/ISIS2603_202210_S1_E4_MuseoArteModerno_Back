package co.edu.uniandes.dse.museoartemoderno.entities;

import uk.co.jemos.podam.common.PodamExclude;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import co.edu.uniandes.dse.museoartemoderno.entities.ObraEntity;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class ObraEntity extends BaseEntity{
	
	private String nombre;
	private String tipo;
	private String descripcion;
	
	@PodamExclude
	@ManyToOne
	private MuseoEntity museoExhibicion;
	
	@PodamExclude
	@ManyToOne
	private ArtistaEntity autor;
	
	@PodamExclude
	@ManyToOne
	private FechaEntity fechaPublicacion;
	
	@PodamExclude
	@ManyToOne
	private MovimientoArtisticoEntity movimiento;
	
	


}
