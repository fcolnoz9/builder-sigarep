package sigarep.modelos.servicio.maestros;

import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sigarep.modelos.data.maestros.SancionMaestro;
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

	public List<SancionMaestro> listaTipoSanciones() {
		List<SancionMaestro> sancionesLista = san.buscarSancionesActivas();
		return sancionesLista;
	}

	public List<SancionMaestro> buscarTipoSancion(String nombre) {
		List<SancionMaestro> result = new LinkedList<SancionMaestro>();
		if (nombre == null) {
			result = listaTipoSanciones();
		} else {
			for (SancionMaestro sm : listaTipoSanciones()) {
				if (sm.getNombreSancion().toLowerCase().contains(nombre)) {
					result.add(sm);
				}
			}
		}
		return result;
	}
}
