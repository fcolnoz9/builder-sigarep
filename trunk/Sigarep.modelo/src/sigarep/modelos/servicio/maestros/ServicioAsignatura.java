package sigarep.modelos.servicio.maestros;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sigarep.modelos.data.maestros.Asignatura;
import sigarep.modelos.data.maestros.ProgramaAcademico;
import sigarep.modelos.repositorio.maestros.IAsignaturaDAO;

/**
 * Clase ServicioAsignatura 
 * 
 * @author BUILDER
 * @version 1.0
 * @since 18/12/2013
 */


@Service("servicioAsignatura")
public class ServicioAsignatura {
	private @Autowired
	IAsignaturaDAO iAsignatura;
	

	/**
	 * Buscar Asignatura
	 * @param codigoAsignatura
	 * @return el objeto buscado
	 * @throws No dispara ninguna excepción.
	 */
	public Asignatura buscarAsignatura(String codigoAsignatura){
		return iAsignatura.findOne(codigoAsignatura);
	}
	
	/**
	 * Guardar Asignatura
	 * @param Asignatura asignatura
	 * @return Guarda el objeto
	 * @throws No dispara ninguna excepción.
	 */
	public void guardarAsignatura(Asignatura asignatura){
		iAsignatura.save(asignatura);
	}
	
	/** listaAsignaturas
	 * @param todos
	 * @return lista de asignaturas
	 * @throws 
	 */
	public List<Asignatura> listaAsignaturas() {
		List<Asignatura> asignaturasLista = iAsignatura.findByEstatusTrue();
		return asignaturasLista;
	}
	
	/** buscarTodas
	 * @param todos
	 * @return lista de asignaturas, con estatus = true
	 * @throws 
	 */
	public List<Asignatura> buscarTodas(){
		return iAsignatura.findByEstatusTrue();
	}
	
	/**
	 * Buscar Asignatura filtrando por Programa
	 * 
	 * @param String ProgramaAcedemico
	 * @return Busca una asignatura por Programa
	 * @throws No dispara ninguna excepción.
	 */
	public List<Asignatura> buscarAsignaturasPorPrograma (ProgramaAcademico programa){
		return iAsignatura.findByProgramaAcademicoAndEstatusTrue(programa);
	}
	
		
	/**
	 * Buscar Asignatura filtrando por nombre
	 * 
	 * @param String NombreAsignatura
	 * @return Busca una asignatura por nombre
	 * @throws No dispara ninguna excepción.
	 */
	public Asignatura buscarAsignaturaNombre(String nombreAsignatura) {
		Asignatura asignatura = iAsignatura.findByNombreAsignatura(nombreAsignatura);
	    return asignatura;
	}
	
	/**
	 * Buscar Asignatura filtrando por nombre y programa Academico
	 * 
	 * @param String NombreAsignatura y Programa Academico
	 * @return Busca una asignatura por nombre y Programa Academico
	 * @throws No dispara ninguna excepción.
	 */
	public Asignatura buscarAsignaturaNombreAndProgramaAcademico(String nombreAsignatura, ProgramaAcademico programaAcademico) {
		Asignatura asignatura = iAsignatura.findByNombreAsignaturaAndProgramaAcademico(nombreAsignatura,programaAcademico);
	    return asignatura;
	}
}
