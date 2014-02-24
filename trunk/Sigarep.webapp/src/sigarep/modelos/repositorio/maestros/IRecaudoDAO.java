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
import sigarep.modelos.data.maestros.TipoMotivo;

public interface IRecaudoDAO extends JpaRepository<Recaudo, Integer> {

	/**
	 * Busca el ultimo id insertado en la tabla Recaudo
	 * @return Ultimo id insertado en la tabla Recaudo
	 */
	@Query("SELECT COALESCE(MAX(r.idRecaudo),0) FROM Recaudo AS r")
	public int buscarUltimoID();
	
	/**
	 * Busca los recaudos activos de un tipo de motivo dado
	 * @param motivo identificador del tipo de motivo al que se le buscaran los recaudos
	 * @return List<Recaudo> Lista de recaudos activos para el tipo de motivo dado
	 */
	public List<Recaudo> findByTipoMotivoAndEstatusTrue(TipoMotivo motivo);

	/**
	 * Busca un recaudo por su nombre
	 * @param nombreRecaudo Nombre del recaudo que se quiere encontrar
	 * @return Recaudo Recaudo encontrado por su nombre
	 */
	public Recaudo findByNombreRecaudo(String nombreRecaudo);
	
	/**
	 * Busca los Recaudos activos, es decir, que poseen estatus true
	 * @return List<Recaudo> Lista de Recaudos con estatus true
	 */
	public List<Recaudo> findByEstatusTrue();
	
	/**
	 * Busca los Recaudos que estan asociados al tipo de motivo General
	 * @return List<Recaudo> Lista de Recaudos asociados al tipo de motivo General
	 */
	@Query("Select rec FROM Recaudo AS rec WHERE rec.tipoMotivo.idTipoMotivo = '1'")
	public List<Recaudo> buscaRecaudosGenerales();
	
	/**
	 * Busca los Recaudos asociados a una apelacion de un estudiante en un lapaso especifico.
	 * @param cedulaEstudiante Cedula del estudiante sancionado
	 * @param codigoLapso Lapso Academico en el cual ocurrio la apelacion
	 * @param idInstanciaApelada Instancia ante la cual se registro la apelacion
	 * @return List<Recaudo> Lista de Recaudos asociados a la apelacion del estudiante en el lapso dados
	 */
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
	 * @throws Todos los recaudos menos los que haya entregado, y los generales
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
