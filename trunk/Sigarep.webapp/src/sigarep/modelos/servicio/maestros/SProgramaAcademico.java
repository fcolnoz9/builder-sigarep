package sigarep.modelos.servicio.maestros;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sigarep.modelos.data.maestros.ProgramaAcademico;
import sigarep.modelos.repositorio.maestros.IProgramaAcademicoDAO;

@Service("sProgramaAcademico")
public class SProgramaAcademico {
	
	private @Autowired IProgramaAcademicoDAO iPrograma;

	public SProgramaAcademico() {
		// TODO Auto-generated constructor stub
	}
	
	public void guardar(ProgramaAcademico programa){
		iPrograma.save(programa);
	}
	
	public void actualizar(){
		
	}
	
	public void eliminar(Integer idPrograma){
		ProgramaAcademico miPrograma = iPrograma.findOne(idPrograma);
		miPrograma.setEstatusPrograma(false);
		iPrograma.save(miPrograma);
	}
	
	public List<ProgramaAcademico> listadoProgramaAcademico(){
		List<ProgramaAcademico> programaLista = iPrograma.findAll();
		return programaLista;
	}

}
