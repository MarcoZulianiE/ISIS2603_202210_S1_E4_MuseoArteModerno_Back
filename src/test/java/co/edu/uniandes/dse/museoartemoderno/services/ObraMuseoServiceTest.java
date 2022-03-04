//package co.edu.uniandes.dse.museoartemoderno.services;
//
//import javax.transaction.Transactional;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.context.annotation.Import;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//
//import co.edu.uniandes.dse.museoartemoderno.services.ObraMuseoService;
//import uk.co.jemos.podam.api.PodamFactory;
//import uk.co.jemos.podam.api.PodamFactoryImpl;
//import co.edu.uniandes.dse.museoartemoderno.services.MuseoService;
//import co.edu.uniandes.dse.museoartemoderno.entities.MuseoEntity;
//import co.edu.uniandes.dse.museoartemoderno.entities.ObraEntity;
//import co.edu.uniandes.dse.museoartemoderno.entities.ArtistaEntity;
//import co.edu.uniandes.dse.museoartemoderno.entities.MovimientoArtisticoEntity;
//
//
//
//@ExtendWith(SpringExtension.class)
//@DataJpaTest
//@Transactional
//@Import({ ObraMuseoService.class, MuseoService.class })
//
//public class ObraMuseoServiceTest {
//	
//	@Autowired
//	private ObraMuseoService obraMuseoService;
//	
//	@Autowired
//	private TestEntityManager entityManager;
//	
//	private PodamFactory factory = new PodamFactoryImpl();
//	
//	private MuseoEntity museo = new MuseoEntity();	
//	private ObraEntity obra = new ObraEntity();
//	
//	 /**
//     * Configuración inicial de la prueba.
//     */
//    @BeforeEach
//    void setUp() {
//            clearData();
//            insertData();
//    }
//	
//	public void clearData()
//	{
//		entityManager.getEntityManager().createQuery("delete from ArtistaEntity").executeUpdate();
//		entityManager.getEntityManager().createQuery("delete from MuseoEntity").executeUpdate();
//		entityManager.getEntityManager().createQuery("delete from MovimientoArtisticoEntity").executeUpdate();
//		entityManager.getEntityManager().createQuery("delete from ObraEntity").executeUpdate();
//	}
//	
//	/**
//	 * Inserta los datos iniciales para el correcto funcionamiento de las pruebas.
//	 */
//	private void insertData() {
//		for (int i = 0; i < 3; i++) {
//			ArtistaEntity artistaEntity = factory.manufacturePojo(ArtistaEntity.class);
//			entityManager.persist(artistaEntity);
//			artistaList.add(artistaEntity);
//		}
//
//		for (int i = 0; i < 3; i++) {
//			ObraEntity obraEntity = factory.manufacturePojo(ObraEntity.class);
//			entityManager.persist(obraEntity);
//			obraList.add(obraEntity);
//		}
//
//		for (int i = 0; i < 3; i++) {
//			MovimientoArtisticoEntity movimientoArtisticoEntity = factory.manufacturePojo(MovimientoArtisticoEntity.class);
//			entityManager.persist(movimientoArtisticoEntity);
//			movimientoArtisticoList.add(movimientoArtisticoEntity);
//		}
//		
//		museo = factory.manufacturePojo(MuseoEntity.class);
//		museo.setArtistas(artistaList);
//		museo.setObras(obraList);
//		museo.setMovimientos(movimientoArtisticoList);
//		museo.setUbicacion(paisList.get(0));
//		entityManager.persist(museo);
//	
//}
