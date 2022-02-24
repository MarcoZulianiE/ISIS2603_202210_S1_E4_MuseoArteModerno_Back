package co.edu.uniandes.dse.museoartemoderno.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;
import uk.co.jemos.podam.common.PodamExclude;

@Entity
@Getter
@Setter
public class CiudadEntity extends BaseEntity {
	@PodamExclude
	@ManyToOne
	private PaisEntity ciudades;
}
