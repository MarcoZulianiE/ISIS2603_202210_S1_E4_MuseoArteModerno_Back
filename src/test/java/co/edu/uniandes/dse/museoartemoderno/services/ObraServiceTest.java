//package co.edu.uniandes.dse.museoartemoderno.services;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.transaction.Transactional;
//
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.context.annotation.Import;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import uk.co.jemos.podam.api.PodamFactory;
//import uk.co.jemos.podam.api.PodamFactoryImpl;
//import co.edu.uniandes.dse.museoartemoderno.entities.ObraEntity;
//import co.edu.uniandes.dse.museoartemoderno.exceptions.EntityNotFoundException;
//import co.edu.uniandes.dse.museoartemoderno.exceptions.IllegalOperationException;
//import co.edu.uniandes.dse.museoartemoderno.entities.ArtistaEntity;
//import co.edu.uniandes.dse.museoartemoderno.entities.MuseoEntity;
//import co.edu.uniandes.dse.museoartemoderno.entities.MovimientoArtisticoEntity;
//import co.edu.uniandes.dse.museoartemoderno.services.ObraService;
//
//@ExtendWith(SpringExtension.class)
//@DataJpaTest
//@Transactional
//@Import(ObraService.class)
//public class ObraServiceTest {
//	
//	@Autowired
//	private ObraService obraService;
//	
//	@Autowired 
//	private TestEntityManager entityManager;
//	
//	private PodamFactory factory = new PodamFactoryImpl();
//	
//	private List<ObraEntity> obraList = new ArrayList<>();
//	
//	/**
//	 * Configuración inicial de la prueba.
//	 */
//	@BeforeEach
//	void setUp() {
//		clearData();
//		insertData();
//	}
//	
//	private void clearData() {
//		entityManager.getEntityManager().createQuery("delete from ArtistaEntity").executeUpdate();
//		entityManager.getEntityManager().createQuery("delete from MuseoEntity").executeUpdate();
//		entityManager.getEntityManager().createQuery("delete from MovimientoArtisticoEntity").executeUpdate();
//		entityManager.getEntityManager().createQuery("delete from ObraEntity").executeUpdate();
//	}
//	
//	private void insertData() {
//		for (int i =0; i<3; i++) {
//			ObraEntity obraEntity = factory.manufacturePojo(ObraEntity.class);
//			entityManager.persist(obraEntity);
//			obraList.add(obraEntity);			
//		}
//		
//		ObraEntity obraEntity = obraList.get(2);
//		ArtistaEntity artistaEntity = factory.manufacturePojo(ArtistaEntity.class);
//		artistaEntity.getObras().add(obraEntity);
//		
//	}
//	
//	
//	@Test
//	
//	void testCreateObra() throws EntityNotFoundException, IllegalOperationException{
//		ObraEntity newEntity = factory.manufacturePojo(ObraEntity.class, null)
//		
//	}
//
//}
