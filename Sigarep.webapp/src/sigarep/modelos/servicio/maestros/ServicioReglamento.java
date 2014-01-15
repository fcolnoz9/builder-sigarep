package sigarep.modelos.servicio.maestros;



import java.util.LinkedList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sigarep.modelos.data.maestros.EnlaceInteres;
import sigarep.modelos.data.maestros.Recaudo;
import sigarep.modelos.data.maestros.RecaudoFiltro;
import sigarep.modelos.data.maestros.Reglamento;
import sigarep.modelos.data.maestros.ReglamentoFiltros;
import sigarep.modelos.repositorio.maestros.IReglamentoDAO;


@Service("servicioreglamento")
public class ServicioReglamento {
	private @Autowired IReglamentoDAO rg;

	public ServicioReglamento() {
		super();
		// TODO Auto-generated constructor stub
	}
	


public void guardarReglamento(Reglamento r){
	rg.save(r);
}


//Permite la eliminación lógica del registro, por id, busca el id y cambia su estatus a false, 
	//la llamada reglamento.save(enlaceBorrarLogico); actualiza el estatus del registro.
		public void eliminar(Integer idDocumento) {
			Reglamento reglamentoBorrarLogico = rg.findOne(idDocumento);
			reglamentoBorrarLogico.setEstatus(false);
			rg.save(reglamentoBorrarLogico);
		}

public Reglamento buscarReglamento(Integer idDocumento){
	return rg.findOne(idDocumento);
}

//Utiliza IDAO, el cual trae todos los registros en true,los que no han sido eliminado logicamente
		public List<Reglamento> listaReglamento() {
			return rg.listaReglamentoLogico();
		}

public List<Reglamento> buscarReglamentoTitulo(ReglamentoFiltros filtros, List<Reglamento> listaFiltrada){
	List<Reglamento> resultado = new LinkedList<Reglamento>();
	String titulo = filtros.getTitulo().toLowerCase();
	if (titulo == null){		
		resultado = listaFiltrada;
	}
	else
	{
		for (Reglamento r : listaFiltrada){
			System.out.print(r);
			if (r.getTitulo().toLowerCase().contains(titulo)){
				resultado.add(r);
			}
				
		}		
	}
	return resultado;
	
	
}

public List<Reglamento> buscarReglamentoDescripcion(ReglamentoFiltros filtros, List<Reglamento> listaFiltrada){
	List<Reglamento> resultado = new LinkedList<Reglamento>();
	String descripcion = filtros.getDescripcion().toLowerCase();
	if (descripcion == null){		
		resultado = listaFiltrada;
	}
	else
	{
		for (Reglamento r : listaFiltrada){
			System.out.print(r);
			if (r.getDescripcion().toLowerCase().contains(descripcion)){
				resultado.add(r);
			}
				
		}		
	}
	return resultado;
	
	
}

public List<Reglamento> buscarReglamentoCategoria(ReglamentoFiltros filtros, List<Reglamento> listaFiltrada){
	List<Reglamento> resultado = new LinkedList<Reglamento>();
	String categoria = filtros.getCategoria().toLowerCase();
	if (categoria == null){		
		resultado = listaFiltrada;
	}
	else
	{
		for (Reglamento r : listaFiltrada){
			System.out.print(r);
			if (r.getCategoria().toLowerCase().contains(categoria)){
				resultado.add(r);
			}
				
		}		
	}
	return resultado;
	
}

}