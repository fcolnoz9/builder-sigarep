package sigarep.modelos.repositorio.transacciones;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
//
import sigarep.modelos.data.transacciones.RecaudoEntregado;
import sigarep.modelos.data.transacciones.RecaudoEntregadoPK;

/**
 * Repositorio IRecaudoEntregadoDAO
 * @author Equipo Builder
 * @version 1.0
 * @since 20/12/2013
 * @last 12/05/2014
 */
public interface IRecaudoEntregadoDAO extends
		JpaRepository<RecaudoEntregado, RecaudoEntregadoPK> {
	
	/** Busqueda de recaudos entregados por estudiante que no tienen un soporte asociado en el lapso actual 
	    * @param cedula: Cedula del estudiante
	    * @return List<RecaudoEntregado> lista de recaudos entregados
	    */
	@Query("SELECT re FROM RecaudoEntregado AS re, LapsoAcademico AS la "
			+ "WHERE re.id.idRecaudo NOT IN (SELECT s.recaudoEntregado.id.idRecaudo FROM Soporte AS s, LapsoAcademico AS la "
			+ "								 WHERE s.recaudoEntregado.id.cedulaEstudiante = :cedula "
			+ "								 AND s.recaudoEntregado.id.codigoLapso = la.codigoLapso "
			+ "								 AND la.estatus = 'TRUE') "
			+ "AND re.id.idInstanciaApelada = (SELECT MAX(r.id.idInstanciaApelada) "
			+ "								   FROM RecaudoEntregado AS r "
			+ "								   WHERE r.id.idRecaudo = re.id.idRecaudo) "
			+ "AND re.id.cedulaEstudiante = :cedula "
			+ "AND re.id.codigoLapso = la.codigoLapso "
			+ "AND la.estatus='TRUE'")
	public List<RecaudoEntregado> buscarRecaudosEntregadosSinSoporte(@Param("cedula") String cedula);

	/** Busqueda de recaudos entregados para el Recurso Reconsideracion  
    * @param cedula
    * @return List<RecaudoEntregado> lista de recaudos entregados de un estudiante sancionado
    */
	@Query("SELECT re FROM RecaudoEntregado AS re, LapsoAcademico  la, InstanciaApelada i " +
			   "WHERE re.id.cedulaEstudiante = :cedula AND re.id.codigoLapso = la.codigoLapso " +
			   "AND re.id.idInstanciaApelada = i.idInstanciaApelada AND la.estatus = 'TRUE' AND re.id.idInstanciaApelada = '1'")
	public List<RecaudoEntregado> buscarRecaudosEntregadosReconsideracion(@Param("cedula") String cedula);
	
	/** Busqueda de recaudos entregados para el Recurso Jerarquico  
	    * @param cedula
	    * @return List<RecaudoEntregado> lista de recaudos entregados de un estudiante sancionado
	    */
	@Query("SELECT re FROM RecaudoEntregado AS re, LapsoAcademico  la "
			+ "WHERE re.id.cedulaEstudiante = :cedula "
			+ "AND re.id.codigoLapso = la.codigoLapso "
			+ "AND la.estatus = 'TRUE'")
	public List<RecaudoEntregado> buscarRecaudosEntregadosRecursoJerarquico(@Param("cedula") String cedula);
	
	/**
	 * Busqueda de recaudos entregados para los veredictos en cualquier apelacion (I, II, III)
	 * @param cedulaEstudiante
	 * @param codigoLapso
	 * @param idInstanciaApelada
	 * @return List<RecaudoEntregado> lista de recaudos entregados de un estudiante sancionado 
	 */
	public List<RecaudoEntregado> findById_CedulaEstudianteAndId_CodigoLapsoAndId_IdInstanciaApelada(String cedulaEstudiante, String codigoLapso, Integer idInstanciaApelada);
	
	/**
	 * Busqueda de recaudos entregados para analizar la validez en la primera apelacion
	 * @param cedula
	 * @return List<RecaudoEntregado> lista de recaudos entregados de un estudiante sancionado
	 */
	@Query("SELECT re FROM RecaudoEntregado AS re, LapsoAcademico AS la " +
			"WHERE re.id.codigoLapso = la.codigoLapso " +
			"AND la.estatus = 'TRUE' " +
			"AND re.id.cedulaEstudiante = :cedula " +
			"AND re.id.idInstanciaApelada = '1'")
	public List<RecaudoEntregado> buscarRecaudosEntregadosAnalizarValidezI(@Param("cedula") String cedula);
	
	/**
	 * Busqueda de recaudos entregados para la verificacion en la segunda apelacion
	 * @param cedula
	 * @return List<RecaudoEntregado> lista de recaudos entregados de un estudiante sancionado
	 */
	@Query("SELECT re FROM RecaudoEntregado AS re, LapsoAcademico AS la " +
			"WHERE re.id.codigoLapso = la.codigoLapso " +
			"AND la.estatus = 'TRUE' " +
			"AND re.id.cedulaEstudiante = :cedula " +
			"AND re.id.idInstanciaApelada = '1'")
	public List<RecaudoEntregado> buscarRecaudosEntregadosVerificarRecaudosII(@Param("cedula") String cedula);

	/**
	 * Busqueda de recaudos entregados para la verificacion en la tercera apelacion
	 * @param cedula
	 * @return List<RecaudoEntregado> lista de recaudos entregados de un estudiante sancionado
	 */
	@Query("SELECT re FROM RecaudoEntregado AS re, LapsoAcademico AS la " +
			"WHERE re.id.codigoLapso = la.codigoLapso " +
			"AND la.estatus = 'TRUE' " +
			"AND re.id.cedulaEstudiante = :cedula " +
			"AND (re.id.idInstanciaApelada = '1' " +
			"OR re.id.idInstanciaApelada = '2')")
	public List<RecaudoEntregado> buscarRecaudosEntregadosVerificarRecaudosIII(@Param("cedula") String cedula);
	
	/**
	 * Busqueda de recaudos entregados para analizar la validez en la segunda apelacion
	 * @param cedula
	 * @return List<RecaudoEntregado> lista de recaudos entregados de un estudiante sancionado
	 */
	@Query("SELECT re FROM RecaudoEntregado AS re, LapsoAcademico AS la " +
			"WHERE re.id.codigoLapso = la.codigoLapso " +
			"AND la.estatus = 'TRUE' " +
			"AND re.id.cedulaEstudiante = :cedula " +
			"AND (re.id.idInstanciaApelada = '1' " +
			"OR re.id.idInstanciaApelada = '2')")
	public List<RecaudoEntregado> buscarRecaudosEntregadosAnalizarValidezII(@Param("cedula") String cedula);

	/**
	 * Busqueda de recaudos entregados para analizar la validez en la tercera apelacion
	 * @param cedula
	 * @return List<RecaudoEntregado> lista de recaudos entregados de un estudiante sancionado
	 */
	@Query("SELECT re FROM RecaudoEntregado AS re, LapsoAcademico AS la " +
			"WHERE re.id.codigoLapso = la.codigoLapso " +
			"AND la.estatus = 'TRUE' " +
			"AND re.id.cedulaEstudiante = :cedula " +
			"AND (re.id.idInstanciaApelada = '2' " +
			"OR re.id.idInstanciaApelada = '3')")
	public List<RecaudoEntregado> buscarRecaudosEntregadosAnalizarValidezIII(@Param("cedula") String cedula);
	
	/**
	 * Busqueda de recaudos entregados y sus observaciones para analizar la validez en la tercera apelacion
	 * @param cedula
	 * @return List<RecaudoEntregado> lista de recaudos entregados de un estudiante sancionado
	 */
	@Query("SELECT re FROM RecaudoEntregado AS re, LapsoAcademico AS la " +
			"WHERE re.id.codigoLapso = la.codigoLapso " +
			"AND re.id.cedulaEstudiante = :cedula " +
			"AND (re.id.idInstanciaApelada = '2')")
	public List<RecaudoEntregado> buscarRecaudosEntregadosObservacionesanalizarIII(@Param("cedula") String cedula);
	
	/** Busqueda de recaudos entregados por estudiante que tienen un soporte asociado en el lapso actual 
	    * @param cedula: Cedula del estudiante
	    * @return List<RecaudoEntregado> lista de recaudos entregados de un estudiante sancionado
	    */
	@Query("SELECT re FROM RecaudoEntregado AS re, LapsoAcademico AS la "
			+ "WHERE re.id IN "
			+ "(SELECT s.recaudoEntregado.id FROM Soporte AS s, LapsoAcademico AS la "
			+ "WHERE s.recaudoEntregado.id.cedulaEstudiante = :cedula "
			+ "AND s.recaudoEntregado.id.codigoLapso = la.codigoLapso "
			+ "AND la.estatus = 'TRUE') "
			+ "AND re.id.cedulaEstudiante = :cedula "
			+ "AND re.id.codigoLapso = la.codigoLapso "
			+ "AND la.estatus = 'TRUE'")
	public List<RecaudoEntregado> buscarRecuadosEntregadosConSoporte(@Param("cedula") String cedula);
}