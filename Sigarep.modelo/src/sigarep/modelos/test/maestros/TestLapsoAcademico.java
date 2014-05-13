package sigarep.modelos.test.maestros;

import static org.junit.Assert.*;
import java.text.SimpleDateFormat;
import javax.persistence.EntityManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import sigarep.modelos.data.maestros.LapsoAcademico;
import sigarep.modelos.repositorio.maestros.ILapsoAcademicoDAO;

/**
 * Clase TestLapsoAcademico
 * 
 * @author Equipo Builder
 * @version 1.0
 * @since 15/12/2013
 * @last 12/05/2014
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/META-INF/application-context.xml")
@Transactional
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)//si defaultRollback=false guarda en la BD y si se coloca true No guarda en la BD
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class TestLapsoAcademico {
	
	//Atributos de la clase
	private LapsoAcademico lapsoAcademico,resultado;
	@Autowired
	ILapsoAcademicoDAO lapso;
	private String fechainicio;
	private String fechacierre;
	private static EntityManager em = null;
	private static Log LOG = LogFactory.getLog(TestLapsoAcademico.class);
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	/**
	 * Metodo Guardar Lapso
	 * Timeout en 125 segundos 
	 */
	@Test(timeout=125000)
	public void testGuardarLapso() { 
		try {
			resultado =new LapsoAcademico();
			SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");//le da formato a la Fecha
    		fechainicio="2013-05-05";
   		    fechacierre="2014-04-07";
   		    lapsoAcademico =new LapsoAcademico("2014-1", formatoDelTexto.parse(fechainicio), formatoDelTexto.parse(fechacierre), true);
            lapso.save(lapsoAcademico);
    		resultado=lapso.findOne("2014-1");
    		assertEquals(resultado.getCodigoLapso(), lapsoAcademico.getCodigoLapso());//compara si el codigo del lapso buscado es igual al codigo guardado en bd
    		System.out.println("Lapso-Guardado"+lapsoAcademico.getCodigoLapso());
		} catch (Exception e) {
			// TODO: hand.le exception
		  e.printStackTrace();
		}
		
	}
	
	/**
	 * Metodo Buscar un lapso Lapso cualesquiera en la BD
	 * Timeout en 2 segundos 
	 */
	@Test(timeout=2000)
	public void testBuscarLapso(){
	resultado=lapso.findOne("2013-2");
	assertNotNull(resultado);//si resultado es no nulo quiere decir que consiguió el lapso en la BD
	System.out.println("Resulatado"+resultado.getFechaCierre());
	}
}
