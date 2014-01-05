package sigarep.modelos.servicio.maestros;

import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import sigarep.modelos.data.maestros.Momento;
import sigarep.modelos.data.maestros.MomentoFiltros;
import sigarep.modelos.repositorio.maestros.IMomentoDAO;

/*
 * @ (#) ServicioMomento.java 
 *
 * Copyright 2013 Builder. Todos los derechos reservados.
 * CONFIDENCIAL. El uso está sujeto a los términos de la licencia.
 */
/*
 ** Servicio del registro del maestro "Momento"
 * @ Author Lilibeth Achji 
 * @ Version 1.0, 16/12/13
 */

@Service("serviciomomento")
public class ServicioMomento {
	private @Autowired
	IMomentoDAO mo;

	// metodo para guardar
	public void guardar(Momento mome) {
		mo.save(mome);
	}

	// metodo que permite eliminar
	public void eliminarMomento(Integer momento) {
		Momento mom = mo.findOne(momento);
		mom.setEstatus(false);
		mo.save(mom);
	}

	public List<Momento> listadoMomento() {
		List<Momento> MomentoLista = mo.buscarMomentoActivas();
		return MomentoLista;
	}

	// Busca en la lista de Momentos
	public List<Momento> buscarMomento(MomentoFiltros filtros) {
		List<Momento> result = new LinkedList<Momento>();
		String nombreMomento = filtros.getNombreMomento().toLowerCase();
		String descripcion = filtros.getDescripcion().toLowerCase();

		if (nombreMomento == null || descripcion == null) {
			result = listadoMomento();
		} else {
			for (Momento mome : listadoMomento()) {
				if (mome.getNombreMomento().toLowerCase()
						.contains(nombreMomento)
						&& mome.getDescripcion().toLowerCase()
								.contains(descripcion)) {
					result.add(mome);
				}
			}
		}
		return result;
	}

}
