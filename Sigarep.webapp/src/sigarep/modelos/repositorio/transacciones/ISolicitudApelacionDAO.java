package sigarep.modelos.repositorio.transacciones;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sigarep.modelos.data.transacciones.EstudianteSancionado;
import sigarep.modelos.data.transacciones.SolicitudApelacion;
import sigarep.modelos.data.transacciones.SolicitudApelacionPK;

public interface ISolicitudApelacionDAO extends JpaRepository<SolicitudApelacion, SolicitudApelacionPK> {

	@Query("SELECT sa FROM SolicitudApelacion sa WHERE sa.id.codigoLapso=:codigoLapso "
			+ "AND sa.id.cedulaEstudiante = :cedulaEstudiante")
	public List<SolicitudApelacion> buscarSolicitudesEstudianteLapsoActual(
			@Param("cedulaEstudiante") String cedulaEstudiante,
			@Param("codigoLapso") String codigoLapso);
	
	@Query("select sa from SolicitudApelacion sa where sa.id.codigoLapso=:codigoLapso")
	public List<SolicitudApelacion> buscarPorLapso(@Param("codigoLapso")String codigoLapso);
	
	@Query("select sa.estudianteSancionado from SolicitudApelacion sa where sa.id.cedulaEstudiante = :cedulaEstudiante")
	public EstudianteSancionado buscarSancionado(@Param("cedulaEstudiante")String cedulaEstudiante);
	
	@Query("select count(sa.id.cedulaEstudiante) AS cuenta from SolicitudApelacion sa where sa.veredicto is null")
	public long numeroApleacionesSinVeredicto();
	
	@Query("select count(sa.id.cedulaEstudiante) AS cuenta from SolicitudApelacion sa where sa.numeroSesion is null")
	public long numeroApleacionesSinSesion();
	
	@Query("SELECT sa FROM SolicitudApelacion AS sa, LapsoAcademico AS la " +
			"WHERE sa.id.codigoLapso = la.codigoLapso " +
			"AND la.estatus = 'TRUE'" +
			"AND sa.id.idInstanciaApelada = '1'")
	public List<SolicitudApelacion> buscarSolicitudesCargarRecaudoEntregado();
	
	@Query("SELECT sa FROM SolicitudApelacion AS sa, LapsoAcademico AS la " +
			"WHERE sa.id.codigoLapso = la.codigoLapso " +
			"AND sa.id.idInstanciaApelada = '1' " +
			"AND la.estatus = 'TRUE' " +
			"AND sa.verificado = 'FALSE'")
	public List<SolicitudApelacion> buscarApelacionesVerificarRecaudosI();
	
    //Yelitza Camejo
	@Query("SELECT sa FROM SolicitudApelacion AS sa, LapsoAcademico AS la " +
			"WHERE sa.id.codigoLapso = la.codigoLapso " +
			"AND sa.id.idInstanciaApelada = '3' " +
			"AND la.estatus = 'TRUE' " +
			"AND sa.verificado = 'FALSE'")
		public List<SolicitudApelacion> buscarSancionadosJerarquicoVerificar();

	//Lilibeth Achji
	@Query("SELECT sa FROM SolicitudApelacion AS sa, LapsoAcademico AS la " +
			"WHERE sa.id.codigoLapso = la.codigoLapso " +
			"AND sa.id.idInstanciaApelada = '2' " +
			"AND la.estatus = 'TRUE' " +
			"AND sa.verificado = 'FALSE'")
		public List<SolicitudApelacion> buscarSancionadosReconsideracionVerificar();

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
			"AND sa.analizado = 'TRUE' " +
			"AND sa.id.idInstanciaApelada = '2' " +
			"AND (sa.veredicto IS NULL " +
			"OR sa.numeroSesion IS NULL)")
	public List<SolicitudApelacion> buscarApelacionesVeredictoII();
	
	//Marinel, Bely y Jesus
	@Query("SELECT sa FROM SolicitudApelacion AS sa, LapsoAcademico AS la " +
			"WHERE sa.id.codigoLapso = la.codigoLapso " +
			"AND la.estatus = 'TRUE' " +
			"AND sa.analizado = 'TRUE' " +
			"AND sa.id.idInstanciaApelada = '3'" +
			"AND (sa.veredicto IS NULL " +
			"OR sa.numeroSesion IS NULL)")
	public List<SolicitudApelacion> buscarApelacionesVeredictoIII();
	
	
	@Query("select max(SUBSTR(sa.numeroCaso,13,14)) from SolicitudApelacion AS sa, LapsoAcademico la where la.estatus = 'TRUE' " +
			"and la.codigoLapso = sa.id.codigoLapso")
	public String mayorNumeroCaso();
	
	//Flor/Amanda
		//lista de estudiantes sancionados Analizar Validez/Primera Apelación 
		@Query("SELECT sa FROM SolicitudApelacion AS sa, LapsoAcademico AS la " +
					"WHERE sa.id.codigoLapso = la.codigoLapso " +
					"AND la.estatus = 'TRUE' " +
					"AND sa.verificado = 'TRUE' " +
					"AND sa.analizado = 'FALSE' " +
					"AND sa.id.idInstanciaApelada = '1'")

			public List<SolicitudApelacion> BuscarAnalizarValidezI();

		//Flor/Amanda
		//lista de estudiantes sancionados Analizar Validez/Recurso de Reconsideración 
		@Query("SELECT sa FROM SolicitudApelacion AS sa, LapsoAcademico AS la " +
					"WHERE sa.id.codigoLapso = la.codigoLapso " +
					"AND la.estatus = 'TRUE' " +
					"AND sa.verificado = 'TRUE' " +
					"AND sa.analizado = 'FALSE' " +
					"AND sa.veredicto IS NULL " +
					"AND sa.id.idInstanciaApelada = '2'")
			public List<SolicitudApelacion> BuscarAnalizarValidezII();
		
		//lista de estudiantes sancionados Analizar Validez/Recurso Jerarquico 
		@Query("SELECT sa FROM SolicitudApelacion AS sa, LapsoAcademico AS la " +
					"WHERE sa.id.codigoLapso = la.codigoLapso " +
					"AND la.estatus = 'TRUE' " +
					"AND sa.verificado = 'TRUE' " +
					"AND sa.analizado = 'FALSE' " +
					"AND sa.veredicto IS NULL " +
					"AND sa.id.idInstanciaApelada = '3'")
			public List<SolicitudApelacion> BuscarAnalizarValidezIII();

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
}
