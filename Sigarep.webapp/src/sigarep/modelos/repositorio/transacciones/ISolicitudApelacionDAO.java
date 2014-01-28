package sigarep.modelos.repositorio.transacciones;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sigarep.modelos.data.maestros.LapsoAcademico;
import sigarep.modelos.data.transacciones.EstudianteSancionado;
import sigarep.modelos.data.transacciones.EstudianteSancionadoPK;
import sigarep.modelos.data.transacciones.SolicitudApelacion;
import sigarep.modelos.data.transacciones.SolicitudApelacionPK;

public interface ISolicitudApelacionDAO extends JpaRepository<SolicitudApelacion, SolicitudApelacionPK> {

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
			"AND la.estatus = 'TRUE'")
	public List<SolicitudApelacion> buscarSolicitudesCargarRecaudoEntregado();
	
	@Query("SELECT sa FROM SolicitudApelacion AS sa, LapsoAcademico AS la " +
			"WHERE sa.id.codigoLapso = la.codigoLapso " +
			"AND la.estatus = 'TRUE' " +
			"AND sa.verificado = 'FALSE'")
	public List<SolicitudApelacion> buscarApelacionesVerificarRecaudosI();

	
	@Query("Select DISTINCT sap FROM SolicitudApelacion AS sap, LapsoAcademico la, InstanciaApelada i, " +
			"EstudianteSancionado esa, ApelacionEstadoApelacion ap where la.estatus = 'TRUE' and " +
			"ap.id.idInstanciaApelada  = '1' and sap.veredicto <> '' and esa.id.cedulaEstudiante = " +
			"sap.id.cedulaEstudiante and i.idInstanciaApelada = ap.id.idInstanciaApelada and " +
			" la.codigoLapso = esa.id.codigoLapso and sap.veredicto = 'No Aprobado' " +
			"and sap.id.cedulaEstudiante not in (select ap.id.cedulaEstudiante from ApelacionEstadoApelacion " +
			"as ap where ap.id.idInstanciaApelada = '2') ")		
	public List<SolicitudApelacion> buscarSancionadosReconsideracion();
    //Yelitza Camejo
	@Query("Select DISTINCT sap FROM SolicitudApelacion AS sap, LapsoAcademico la, InstanciaApelada i, " +
			"EstudianteSancionado esa, ApelacionEstadoApelacion ap " +
			"where la.estatus = 'TRUE' and " +
			"sap.id.idInstanciaApelada = ap.id.idInstanciaApelada and " +
			"ap.id.idInstanciaApelada  = '3' " +
			"and esa.id.cedulaEstudiante = " +
			"sap.id.cedulaEstudiante and i.idInstanciaApelada = ap.id.idInstanciaApelada and " +
			" la.codigoLapso = esa.id.codigoLapso ")
		public List<SolicitudApelacion> buscarSancionadosJerarquicoVerificar();

	//Lilibeth Achji
	@Query("Select DISTINCT sap FROM SolicitudApelacion AS sap, LapsoAcademico la, InstanciaApelada i, " +
			"EstudianteSancionado esa, ApelacionEstadoApelacion ap " +
			"where la.estatus = 'TRUE' and " +
			"sap.id.idInstanciaApelada = ap.id.idInstanciaApelada and " +
			"ap.id.idInstanciaApelada  = '2' " +
			"and esa.id.cedulaEstudiante = " +
			"sap.id.cedulaEstudiante and i.idInstanciaApelada = ap.id.idInstanciaApelada and " +
			" la.codigoLapso = esa.id.codigoLapso ")
		public List<SolicitudApelacion> buscarSancionadosReconsideracionVerificar();

	//Maria Flores
	@Query("Select DISTINCT sap FROM SolicitudApelacion AS sap, LapsoAcademico la, InstanciaApelada i, " +
			"EstudianteSancionado esa, ApelacionEstadoApelacion ap where la.estatus = 'TRUE' and " +
			"ap.id.idInstanciaApelada  = '1' and sap.veredicto <> '' and esa.id.cedulaEstudiante = " +
			"sap.id.cedulaEstudiante and i.idInstanciaApelada = ap.id.idInstanciaApelada and " +
			" la.codigoLapso = esa.id.codigoLapso " +
			"and sap.id.cedulaEstudiante not in (select ap.id.cedulaEstudiante from ApelacionEstadoApelacion " +
			"as ap where ap.id.idInstanciaApelada = '2') ")		
	public List<SolicitudApelacion> buscarSancionadosRecursoJerarquico();

//Marinel, Bely y Jesus
	@Query("SELECT sa FROM SolicitudApelacion AS sa, LapsoAcademico AS la " +
			"WHERE sa.id.codigoLapso = la.codigoLapso " +
			"AND la.estatus = 'TRUE' " +
			"AND sa.analizado = 'TRUE' " +
			"AND (sa.veredicto IS NULL " +
			"OR sa.numeroSesion IS NULL)")
	public List<SolicitudApelacion> buscarApelacionesVeredictoI();
	
	//Marinel, Bely y Jesus
			@Query("SELECT sa FROM SolicitudApelacion AS sa, LapsoAcademico AS la " +
					"WHERE sa.id.codigoLapso = la.codigoLapso " +
					"AND la.estatus = 'TRUE' " +
					"AND sa.analizado = 'TRUE' " +
					"AND sa.id.idInstanciaApelada = '3'" +
					"AND (sa.veredicto IS NULL " +
					"OR sa.numeroSesion IS NULL)")
			public List<SolicitudApelacion> buscarApelacionesVeredictoIII();
	
	
	@Query("select max(sa.numeroCaso) from SolicitudApelacion AS sa, LapsoAcademico la where la.estatus = 'TRUE' " +
			"and la.codigoLapso = sa.id.codigoLapso")
	public int mayorNumeroCaso();
	
	//Flor/Amanda
	//lista de estudiantes sancionados Analizar Validez/Primera Apelación 
	@Query("SELECT sa FROM SolicitudApelacion AS sa, LapsoAcademico AS la " +
				"WHERE sa.id.codigoLapso = la.codigoLapso " +
				"AND la.estatus = 'TRUE' " +
				"AND sa.verificado = 'TRUE' " +
				"AND sa.analizado = 'FALSE' " +
				"AND sa.id.idInstanciaApelada = '1'")
		public List<SolicitudApelacion> BuscarAnalizarValidez1();
}
