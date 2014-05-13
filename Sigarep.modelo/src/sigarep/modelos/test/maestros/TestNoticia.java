package sigarep.modelos.test.maestros;

import static org.junit.Assert.*;
import java.util.List;
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
import sigarep.modelos.data.maestros.Noticia;
import sigarep.modelos.repositorio.maestros.INoticiaDAO;

/**
 * Clase TestNoticia
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
public class TestNoticia {
	
	//Atributos de la clase
	private Noticia noticia,resultado,resultadoB;
	private List<Noticia> ListaNoticias;
	@Autowired
	INoticiaDAO noticiaDAO;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * Metodo Colocar Noticia en atributos
	 *  
	 */
	@Before
	public void setUp() throws Exception {
		noticia=new Noticia();
		resultado=new Noticia();
	}

	/**
	 * Metodo Para colocar en Null
	 *  atributos noticia, resultado
	 */
	@After
	public void tearDown() throws Exception {
		noticia=null;
		resultado=null;
	}

	/**
	 * Metodo Guardar Noticia de prueba
	 *  
	 */
	@Test
	public void testGuardarNoticia(){
		noticia= new Noticia(999,"Noticia de Prueba", null, true, null, null, "Probando Noticia", null);
		noticiaDAO.save(noticia);
		resultado=noticiaDAO.findOne(999);
		assertEquals(resultado.getIdNoticia(),noticia.getIdNoticia());
	}
	
	/**
	 * Metodo de prueba para Buscar Noticia
	 *  
	 */
	@Test
	public void testBuscarNoticia(){
		resultadoB=noticiaDAO.findOne(2);
		assertNotNull(resultadoB);
		System.out.println("El contenido de la Noticia encontrada es :"+resultadoB.getContenido());
	}
	
	/**
	 * Metodo de prueba para generar lista de Noticias
	 *  
	 */
	@Test
	public void testListaNoticias(){
		ListaNoticias=noticiaDAO.findAll();
		for(int i = 0;i<ListaNoticias.size();i++){
			System.out.println(" Noticias : "+ListaNoticias.get(i).getContenido());
		}
	}
}
