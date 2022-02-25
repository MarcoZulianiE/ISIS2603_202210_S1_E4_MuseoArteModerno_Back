package co.edu.uniandes.dse.museoartemoderno.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Date;
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

import co.edu.uniandes.dse.museoartemoderno.entities.ArtistaEntity;
import co.edu.uniandes.dse.museoartemoderno.entities.PaisEntity;
import co.edu.uniandes.dse.museoartemoderno.entities.MuseoEntity;
import co.edu.uniandes.dse.museoartemoderno.entities.ObraEntity;
import co.edu.uniandes.dse.museoartemoderno.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.museoartemoderno.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.museoartemoderno.podam.DateStrategy;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import uk.co.jemos.podam.common.PodamStrategyValue;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional
@Import(ArtistaService.class)
public class ArtistaServiceTest {

	@Autowired
	private ArtistaService artistaService;
	
	@Autowired
	private TestEntityManager entityManager;
	
	private PodamFactory factory = new PodamFactoryImpl();
	
	private List<ArtistaEntity> artistaList = new ArrayList<>();
	private List<Date> fechaNacimientoList = new ArrayList<>();
	private List<Date> fechaFallecimientoList = new ArrayList<>();
	private List<PaisEntity> paisList = new ArrayList<>();
	private List<MuseoEntity> museoList = new ArrayList<>();
	private List<ObraEntity> obraList = new ArrayList<>();
	
	/**
     * Configuración inicial de la prueba.
     */
    @BeforeEach
    void setUp() {
            clearData();
            insertData();
    }
    
    /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() {
            entityManager.getEntityManager().createQuery("delete from ArtistaEntity");
            entityManager.getEntityManager().createQuery("delete from FechaEntity");
            entityManager.getEntityManager().createQuery("delete from PaisEntity");
            entityManager.getEntityManager().createQuery("delete from MuseoEntity");
            entityManager.getEntityManager().createQuery("delete from ObraEntity");
    }
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las pruebas.
     */
    private void insertData() {
            for (int i = 0; i < 3; i++) {
            		Date fechaEntity = factory.manufacturePojo(Date.class);
                    entityManager.persist(fechaEntity);
                    fechaNacimientoList.add(fechaEntity);
            }
            
            for (int i = 0; i < 3; i++) {
            	Date fechaEntity = factory.manufacturePojo(Date.class);
                entityManager.persist(fechaEntity);
                fechaFallecimientoList.add(fechaEntity);
            }
            
            for (int i = 0; i < 6; i++) {
            	PaisEntity paisEntity = factory.manufacturePojo(PaisEntity.class);
                entityManager.persist(paisEntity);
                paisList.add(paisEntity);
            }
            
            for (int i = 0; i < 6; i++) {
            	MuseoEntity museoEntity = factory.manufacturePojo(MuseoEntity.class);
                entityManager.persist(museoEntity);
                museoList.add(museoEntity);
            }
            
            for (int i = 0; i < 6; i++) {
            	ObraEntity obraEntity = factory.manufacturePojo(ObraEntity.class);
                entityManager.persist(obraEntity);
                obraList.add(obraEntity);
            }

            for (int i = 0; i < 3; i++) {
            		ArtistaEntity artistaEntity = factory.manufacturePojo(ArtistaEntity.class);
            		artistaEntity.setFechaNacimiento(fechaNacimientoList.get(0));
            		artistaEntity.setFechaFallecimiento(fechaFallecimientoList.get(0));
            		artistaEntity.setLugarNacimiento(paisList.get(0));
            		artistaEntity.setLugarFallecimiento(paisList.get(1));
            		artistaEntity.getMuseos().add(museoList.get(0));
            		artistaEntity.getObras().add(obraList.get(0));
                    entityManager.persist(artistaEntity);
                    artistaList.add(artistaEntity);
            }
    }

