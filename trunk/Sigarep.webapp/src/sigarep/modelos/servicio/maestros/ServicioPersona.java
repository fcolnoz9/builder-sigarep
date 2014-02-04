package sigarep.modelos.servicio.maestros;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import sigarep.modelos.data.maestros.Persona;
import sigarep.modelos.data.maestros.Recaudo;

import sigarep.modelos.repositorio.maestros.IPersonaDAO;

@Service("serviciopersona")
public class ServicioPersona {
	private @Autowired IPersonaDAO iPersona;

	public void guardar(Persona person) {
		iPersona.save(person);
	}

	public void eliminar(String cedulaPersona) {
		Persona  per = iPersona.findOne(cedulaPersona);
		per.setEstatus(false);
		iPersona.save(per);
	}

	public List<Persona> listadoPersona() {
		List<Persona> personaLista = iPersona.buscarpersona();
		return personaLista;
	}
	
	public  List<Persona> buscarper(String cedulaPersona) {
		List<Persona> r = new LinkedList<Persona>();
		r = iPersona.buscarpersona();
		return r;
	}

	public List<Persona> buscarPersonaFiltro(String cedulaPersona,String  nombreCompleto, String nombreUsuario) {
		List<Persona> resultado = new LinkedList<Persona>();	
		if (cedulaPersona == null || nombreCompleto==null || nombreUsuario==null) {
			resultado = listadoPersona();
		} else {
			for (Persona persona : listadoPersona()) {
				if (persona.getCedulaPersona().toLowerCase().contains(cedulaPersona)
						&& persona.getNombreUsuario().getNombreCompleto().toLowerCase()
						.contains(nombreCompleto)
						&& persona.getNombreUsuario().getNombreUsuario().toLowerCase()
						.contains(nombreUsuario))
					resultado.add(persona);
			}
		}
		return resultado;
	}
	
	public List<Persona> buscarPersonas(Persona personas){
		List<Persona> result = new ArrayList<Persona>();
        String cedula = personas.getCedulaPersona();
       
        if(cedula==null){
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

