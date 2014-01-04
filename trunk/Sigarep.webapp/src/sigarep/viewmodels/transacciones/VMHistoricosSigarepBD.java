package sigarep.viewmodels.transacciones;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import java.util.List;



import org.zkoss.bind.annotation.Command;

import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;

import org.zkoss.zhtml.Messagebox;

import org.zkoss.zk.ui.select.annotation.VariableResolver;

import org.zkoss.zk.ui.select.annotation.WireVariable;

import org.zkoss.zul.Radio;
import org.zkoss.zul.Radiogroup;


import sigarep.herramientas.UtilidadesSigarep;
import sigarep.herramientas.mensajes;


import sigarep.modelos.servicio.transacciones.ServicioSolicitudApelacion;


import sigarep.modelos.servicio.maestros.ServicioEstudiante;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMHistoricosSigarepBD {	

	@WireVariable
	ServicioSolicitudApelacion serviciosolicitudapelacion;
	@WireVariable
	private Radio radio;
	@WireVariable
	private boolean checkTodos;
	mensajes msjs = new mensajes(); //para llamar a los diferentes mensajes de dialogo
	
	@WireVariable
	private Date fecha;
	
	@WireVariable
	private String selected = "";
	
	public String getSelected() {
		return selected;
	}

	@NotifyChange
	public void setSelected(String selected) {
		this.selected = selected;
	}
	
	public boolean isCheckTodos() {
		return checkTodos;
	}

	public void setCheckTodos(boolean checkTodos) {
		this.checkTodos = checkTodos;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	public Radio getRadio() {
		return radio;
	}

	public void setRadio(Radio radio) {
		this.radio = radio;
	}

	@Init
	public void init() {
		// initialization code
	}
	

	@Command
	@NotifyChange({"fecha","radio"})
	public void generarHistorico() {
		if (fecha!=null) {
			List<String> listaElementosAInsertar = new ArrayList<String>();
			List<String> listaAuxiliarElementos = new ArrayList<String>();
			SimpleDateFormat sdf=new java.text.SimpleDateFormat("dd-MM-yyyy");
			String fechaString =  sdf.format(fecha);
			String nombreHistorico = "historicoTodosSigarep-" + fechaString;
			String destinoHistorico = "todos/historicoTodosSigarep-" + fechaString;
			if (!selected.equals("")) {
				if (getSelected().equals("todos")) {
					listaAuxiliarElementos = serviciosolicitudapelacion.historicoSolicitudApelacion(fecha);
					if (listaAuxiliarElementos.size() > 0) {
						listaElementosAInsertar.addAll(listaAuxiliarElementos);
					}
					// listaAuxiliarElementos=servicioOrdenEntrega.historicoOrdenEntrega(fecha);
					// if(listaAuxiliarElementos.size()>0){
					// listaElementosAInsertar.addAll(listaAuxiliarElementos);
					// }
					// listaAuxiliarElementos=servicioOrdenServicio.historicoOrdenServicio(fecha);
					// if(listaAuxiliarElementos.size()>0){
					// listaElementosAInsertar.addAll(listaAuxiliarElementos);
					// }
					// listaAuxiliarElementos=servicioRequisicion.historicoRequisicion(fecha);
					// if(listaAuxiliarElementos.size()>0){
					// listaElementosAInsertar.addAll(listaAuxiliarElementos);
					// }
				}
				if (getSelected().equals("solicitud")) {
					listaAuxiliarElementos = serviciosolicitudapelacion.historicoSolicitudApelacion(fecha);
					if (listaAuxiliarElementos.size() > 0) {
						listaElementosAInsertar.addAll(listaAuxiliarElementos);
					}
					nombreHistorico = "solicitudes-" + fechaString;
					destinoHistorico = "solicitudes/solicitudesApelacion-"+ fechaString;
				}
				// if(getSelected().equals("transaccion2")){
				// listaAuxiliarElementos=servicioservicioTransaccion2.historicoservicioTransaccion2(fecha));
				// if(listaAuxiliarElementos.size()>0){
				// listaElementosAInsertar.addAll(listaAuxiliarElementos);
				// }
				// nombreHistorico="transaccion2-"+fechaString;
				// destinoHistorico="transaccion2/transaccion2-"+fechaString;
				// }
				// if(getSelected().equals("transaccion3")){
				// listaAuxiliarElementos=servicioservicioTransaccion3.historicoservicioTransaccion3(fecha);
				// if(listaAuxiliarElementos.size()>0){
				// listaElementosAInsertar.addAll(listaAuxiliarElementos);
				// }
				// nombreHistorico="transaccion3-"+fechaString;
				// destinoHistorico="transaccion3/transaccion3-"+fechaString;
				// }
				// if(getSelected().equals("transaccion4")){
				// listaAuxiliarElementos=servicioTransaccion4.historicoTransaccion4(fecha);
				// if(listaAuxiliarElementos.size()>0){
				// listaElementosAInsertar.addAll(listaAuxiliarElementos);
				// }
				// nombreHistorico="transaccion4-"+fechaString;
				// destinoHistorico="transaccion4/transaccion4-"+fechaString;
				// }

				String ruta = UtilidadesSigarep.obtenerDirectorio();
				ruta = ruta + "Sigarep.webapp/WebContent/WEB-INF/sigarep/administracionBaseDatos/historicos";
				if (listaElementosAInsertar.size() > 0) {
					File fichero = new File(ruta + "/" + destinoHistorico+ ".sql");
					try {
						BufferedWriter writer = new BufferedWriter(new FileWriter(fichero));
						for (int j = 0; j < listaElementosAInsertar.size(); j++) {
							String ln = System.getProperty("line.separator");
							writer.write(listaElementosAInsertar.get(j) + ln);
						}
						Messagebox.show("Operacion Exitosa", "Informacion",Messagebox.OK, Messagebox.INFORMATION);
						Messagebox.show("Se a Creado un archivo historico bajo el nombre de: "+ nombreHistorico + ".sql","Informacion", Messagebox.OK,Messagebox.INFORMATION);
						writer.close();
					} 
					catch (Exception e) {
						System.err.println(e);
					}
				}
				else{ 
					Messagebox.show("No hay nada a lo que hacer respaldo en esta fecha","Advertencia", Messagebox.OK,Messagebox.EXCLAMATION);
				}
			}
			else {
					Messagebox.show("Debe Seleccionar una Opción","Advertencia", Messagebox.OK,Messagebox.EXCLAMATION);
				}
		} else {
			Messagebox.show("Debe colocar una Fecha Limite", "Advertencia",Messagebox.OK, Messagebox.EXCLAMATION);
		}
	}
}