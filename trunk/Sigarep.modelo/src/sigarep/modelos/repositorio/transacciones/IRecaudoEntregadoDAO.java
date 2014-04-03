package sigarep.modelos.repositorio.transacciones;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sigarep.modelos.data.transacciones.RecaudoEntregado;
import sigarep.modelos.data.transacciones.RecaudoEntregadoPK;

public interface IRecaudoEntregadoDAO extends
		JpaRepository<RecaudoEntregado, RecaudoEntregadoPK> {

	@Query("SELECT re FROM RecaudoEntregado AS re, LapsoAcademico  la "
			+ "WHERE re.id.cedulaEstudiante = :cedula "
			+ "AND re.id.codigoLapso = la.codigoLapso "
			+ "AND la.estatus = 'TRUE'")
	public List<RecaudoEntregado> buscarRecaudosEntregadosLapsoActual(@Param("cedula") String cedula);
	
	@Query("SELECT re FROM RecaudoEntregado AS re, LapsoAcademico  la "
			+ "WHERE re.id.cedulaEstudiante = :cedula "
			+ "AND re.id.codigoLapso = :codigoLapso")
	public List<RecaudoEntregado> buscarRecaudosEntregadosPorLapsoAcademico(@Param("cedula") String cedula, @Param("codigoLapso") String codigoLapso);
	
	@Query("SELECT re FROM RecaudoEntregado AS re, LapsoAcademico AS la " +
			"WHERE re.id.codigoLapso = la.codigoLapso " +
			"AND la.estatus = 'TRUE' " +
			"AND re.id.cedulaEstudiante = :cedula " +
			"AND re.id.idInstanciaApelada = '1'")
	public List<RecaudoEntregado> buscarRecaudosEntregadosVerificarRecaudosII(@Param("cedula") String cedula);

	@Query("SELECT re FROM RecaudoEntregado AS re, LapsoAcademico AS la " +
			"WHERE re.id.codigoLapso = la.codigoLapso " +
			"AND la.estatus = 'TRUE' " +
			"AND re.id.cedulaEstudiante = :cedula " +
			"AND re.id.idInstanciaApelada='2'")
	public List<RecaudoEntregado> buscarRecaudosEntregadosVerificarRecaudosIII(@Param("cedula") String cedula);
	
	
	@Query("SELECT re FROM RecaudoEntregado AS re, LapsoAcademico AS la " +
			"WHERE re.id.codigoLapso = la.codigoLapso " +
			"AND re.id.cedulaEstudiante = :cedula " +
			"AND (re.id.idInstanciaApelada = '2')")
	public List<RecaudoEntregado> buscarRecaudosEntregadosObservacionesanalizarIII(@Param("cedula") String cedula);
	
	
}