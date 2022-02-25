package co.edu.uniandes.dse.museoartemoderno.entities;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;
import uk.co.jemos.podam.common.PodamExclude;

@Entity
@Getter
@Setter
public class MovimientoArtisticoEntity extends BaseEntity{
	@Id
	private Long id;

	private String nombre;
	
	Date fechasApogeo = new Date();

	@PodamExclude
	@OneToMany(
		mappedBy = "movimiento")
	List<ObraEntity> obras = new ArrayList<>();


	@PodamExclude
	@ManyToMany(
		mappedBy = "movimientos")
	List<ArtistaEntity> artistas = new ArrayList<>();

	@PodamExclude
	@ManyToMany
	List<MuseoEntity> museos = new ArrayList<>();

	@PodamExclude
	@ManyToOne
	PaisEntity lugarOrigen;

	
}
