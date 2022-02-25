package co.edu.uniandes.dse.museoartemoderno.entities;
import java.util.ArrayList;
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
public class FechaEntity extends BaseEntity {
	
 @Id
 private Long id;
	
 private int dia;
 private int mes;
 private int año;
 
 
 @PodamExclude
 @OneToMany
 (mappedBy="fechaNacimiento")
 List<ArtistaEntity> artistasNacimiento= new ArrayList<>();
 
 @PodamExclude
 @OneToMany
 (mappedBy="fechaFallecimiento")
 List<ArtistaEntity> artistasFallecimiento= new ArrayList<>();
 
 @PodamExclude
 @OneToMany
 (mappedBy="fechaPublicacion")
 List<ObraEntity> obras= new ArrayList<>();
 
 @PodamExclude
 @ManyToMany
 List<MovimientoArtisticoEntity> movimientoArtisticos;
 
}
