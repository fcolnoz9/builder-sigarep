package sigarep.modelos.test.maestros;

import static org.junit.Assert.*;
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
import sigarep.modelos.data.maestros.TipoMotivo;
import sigarep.modelos.repositorio.maestros.ITipoMotivoDAO;

/**
 * Clase TestTipoMotivo
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
public class TestTipoMotivo {
	
	//Atributo de la clase
	private TipoMotivo tipoMotivo,resultado,resultadoB;
	@Autowired
	ITipoMotivoDAO motivoDAO;

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
	 * Metodo de prueba para guardar un
	 *  Tipo motivo
	 */
	@Test
	public void testGuardarTipoMotivo() {
		tipoMotivo=new TipoMotivo(999,"Test Case", true,"Primera Instancia Test Case", false);
		motivoDAO.save(tipoMotivo);
		System.out.println("Se ha Guardado un Motivo de Prueba y el id de Motivo es : "+tipoMotivo.getIdTipoMotivo());
		resultado=motivoDAO.findOne(999);
		assertEquals(resultado.getIdTipoMotivo(),tipoMotivo.getIdTipoMotivo());
	}

	/**
	 * Metodo de prueba para buscar un
	 *  Tipo motivo
	 */
	@Test(timeout=1000)
	public void testBuscarMotivo(){
		resultadoB=motivoDAO.findOne(3);
		assertNotNull(resultadoB);
	}
}