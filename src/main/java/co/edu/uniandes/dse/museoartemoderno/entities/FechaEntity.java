package co.edu.uniandes.dse.museoartemoderno.entities;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

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
 @ManyToOne
 ArtistaEntity fechaFallecimiento;
 
 @PodamExclude
 @ManyToOne
 ArtistaEntity fechaNacimiento;
 
 @PodamExclude
 @ManyToOne
 ObraEntity fechaPublicacion;
 
 @PodamExclude
 @ManyToMany
 (mappedBy="movimientoArtisticos")
 MovimientoArtisticoEntity fechasApogeo;
 
}
