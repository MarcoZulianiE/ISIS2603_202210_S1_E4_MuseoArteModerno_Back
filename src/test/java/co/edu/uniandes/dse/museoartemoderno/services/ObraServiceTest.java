package co.edu.uniandes.dse.museoartemoderno.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import co.edu.uniandes.dse.museoartemoderno.entities.ObraEntity;
import co.edu.uniandes.dse.museoartemoderno.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.museoartemoderno.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.museoartemoderno.entities.ArtistaEntity;
import co.edu.uniandes.dse.museoartemoderno.entities.MuseoEntity;
import co.edu.uniandes.dse.museoartemoderno.entities.MovimientoArtisticoEntity;
import co.edu.uniandes.dse.museoartemoderno.services.ObraService;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional
@Import(ObraService.class)
public class ObraServiceTest {
	
	@Autowired
	private ObraService obraService;
	
	@Autowired 
	private TestEntityManager entityManager;
	
	private PodamFactory factory = new PodamFactoryImpl();
	
	private List<ObraEntity> obraList = new ArrayList<>();
	
	/**
	 * Configuración inicial de la prueba.
	 */
	@BeforeEach
	void setUp() {
		clearData();
		insertData();
	}
	
	private void clearData() {
		entityManager.getEntityManager().createQuery("delete from ArtistaEntity").executeUpdate();
		entityManager.getEntityManager().createQuery("delete from MuseoEntity").executeUpdate();
		entityManager.getEntityManager().createQuery("delete from MovimientoArtisticoEntity").executeUpdate();
		entityManager.getEntityManager().createQuery("delete from ObraEntity").executeUpdate();
	}
	
	private void insertData() {
		for (int i =0; i<3; i++) {
			ObraEntity obraEntity = factory.manufacturePojo(ObraEntity.class);
			entityManager.persist(obraEntity);
			obraList.add(obraEntity);			
		}
		
		ObraEntity obraEntity = obraList.get(2);
		ArtistaEntity artistaEntity = factory.manufacturePojo(ArtistaEntity.class);
		artistaEntity.getObras().add(obraEntity);
		
	}

	/**
	 * Prueba para crear una Obra.
	 */
	
	@Test
	void testCreateObra() throws EntityNotFoundException, IllegalOperationException {
		ObraEntity newEntity = factory.manufacturePojo(ObraEntity.class);
		ObraEntity result = obraService.createObra(newEntity);
		assertNotNull(result);

		ObraEntity entity = entityManager.find(ObraEntity.class, result.getId());

		assertEquals(newEntity.getId(), entity.getId());
		assertEquals(newEntity.getNombre(), entity.getNombre());
		assertEquals(newEntity.getArtista(), entity.getArtista());
		assertEquals(newEntity.getDescripcion(), entity.getDescripcion());
	}
	
	
	/**
	 * Prueba para crear una Obra con una fecha de publicación mayor que la fecha actual.
	 * @throws IllegalOperationException 
	 */
	@Test
	void testCreateObraInvalidFechaPublicacion() {
		assertThrows(IllegalOperationException.class, ()->{
			ObraEntity newEntity = factory.manufacturePojo(ObraEntity.class);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date()); 
			calendar.add(Calendar.DATE, 15);
			newEntity.setFechaPublicacion(calendar.getTime());
			obraService.createObra(newEntity);
		});
	}
	
	/**
	 * Prueba para consultar una Obra.
	 */
	@Test
	void testGetObra() throws EntityNotFoundException {
		ObraEntity obraEntity = obraList.get(0);

		ObraEntity resultEntity = obraService.getObra(obraEntity.getId());
		assertNotNull(resultEntity);

		assertEquals(obraEntity.getId(), resultEntity.getId());
		assertEquals(obraEntity.getNombre(), resultEntity.getNombre());
		assertEquals(obraEntity.getFechaPublicacion(), resultEntity.getFechaPublicacion());
		assertEquals(obraEntity.getDescripcion(), resultEntity.getDescripcion());
	}
	
	/**
	 * Prueba para consultar una Obra que no existe.
	 */
	@Test
	void testGetInvalidObra() {
		assertThrows(EntityNotFoundException.class, ()->{
			obraService.getObra(0L);
		});
	}

	/**
	 * Prueba para actualizar una Obra.
	 */
	@Test
	void testUpdateObra() throws EntityNotFoundException, IllegalOperationException {
		ObraEntity obraEntity = obraList.get(0);
		ObraEntity pojoEntity = factory.manufacturePojo(ObraEntity.class);

		pojoEntity.setId(obraEntity.getId());

		obraService.updateObra(obraEntity.getId(), pojoEntity);

		ObraEntity response = entityManager.find(ObraEntity.class, obraEntity.getId());

		assertEquals(pojoEntity.getId(), response.getId());
		assertEquals(pojoEntity.getNombre(), response.getNombre());
		assertEquals(pojoEntity.getFechaPublicacion(), response.getFechaPublicacion());
		assertEquals(pojoEntity.getDescripcion(), response.getDescripcion());
	}
	
	/**
	 * Prueba para actualizar una Obra que no existe.
	 */
	@Test
	void testUpdateInvalidObra()  {
		assertThrows(EntityNotFoundException.class, ()->{
			ObraEntity pojoEntity = factory.manufacturePojo(ObraEntity.class);
			obraService.updateObra(0L, pojoEntity);	
		});
	}

	/**
	 * Prueba para eliminar una Obra
	 *
	 */
	@Test
	void testDeleteObra() throws EntityNotFoundException, IllegalOperationException {
		ObraEntity obraEntity = obraList.get(0);
		obraService.deleteObra(obraEntity.getId());
		ObraEntity deleted = entityManager.find(ObraEntity.class, obraEntity.getId());
		assertNull(deleted);
	}
	
	/**
	 * Prueba para eliminar una Obra que no existe
	 *
	 */
	@Test
	void testDeleteInvalidObra() {
		assertThrows(EntityNotFoundException.class, ()->{
			obraService.deleteObra(0L);
		});
	}

	/**
	 * Prueba para eliminar una Obra asociada a un Artista
	 *
	 */
	@Test
	void testDeleteObraWithArtista() {
		assertThrows(IllegalOperationException.class, () -> {
			obraService.deleteObra(obraList.get(2).getId());
		});
	}

	/**
	 * Prueba para eliminar una Obra asociada a un Movimiento Artistico
	 *
	 */
	@Test
	void testDeleteObraWithMovimientoArtistico() {
		assertThrows(IllegalOperationException.class, () -> {
			obraService.deleteObra(obraList.get(1).getId());
		});
	}
	
	/**
	 * Prueba para eliminar una Obra asociada a un Museo
	 *
	 */
	@Test
	void testDeleteObraWithMuseo() {
		assertThrows(IllegalOperationException.class, () -> {
			obraService.deleteObra(obraList.get(0).getId());
		});
	}
		
		
}
