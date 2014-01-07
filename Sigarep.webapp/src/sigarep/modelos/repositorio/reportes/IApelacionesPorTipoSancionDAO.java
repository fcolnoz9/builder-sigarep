package sigarep.modelos.repositorio.reportes;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import sigarep.modelos.data.reportes.ApelacionesPorLapso;
import sigarep.modelos.data.reportes.ApelacionesPorTipoSancion;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public interface IApelacionesPorTipoSancionDAO {
	
	
	/**
	 * Busca todas las apelaciones de estudiantes agrupadas por lapso.
	 * 
	 * @return numero de apelaciones agrupadas por lapso academico<code>ApelacionesPorTipoSancion</code>;
	 * 		   o una lista vacia si no se registraron apelaciones
	 */
	List<ApelacionesPorTipoSancion> buscarTodos();
	
	
	/**
	 * Busca por los filtros asignados para las apelaciones.
	 * 
	 * @param programa 
	 * @return las apelaciones que cumplan con los filtros <code>ApelacionesPorTipoSancion</code>;
	 * 		  o una lista vacia si no existe solicitudes que cumplan con dichos filtros.
	 */
	List<ApelacionesPorTipoSancion> buscarPorPrograma(String programa);
	
	/**
	 * Busca por los filtros asignados para las apelaciones.
	 * 
	 * @param tipomotivo 
	 * @return las apelaciones que cumplan con los filtros <code>ApelacionesPorTipoSancion</code>;
	 * 		  o una lista vacia si no existe solicitudes que cumplan con dichos filtros.
	 */
	List<ApelacionesPorTipoSancion> buscarPorTipoMotivo(String tipomotivo);
	
	
	
	/**
	 * Busca por los filtros asignados para las apelaciones.
	 * 
	 * @param lapsoinicial, lapsofinal 
	 * @return las apelaciones que cumplan con los filtros <code>ApelacionesPorTipoSancion</code>;
	 * 		  o una lista vacia si no existe solicitudes que cumplan con dichos filtros.
	 */
	List<ApelacionesPorTipoSancion> buscarPorLapso(String lapsoinicial, String lapsofinal);
	
	
	
	
	
	

	
	/**
	 * Busca por los filtros asignados para las apelaciones.
	 * 
	 * @param tipomotivo, programa
	 * @return las apelaciones que cumplan con los filtros <code>ApelacionesPorTipoSancion</code>;
	 * 		  o una lista vacia si no existe solicitudes que cumplan con dichos filtros.
	 */
	List<ApelacionesPorTipoSancion> buscarPorTipoMotivoYPrograma(String programa, String tipomotivo);
	
	/**
	 * Busca por los filtros asignados para las apelaciones.
	 * 
	 * @param lapsoinicial, lapsofinal, programa 
	 * @return las apelaciones que cumplan con los filtros <code>ApelacionesPorTipoSancion</code>;
	 * 		  o una lista vacia si no existe solicitudes que cumplan con dichos filtros.
	 */
	List<ApelacionesPorTipoSancion> buscarPorLapsoYPrograma(String lapsoinicial, String lapsofinal, String programa);
	
	
	
	
	
	
	
	
	
	/**
	 * Busca por los filtros asignados para las apelaciones.
	 * 
	 * @param lapsoinicial, lapsofinal, tipomotivo 
	 * @return las apelaciones que cumplan con los filtros <code>ApelacionesPorTipoSancion</code>;
	 * 		  o una lista vacia si no existe solicitudes que cumplan con dichos filtros.
	 */
	List<ApelacionesPorTipoSancion> buscarPorLapsoYTipoMotivo(String lapsoinicial, String lapsofinal, String tipomotivo);
	
	
	
	
	
	
	/**
	 * Busca por los filtros asignados para las apelaciones.
	 * 
	 * @param lapsoinicial, lapsofinal, tiposancion, programa 
	 * @return las apelaciones que cumplan con los filtros <code>apelacionesportiposancion</code>;
	 * 		  o una lista vacia si no existe solicitudes que cumplan con dichos filtros.
	 */
	List<ApelacionesPorTipoSancion> buscarPorLapsoYTipoMotivoYPrograma(String lapsoinicial, String lapsofinal, String tipomotivo, String programa);


}