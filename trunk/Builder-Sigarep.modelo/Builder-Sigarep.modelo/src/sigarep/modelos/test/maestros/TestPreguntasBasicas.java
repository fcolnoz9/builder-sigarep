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

import sigarep.modelos.data.maestros.PreguntaBasica;
import sigarep.modelos.repositorio.maestros.IPreguntaBasicaDAO;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/META-INF/application-context.xml")
@Transactional
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)//si defaultRollback=false guarda en la BD y si se coloca true No guarda en la BD
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class TestPreguntasBasicas {
	private PreguntaBasica preguntas,resultado,resultadoB;
	private List<PreguntaBasica> ListaPreguntas;
	@Autowired
	IPreguntaBasicaDAO preguntasDAO;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		preguntas=new PreguntaBasica();
	}

	@After
	public void tearDown() throws Exception {
		preguntas=null;
	}

	@Test
	public void testGuardarPreguntaBasica() {
		preguntas=new PreguntaBasica(999,"Usted Cree que esto es un test","Si es un Test y se usa JUNIT 4", true);
		preguntasDAO.save(preguntas);
		resultado=preguntasDAO.findOne(999);
		assertEquals(resultado.getIdPreguntaBasica(),preguntas.getIdPreguntaBasica());
		System.out.println("Se Ha guardado Una Pregunta Basica");
	}
	@Test
	public void testBuscaPreguntaBasica(){
		resultadoB=preguntasDAO.findOne(3);
		assertNotNull(resultadoB);
		System.out.println("La Pregunta Basica encontrada es:"+resultadoB.getIdPreguntaBasica());
	}
	@Test
	public void testBuscarListaPreguntas(){
		ListaPreguntas=preguntasDAO.findAll();
		for(int i = 0; i<ListaPreguntas.size(); i++){
			System.out.println("Preguntas Encontradas:"+ListaPreguntas.get(i).getPregunta());
		}
	}

}
