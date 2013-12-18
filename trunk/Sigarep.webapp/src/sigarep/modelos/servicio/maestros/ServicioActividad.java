package sigarep.modelos.servicio.maestros;

import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sigarep.modelos.data.maestros.Actividad;
import sigarep.modelos.repositorio.maestros.IActividadDAO;

@Service("servicioactividad")
public class ServicioActividad {
	private @Autowired
	IActividadDAO iActividad;

	public void guardar(Actividad actividad) {
		iActividad.save(actividad);
	}

	public void eliminar(Integer id_actividad) {
		iActividad.delete(id_actividad);
	}

	public List<Actividad> listadoActividad() {
		List<Actividad> actividadLista = iActividad.findAll();
		return actividadLista;
	}

	public List<Actividad> buscarActividad(String nombre) {
		List<Actividad> resultado = new LinkedList<Actividad>();
		if (nombre == null || "".equals(nombre)) {
			resultado = listadoActividad();
		} else {
			for (Actividad actividad : listadoActividad()) {
				if (actividad.getNombre().toLowerCase()
						.contains(nombre.toLowerCase())) {
					resultado.add(actividad);
				}
			}
		}
		return resultado;
	}

}
