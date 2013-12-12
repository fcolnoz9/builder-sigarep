package sigarep.modelos.servicio.maestros;

import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import sigarep.modelos.data.maestros.Noticia;
import sigarep.modelos.repositorio.maestros.INoticiaDAO;

@Service("servicionoticia")
public class ServicioNoticia {
	private @Autowired
	INoticiaDAO iNoticia;

	public void guardar(Noticia noticia) {
		iNoticia.save(noticia);
	}

	public void eliminar(Integer idNoticia) {
		iNoticia.delete(idNoticia);
	}

	public List<Noticia> listadoNoticia() {
		List<Noticia> noticiaLista = iNoticia.findAll();
		return noticiaLista;
	}

	public List<Noticia> buscarNoticia(String nombre) {
		List<Noticia> resultado = new LinkedList<Noticia>();
		if (nombre == null || "".equals(nombre)) {
			// si el codigo es null o vacio,el resultado va a ser la lista completa de
			//todas las noticias
			resultado = listadoNoticia();
		} else {// caso contrario se recorre toda la lista y busca las noticias.
			for (Noticia noticia : listadoNoticia()) {
				if (noticia.getTitulo().toLowerCase().contains(nombre.toLowerCase())) {
					resultado.add(noticia);
				}
			}
		}
		return resultado;
	}

}
