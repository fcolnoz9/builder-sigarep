package sigarep.viewmodels.maestros;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Filedownload;

import sigarep.modelos.data.maestros.Reglamento;
import sigarep.modelos.servicio.maestros.ServicioReglamento;

@SuppressWarnings("serial")
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMreglamentoPortal {
	
	private List<Reglamento> listaReglamento;
	@WireVariable
	private ServicioReglamento servicioreglamento;
	
// 
	@Init
	public void init(){
        //initialization code
		buscarReglamentoss();
    }
		

//	@Command
//	@NotifyChange({"listaReglamento"})
//	public void buscarReglamento(){
//		listaReglamento =servicioreglamentoportal.buscarReglamento(idDocumento);
//		//listaReglamento =servicioreglamento.listadoReglamento(); 
//	}
	
//	
//	@Command
//	public void descargarRecaudo(
//			@ContextParam(ContextType.COMPONENT) Component componente) {
//		int idRecaudo = Integer.parseInt(componente.getAttribute("idRecaudo")
//				.toString());
//		for (int j = 0; j < listaRecaudos.size(); j++) {
//			if (listaRecaudos.get(j).getIdRecaudo() == idRecaudo)
//				Filedownload.save(listaRecaudos.get(j).getContenidoDocumento(),
//						listaRecaudos.get(j).getTipoDocumento(), listaRecaudos
//								.get(j).getNombreDocumento());
//		}
//	}
	
	
	@Command
	 @NotifyChange({ "listaReglamento" })
	 public void buscarReglamentoss() {
		listaReglamento = servicioreglamento.buscarReglamentoPortal();
		
	 }

	public List<Reglamento> getListaReglamento() {
		return listaReglamento;
	}

	public void setListaReglamento(List<Reglamento> listaReglamento) {
		this.listaReglamento = listaReglamento;
	}
	
	@Command
	public void descargarArchivo(@ContextParam(ContextType.COMPONENT) Component componente){
		int idDocumento = Integer.parseInt(componente.getAttribute("idReglamento").toString());
		for (int i = 0; i < listaReglamento.size(); i++) {
			if (idDocumento == listaReglamento.get(i).getIdDocumento())
				Filedownload.save(listaReglamento.get(i).getDocumento().getContenidoDocumento(),
								   listaReglamento.get(i).getDocumento().getTipoDocumento(),
								   listaReglamento.get(i).getDocumento().getNombreDocumento());
		}
	}
}