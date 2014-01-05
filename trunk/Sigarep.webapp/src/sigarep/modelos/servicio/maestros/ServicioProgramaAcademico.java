package sigarep.modelos.servicio.maestros;

import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sigarep.modelos.data.maestros.ProgramaAcademico;
import sigarep.modelos.data.maestros.ProgramaAcademicoFiltros;
import sigarep.modelos.repositorio.maestros.IProgramaAcademicoDAO;

// El servicio interactua con la base de datos

@Service("servicioprogramaacademico")
// Definiendo la variable servicio
public class ServicioProgramaAcademico {
	private @Autowired
	IProgramaAcademicoDAO pro;

	public void guardarPrograma(ProgramaAcademico proa) {
		pro.save(proa);
	}

	public void actualizar(ProgramaAcademico proa) {
	}

	public void eliminarPrograma(Integer idPrograma) {
		ProgramaAcademico miPrograma = pro.findOne(idPrograma);
		miPrograma.setEstatusPrograma(false);
		pro.save(miPrograma);
	}

	public ProgramaAcademico buscarUnPrograma(Integer idProgramaAcademico) {
		return pro.findOne(idProgramaAcademico);
	}

	public List<ProgramaAcademico> listadoProgramas() {
		List<ProgramaAcademico> programasLista = pro.buscarProgramasActivos();
		return programasLista;
	}

	public List<ProgramaAcademico> buscarPrograma(
			ProgramaAcademicoFiltros filtros) {
		List<ProgramaAcademico> result = new LinkedList<ProgramaAcademico>();
		String nombre = filtros.getNombre().toLowerCase();
		if (nombre == null) {
			result = listadoProgramas();
		} else {
			for (ProgramaAcademico pr : listadoProgramas()) {
				if (pr.getNombrePrograma().toLowerCase().contains(nombre)) {
					result.add(pr);
				}
			}
		}
		return result;
	}
	
	public List<ProgramaAcademico> buscarPr(String programa) {
		List<ProgramaAcademico> result = new LinkedList<ProgramaAcademico>();
		if (programa == null || "".equals(programa)) {
			result = listadoProgramas();
		} else {
			for (ProgramaAcademico pr : listadoProgramas()) {
				if (pr.getNombrePrograma().toLowerCase()
						.contains(programa.toLowerCase())) {
					result.add(pr);
				}
			}
		}
		return result;
	}
}
