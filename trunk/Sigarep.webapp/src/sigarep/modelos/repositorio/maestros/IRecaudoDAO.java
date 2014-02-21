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

import sigarep.modelos.data.maestros.Actividad;
import sigarep.modelos.data.maestros.Recaudo;

public interface IRecaudoDAO extends JpaRepository<Recaudo, Integer> {

	/**
	 * Busca el ultimo id insertado en la tabla Recaudo
	 * @return Ultimo id insertado en la tabla Recaudo
	 */
	@Query("SELECT COALESCE(MAX(r.idRecaudo),0) FROM Recaudo AS r")
	public int buscarUltimoID();
	
	/**
	 * Busca los recaudos activos de un tipo de motivo dado
	 * @param idTipoMotivo identificador del tipo de motivo al que se le buscaran los recaudos
	 * @return List<Recaudo> Lista de recaudos activos para el tipo de motivo dado
	 */
	public List<Recaudo> findByTipoMotivoAndEstatusTrue(Integer tipoMotivo);

	/**
	 * Busca un recaudo por su nombre
	 * @param nombreRecaudo Nombre del recaudo que se quiere encontrar
	 * @return Recaudo Recaudo encontrado por su nombre
	 */
	public Recaudo findByNombreRecaudo(String nombreRecaudo);
	
	@Query("Select rec FROM Recaudo AS rec WHERE rec.estatus = TRUE")
	public List<Recaudo> buscaRecaudosActivos();
	
	@Query("Select rec FROM Recaudo AS rec")
	public List<Recaudo> buscaRecaudos();
	
	@Query("Select rec FROM Recaudo AS rec WHERE rec.tipoMotivo.idTipoMotivo = '1'")
	public List<Recaudo> buscaRecaudosGenerales();
	
	@Query("SELECT r FROM Recaudo AS r, TipoMotivo AS tm, Motivo AS m, LapsoAcademico AS la " +
			"WHERE la.codigoLapso = m.id.codigoLapso AND la.estatus = 'TRUE' " +
			"AND r.tipoMotivo.idTipoMotivo = tm.idTipoMotivo " +
			"AND m.id.cedulaEstudiante = :cedula " +
			"AND m.id.codigoLapso = :codigoLapso " +
			"AND m.id.idInstanciaApelada = :idInstancia " +
			"AND m.id.idTipoMotivo = tm.idTipoMotivo ORDER BY r.tipoMotivo.idTipoMotivo")
	public List<Recaudo> listadoRecaudosPorApelacion(@Param("cedula") String cedula, @Param("codigoLapso") String codigoLapso, @Param("idInstancia") Integer idInstancia);
	
	/** busqueda de recaudos faltantes, Verificar Recaudos - Recurso Reconsideracion  
	 * @param cedula
	 * @return recaudos faltantes por entregar de un estudiante sancionado
	 * @throws Todos losrecaudos menos los que haya entregado, y los generales
	 *  correspondientes a las apelaciones 1 y 3
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


	/** busqueda de recaudos faltantes, Verificar Recaudos - Recurso Jerarquico 
	 * @param cedula
	 * @return recaudos faltantes por entregar de un estudiante sancionado
	 * @throws Todos losrecaudos menos los que haya entregado, y los generales
	 *  correspondientes a las apelaciones 1 y 2
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
