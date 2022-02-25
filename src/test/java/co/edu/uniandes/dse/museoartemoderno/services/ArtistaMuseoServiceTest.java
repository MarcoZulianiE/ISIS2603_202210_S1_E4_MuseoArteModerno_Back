package co.edu.uniandes.dse.museoartemoderno.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import co.edu.uniandes.dse.museoartemoderno.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.museoartemoderno.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.museoartemoderno.entities.ArtistaEntity;
import co.edu.uniandes.dse.museoartemoderno.entities.MuseoEntity;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional
@Import(ArtistaMuseoService.class)
public class ArtistaMuseoServiceTest {

	@Autowired
	private ArtistaMuseoService artistaMuseoService;
	
	@Autowired
	private TestEntityManager entityManager;
	
	private PodamFactory factory = new PodamFactoryImpl();
	
	private ArtistaEntity artista = new ArtistaEntity();
	private List<MuseoEntity> museoList = new ArrayList<>();
	
	@BeforeEach
	void setUp() {
		clearData();
		insertData();
	}
	
	/**
	 * Limpia las tablas que están implicadas en la prueba.
	 */
	private void clearData() {
		entityManager.getEntityManager().createQuery("delete from ArtistaEntity").executeUpdate();
		entityManager.getEntityManager().createQuery("delete from MuseoEntity").executeUpdate();
	}
	
	/**
	 * Inserta los datos iniciales para el correcto funcionamiento de las pruebas.
	 */
	private void insertData() {
		artista = factory.manufacturePojo(ArtistaEntity.class);
		entityManager.persist(artista);

		for (int i = 0; i < 3; i++) {
			MuseoEntity entity = factory.manufacturePojo(MuseoEntity.class);
			entityManager.persist(entity);
			entity.getArtistas().add(artista);
			museoList.add(entity);
			artista.getMuseos().add(entity);	
		}
	}
	
	/**
	 * Prueba para asociar un museo a un artista.
	 *
	 */
	@Test
	void testAddMuseo() throws EntityNotFoundException, IllegalOperationException {
		ArtistaEntity newArtista = factory.manufacturePojo(ArtistaEntity.class);
		entityManager.persist(newArtista);
		
		MuseoEntity museo = factory.manufacturePojo(MuseoEntity.class);
		entityManager.persist(museo);
		
		artistaMuseoService.addMuseo(newArtista.getId(), museo.getId());
		
		MuseoEntity lastMuseo = artistaMuseoService.getMuseo(newArtista.getId(), museo.getId());
		assertEquals(museo.getId(), lastMuseo.getId());
		assertEquals(museo.getNombre(), lastMuseo.getNombre());
	}
	
	/**
	 * Prueba para asociar un museo que no existe a un artista.
	 *
	 */
	@Test
	void testAddInvalidMuseo() {
		assertThrows(EntityNotFoundException.class, ()->{
			ArtistaEntity newArtista = factory.manufacturePojo(ArtistaEntity.class);
			entityManager.persist(newArtista);
			artistaMuseoService.addMuseo(newArtista.getId(), 0L);
		});
	}
	
	/**
	 * Prueba para asociar un autor a un libro que no existe.
	 *
	 */
	@Test
	void testAddMuseoInvalidArtista() throws EntityNotFoundException, IllegalOperationException {
		assertThrows(EntityNotFoundException.class, ()->{
			MuseoEntity museo = factory.manufacturePojo(MuseoEntity.class);
			entityManager.persist(museo);
			artistaMuseoService.addMuseo(0L, museo.getId());
		});
	}
	
}
