package sigarep.modelos.servicio.transacciones;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Service;
import sigarep.modelos.data.transacciones.ListaCronograma;

@Service("serviciolistacronograma")
public class ServicioListaCronograma {

	@PersistenceContext
	private EntityManager em;

	public List<ListaCronograma> buscarActividadCronograma() {
		String queryStatement2 = "SELECT act.nombre, act.descripcion, cro.fecha_inicio, cro.fecha_fin, "
				+"cro.hora_inicio, cro.observacion, cro.lugar, cro.codigo_lapso FROM lapso_academico la "
				+ "INNER JOIN cronograma AS cro ON la.codigo_lapso = cro.codigo_lapso "
				+ "INNER JOIN actividad AS act ON act.id_actividad = cro.id_actividad  "
				+ "WHERE la.estatus = TRUE ORDER BY cro.fecha_inicio";
		/*if(idactividad!=null){
			queryStatement2 = queryStatement2 + " AND cro.id_actividad = ?";
		}
		*/

		Query query = em.createNativeQuery(queryStatement2);
		//query.setParameter(1, idactividad);
		
		@SuppressWarnings("unchecked")
		List<Object[]> resultSet = query.getResultList();

		List<ListaCronograma> results = new ArrayList<ListaCronograma>();
		for (Object[] resultRow : resultSet) {

			results.add(new ListaCronograma((String) resultRow[0],
					(String) resultRow[1], (Date) resultRow[2],
					(Date) resultRow[3], (Time) resultRow[4],
					(String) resultRow[5], (String) resultRow[6]));
		}

		return results;
	}
}
