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
import sigarep.modelos.repositorio.maestros.ILapsoAcademicoDAO;


@Service("serviciolapsoacademico") //Definiendo la variable servicio
public class ServicioLapsoAcademico{
	private @Autowired ILapsoAcademicoDAO iLapsoAcademico ;
	
	/** Guardar lapso academico 
	 * @return nada
	 * @parameters el objeto lapsoA
	 * @throws No dispara ninguna excepcion.
	 */
	public void guardarLapso(LapsoAcademico lapsoA) {
		iLapsoAcademico.save(lapsoA);
	}
	
	/** Lista de lapsos academicos activos
	 * @return Lista de lapsos academicos  registrados y activos
	 * @parameters vacio
	 * @throws No dispara ninguna excepcion.
	   */
	public List<LapsoAcademico> listadoLapsoAcademico() {
		List<LapsoAcademico> LapsoAcademicoLista=iLapsoAcademico.buscarActivoLapso();
	    return LapsoAcademicoLista ;
	}
	

	/** Buscar lapsos academicos por codigo de lapso
	 * @return el lapso academico buscado si existe
	 * @parameters codigo del lapso
	 * @throws No dispara ninguna excepcion.
	   */

	public LapsoAcademico buscarLapsoActivo(){
		return iLapsoAcademico.buscarLapsoActivo();
	}
	
	public List<LapsoAcademico> buscarTodosLosLapsos(){
		return iLapsoAcademico.buscarLapsosAcademicos();
	}
	public LapsoAcademico buscarUnLapsoAcademico(String codigoLapso){
		return iLapsoAcademico.findOne(codigoLapso);
	}

	public List<LapsoAcademico> buscarLapsoAcademico(String codigoLapso){
		List<LapsoAcademico> result = new LinkedList<LapsoAcademico>();
		if (codigoLapso==null || "".equals(codigoLapso)){
			result = listadoLapsoAcademico();
		}else{
			for (LapsoAcademico lapso: listadoLapsoAcademico()){
				if (lapso.getCodigoLapso().toLowerCase().contains(codigoLapso.toLowerCase()))
				{
					result.add(lapso);
				}
			}
		}
		return result;

	}
}
