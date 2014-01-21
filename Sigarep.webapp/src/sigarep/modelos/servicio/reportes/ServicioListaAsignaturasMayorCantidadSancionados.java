package sigarep.modelos.servicio.reportes;

import java.math.BigInteger;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import org.springframework.stereotype.Service;


import sigarep.modelos.data.reportes.ListaAsignaturasMayorCantidadSancionados;


@Service("servicioListaAsignaturasMayor")
public class ServicioListaAsignaturasMayorCantidadSancionados {
	
	@PersistenceContext
	private EntityManager em;
	
	public List<ListaAsignaturasMayorCantidadSancionados> buscarAsignaturasSancionados(Integer idPrograma,String codigoLapso){
		String queryStatement2= "SELECT * from (SELECT b.nombre_asignatura, b.cantidadSancionados,"
				+ " rank()  OVER (PARTITION BY b.lapso ORDER BY b.cantidadSancionados DESC)" 
				+ "from (SELECT a.nombre_asignatura, count(aes.cedula_estudiante) as cantidadSancionados,"
				+ "aes.codigo_lapso as lapso from asignatura as a, asignatura_estudiante_sancionado as aes," 
				+ "programa_academico as p where p.id_programa= a.id_programa and a.id_programa= ? and "
				+ "aes.codigo_asignatura= a.codigo_asignatura and aes.codigo_lapso= ? "
				+ "group by  aes.codigo_asignatura, a.nombre_asignatura, aes.codigo_lapso order by cantidadSancionados desc) as b order by b.lapso asc) sub_query WHERE rank <= 5";
	
		Query query= em.createNativeQuery(queryStatement2);
		
		query.setParameter(1,idPrograma);
		query.setParameter(2,codigoLapso);
		//query.setParameter(3,codigoLapsoF);
		@SuppressWarnings("unchecked")
		List<Object[]> resultSet= query.getResultList();
	
		List<ListaAsignaturasMayorCantidadSancionados> results= new ArrayList<ListaAsignaturasMayorCantidadSancionados>();
		for (Object[] resultRow: resultSet){
			
			results.add(new ListaAsignaturasMayorCantidadSancionados((String)resultRow[0],
					(BigInteger) resultRow[1]));
		}
		
		return results;
	}
}
