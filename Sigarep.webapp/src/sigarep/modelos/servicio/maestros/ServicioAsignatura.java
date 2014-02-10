package sigarep.modelos.servicio.maestros;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import sigarep.modelos.data.maestros.Asignatura;
import sigarep.modelos.data.maestros.Recaudo;
import sigarep.modelos.data.maestros.SancionMaestro;
import sigarep.modelos.data.maestros.TipoMotivo;
import sigarep.modelos.repositorio.maestros.IAsignaturaDAO;

@Service("servicioAsignatura")
public class ServicioAsignatura {
	private @Autowired
	IAsignaturaDAO iAsignatura;
	

	
	public Asignatura buscarAsignatura(String codigoAsignatura){
		return iAsignatura.findOne(codigoAsignatura);
	}
	public void guardarAsignatura(Asignatura asignatura){
		iAsignatura.save(asignatura);
	}
	
	/** buscarTodas
	 * @param todos
	 * @return resultado todos los tipos de motivo
	 * @throws 
	 */
	public List<Asignatura> listaAsignaturas() {
		List<Asignatura> asignaturasLista = iAsignatura.buscarAsignaturasActivas();
		return asignaturasLista;
	}
	
	public List<Asignatura> buscarTodas(){
		return iAsignatura.buscarAsignaturasActivas();
	}
	
	public List<Asignatura> buscarAsignaturasPorPrograma (Integer idPrograma){
		return iAsignatura.buscarAsignaturasPorPrograma(idPrograma);
	}
	
	public Asignatura buscarAsignaturaNombre(String nombreAsignatura) {
		Asignatura asignatura = iAsignatura.buscarAsignaturaPorNombre(nombreAsignatura);
	    return asignatura;
	}
	
	public List<Asignatura> buscarAsignaturasNoSeleccionadas (String cedula){
		return iAsignatura.BuscarAsignaturasNoSeleccionadas(cedula);
	}
	
}
