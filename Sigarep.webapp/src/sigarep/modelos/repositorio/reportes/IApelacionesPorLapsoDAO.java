package sigarep.modelos.repositorio.reportes;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import sigarep.modelos.data.reportes.ChartDataApelacionesPorLapso;
import sigarep.modelos.data.transacciones.SolicitudApelacion;

@SuppressWarnings("unused")
public interface IApelacionesPorLapsoDAO extends JpaRepository<ChartDataApelacionesPorLapso,String> {
	
	//Sin Filtro
	@Query("select count(*), solicitud_apelacion.codigo_lapso from solicitud_apelacion group by solicitud_apelacion.codigo_lapso")
	public List<ChartDataApelacionesPorLapso> BuscarTodos();
	
	//Solo con filtro de carrera
	@Query("select count(*), solicitud_apelacion.codigo_lapso from solicitud_apelacion, estudiante, estudiante_sancionado, programa_academico where solicitud_apelacion.cedula_estudiante = estudiante_sancionado.cedula_estudiante and estudiante_sancionado.cedula_estudiante = estudiante.cedula_estudiante and estudiante.id_programa = programa_academico.id_programa and programa_academico.nombre_programa = :programa group by solicitud_apelacion.codigo_lapso")
    public List<ChartDataApelacionesPorLapso> BuscarPorPrograma(String programa);
	
	//Solo con filtro de tipo sancion
	@Query("select count(*), solicitud_apelacion.codigo_lapso from solicitud_apelacion, estudiante_sancionado, programa_academico where solicitud_apelacion.cedula_estudiante = estudiante_sancionado.cedula_estudiante and estudiante_sancionado.id_sancion = sancion_maestro.id_sancion and sancion_maestro.nombre_sancion = :tiposancion group by solicitud_apelacion.codigo_lapso")
	public List<ChartDataApelacionesPorLapso> BuscarPorTipoSancion(String tiposancion);
	
	//Filtro de tipo sancion y carrera
	@Query("select count(*), solicitud_apelacion.codigo_lapso from solicitud_apelacion, estudiante_sancionado, programa_academico where solicitud_apelacion.cedula_estudiante = estudiante_sancionado.cedula_estudiante and estudiante_sancionado.id_sancion = sancion_maestro.id_sancion and sancion_maestro.nombre_sancion = :tiposancion and estudiante_sancionado.cedula_estudiante = estudiante.cedula_estudiante and estudiante.id_programa = programa_academico.id_programa and programa_academico.nombre_programa = :programa group by solicitud_apelacion.codigo_lapso")
	public List<ChartDataApelacionesPorLapso> BuscarPorTipoSancionYPrograma(String tiposancion, String programa);
	
	//Filtro de lapso inicial y lapso final
	@Query("select count(*), solicitud_apelacion.codigo_lapso from solicitud_apelacion, estudiante, estudiante_sancionado, programa_academico where solicitud_apelacion.fecha_solicitud between (select lapso_academico.fecha_inicio from solicitud_apelacion, estudiante_sancionado, lapso_academico where solicitud_apelacion.codigo_lapso = :lapsoinicial and solicitud_apelacion.codigo_lapso = estudiante_sancionado.codigo_lapso and estudiante_sancionado.codigo_lapso = 'lapso_academico.codigo_lapso') and (select lapso_academico.fecha_cierre from solicitud_apelacion, estudiante_sancionado, lapso_academico where solicitud_apelacion.codigo_lapso = :lapsofinal and solicitud_apelacion.codigo_lapso = estudiante_sancionado.codigo_lapso and estudiante_sancionado.codigo_lapso = 'lapso_academico.codigo_lapso') group by solicitud_apelacion.codigo_lapso")
	public List<ChartDataApelacionesPorLapso> BuscarPorLapso(String lapsoinicial, String lapsofinal);

	//select lapso_academico.fecha_inicio from solicitud_apelacion, estudiante_sancionado, lapso_academico where solicitud_apelacion.codigo_lapso = '2013' and solicitud_apelacion.codigo_lapso = estudiante_sancionado.codigo_lapso and estudiante_sancionado.codigo_lapso = lapso_academico.codigo_lapso
	//select lapso_academico.fecha_cierre from solicitud_apelacion, estudiante_sancionado, lapso_academico where solicitud_apelacion.codigo_lapso = '2013' and solicitud_apelacion.codigo_lapso = estudiante_sancionado.codigo_lapso and estudiante_sancionado.codigo_lapso = lapso_academico.codigo_lapso
	
