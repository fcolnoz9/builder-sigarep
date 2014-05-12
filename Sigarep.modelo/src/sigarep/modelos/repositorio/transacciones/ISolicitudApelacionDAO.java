package sigarep.modelos.repositorio.transacciones;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sigarep.modelos.data.maestros.LapsoAcademico;
import sigarep.modelos.data.transacciones.EstudianteSancionado;
import sigarep.modelos.data.transacciones.SolicitudApelacion;
import sigarep.modelos.data.transacciones.SolicitudApelacionPK;

/**
 * Repositorio ISolicitudApelacionDAO
 * @author Equipo Builder
 * @version 1.0
 * @since 20/12/2013
 * @last 12/05/2014
 */
public interface ISolicitudApelacionDAO extends JpaRepository<SolicitudApelacion, SolicitudApelacionPK> {

	/**
	 * Busqueda de las solicitudes de apelacion hechas en un lapso por un estudiante
	 * @param cedulaEstudiante
	 * @param codigoLapso
	 * @return List<SolicitudApelacion> lista de solicitudes de apelación para un lapso de un estudiante
	 */
	@Query("SELECT sa FROM SolicitudApelacion sa WHERE sa.id.codigoLapso=:codigoLapso "
			+ "AND sa.id.cedulaEstudiante = :cedulaEstudiante")
	public List<SolicitudApelacion> buscarSolicitudesEstudianteLapsoActual(
			@Param("cedulaEstudiante") String cedulaEstudiante,
			@Param("codigoLapso") String codigoLapso);
	
	/**
	 * Busqueda de las solicitudes de apelacion por lapso academico y analizado true
	 * @param lapsoAcademico
	 * @param idInstanciaApelada
	 * @return List<SolicitudApelacion> lista de solicitudes de apelación para ese lapsoAcademico y para solicitudes de apelacion analizadas
	 */
	public List<SolicitudApelacion> findByAnalizadoTrueAndEstudianteSancionado_LapsoAcademicoAndId_IdInstanciaApeladaAndEstudianteSancionado_LapsoAcademico_EstatusTrue(LapsoAcademico lapsoAcademico, Integer idInstanciaApelada);
	
	/** 
	 * Busqueda de las solicitudes de apelacion hechas por lapsoAcademico  
	 * @param LapsoAcademico
	 * @return List<SolicitudApelacion> lista de solicitudes de apelación para ese lapsoAcademico
	*/
	public List<SolicitudApelacion> findByEstudianteSancionado_LapsoAcademico(LapsoAcademico lapsoAcademico);
	
	/** 
	 * Busqueda de un estudiante sancionado por cedula de identidad  
	 * @param Cedula de identidad de estudiante
	 * @return Estudiante sancionado
	*/
	public SolicitudApelacion findById_CedulaEstudiante(String cedulaEstudiante);
	
	/**
	 * Busca y cuenta las apelaciones que no tengan veredicto
	 * @return Numero de apelaciones sin veredicto
	 */
	@Query("select count(sa.id.cedulaEstudiante) AS cuenta from SolicitudApelacion sa where sa.veredicto is null")
	public long numeroApleacionesSinVeredicto();
	
	/**
	 * Busca y cuenta las apelaciones que no tengan secion asociada
	 * @return Numero de apelaciones sin secion
	 */
	@Query("select count(sa.id.cedulaEstudiante) AS cuenta from SolicitudApelacion sa where sa.numeroSesion is null")
	public long numeroApleacionesSinSesion();
	
	/**
	 * Busca todas las solicitudes de apelacion para el lapso academico actual y la instancia dada
	 * @param idInstanciaApelada
	 * @return List<SolicitudApelacion> Lista de solicitudes de apelacion del lapso actual
	 */
	public List<SolicitudApelacion> findById_IdInstanciaApeladaAndEstudianteSancionado_LapsoAcademico_EstatusTrue(Integer idInstanciaApelada);
	
	/**
	 * Funcion reusable para busqueda de apelaciones verificar recaudos en instancia I, II y III
	 * @param lapsoAcademico
	 * @param idInstanciaApelada
	 * @return List<SolicitudApelacion> lista de solicitudes de apelación para los verificar recaudos de instancia I, II y III
	 */
	public List<SolicitudApelacion> findByEstudianteSancionado_LapsoAcademicoAndId_IdInstanciaApeladaAndEstudianteSancionado_LapsoAcademico_EstatusTrueAndVerificadoFalse(LapsoAcademico lapsoAcademico, Integer idInstanciaApelada);

	/**
	 * Funcion reusable para busqueda de Apelaciones ante instancia III
	 * @param lapsoAcademico
	 * @param idInstanciaApelada
	 * @return List<SolicitudApelacion> lista de solicitudes de apelación en la instancia  III 
	 */
	public List<SolicitudApelacion> findByEstudianteSancionado_LapsoAcademicoAndId_IdInstanciaApeladaAndEstudianteSancionado_LapsoAcademico_EstatusTrue(LapsoAcademico lapsoAcademico, Integer idInstanciaApelada);

