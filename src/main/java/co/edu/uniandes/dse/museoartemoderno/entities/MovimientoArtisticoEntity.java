package co.edu.uniandes.dse.museoartemoderno.entities;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class MovimientoArtisticoEntity extends BaseEntity{
	@Id
	private Long id;

	private String nombre;


	@PodamExclusive
	@OneToMany
	List<ObraEntity> obras = new ArrayList<>();


	@PodamExclusive
	@ManyToMany
	List<ArtistaEntity> artistas = new ArrayList<>();

	@PodamExclusive
	@ManyToMany(
		mappedBy = "museos")
	List<MuseoEntity> museos = new ArrayList<>();

	@PodamExclusive
	@ManyToOne(
		mappedBy = "lugarOrigen")
	PaisEntity lugarOrigen;

	@PodamExclusive
	@ManyToMany
	List<FechaEntity> fechasApogeo = new ArrayList<>();
}
