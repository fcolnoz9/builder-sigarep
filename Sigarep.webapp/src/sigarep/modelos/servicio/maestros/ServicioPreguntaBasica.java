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

// El servicio interactua con la base de datos

@Service("serviciopreguntabasica") //Definiendo la variable servicio
public class ServicioPreguntaBasica{
	private @Autowired IPreguntaBasicaDAO pre ;

	// Servicio para guardar una pregunta
	public void guardarPregunta(PreguntaBasica preb) {
		
    pre.save(preb);
	}
	
	// Servicio que elimina una pregunta
	public void eliminarPregunta(Integer idPreguntaBasica){
		PreguntaBasica pb = pre.findOne(idPreguntaBasica);
		pb.setEstatus(false);
		pre.save(pb);
	}
	
//	//Servicio para la busqueda de una pregunta
//	public PreguntaBasica buscarPregunta(Integer idPreguntaBasica){
//		return pre.findOne(idPreguntaBasica);
//	
//	}
//	
	
	public List<PreguntaBasica> listadoPreguntas() {
	    return pre.buscarPreguntab() ;
	}
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

	
