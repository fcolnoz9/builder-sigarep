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
	private @Autowired INoticiaDAO Inoticia ;

	public void guardar(Noticia noticias) {
	
		Inoticia.save(noticias);
	}
	public void actualizar(){
		
	}
	public void eliminar(Integer idNoticia){
		Inoticia.delete(idNoticia);
	}
//	public Noticia buscar(String titulo){
//		return not.findOne(titulo);
//	}
	public List<Noticia> listadoNoticia() {
		List<Noticia> noticiaLista=Inoticia.findAll();
	    return noticiaLista ;
	}
	public List<Noticia> buscarNoticia(String titulo){
		List<Noticia> result = new LinkedList<Noticia>();
		if (titulo==null || "".equals(titulo)){//si el codigo es null o vacio,el resultado va a ser la lista completa de todos los productos
			result = listadoNoticia();
		}else{//caso contrario se recorre toda la lista y busca los productos con el codigo indicado en la caja de texto y tambien busca todos los que tengan  las letras iniciales de ese codigo. Realiza la busqueda con el codigo e inicial del codigo.
			for (Noticia p: listadoNoticia()){
				if (p.getTitulo().toLowerCase().contains(titulo.toLowerCase())){
					result.add(p);
				}
			}
		}
		return result;

	}

}
