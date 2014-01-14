package sigarep.modelos.repositorio.seguridad;



import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import sigarep.modelos.data.seguridad.Nodo;


public interface INodo extends JpaRepository<Nodo,Integer> {

public List<Nodo> findByPadre(int i);
}
