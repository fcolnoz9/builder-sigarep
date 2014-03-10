package sigarep.modelos.servicio.maestros;

import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sigarep.modelos.data.maestros.ProgramaAcademico;
import sigarep.modelos.repositorio.maestros.IProgramaAcademicoDAO;


/**Servicio Programa Academico
 * UCLA DCYT Sistemas de Información
 * @author Equipo : Builder-Sigarep Lapso 2013-1
 * @version 1.0 
 *@since 22/01/14
 */
// El servicio interactua con la base de datos

@Service("servicioprogramaacademico")
// Definiendo la variable servicio
public class ServicioProgramaAcademico {
	private @Autowired
	IProgramaAcademicoDAO pro;

	/**guardarPrograma academico
	 * @param proa
	 * @return pbjeto guardado
	 */
	public void guardarPrograma(ProgramaAcademico proa) {
		if (proa.getIdPrograma() != null)
			pro.save(proa);
		else{
			proa.setIdPrograma(pro.buscarUltimoID()+1);
			pro.save(proa);
		}
	}

	public void eliminarPrograma(Integer idPrograma) {
		ProgramaAcademico miPrograma = pro.findOne(idPrograma);
		miPrograma.setEstatusPrograma(false);
		pro.save(miPrograma);
	}

	public List<ProgramaAcademico> listadoProgramas() {
		List<ProgramaAcademico> programasLista = pro.findByEstatusProgramaTrue();
		return programasLista;
	}

	public ProgramaAcademico buscarPrograma(Integer idProgramaAcademico) {
		return pro.findOne(idProgramaAcademico);
	}

	public List<ProgramaAcademico> buscarPrograma(String programa) {
		List<ProgramaAcademico> result = new LinkedList<ProgramaAcademico>();
		if (programa == null) {
			result = listadoProgramas();
		} else {
			for (ProgramaAcademico pr : listadoProgramas()) {
				if (pr.getNombrePrograma().toLowerCase().contains(programa)) {
					result.add(pr);
				}
			}
		}
		return result;
	}
}
