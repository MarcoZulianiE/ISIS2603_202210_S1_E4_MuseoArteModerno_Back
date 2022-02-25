package co.edu.uniandes.dse.museoartemoderno.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.context.annotation.Import;

import co.edu.uniandes.dse.museoartemoderno.entities.PaisEntity;
import co.edu.uniandes.dse.museoartemoderno.exceptions.IllegalOperationException;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional
@Import(PaisService.class)
public class PaisServiceTest {
	
	@Autowired
	private PaisService paisService;
	
	@Autowired
	private TestEntityManager entityManager;
	
	private PodamFactory factory = new PodamFactoryImpl();
	
	private List<PaisEntity> listaPais = new ArrayList<PaisEntity>();
	

	@BeforeEach
	void setUp() {
		clearData();
		insertData();
}

	private void clearData() {
		entityManager.getEntityManager().createQuery("delete from PaisEntity").executeUpdate();
	}
	

	private void insertData() {
		for(int i=0; i<3; i++) {
			PaisEntity paisEntity = factory.manufacturePojo(PaisEntity.class);
			entityManager.persist(paisEntity);
			listaPais.add(paisEntity);
		}
	}

	
	@Test
	void testGetPais() {
		List<PaisEntity> list = paisService.getPaises();
        assertEquals(list.size(), listaPais.size());
	}


	@Test
	void testCreatePais() throws co.edu.uniandes.dse.museoartemoderno.exceptions.EntityNotFoundException, IllegalOperationException{
		PaisEntity nuevoPais = factory.manufacturePojo(PaisEntity.class);
		PaisEntity paisCreado = paisService.createPais(nuevoPais);
		PaisEntity busqueda = entityManager.find(PaisEntity.class, paisCreado.getId());
		
		assertEquals(nuevoPais.getId(), busqueda.getId());
		assertEquals(nuevoPais.getNombrePais(), busqueda.getNombrePais());
	}
	

	@Test
	void testGetPaisID() throws EntityNotFoundException, co.edu.uniandes.dse.museoartemoderno.exceptions.EntityNotFoundException, IllegalOperationException {
		PaisEntity nuevoPais = factory.manufacturePojo(PaisEntity.class);
		PaisEntity paisCreado = paisService.createPais(nuevoPais);
		PaisEntity busqueda;
		
		busqueda = paisService.getPais(paisCreado.getId());
		assertEquals(nuevoPais.getId(), busqueda.getId());
		assertEquals(nuevoPais.getNombrePais(), busqueda.getNombrePais());
	}

	@Test
	void testUpdatePais() throws EntityNotFoundException, co.edu.uniandes.dse.museoartemoderno.exceptions.EntityNotFoundException {
		PaisEntity paisEntity = listaPais.get(0);
		PaisEntity pojoEntity = factory.manufacturePojo(PaisEntity.class);

		pojoEntity.setId(paisEntity.getId());

		paisService.updatePais(paisEntity.getId(), pojoEntity);

		PaisEntity response = entityManager.find(PaisEntity.class, paisEntity.getId());

		assertEquals(pojoEntity.getId(), response.getId());
		assertEquals(pojoEntity.getNombrePais(), response.getNombrePais());
	}

	@Test
	void testDeletePais() throws EntityNotFoundException, co.edu.uniandes.dse.museoartemoderno.exceptions.EntityNotFoundException, IllegalOperationException {
		PaisEntity paisEntity = listaPais.get(0);
		paisService.deletePais(paisEntity.getId());
		PaisEntity deleted = entityManager.find(PaisEntity.class, paisEntity.getId());
		assertNull(deleted);
	}

}
