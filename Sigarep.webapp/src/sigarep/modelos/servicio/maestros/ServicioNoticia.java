package sigarep.modelos.servicio.maestros;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import sigarep.modelos.data.maestros.Noticia;
import sigarep.modelos.data.maestros.NoticiaFiltro;
import sigarep.modelos.repositorio.maestros.INoticiaDAO;

@Service("servicionoticia")
public class ServicioNoticia {
	private @Autowired
	INoticiaDAO iNoticia;

	public void guardar(Noticia noticia) {
		iNoticia.save(noticia);
	}

	public void eliminar(Integer idNoticia) {
		Noticia n = iNoticia.findOne(idNoticia);
		n.setEstatus(false);
		iNoticia.save(n);
	}

	public List<Noticia> listadoNoticia() {
		List<Noticia> noticiaLista = iNoticia.busar();
		return noticiaLista;
	}
	
	public  List<Noticia> buscarn(Integer idNoticia) {
		List<Noticia> r = new LinkedList<Noticia>();
		r = iNoticia.findAll();
		return r;
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
	
	public List<Noticia> buscarNoticias(NoticiaFiltro noticias){
		List<Noticia> result = new ArrayList<Noticia>();
        String titulo = noticias.getTitulo().toLowerCase();
        String contenido = noticias.getContenido().toLowerCase();
        if(titulo==null || contenido==null){
        	result= listadoNoticia();
        }
        else{
			for (Noticia n: listadoNoticia())
			{
				if (n.getTitulo().toLowerCase().contains(titulo)&&
					n.getContenido().toLowerCase().contains(contenido)){
					result.add(n);
				}
			}
        }
		return result;
        } 
}