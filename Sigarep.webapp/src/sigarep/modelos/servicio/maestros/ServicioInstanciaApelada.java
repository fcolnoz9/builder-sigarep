package sigarep.modelos.servicio.maestros;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;


import sigarep.modelos.data.maestros.LapsoAcademico;
import sigarep.modelos.repositorio.maestros.ILapsoAcademicoDAO;;

@Service("spp") //Definiendo la variable servicio
public class ServicioInstanciaApelada{
	private @Autowired IInstanciaApeladaDAO pv ;

	public void guardar(InstanciaApelada pro) {
		
    pv.save(pro);
	}
	public void actualizar(){
		
	}
	public void eliminar(String codigoInstancia){
		pv.delete(codigoInstancia);
	}
	public LapsoAcademico buscar(String codigoInstancia){
		return pv.findOne(codigoInstancia);
	}
	public List<InstanciaApelada> listadoInstanciaApelada() {
		List<InstanciaApelada> InstanciaApeladaLista=pv.findAll();
	    return InstanciaApeladaLista ;
	}
	public List<InstanciaApelada> buscarP(String codigoInstancia){
		List<InstanciaApelada> result = new LinkedList<InstanciaApelada>();
		if (codigoInstancia==null || "".equals(codigoInstancia)){//si el nombre es null o vacio,el resultado va a ser la lista completa de todos los profesores
			result = listadoInstanciaApelada();
		}else{//caso contrario se recorre toda la lista y busca los profesores con el nombre indicado en la caja de texto y tambien busca todos los que tengan  las letras iniciales de ese nombre. Realiza la busqueda con el apellido e inicial del apellido.
			for (InstanciaApelada l: listadoInstanciaApelada()){
				if (l.getIdInstanciaApelada.toLowerCase().contains(codigoInstancia.toLowerCase()))
				{
					result.add(l);
				}
			}
		}
		return result;
	}
}
