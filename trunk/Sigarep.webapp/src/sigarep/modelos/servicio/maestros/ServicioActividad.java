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
		Actividad miActividad = iActividad.findOne(id_actividad);
		miActividad.setEstatus(false);
		iActividad.save(miActividad);
	}

	public List<Actividad> listadoActividad() {
		List<Actividad> actividadLista = iActividad.buscarActividadesActivas();
		return actividadLista;
	}

	public List<Actividad> buscarActividad(String nombre, String responsable) {
		List<Actividad> resultado = new LinkedList<Actividad>();
		if (nombre == null || responsable == null) {
			resultado = listadoActividad();
		} else {
			for (Actividad act : listadoActividad()) {
				if (act.getNombre().toLowerCase().contains(nombre)
						&& act.getInstanciaApelada().getInstanciaApelada().toLowerCase()
								.contains(responsable)) {
					resultado.add(act);
				}
			}
		}
		return resultado;
	}
}
