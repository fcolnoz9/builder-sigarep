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
public class ServicioLapsoAcademico{
	private @Autowired ILapsoAcademicoDAO pv ;

	public void guardar(LapsoAcademico pro) {
		
    pv.save(pro);
	}
	public void actualizar(){
		
	}
	public void eliminar(String codigoLapso){
		pv.delete(codigoLapso);
	}
	public LapsoAcademico buscar(String codigoLapso){
		return pv.findOne(codigoLapso);
	}
	public List<LapsoAcademico> listadoLapsoAcademico() {
		List<LapsoAcademico> LapsoAcademicoLista=pv.findAll();
	    return LapsoAcademicoLista ;
	}
	public List<LapsoAcademico> buscarP(String codigoLapso){
		List<LapsoAcademico> result = new LinkedList<LapsoAcademico>();
		if (codigoLapso==null || "".equals(codigoLapso)){//si el nombre es null o vacio,el resultado va a ser la lista completa de todos los profesores
			result = listadoLapsoAcademico();
		}else{//caso contrario se recorre toda la lista y busca los profesores con el nombre indicado en la caja de texto y tambien busca todos los que tengan  las letras iniciales de ese nombre. Realiza la busqueda con el apellido e inicial del apellido.
			for (LapsoAcademico l: listadoLapsoAcademico()){
				if (l.getCodigoLapso().toLowerCase().contains(codigoLapso.toLowerCase()))
				{
					result.add(l);
				}
			}
		}
		return result;

	}
}
