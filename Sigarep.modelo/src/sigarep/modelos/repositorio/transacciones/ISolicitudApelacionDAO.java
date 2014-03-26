package sigarep.modelos.repositorio.transacciones;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sigarep.modelos.data.maestros.LapsoAcademico;
import sigarep.modelos.data.transacciones.EstudianteSancionado;
import sigarep.modelos.data.transacciones.SolicitudApelacion;
import sigarep.modelos.data.transacciones.SolicitudApelacionPK;

public interface ISolicitudApelacionDAO extends JpaRepository<SolicitudApelacion, SolicitudApelacionPK> {

	@Query("SELECT sa FROM SolicitudApelacion sa WHERE sa.id.codigoLapso=:codigoLapso "
			+ "AND sa.id.cedulaEstudiante = :cedulaEstudiante")
	public List<SolicitudApelacion> buscarSolicitudesEstudianteLapsoActual(
			@Param("cedulaEstudiante") String cedulaEstudiante,
			@Param("codigoLapso") String codigoLapso);
	
	/** función función para buscar la solicitud de apelación por lapsoAcademico  
	    * @param LapsoAcademico
	    * @return lista de solicitudes de apelación para ese lapsoAcademico
	*/
	public List<SolicitudApelacion> findByEstudianteSancionado_LapsoAcademico(LapsoAcademico lapsoAcademico);
	
	/** función función para buscar un estudiante sancionado por cedula de identidad  
	    * @param Cedula de identidad de estudiante
	    * @return EstudianteSancionado
	*/
	public EstudianteSancionado findById_CedulaEstudiante(String cedulaEstudiante);
	
	@Query("select count(sa.id.cedulaEstudiante) AS cuenta from SolicitudApelacion sa where sa.veredicto is null")
	public long numeroApleacionesSinVeredicto();
	
	@Query("select count(sa.id.cedulaEstudiante) AS cuenta from SolicitudApelacion sa where sa.numeroSesion is null")
	public long numeroApleacionesSinSesion();
	
	@Query("SELECT sa FROM SolicitudApelacion AS sa, LapsoAcademico AS la " +
			"WHERE sa.id.codigoLapso = la.codigoLapso " +
			"AND la.estatus = 'TRUE'" +
			"AND sa.id.idInstanciaApelada = '1'")
	public List<SolicitudApelacion> buscarSolicitudesCargarRecaudoEntregado();
	
	/** Busca todas las solicitudes de apelacion para el lapso academico actual y la instancia dada
	    * @param Ninguno
	    * @return List<SolicitudApelacion> Lista de solicitudes de apelacion del lapso actual
	*/
	public List<SolicitudApelacion> findById_IdInstanciaApeladaAndEstudianteSancionado_LapsoAcademico_EstatusTrue(Integer idInstanciaApelada);
	
	/** función reusable para busqueda de ApelacionesVerificarRecaudos en instancia I, II y III  
	    * @param LapsoAcademico e idInstanciaApelada
	    * @return lista de solicitudes de apelación para los verificar recaudos de instancia I, II y III
	*/
	public List<SolicitudApelacion> findByEstudianteSancionado_LapsoAcademicoAndId_IdInstanciaApeladaAndEstudianteSancionado_LapsoAcademico_EstatusTrueAndVerificadoFalse(LapsoAcademico lapsoAcademico, Integer idInstanciaApelada);

	/** función reusable para busqueda de ApelacionesAnalizarValidez en instancia I, II y III  
	    * @param LapsoAcademico e idInstanciaApelada
	    * @return lista de solicitudes de apelación para los analizarValidez de instancia I, II y III
	*/
	public List<SolicitudApelacion> findByEstudianteSancionado_LapsoAcademicoAndId_IdInstanciaApeladaAndEstudianteSancionado_LapsoAcademico_EstatusTrueAndVerificadoTrueAndAnalizadoFalseAndVeredictoIsNull(LapsoAcademico lapsoAcademico, Integer idInstanciaApelada);
	
