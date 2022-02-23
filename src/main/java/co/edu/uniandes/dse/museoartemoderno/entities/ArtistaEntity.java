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

@Getter
@Setter
@Entity
public class ArtistaEntity extends BaseEntity {

	@PodamExclude
	@ManyToOne
	private PaisEntity lugarNacimiento;
	
	@PodamExclude
	@ManyToOne
	private PaisEntity lugarFallecimiento;
	
	@PodamExclude
	@ManyToMany
	private List<MuseoEntity> museos = new ArrayList<>();
	
	@PodamExclude
	@OneToMany(mappedBy = "artista")
	private List<ObraEntity> obras = new ArrayList<>();
	
	@PodamExclude
	@ManyToOne
	private FechaEntity fechaNacimiento;
	
	@PodamExclude
	@ManyToOne
	private FechaEntity fechaFallecimiento;
	
	@PodamExclude
	@ManyToMany
	private List<MovimientoArtisticoEntity> movimientos = new ArrayList<>();
	
	private String nombre;
}
