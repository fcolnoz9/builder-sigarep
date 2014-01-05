package sigarep.modelos.servicio.maestros;

import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sigarep.modelos.data.maestros.SancionMaestro;
import sigarep.modelos.data.maestros.SancionMaestroFiltros;
import sigarep.modelos.repositorio.maestros.ISancionMaestroDAO;

// El servicio interactua con la base de datos

@Service("serviciosancionmaestro")
// Definiendo la variable servicio
public class ServicioSancionMaestro {
	private @Autowired
	ISancionMaestroDAO san;

	public void guardarSancion(SancionMaestro sanm) {
		san.save(sanm);
	}

	public void eliminarSancion(Integer sanm) {
		SancionMaestro sancion = san.findOne(sanm);
		sancion.setEstatus(false);
		san.save(sancion);
	}

	public SancionMaestro buscarUnaSancion(Integer idSancionMaestro) {
		return san.findOne(idSancionMaestro);
	}

	public List<SancionMaestro> listadoSanciones() {
		List<SancionMaestro> sancionesLista = san.buscarSancionesActivas();
		return sancionesLista;
	}

	public List<SancionMaestro> buscarSancion(SancionMaestroFiltros filtros) {
		List<SancionMaestro> result = new LinkedList<SancionMaestro>();
		String nombre = filtros.getNombre().toLowerCase();
		String descripcion = filtros.getDescripcion().toLowerCase();

		if (nombre == null || descripcion == null) {
			result = listadoSanciones();
		} else {
			for (SancionMaestro sm : listadoSanciones()) {
				if (sm.getNombreSancion().toLowerCase().contains(nombre)
						&& sm.getDescripcion().toLowerCase()
								.contains(descripcion)) {
					result.add(sm);
				}
			}
		}
		return result;
	}
}
