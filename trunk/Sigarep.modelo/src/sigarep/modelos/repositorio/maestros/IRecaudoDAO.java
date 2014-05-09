package sigarep.modelos.repositorio.maestros;

import java.util.List;

/*
 * @ (#) EstadoApelacion.java 
 *
 * Copyright 2013 Builder. Todos los derechos reservados.
 * CONFIDENCIAL. El uso está sujeto a los términos de la licencia.
 */
/*
** Archivo del repositorio  del registro del maestro "Recaudo"
 * @ Author Beleanny Atacho 
 * @ Version 1.0, 16/12/13
*/


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sigarep.modelos.data.maestros.Recaudo;
import sigarep.modelos.data.maestros.TipoMotivo;

/**
 * Repositorio IRecaudoDAO: Repositorio relacionado con el Maestro Recaudo.
 * 
 * @author Equipo Builder
 * @version 1.0
 * @since 12/12/2013
 * @last 08/05/2014
 */ 
public interface IRecaudoDAO extends JpaRepository<Recaudo, Integer> {

	/**
	 * Busca el último id insertado en la tabla Recaudo
	 * 
	 * @return Último id insertado en la tabla Recaudo
	 */
	@Query("SELECT COALESCE(MAX(r.idRecaudo),0) FROM Recaudo AS r")
	public int buscarUltimoID();
	
	/**
	 * Busca todos los recaudos activos de un tipo de motivo dado
	 * 
	 * @param motivo Motivo identificador del tipo de motivo al que se le buscaran los recaudos
	 * @return List<Recaudo> Lista de recaudos activos para el tipo de motivo dado
	 */
	public List<Recaudo> findByTipoMotivoAndEstatusTrue(TipoMotivo motivo);

	/**
	 * Busca un recaudo por su nombre
	 * 
	 * @param nombreRecaudo  Nombre del recaudo que se quiere encontrar
	 * @return Recaudo encontrado por su nombre
	 */
	public Recaudo findByNombreRecaudo(String nombreRecaudo);
	
	/**
	 * Busca los Recaudos activos, es decir, que poseen estatus == true
	 * 
	 * @return List<Recaudo> Lista de Recaudos con estatus == true
	 */
	public List<Recaudo> findByEstatusTrue();
	
	/**
	 * Busca los Recaudos que estan asociados al tipo de motivo General
	 * 
	 * @return List<Recaudo> Lista de Recaudos asociados al tipo de motivo General
	 */
	@Query("Select rec FROM Recaudo AS rec WHERE rec.tipoMotivo.idTipoMotivo = '1'")
	public List<Recaudo> buscaRecaudosGenerales();
	
	/**
	 * Busca los Recaudos asociados a una apelacion de un estudiante en un lapso especifico.
	 * 
	 * @param cedula cedula del estudiante sancionado
	 * @param codigoLapso lapso académico en el cual ocurrio la apelación
	 * @param idInstanciaApelada Instancia ante la cual se registro la apelación
	 * @return List<Recaudo> Lista de Recaudos asociados a la apelacion del estudiante en el lapso dado
	 */
	@Query("SELECT r FROM Recaudo AS r, TipoMotivo AS tm, Motivo AS m, LapsoAcademico AS la " +
			"WHERE la.codigoLapso = m.id.codigoLapso AND la.estatus = 'TRUE' " +
			"AND r.tipoMotivo.idTipoMotivo = tm.idTipoMotivo " +
			"AND m.id.cedulaEstudiante = :cedula " +
			"AND m.id.codigoLapso = :codigoLapso " +
			"AND m.id.idInstanciaApelada = :idInstancia " +
			"AND m.id.idTipoMotivo = tm.idTipoMotivo ORDER BY r.tipoMotivo.idTipoMotivo")
	public List<Recaudo> listadoRecaudosPorApelacion(@Param("cedula") String cedula,
			@Param("codigoLapso") String codigoLapso, @Param("idInstancia") Integer idInstancia);
	
	/** Busca los recaudos faltantes, Verificar Recaudos - Recurso Reconsideración  
	 * 
	 * @param cedula
	 * @return List<Recaudo> Recaudos faltantes por entregar de un estudiante sancionado
	 */
	@Query("SELECT r FROM Recaudo AS r, Motivo AS m, LapsoAcademico AS la " +
			"WHERE r.tipoMotivo.idTipoMotivo != '1' AND r.tipoMotivo.idTipoMotivo != '3' " +
			"AND r.idRecaudo NOT IN " +
			"(SELECT re.id.idRecaudo FROM RecaudoEntregado AS re, LapsoAcademico AS la " + 
			"WHERE re.id.cedulaEstudiante = :cedula " +
			"AND la.estatus = 'TRUE') " +
			"AND r.tipoMotivo.idTipoMotivo = m.id.idTipoMotivo " +
			"AND m.id.cedulaEstudiante = :cedula " +
			"AND m.id.codigoLapso = la.codigoLapso " +
			"AND la.estatus = 'TRUE'")
	public List<Recaudo> buscarRecaudosVerificarRecaudosII(@Param("cedula") String cedula);


	/** Busca los recaudos faltantes, Verificar Recaudos - Recurso Jerarquico 
	 * @param cedula
	 * @return List<Recaudo> Recaudos faltantes por entregar de un estudiante sancionado
	 */
	@Query("SELECT r FROM Recaudo AS r, Motivo AS m, LapsoAcademico AS la " +
			"WHERE r.tipoMotivo.idTipoMotivo != '1' AND r.tipoMotivo.idTipoMotivo != '2' " +
			"AND r.idRecaudo NOT IN " +
			"(SELECT re.id.idRecaudo FROM RecaudoEntregado AS re, LapsoAcademico AS la " + 
			"WHERE re.id.cedulaEstudiante = :cedula " +
			"AND la.estatus = 'TRUE') " +
			"AND r.tipoMotivo.idTipoMotivo = m.id.idTipoMotivo " +
			"AND m.id.cedulaEstudiante = :cedula " +
			"AND m.id.codigoLapso = la.codigoLapso " +
			"AND la.estatus = 'TRUE'")
	public List<Recaudo> buscarRecaudosVerificarRecaudosIII(@Param("cedula") String cedula);

}
