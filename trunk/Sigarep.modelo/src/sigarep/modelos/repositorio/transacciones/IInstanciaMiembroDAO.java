package sigarep.modelos.repositorio.transacciones;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import sigarep.modelos.data.transacciones.InstanciaMiembro;
import sigarep.modelos.data.transacciones.InstanciaMiembroPK;

public interface IInstanciaMiembroDAO extends
		JpaRepository<InstanciaMiembro, InstanciaMiembroPK> {
	public List<InstanciaMiembro> findById_CedulaPersona(String CedulaPersona);
	public List<InstanciaMiembro> findById_IdInstanciaApelada(Integer idInstanciaApelada);
}
