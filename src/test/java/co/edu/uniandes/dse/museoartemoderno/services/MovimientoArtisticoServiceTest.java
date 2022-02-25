package co.edu.uniandes.dse.museoartemoderno.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import co.edu.uniandes.dse.museoartemoderno.entities.*;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional
@Import(MovimientoArtisticoService.class)
class MovimientoArtisticoServiceTest {

	@Autowired
	private MovimientoArtisticoService movimientoArtisticoService;

	@Autowired
	private TestEntityManager entityManager;


	private PodamFactory factory = new PodamFactoryImpl();

	private List<MovimientoArtisticoEntity> movimientoArtisticoList = new ArrayList<>();
	private List<MuseoEntity> museoList = new ArrayList<>();

	private List<ObraEntity> obraList = new ArrayList<>();

	private List<ArtistaEntity> artistaList = new ArrayList<>();
	
	@BeforeEach
	void setUp() 
	{
		clearData();
		insertData();
	}
	
	 /**
     * Limpia las tablas que estan implicadas en la prueba
     */
    private void clearData()
    {
    	entityManager.getEntityManager().createQuery("delete from MovimientoArtisticoEntity");
    	entityManager.getEntityManager().createQuery("delete from ObraEntity");
    	entityManager.getEntityManager().createQuery("delete from MuseoEntity");
    	entityManager.getEntityManager().createQuery("delete from FechaEntity");
    	entityManager.getEntityManager().createQuery("delete from ArtistaEntity");
    	entityManager.getEntityManager().createQuery("delete from PaisEntity");
    }

	@Test
	void test() {
		fail("Not yet implemented");
	}

}
