package sigarep.modelos.repositorio.transacciones;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;




import sigarep.modelos.data.transacciones.InstanciaMiembro;
import sigarep.modelos.data.transacciones.InstanciaMiembroPK;

public interface IInstanciaMiembroDAO extends
		JpaRepository<InstanciaMiembro, InstanciaMiembroPK> {

	 //@Query()
	 //public List<SolicitudApelacion> solicitudesApelacionPorSancionado(EstudianteSancionadoPK id);
}
