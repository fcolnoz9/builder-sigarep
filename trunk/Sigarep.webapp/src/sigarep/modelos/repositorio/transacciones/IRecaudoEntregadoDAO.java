package sigarep.modelos.repositorio.transacciones;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sigarep.modelos.data.transacciones.MotivoPK;
import sigarep.modelos.data.transacciones.RecaudoEntregado;
import sigarep.modelos.data.transacciones.RecaudoEntregadoPK;

public interface IRecaudoEntregadoDAO extends
		JpaRepository<RecaudoEntregado, RecaudoEntregadoPK> {

//	@Query("delete rece FROM RecaudoEntregado AS rece WHERE rece.id.cedulaEstudiante = : cedula " +
//			"AND rece.id.codigoLapso = : codigoLapso AND rece.id.idTipoMotivo = : idTipoMotivo " +
//			"AND rece.id.idInstanciaApelada = : idInstancia ")
//	public void eliminarRecaudosPorMotivo(@Param("cedula") String cedula, @Param("lapso") String lapso
//			, @Param("idTipoMotivo") Integer idTipoMotivo, @Param("idInstancia") Integer idInstancia);
}