	/** Busca todas las apelaciones para el lapso actual y la instancia dada donde el veredicto y numero de sesion sean null
	    * @param idInstanciaApelada
	    * @return List<SolicitudApelacion> lista de solicitudes de apelación para el lapso actual y la instancia dada
	*/
	public List<SolicitudApelacion> findById_IdInstanciaApeladaAndEstudianteSancionado_LapsoAcademico_EstatusTrueAndNumeroSesionIsNull(Integer idInstanciaApelada);
	
//Marinel, Bely y Jesus
	@Query("SELECT sa FROM SolicitudApelacion AS sa, LapsoAcademico AS la " +
			"WHERE sa.id.codigoLapso = la.codigoLapso " +
			"AND la.estatus = 'TRUE' " +
			"AND sa.analizado = 'TRUE' " +
			"AND sa.id.idInstanciaApelada = '1' " +
			"AND (sa.veredicto IS NULL " +
			"OR sa.numeroSesion IS NULL)")
	public List<SolicitudApelacion> buscarApelacionesVeredictoI();
	
	@Query("SELECT sa FROM SolicitudApelacion AS sa, LapsoAcademico AS la " +
			"WHERE sa.id.codigoLapso = la.codigoLapso " +
			"AND la.estatus = 'TRUE' " +
			"AND ( sa.verificado = 'TRUE'" +
			"OR sa.analizado = 'TRUE') " +
			"AND sa.id.idInstanciaApelada = '2' " +
			"AND (sa.veredicto IS NULL " +
			"OR sa.numeroSesion IS NULL)")
	public List<SolicitudApelacion> buscarApelacionesVeredictoII();
	
	//Marinel, Bely y Jesus
	@Query("SELECT sa FROM SolicitudApelacion AS sa, LapsoAcademico AS la " +
			"WHERE sa.id.codigoLapso = la.codigoLapso " +
			"AND la.estatus = 'TRUE' " +
			"AND ( sa.verificado = 'TRUE'" +
			"OR sa.analizado = 'TRUE') " +
			"AND sa.id.idInstanciaApelada = '3'" +
			"AND (sa.veredicto IS NULL " +
			"OR sa.numeroSesion IS NULL)")
	public List<SolicitudApelacion> buscarApelacionesVeredictoIII();
	
	@Query("select max(SUBSTR(sa.numeroCaso,16,17)) from SolicitudApelacion AS sa, LapsoAcademico la where la.estatus = 'TRUE' " +
			"and la.codigoLapso = sa.id.codigoLapso")
	public String mayorNumeroCaso();

	@Query("SELECT sa FROM SolicitudApelacion AS sa, LapsoAcademico AS la " +
				"WHERE sa.id.codigoLapso = la.codigoLapso " +
				"AND la.estatus = 'TRUE'" +
				"AND sa.id.idInstanciaApelada = :instancia " +
				"AND sa.fechaSesion = " +
				"(SELECT MAX(sa.fechaSesion) FROM SolicitudApelacion AS sa) ")
	public List<SolicitudApelacion> buscarSolicitudParaDatosSesion(@Param("instancia")Integer instancia);

	@Query("SELECT sa FROM SolicitudApelacion sa, LapsoAcademico la " +
			"WHERE sa.id.cedulaEstudiante = :cedulaEstudiante " +
			"AND sa.id.codigoLapso = la.codigoLapso " +
		    "AND la.estatus = 'TRUE'")
	public List<SolicitudApelacion> buscarSolicitudEstudiante(@Param("cedulaEstudiante")String cedulaEstudiante);
	
	@Query("SELECT distinct sa FROM SolicitudApelacion sa WHERE sa.id.codigoLapso=:codigoLapso "
			+ "AND sa.id.cedulaEstudiante = :cedulaEstudiante")
	public List<SolicitudApelacion> buscarSolicitudApelacionLapsoActual(
				@Param("cedulaEstudiante") String cedulaEstudiante,
				@Param("codigoLapso") String codigoLapso);

	public List<SolicitudApelacion> findByEstudianteSancionado(EstudianteSancionado estudianteSancionado);
	
	@Query("SELECT distinct(sa.numeroSesion) FROM SolicitudApelacion AS sa, LapsoAcademico AS la " +
			"WHERE sa.id.codigoLapso = la.codigoLapso " +
			"AND la.estatus = 'TRUE' ")			
	public List<String> buscarSesion();
}