	/**
	 * Funcion reusable para busqueda de ApelacionesAnalizarValidez en instancia I, II y III
	 * @param lapsoAcademico
	 * @param idInstanciaApelada
	 * @return List<SolicitudApelacion> lista de solicitudes de apelación para los analizarValidez de instancia I, II y III 
	 */
	public List<SolicitudApelacion> findByEstudianteSancionado_LapsoAcademicoAndId_IdInstanciaApeladaAndEstudianteSancionado_LapsoAcademico_EstatusTrueAndVerificadoTrueAndAnalizadoFalseAndVeredictoIsNull(LapsoAcademico lapsoAcademico, Integer idInstanciaApelada);
	
	/**
	 * Busca todas las apelaciones para el lapso actual y la instancia dada donde el veredicto y numero de sesion sean null
	 * @param idInstanciaApelada
	 * @return List<SolicitudApelacion> lista de solicitudes de apelación para el lapso actual y la instancia dada
	 */
	public List<SolicitudApelacion> findById_IdInstanciaApeladaAndEstudianteSancionado_LapsoAcademico_EstatusTrueAndNumeroSesionIsNull(Integer idInstanciaApelada);
	
	/**
	 * Busca las solicitudes de apelaciones para el veredicto en la primera apelacion
	 * @return List<SolicitudApelacion> Lista con las solicitudes para el veredicto en la primera apelacion 
	 */
	@Query("SELECT sa FROM SolicitudApelacion AS sa, LapsoAcademico AS la " +
			"WHERE sa.id.codigoLapso = la.codigoLapso " +
			"AND la.estatus = 'TRUE' " +
			"AND sa.analizado = 'TRUE' " +
			"AND sa.id.idInstanciaApelada = '1' " +
			"AND (sa.veredicto IS NULL " +
			"OR sa.numeroSesion IS NULL)")
	public List<SolicitudApelacion> buscarApelacionesVeredictoI();
	
	/**
	 * Busca las solicitudes de apelaciones para el veredicto en la segunda apelacion
	 * @return List<SolicitudApelacion> Lista con las solicitudes para el veredicto en la segunda apelacion
	 */
	@Query("SELECT sa FROM SolicitudApelacion AS sa, LapsoAcademico AS la " +
			"WHERE sa.id.codigoLapso = la.codigoLapso " +
			"AND la.estatus = 'TRUE' " +
			"AND ( sa.verificado = 'TRUE'" +
			"OR sa.analizado = 'TRUE') " +
			"AND sa.id.idInstanciaApelada = '2' " +
			"AND (sa.veredicto IS NULL " +
			"OR sa.numeroSesion IS NULL)")
	public List<SolicitudApelacion> buscarApelacionesVeredictoII();
	
	/**
	 * Busca las solicitudes de apelaciones para el veredicto en la tercera apelacion
	 * @return List<SolicitudApelacion> Lista con las solicitudes para el veredicto en la tercera apelacion
	 */
	@Query("SELECT sa FROM SolicitudApelacion AS sa, LapsoAcademico AS la " +
			"WHERE sa.id.codigoLapso = la.codigoLapso " +
			"AND la.estatus = 'TRUE' " +
			"AND ( sa.verificado = 'TRUE'" +
			"OR sa.analizado = 'TRUE') " +
			"AND sa.id.idInstanciaApelada = '3'" +
			"AND (sa.veredicto IS NULL " +
			"OR sa.numeroSesion IS NULL)")
	public List<SolicitudApelacion> buscarApelacionesVeredictoIII();
	
	/**
	 * Busqueda del caso con el numero mayor
	 * @return Numero de caso mayor en string
	 */
	@Query("select max(SUBSTR(sa.numeroCaso,16,17)) from SolicitudApelacion AS sa, LapsoAcademico la where la.estatus = 'TRUE' " +
			"and la.codigoLapso = sa.id.codigoLapso")
	public String mayorNumeroCaso();

	/**
	 * Busqueda de los datos de la secion para las solicitudes de apelacion
	 * @param instancia
	 * @return List<SolicitudApelacion> Lista con los datos de la secion de esa instancia
	 */
	@Query("SELECT sa FROM SolicitudApelacion AS sa, LapsoAcademico AS la " +
				"WHERE sa.id.codigoLapso = la.codigoLapso " +
				"AND la.estatus = 'TRUE'" +
				"AND sa.id.idInstanciaApelada = :instancia " +
				"AND sa.fechaSesion = " +
				"(SELECT MAX(sa.fechaSesion) FROM SolicitudApelacion AS sa) ")
	public List<SolicitudApelacion> buscarSolicitudParaDatosSesion(@Param("instancia")Integer instancia);

