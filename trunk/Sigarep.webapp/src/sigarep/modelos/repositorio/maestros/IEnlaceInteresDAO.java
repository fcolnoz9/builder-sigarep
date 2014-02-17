package sigarep.modelos.repositorio.maestros;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sigarep.modelos.data.maestros.EnlaceInteres;

/** IEnlaceInteresDAO
 *  Permite el uso de query para consultas. se utiliza en ServicioEnlaceInteres.
 * UCLA DCYT Sistemas de Informacion.
 * @author Equipo : Builder-Sigarep Lapso 2013-2
 * @version 1.0
 * @since 22/01/14
 */
public interface IEnlaceInteresDAO extends JpaRepository<EnlaceInteres, Integer> {

	//permite la busqueda de los registros por estatus, para mostrar los registros en true, luego de la eliminación lógica.
	//se utiliza en ServicioEnlaceInteres public List<EnlaceInteres> listadoEnlaceInteres()
		@Query("select  e from EnlaceInteres e where e.estatus='true'")
		public List<EnlaceInteres> listaEnlaceLogico();
		
		@Query("SELECT COALESCE(MAX(ei.idEnlace),0) FROM EnlaceInteres AS ei")
		public int buscarUltimoID();
}
