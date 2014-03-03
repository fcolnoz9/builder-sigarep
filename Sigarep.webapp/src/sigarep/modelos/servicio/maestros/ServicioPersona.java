package sigarep.modelos.servicio.maestros;

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

	public void eliminar(String cedulaPersona) {
		Persona  per = iPersona.findOne(cedulaPersona);
		per.setEstatus(false);
		iPersona.save(per);
	}

	public List<Persona> listadoPersona() {
		return iPersona.findByEstatusTrue();
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
	
	public Persona buscarPersonaNombreUsuario(String nombreUsuario) {
		Persona  persona = iPersona.buscarPersonaPorNombreUsuario(nombreUsuario);
		return persona;
	}
	
	public Persona buscaUnaPersona(String cedula){
		Persona persona = iPersona.findOne(cedula);
		return persona;
	}
	
	public void eliminarFisicamente(Persona persona){
		iPersona.delete(persona);
	}
}