	/**
	 * Busqueda de las solicitudes de apelacion de un estudiante en el lapso en curso
	 * @param cedulaEstudiante
	 * @return List<SolicitudApelacion> Lista con las solicitudes de un estudiante en el lapso en curso
	 */
	@Query("SELECT sa FROM SolicitudApelacion sa, LapsoAcademico la " +
			"WHERE sa.id.cedulaEstudiante = :cedulaEstudiante " +
			"AND sa.id.codigoLapso = la.codigoLapso " +
		    "AND la.estatus = 'TRUE'")
	public List<SolicitudApelacion> buscarSolicitudEstudiante(@Param("cedulaEstudiante")String cedulaEstudiante);
	
	/**
	 * Busca los estudiantes sancionados que no sea el de esa cedula
	 * @param cedulaEstudiante
	 * @param codigoLapso
	 * @return List<SolicitudApelacion> lista con estudiantes sancionados
	 */
	@Query("SELECT distinct sa FROM SolicitudApelacion sa WHERE sa.id.codigoLapso=:codigoLapso "
			+ "AND sa.id.cedulaEstudiante = :cedulaEstudiante")
	public List<SolicitudApelacion> buscarSolicitudApelacionLapsoActual(
				@Param("cedulaEstudiante") String cedulaEstudiante,
				@Param("codigoLapso") String codigoLapso);

	/**
	 * Busca a un estudiante sancionado
	 * @param estudianteSancionado
	 * @return List<SolicitudApelacion> lista con el estudiante sancionado
	 */
	public List<SolicitudApelacion> findByEstudianteSancionado(EstudianteSancionado estudianteSancionado);
	
	@Query("SELECT distinct(sa.numeroSesion) FROM SolicitudApelacion AS sa, LapsoAcademico AS la " +
			"WHERE sa.id.codigoLapso = la.codigoLapso " +
			"AND la.estatus = 'TRUE' ")			
	public List<String> buscarSesion();
	
	/**
	 * Busca las seciones validas para revision de solicitudes
	 * @param sess
	 * @param insta
	 * @param instb
	 * @return List<SolicitudApelacion> lista con las seciones validas
	 */
	@Query("SELECT sa FROM SolicitudApelacion AS sa, LapsoAcademico AS la " +
			"WHERE sa.id.codigoLapso = la.codigoLapso " +
			"AND la.estatus = 'TRUE'" +
			"AND (sa.id.idInstanciaApelada = :insta OR sa.id.idInstanciaApelada = :instb) " +
			"AND sa.numeroSesion = :sess ")
	public List<SolicitudApelacion> buscarSesionValida(
			@Param("sess") String sess,
			@Param("insta") Integer insta, 
			@Param("instb") Integer instb);
	
	/**
	 * Busca el numero de caso y la instancia que esta revisando la solicitud
	 * @param cedula
	 * @return Numero del caso
	 */
	@Query("SELECT sa.numeroCaso FROM SolicitudApelacion AS sa, LapsoAcademico AS la "
			+ "WHERE sa.id.idInstanciaApelada = (SELECT MAX(s.id.idInstanciaApelada) "
			+ "									 FROM SolicitudApelacion AS s, LapsoAcademico AS l "
			+ "									 WHERE s.id.cedulaEstudiante = :cedula "
			+ "									 AND s.id.codigoLapso = l.codigoLapso "
			+ "									 AND l.estatus='TRUE') "
			+ "AND sa.id.cedulaEstudiante = :cedula "
			+ "AND sa.id.codigoLapso = la.codigoLapso "
			+ "AND la.estatus='TRUE'")
	public String buscarNumeroDeCasoCargarRecaudo(@Param("cedula") String cedula);
	
	/**
	 * Busca la fecha de solicitud y la instancia que esta revisando la solicitud
	 * @param cedula
	 * @return Fecha de la solicitud
	 */
	@Query("SELECT sa.fechaSolicitud FROM SolicitudApelacion AS sa, LapsoAcademico AS la "
			+ "WHERE sa.id.idInstanciaApelada = (SELECT MAX(s.id.idInstanciaApelada) "
			+ "									 FROM SolicitudApelacion AS s, LapsoAcademico AS l "
			+ "									 WHERE s.id.cedulaEstudiante = :cedula "
			+ "									 AND s.id.codigoLapso = l.codigoLapso "
			+ "									 AND l.estatus='TRUE') "
			+ "AND sa.id.cedulaEstudiante = :cedula "
			+ "AND sa.id.codigoLapso = la.codigoLapso "
			+ "AND la.estatus='TRUE'")
	public Date buscarFechaApelacionCargarRecaudo(@Param("cedula") String cedula);
	
	
}
