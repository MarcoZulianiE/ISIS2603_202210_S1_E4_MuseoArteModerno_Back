package co.edu.uniandes.dse.museoartemoderno.entities;


@Getter
@Setter
@Entity

public class ObraEntity extends BaseEntity{
	
	private String nombre;
	private String tipo;
	private String descripcion;
	
	
	@ManyToOne
	private MuseoEntity museoDeExhibicion;
	
	@ManyToOne
	private ArtistaEntity autor;
	
	@ManyToOne
	private FechaEntity fechaPublicacion;
	
	@ManyToOne
	private MovimientoArtisticoEntity movimiento;
	
	


}
