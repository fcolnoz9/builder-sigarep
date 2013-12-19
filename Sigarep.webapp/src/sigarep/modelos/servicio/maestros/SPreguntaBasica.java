package sigarep.modelos.servicio.maestros;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sigarep.modelos.data.maestros.PreguntaBasica;
import sigarep.modelos.repositorio.maestros.IPreguntaBasicaDAO;

@Service("sPreguntaBasica")
public class SPreguntaBasica {
	
	private @Autowired IPreguntaBasicaDAO iPreguntaBasica;

	public SPreguntaBasica() {
		// TODO Auto-generated constructor stub
	}
	
	public void guardar(PreguntaBasica pb){
		iPreguntaBasica.save(pb);
	}
	
	public void eliminar(Integer id_pregunta){
		PreguntaBasica miPregunta = iPreguntaBasica.findOne(id_pregunta);
		miPregunta.setEstatus(false);
		iPreguntaBasica.save(miPregunta);
	}
	
	public PreguntaBasica buscar(Integer id_pregunta){
		PreguntaBasica miPregunta = iPreguntaBasica.findOne(id_pregunta);
		return miPregunta;
	}
	
}
