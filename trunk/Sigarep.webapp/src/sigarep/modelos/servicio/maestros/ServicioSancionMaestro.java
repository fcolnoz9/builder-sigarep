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

	public void actualizar(SancionMaestro sanm) {

	}

	public void eliminarSancion(Integer SancionMaestro) {
		san.delete(SancionMaestro);
	}

	public SancionMaestro buscarSancion(Integer idSancionMaestro) {
		return san.findOne(idSancionMaestro);
	}

	public List<SancionMaestro> listadoSanciones() {
		List<SancionMaestro> sancionesLista = san.findAll();
		return sancionesLista;
	}

	public List<SancionMaestro> buscarSan(String nombre) {
		List<SancionMaestro> result = new LinkedList<SancionMaestro>();
		if (nombre == null || "".equals(nombre)) {
			result = listadoSanciones();
		} else {
			for (SancionMaestro sm : listadoSanciones()) {
				if (sm.getNombreSancion().toLowerCase()
						.contains(nombre.toLowerCase())
						|| sm.getDescripcion().toLowerCase()
								.contains(nombre.toLowerCase())) {
					result.add(sm);
				}
			}
		}
		return result;
	}
}
