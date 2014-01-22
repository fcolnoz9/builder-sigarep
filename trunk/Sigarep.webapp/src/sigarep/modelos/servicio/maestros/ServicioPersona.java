package sigarep.modelos.servicio.maestros;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import sigarep.modelos.data.maestros.Persona;

import sigarep.modelos.repositorio.maestros.IPersonaDAO;

@Service("serviciopersona")
public class ServicioPersona {
	private @Autowired IPersonaDAO iPersona;

	public void guardar(Persona person) {
		iPersona.save(person);
	}

	public void eliminar(Integer cedulaPersona) {
		Persona  per = iPersona.findOne(cedulaPersona);
		per.setEstatus(false);
		iPersona.save(per);
	}

	public List<Persona> listadoPersona() {
		List<Persona> personaLista = iPersona.buscarpersona();
		return personaLista;
	}
	
	public  List<Persona> buscarper(Integer cedulaPersona) {
		List<Persona> r = new LinkedList<Persona>();
		r = iPersona.findAll();
		return r;
	}

//	public List<Persona> buscarNoticia(String nombre) {
//		List<Noticia> resultado = new LinkedList<Noticia>();
//		if (nombre == null || "".equals(nombre)) {
//			// si el codigo es null o vacio,el resultado va a ser la lista completa de
//			//todas las noticias
//			resultado = listadoNoticia();
//		} else {// caso contrario se recorre toda la lista y busca las noticias.
//			for (Noticia noticia : listadoNoticia()) {
//				if (noticia.getTitulo().toLowerCase().contains(nombre.toLowerCase())) {
//					resultado.add(noticia);
//				}
//			}
//		}
//		return resultado;
//	}
	
	public List<Persona> buscarPersonas(Persona personas){
		List<Persona> result = new ArrayList<Persona>();
        Integer cedula = personas.getCedulaPersona();
       
        if(cedula==null || cedula==0){
        	result= listadoPersona();
        }
        else{
			for (Persona per: listadoPersona())
			{
				if (per.getCedulaPersona()==cedula){
					result.add(per);
				}
			}
        }
		return result;
        } 
}

