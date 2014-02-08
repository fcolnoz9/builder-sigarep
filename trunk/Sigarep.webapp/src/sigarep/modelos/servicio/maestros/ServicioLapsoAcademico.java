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


@Service("serviciolapsoacademico") 
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
	 * @return el lapso academico buscado si es el activo
	 * @parameters codigo del lapso
	 * @throws No dispara ninguna excepcion.
	   */
	public LapsoAcademico encontrarLapsoActivo(){
	    return iLapsoAcademico.buscarLapsoActivo();
	}
	public LapsoAcademico buscarLapsoActivo(){
		return iLapsoAcademico.buscarLapsoActivo();
	}
	/** Lista de lapsos academicos inactivos
	 * @return Lista de lapsos academicos inactivos
	 * @parameters vacio
	 * @throws No dispara ninguna excepcion.
	   */
	public List<LapsoAcademico> listadoLapsoAcademicoInactivos() {
	    List<LapsoAcademico> LapsoAcademicoLista=iLapsoAcademico.buscarInactivoLapso();
	    return LapsoAcademicoLista ;
	}
	/** Lista de lapso academico
	 * @return Lista de todos los lapsos academicos activas e inactivas
	 * @parameters vacio
	 * @throws No dispara ninguna excepcion.
	   */
	public List<LapsoAcademico> buscarTodosLosLapsos(){
		return iLapsoAcademico.buscarLapsosAcademicos();
	}
	/** Buscar un lapso academico 
	 * @return lapso academico  buscada
	 * @parameters  String codigolapso
	 * @throws No dispara ninguna excepcion.
	   */
	public LapsoAcademico buscarUnLapsoAcademico(String codigoLapso){
		return iLapsoAcademico.findOne(codigoLapso);
	}
	/**Buscar un lapso academico por codigo lapso
	 * @param String codigo lapso
	 * @return Busca un lapso academico por codigo lapso
	 * 	  @throws No dispara ninguna excepcion.
	 */
	
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
