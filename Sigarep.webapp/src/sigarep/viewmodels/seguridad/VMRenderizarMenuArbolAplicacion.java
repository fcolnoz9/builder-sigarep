package sigarep.viewmodels.seguridad;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Hlayout;
import org.zkoss.zul.Treecell;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.TreeitemRenderer;
import org.zkoss.zul.Treerow;
import org.zkoss.zul.Window;
import sigarep.herramientas.UtilidadArbol;
import sigarep.modelos.data.seguridad.Nodo;

/**
* Clase VMRenderizarMenuArbolAplicacion : Clase ViewModels relacionada con la propiedad render del menú arbol principal.
*
* @author Equipo Builder
* @version 1.0
* @since 19/12/2014
* @last 11/05/2014
*/

public class VMRenderizarMenuArbolAplicacion implements TreeitemRenderer<VMNodoMenuArbol> {
	//-----------------Variables Objeto---------------------
	Window w = null;
	UtilidadArbol utilidadArbol = new UtilidadArbol();

	/** Metodo render permite a ciertos nodos del arbol renderizar una pantalla en especifico.
	 * @param articuloArbol, nodoArbol, indice 
	 * @return ninguno.
	 * @throws No dispara ninguna excepción. 
	 */
	
	@Override
	public void render(final Treeitem articuloArbol, VMNodoMenuArbol nodoArbol,
			int indice) throws Exception {
		VMNodoMenuArbol ctn = nodoArbol;
		Nodo nodo = (Nodo) ctn.getData();

		Treerow filaArbol = new Treerow();
		filaArbol.setParent(articuloArbol);
		articuloArbol.setValue(ctn);
		Hlayout hl = new Hlayout();
		Treecell celdaArbol = new Treecell();
		celdaArbol.setLabel(nodo.getNombreFuncion()); // 1
		celdaArbol.appendChild(hl);
		if (nodo.esFuncion()) {
			celdaArbol.setImage("/imagenes/iconos/funcion-tree.png");
		}
		filaArbol.appendChild(celdaArbol);
		filaArbol.addEventListener(Events.ON_CLICK, new EventListener<Event>() {
			@Override
			public void onEvent(Event event) throws Exception {
				VMNodoMenuArbol valorNodoClickeado = (VMNodoMenuArbol) ((Treeitem) event
						.getTarget().getParent()).getValue();
				if (valorNodoClickeado.getData().getVinculo() != null) {
					if (w != null) {
						w.detach();
					}
					utilidadArbol.onClickMenu(valorNodoClickeado.getData()
							.getVinculo(), valorNodoClickeado.getData()
							.getRutaModal());
				} else {
					if (articuloArbol.isOpen()) {
						articuloArbol.setOpen(false);
					} else {
						articuloArbol.setOpen(true);
					}
				}
			}
		});
	}
} //fin VMRenderizarMenuArbolAplicacion