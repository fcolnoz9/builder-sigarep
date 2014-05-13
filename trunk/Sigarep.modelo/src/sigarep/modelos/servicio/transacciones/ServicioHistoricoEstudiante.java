package sigarep.modelos.servicio.transacciones;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Service;
import sigarep.modelos.data.transacciones.HistoricoEstudiante;
import sigarep.modelos.data.transacciones.ListaMomento;

/**
 * Clase ServicioHistoricoEstudiante : Clase de la capa servicio web para el manejo de consultas y persistencia de la tabla Estudiante 
 * 
 * @author Equipo Builder
 * @version 1.0
 * @since 04/01/2014
 * @last 10/05/2014
 */
@Service("serviciohistoricoestudiante")
public class ServicioHistoricoEstudiante {
	
	// Atributos de la clase
	@PersistenceContext
	private EntityManager em;
	
	/**
	 * Busca el estado de apelación de un estudiante por medio del id
	 * @param idestado
	 * @return EstadoApelacion
	 * @throws No dispara ninguna excepción.
	 */
	public String buscarEstadoApelacion(Integer idestado){
		String queryStatement2 = "SELECT nombre_estado FROM sigarep.estado_apelacion AS estape "
				+ "WHERE estape.id_estado_apelacion = ?";
		Query query = em.createNativeQuery(queryStatement2);
		query.setParameter(1, idestado);
		String result = (String) query.getSingleResult();
		return result;
	}

	/**
	 * Busca una lista con los momentos de la apelación de un estudiante
	 * @param cedula, idInstancia
	 * @return List<ListaMomento> Lista de los momentos
	 * @throws No dispara ninguna excepción.
	 */
	public List<ListaMomento> buscarListaMomentos(String cedula, Integer idInstancia) {
		String queryStatement2 = "SELECT fecha_estado, id_estado_apelacion, observacion "
				+ "FROM sigarep.apelacion_estado_apelacion AS apemo "
				+ "WHERE apemo.cedula_estudiante = ? "
				+ "AND apemo.id_instancia_apelada = ? "
				+ "ORDER BY fecha_estado";
		Query query = em.createNativeQuery(queryStatement2);
		query.setParameter(1, cedula);
		query.setParameter(2, idInstancia);

		@SuppressWarnings("unchecked")
		List<Object[]> resultSet = query.getResultList();
		List<ListaMomento> result = new ArrayList<ListaMomento>();
		
		for (Object[] resultRow : resultSet) {
			result.add(new ListaMomento((Date) resultRow[0], buscarEstadoApelacion((Integer) resultRow[1]), (String) resultRow[2]));
		}
		System.out.println(result);
		return result;
	}

	/**
	 * Busca una lista de estudiantes por cedula
	 * @param cedula
	 * @return List<HistoricoEstudiante> Lista de estudiante
	 * @throws No dispara ninguna excepción.
	 */
	public List<HistoricoEstudiante> buscarHistoricoEstudiante(String cedula) {
		String queryStatement2 = "SELECT es.cedula_estudiante, prog.nombre_programa, es.primer_nombre, es.segundo_nombre, "
				+ "es.primer_apellido, es.segundo_apellido, lapac.codigo_lapso, sanm.nombre_sancion, estsan.lapsos_academicos_RP, "
				+ "asig.nombre_asignatura, solape.numero_caso, solape.fecha_solicitud, mot.descripcion "
				+ "FROM sigarep.sancion_maestro sanm, sigarep.programa_academico prog, sigarep.lapso_academico lapac, "
				+ "sigarep.instancia_apelada isap, sigarep.motivo mot, sigarep.estudiante es, sigarep.solicitud_apelacion solape, "
				+ "sigarep.tipo_motivo tipmo, sigarep.estudiante_sancionado AS estsan "
				+ "LEFT JOIN sigarep.asignatura_estudiante_sancionado AS asiges ON (asiges.codigo_lapso = estsan.codigo_lapso "
				+ "AND asiges.cedula_estudiante = estsan.cedula_estudiante) "
				+ "LEFT JOIN sigarep.asignatura AS asig ON asig.codigo_asignatura = asiges.codigo_asignatura "
				+ "WHERE estsan.id_sancion = sanm.id_sancion "
				+ "AND es.cedula_estudiante = ? "
				+ "AND es.id_programa = prog.id_programa "
				+ "AND estsan.codigo_lapso = lapac.codigo_lapso "
				+ "AND solape.cedula_estudiante = estsan.cedula_estudiante "
				+ "AND solape.id_instancia_apelada = isap.id_instancia_apelada "
				+ "AND solape.codigo_lapso = mot.codigo_lapso "
				+ "AND solape.cedula_estudiante = mot.cedula_estudiante "
				+ "AND solape.id_instancia_apelada = mot.id_instancia_apelada "
				+ "AND es.cedula_estudiante = estsan.cedula_estudiante "
				+ "AND solape.codigo_lapso = estsan.codigo_lapso "
				+ "AND tipmo.id_tipo_motivo = mot.id_tipo_motivo";

		Query query = em.createNativeQuery(queryStatement2);
		query.setParameter(1, cedula);

		@SuppressWarnings("unchecked")
		List<Object[]> resultSet = query.getResultList();
		List<HistoricoEstudiante> results = new ArrayList<HistoricoEstudiante>();

		for (Object[] resultRow : resultSet) {
			results.add(new HistoricoEstudiante((String) resultRow[0],
					(String) resultRow[1], (String) resultRow[2] + " "
							+ resultRow[3], (String) resultRow[4] + " "
							+ resultRow[5], (String) resultRow[6],
					(String) resultRow[7], (String) resultRow[8],
					(String) resultRow[8], (String) resultRow[9],
					(Integer) resultRow[10], (Date) resultRow[11],
					(String) resultRow[12]));
		}
		return results;
	}
}