	//Filtro de lapso inicial y lapso final con carrera
	@Query("select count(*), solicitud_apelacion.codigo_lapso from solicitud_apelacion, estudiante, estudiante_sancionado, programa_academico where solicitud_apelacion.fecha_solicitud between (select lapso_academico.fecha_inicio from solicitud_apelacion, estudiante_sancionado, lapso_academico where solicitud_apelacion.codigo_lapso = :LapsoInicial and solicitud_apelacion.codigo_lapso = estudiante_sancionado.codigo_lapso and estudiante_sancionado.codigo_lapso = 'lapso_academico.codigo_lapso') and (select lapso_academico.fecha_cierre from solicitud_apelacion, estudiante_sancionado, lapso_academico where solicitud_apelacion.codigo_lapso = :LapsoFinal and solicitud_apelacion.codigo_lapso = estudiante_sancionado.codigo_lapso and estudiante_sancionado.codigo_lapso = 'lapso_academico.codigo_lapso') and solicitud_apelacion.cedula_estudiante = estudiante_sancionado.cedula_estudiante and estudiante_sancionado.cedula_estudiante = estudiante.cedula_estudiante and estudiante.id_programa = programa_academico.id_programa and programa_academico.nombre_programa = :programa group by solicitud_apelacion.codigo_lapso")
	public List<ChartDataApelacionesPorLapso> BuscarPorLapsoYCarrera(String lapsoinicial, String lapsofinal, String programa);
	
	//Filtro de lapso inicial y lapso final con tipo sancion
	@Query("select count(*), solicitud_apelacion.codigo_lapso from solicitud_apelacion, estudiante, estudiante_sancionado, programa_academico where solicitud_apelacion.fecha_solicitud between (select lapso_academico.fecha_inicio from solicitud_apelacion, estudiante_sancionado, lapso_academico where solicitud_apelacion.codigo_lapso = :LapsoInicial and solicitud_apelacion.codigo_lapso = estudiante_sancionado.codigo_lapso and estudiante_sancionado.codigo_lapso = 'lapso_academico.codigo_lapso') and (select lapso_academico.fecha_cierre from solicitud_apelacion, estudiante_sancionado, lapso_academico where solicitud_apelacion.codigo_lapso = :LapsoFinal and solicitud_apelacion.codigo_lapso = estudiante_sancionado.codigo_lapso and estudiante_sancionado.codigo_lapso = 'lapso_academico.codigo_lapso') and solicitud_apelacion.cedula_estudiante = estudiante_sancionado.cedula_estudiante and estudiante_sancionado.id_sancion = sancion_maestro.id_sancion and sancion_maestro.nombre_sancion = :tiposancion group by solicitud_apelacion.codigo_lapso")
	public List<ChartDataApelacionesPorLapso> BuscarPorLapsoYSancion(String lapsoinicial, String lapsofinal, String tiposancion);
	
	//Filtro de lapso inicial y lapso final con tipo sancion y programa
	@Query("select count(*), solicitud_apelacion.codigo_lapso from sancion_maestro, solicitud_apelacion, estudiante, estudiante_sancionado, programa_academico where solicitud_apelacion.fecha_solicitud between (select lapso_academico.fecha_inicio from solicitud_apelacion, estudiante_sancionado, lapso_academico where solicitud_apelacion.codigo_lapso = :LapsoInicial and solicitud_apelacion.codigo_lapso = estudiante_sancionado.codigo_lapso and estudiante_sancionado.codigo_lapso = 'lapso_academico.codigo_lapso') and (select lapso_academico.fecha_cierre from solicitud_apelacion, estudiante_sancionado, lapso_academico where solicitud_apelacion.codigo_lapso = :LapsoFinal and solicitud_apelacion.codigo_lapso = estudiante_sancionado.codigo_lapso and estudiante_sancionado.codigo_lapso = 'lapso_academico.codigo_lapso') and solicitud_apelacion.cedula_estudiante = estudiante_sancionado.cedula_estudiante and estudiante_sancionado.id_sancion = sancion_maestro.id_sancion and sancion_maestro.nombre_sancion = :tiposancion and estudiante_sancionado.cedula_estudiante = estudiante.cedula_estudiante and estudiante.id_programa = programa_academico.id_programa and programa_academico.nombre_programa = :programa group by solicitud_apelacion.codigo_lapso")
	public List<ChartDataApelacionesPorLapso> BuscarPorLapsoYSancion(String lapsoinicial, String lapsofinal, String tiposancion, String programa);
}