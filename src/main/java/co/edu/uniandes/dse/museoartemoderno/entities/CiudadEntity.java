package co.edu.uniandes.dse.museoartemoderno.entities;

import javax.persistence.ManyToOne;


import uk.co.jemos.podam.common.PodamExclude;

public class CiudadEntity {
	@PodamExclude
	@ManyToOne
	private PaisEntity ciudades;
}