    /**
     * Prueba para crear un Artista
     */
    @Test
    void testCreateArtista() throws EntityNotFoundException, IllegalOperationException {
    	ArtistaEntity newEntity = factory.manufacturePojo(ArtistaEntity.class);
    	newEntity.setFechaNacimiento(fechaNacimientoList.get(0));
    	newEntity.setFechaFallecimiento(fechaFallecimientoList.get(0));
    	newEntity.setLugarNacimiento(paisList.get(0));
    	newEntity.setLugarFallecimiento(paisList.get(1));
    	newEntity.getMuseos().add(museoList.get(0));
    	newEntity.getObras().add(obraList.get(0));
    	ArtistaEntity result = artistaService.createArtista(newEntity);
    	assertNotNull(result);
    	
    	ArtistaEntity entity = entityManager.find(ArtistaEntity.class, result.getId());
    	
    	assertEquals(newEntity.getId(), entity.getId());
    	assertEquals(newEntity.getNombre(), entity.getNombre());
    }
    
    /**
     * Prueba para crear un Artista con Nombre inválido
     */
    @Test
    void testCreateBookWithNoValidISBN() {
    	assertThrows(IllegalOperationException.class, () -> {
    		ArtistaEntity newEntity = factory.manufacturePojo(ArtistaEntity.class);
    		newEntity.setFechaNacimiento(fechaNacimientoList.get(0));
    		newEntity.setFechaFallecimiento(fechaFallecimientoList.get(0));
    		newEntity.setLugarNacimiento(paisList.get(0));
    		newEntity.setLugarFallecimiento(paisList.get(1));
    		newEntity.getMuseos().add(museoList.get(0));
    		newEntity.getObras().add(obraList.get(0));
    		newEntity.setNombre("");
    		artistaService.createArtista(newEntity);
    	});
    }

    /**
     * Prueba para crear un Artista con un Nombre ya existente.
     */
    @Test
    void testCreateArtistaWithStoredNombre() {
    	assertThrows(IllegalOperationException.class, () -> {
    		ArtistaEntity newEntity = factory.manufacturePojo(ArtistaEntity.class);
    		newEntity.setFechaNacimiento(fechaNacimientoList.get(0));
    		newEntity.setFechaFallecimiento(fechaFallecimientoList.get(0));
    		newEntity.setLugarNacimiento(paisList.get(0));
    		newEntity.setLugarFallecimiento(paisList.get(1));
    		newEntity.getMuseos().add(museoList.get(0));
    		newEntity.getObras().add(obraList.get(0));
    		newEntity.setNombre(artistaList.get(0).getNombre());
    		artistaService.createArtista(newEntity);
    	});
    }
    
    /**
     * Prueba para crear un Artista con una fecha de Nacimiento que no existe
     */
    @Test
    void testCreateArtistaWithInvalidFechaNacimiento() {
    	assertThrows(IllegalOperationException.class, () -> {
    		ArtistaEntity newEntity = factory.manufacturePojo(ArtistaEntity.class);
    		Date fecha = new Date();
    		newEntity.setFechaNacimiento(fecha);
    		artistaService.createArtista(newEntity);
    	});
    }
    
    /**
     * Prueba para crear un Artista con una fecha de Fallecimiento que no existe
     */
    @Test
    void testCreateArtistaWithInvalidFechaFallecimiento() {
    	assertThrows(IllegalOperationException.class, () -> {
    		ArtistaEntity newEntity = factory.manufacturePojo(ArtistaEntity.class);
    		Date fecha = new Date();
    		newEntity.setFechaFallecimiento(fecha);
    		artistaService.createArtista(newEntity);
    	});
    }
    
    /**
     * Prueba para crear un Artista con una fecha Nacimiento nula.
     */
    @Test
    void testCreateArtistaWithNullFechaNacimiento() {
            assertThrows(IllegalOperationException.class, () -> {
            		ArtistaEntity newEntity = factory.manufacturePojo(ArtistaEntity.class);
                    newEntity.setFechaNacimiento(null);
                    artistaService.createArtista(newEntity);
            });
    }

    /**
     * Prueba para crear un Artista con una fecha Fallecimiento nula.
     */
    @Test
    void testCreateArtistaWithNullFechaFallecimiento() {
            assertThrows(IllegalOperationException.class, () -> {
            		ArtistaEntity newEntity = factory.manufacturePojo(ArtistaEntity.class);
                    newEntity.setFechaFallecimiento(null);
                    artistaService.createArtista(newEntity);
            });
    }
    
