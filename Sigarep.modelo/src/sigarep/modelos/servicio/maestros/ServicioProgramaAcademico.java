package sigarep.modelos.servicio.maestros;

import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sigarep.modelos.data.maestros.ProgramaAcademico;
import sigarep.modelos.repositorio.maestros.IProgramaAcademicoDAO;

/**
 * Clase  ServicioProgramaAcademico (Servicio para
 * la Clase ProgramaAcademico)
 * 
 * @author Equipo Builder
 * @version 1.0
 * @since 18/12/2013
 * @last 10/05/2014
 */
@Service("servicioprogramaacademico")
public class ServicioProgramaAcademico {
	private @Autowired
	IProgramaAcademicoDAO pro;

	/**guardar Programa Académico
	 * @param ProgramaAcademico proa
	 * @return objeto guardado
	 */
	public void guardarPrograma(ProgramaAcademico proa) {
		if (proa.getIdPrograma() != null)
			pro.save(proa);
		else{
			proa.setIdPrograma(pro.buscarUltimoID()+1);
			pro.save(proa);
		}
	}

	/**
	 * Eliminar Programa
	 * 
	 * @param Integer idPrograma
	 * @return permite la eliminación lógica
	 * @throws No dispara ninguna excepción.
	 */
	public void eliminarPrograma(Integer idPrograma) {
		ProgramaAcademico miPrograma = pro.findOne(idPrograma);
		miPrograma.setEstatusPrograma(false);
		pro.save(miPrograma);
	}
	
	/**
	 * Listado Programas
	 * 
	 * @param vacío
	 * @return listadoEnlaceInteres con estatus = true
	 * @throws No dispara ninguna excepción.
	 */
	public List<ProgramaAcademico> listadoProgramas() {
		List<ProgramaAcademico> programasLista = pro.findByEstatusProgramaTrue();
		return programasLista;
	}
	
	/**
	 * Buscar Programa por ID
	 * 
	 * @param Integer idProgramaAcademico
	 * @return programa.
	 * @throws No dispara ninguna excepción.
	 */
	public ProgramaAcademico buscarPrograma(Integer idProgramaAcademico) {
		return pro.findOne(idProgramaAcademico);
	}

	/**
	 * Buscar Programa por nombre
	 * 
	 * @param String programa
	 * @return programa.
	 * @throws No dispara ninguna excepción.
	 */
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