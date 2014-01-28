package sigarep.modelos.repositorio.transacciones;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sigarep.modelos.data.transacciones.RecaudoEntregado;
import sigarep.modelos.data.transacciones.RecaudoEntregadoPK;

public interface IRecaudoEntregadoDAO extends
		JpaRepository<RecaudoEntregado, RecaudoEntregadoPK> {

	@Query("SELECT re FROM RecaudoEntregado AS re, LapsoAcademico la " +
		   "WHERE re.id.cedulaEstudiante = :cedula AND re.id.codigoLapso = la.codigoLapso " +
		   "AND la.estatus = 'TRUE'")
	public List<RecaudoEntregado> buscarRecaudosEntregados(@Param("cedula") String cedula);
	
	@Query("SELECT re FROM RecaudoEntregado AS re, LapsoAcademico  la, InstanciaApelada i " +
			   "WHERE re.id.cedulaEstudiante = :cedula AND re.id.codigoLapso = la.codigoLapso " +
			   "AND re.id.idInstanciaApelada = i.idInstanciaApelada AND la.estatus = 'TRUE' AND re.id.idInstanciaApelada = '1'")
	public List<RecaudoEntregado> buscarRecaudosEntregadosReconsideracion(@Param("cedula") String cedula);

	@Query("SELECT re FROM RecaudoEntregado AS re, LapsoAcademico  la, InstanciaApelada i " +
			   "WHERE re.id.cedulaEstudiante = :cedula AND re.id.codigoLapso = la.codigoLapso " +
			   "AND re.id.idInstanciaApelada = i.idInstanciaApelada AND la.estatus = 'TRUE' AND re.id.idInstanciaApelada = '1'")
	public List<RecaudoEntregado> buscarRecaudosEntregadosRecurso(@Param("cedula") String cedula);

	@Query("SELECT re FROM RecaudoEntregado AS re, LapsoAcademico AS la " +
			"WHERE re.id.codigoLapso = la.codigoLapso " +
			"AND re.id.cedulaEstudiante = :cedula " +
			"AND re.id.idInstanciaApelada = '1'")
	public List<RecaudoEntregado> buscarRecaudosEntregadosVeredictoI(@Param("cedula") String cedula);
	
	@Query("SELECT re FROM RecaudoEntregado AS re, LapsoAcademico AS la " +
			"WHERE re.id.codigoLapso = la.codigoLapso " +
			"AND re.id.cedulaEstudiante = :cedula " +
			"AND re.id.idInstanciaApelada = '2'")
	public List<RecaudoEntregado> buscarRecaudosEntregadosVeredictoII(@Param("cedula") String cedula);
	
	@Query("SELECT re FROM RecaudoEntregado AS re, LapsoAcademico AS la " +
			"WHERE re.id.codigoLapso = la.codigoLapso " +
			"AND re.id.cedulaEstudiante = :cedula " +
			"AND re.id.idInstanciaApelada = '3'" )
	public List<RecaudoEntregado> buscarRecaudosEntregadosVeredictoIII(@Param("cedula") String cedula);
	
	@Query("SELECT re FROM RecaudoEntregado AS re, LapsoAcademico AS la " +
			"WHERE re.id.codigoLapso = la.codigoLapso " +
			"AND re.id.cedulaEstudiante = :cedula " +
			"AND re.id.idInstanciaApelada = '1'")
	public List<RecaudoEntregado> buscarRecaudosEntregadosAnalizarValidezI(@Param("cedula") String cedula);
	
	
	
}