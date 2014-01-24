package sigarep.modelos.servicio.maestros;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import sigarep.modelos.data.maestros.PreguntaBasica;
import sigarep.modelos.repositorio.maestros.IPreguntaBasicaDAO;

/**PreguntaBasica
 * UCLA DCYT Sistemas de Informacion.
 * @author Equipo : Builder-Sigarep Lapso 2013-1
 * @version 1.0
 * @since -/12/13
 */

@Service("serviciopreguntabasica") //Definiendo la variable servicio
public class ServicioPreguntaBasica{
	private @Autowired IPreguntaBasicaDAO pre ;

	/** guardar
	 * @param preguntaBasica.
	 * @return No devuelve ningun valor.
	 */
	public void guardarPregunta(PreguntaBasica preb) {
		
    pre.save(preb);
	}
	
	/** eliminar
	 * @param idPreguntaBasica
	 * @return No devuelve ningun valor.
	 */
	public void eliminarPregunta(Integer idPreguntaBasica){
		PreguntaBasica pb = pre.findOne(idPreguntaBasica);
		pb.setEstatus(false);
		pre.save(pb);
	}
	
	/** listadoPreguntas
	 * @param una listadoPreguntas
	 * @return listadoPreguntas.
	 */
	
	public List<PreguntaBasica> listadoPreguntas() {
	    return pre.buscarPreguntab() ;
	}
	
	/** buscarPr
	 * @param pregunta
	 * @return resultado es un listadoPregunta.
	 * @throws la Excepcion es que la pregunta o respuesta este en blanco.
	 */
	public List<PreguntaBasica> buscarPr(String pregunta){
		List<PreguntaBasica> result = new LinkedList<PreguntaBasica>();
		if (pregunta==null || "".equals(pregunta)){//si el nombre es null o vacio,el resultado va a ser la lista completa de todos los profesores
			result = listadoPreguntas();
		}else{//caso contrario se recorre toda la lista y busca los profesores con el nombre indicado en la caja de texto y tambien busca todos los que tengan  las letras iniciales de ese nombre. Realiza la busqueda con el apellido e inicial del apellido.
			for (PreguntaBasica pr: listadoPreguntas()){
				if (pr.getPregunta().toLowerCase().contains(pregunta.toLowerCase())||
					pr.getRespuesta().toLowerCase().contains(pregunta.toLowerCase())){
					result.add(pr);
				}
			}
		}
		return result;

	}
}

	