    /**
	 * Prueba para consultar la lista de Artistas.
	 */
	@Test
	void testGetArtistas() {
		List<ArtistaEntity> list = artistaService.getBooks();
		assertEquals(artistaList.size(), list.size());
		for (ArtistaEntity entity : list) {
			boolean found = false;
			for (ArtistaEntity storedEntity : artistaList) {
				if (entity.getId().equals(storedEntity.getId())) {
					found = true;
				}
			}
			assertTrue(found);
		}
	}
	
	/**
	 * Prueba para consultar un Artista.
	 */
	@Test
	void testGetArtista() throws EntityNotFoundException {
		ArtistaEntity entity = artistaList.get(0);
		ArtistaEntity resultEntity = artistaService.getArtista(entity.getId());
		assertNotNull(resultEntity);
		assertEquals(entity.getId(), resultEntity.getId());
    	assertEquals(entity.getNombre(), resultEntity.getNombre());
	}
	
	/**
	 * Prueba para consultar un Artista que no existe.
	 */
	@Test
	void testGetInvalidArtista() {
		assertThrows(EntityNotFoundException.class,()->{
			artistaService.getArtista(0L);
		});
	}
	
	/**
	 * Prueba para actualizar un Artista.
	 */
	@Test
	void testUpdateArtista() throws EntityNotFoundException, IllegalOperationException {
		ArtistaEntity entity = artistaList.get(0);
		ArtistaEntity pojoEntity = factory.manufacturePojo(ArtistaEntity.class);
		pojoEntity.setId(entity.getId());
		artistaService.updateArtista(entity.getId(), pojoEntity);

		ArtistaEntity resp = entityManager.find(ArtistaEntity.class, entity.getId());
		assertEquals(pojoEntity.getId(), resp.getId());
		assertEquals(entity.getNombre(), resp.getNombre());
	}
	
	/**
	 * Prueba para actualizar un Artista inválido.
	 */
	@Test
	void testUpdateArtistaInvalid() {
		assertThrows(EntityNotFoundException.class, () -> {
			ArtistaEntity pojoEntity = factory.manufacturePojo(ArtistaEntity.class);
			pojoEntity.setId(0L);
			artistaService.updateArtista(0L, pojoEntity);
		});
	}
	
	/**
	 * Prueba para actualizar un Artista con Nombre inválido.
	 */
	@Test
	void testUpdateArtistaWithNoValidNombre() {
		assertThrows(IllegalOperationException.class, () -> {
			ArtistaEntity entity = artistaList.get(0);
			ArtistaEntity pojoEntity = factory.manufacturePojo(ArtistaEntity.class);
			pojoEntity.setNombre("");
			pojoEntity.setId(entity.getId());
			artistaService.updateArtista(entity.getId(), pojoEntity);
		});
	}
	
	/**
	 * Prueba para actualizar un Artista con Nombre inválido.
	 */
	@Test
	void testUpdateArtistaWithNoValidNombre2() {
		assertThrows(IllegalOperationException.class, () -> {
			ArtistaEntity entity = artistaList.get(0);
			ArtistaEntity pojoEntity = factory.manufacturePojo(ArtistaEntity.class);
			pojoEntity.setNombre(null);
			pojoEntity.setId(entity.getId());
			artistaService.updateArtista(entity.getId(), pojoEntity);
		});
	}
	
	/**
	 * Prueba para eliminar un Artista.
	 */
	@Test
	void testDeleteArtista() throws EntityNotFoundException, IllegalOperationException {
		ArtistaEntity entity = artistaList.get(1);
		artistaService.deleteArtista(entity.getId());
		ArtistaEntity deleted = entityManager.find(ArtistaEntity.class, entity.getId());
		assertNull(deleted);
	}
	
	/**
	 * Prueba para eliminar un Artista que no existe.
	 */
	@Test
	void testDeleteInvalidArtista() {
		assertThrows(EntityNotFoundException.class, ()->{
			artistaService.deleteArtista(0L);
		});
	}
	
	/**
	 * Prueba para eliminar un Artista con una obras asociadas.
	 */
	@Test
	void testDeleteArtistaWithObras() {
		assertThrows(IllegalOperationException.class, () -> {
			ArtistaEntity entity = artistaList.get(0);
			artistaService.deleteArtista(entity.getId());
		});
	}
	
}
