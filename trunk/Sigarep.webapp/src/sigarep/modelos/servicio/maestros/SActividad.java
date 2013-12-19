package sigarep.modelos.servicio.maestros;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sigarep.modelos.data.maestros.Actividad;
import sigarep.modelos.repositorio.maestros.IActividadDAO;

@Service("Actividad")
public class SActividad {

	private @Autowired IActividadDAO iActividad;
	
	public SActividad() {
		// TODO Auto-generated constructor stub
	}

	public void guardar(Actividad actividad){
		iActividad.save(actividad);
	}
	
	public void actualizar(Actividad actividad){
		
	}
	public void eliminar(Integer idActividad){
		Actividad miActividad = iActividad.findOne(idActividad);
		miActividad.setEstatus(false);
		iActividad.save(miActividad);
	}
	
	public Actividad buscar(Integer idActividad){
		return iActividad.findOne(idActividad);
	}
}
