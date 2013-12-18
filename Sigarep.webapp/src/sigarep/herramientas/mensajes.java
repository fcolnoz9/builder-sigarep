package sigarep.herramientas;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Messagebox;
/*
 * Debe declarar una variable tipo mensajes en donde usará los mensajes (su clase viewmodels), 
 * primero importe el paquete herramientas
 * por ejemplo: private mensajes misMensajes;
 * Cuando vaya a usar algún mensaje coloca misMensajes.NombreDelMetodo();
 * por ejemplo: misMensajes.advertenciaLlenarCampos();
 * Lea los estándaresInterfacesV1.2 para que observe cuáles son los mensajes que puede necesitar.
 * si necesita algún otro mensaje agréguelo a esta clase, en orden.
 * por ejemplo: si es de advertencia, debajo del último de advertencia.
 * 
 * */

public class mensajes {
	
	public void advertenciaLlenarCampos(){
		
		Messagebox.show("¡Debe llenar todos los campos!", "Advertencia", Messagebox.OK, Messagebox.EXCLAMATION);

	}
	
	
	public void advertenciaSoloNumeros(){
		
		Messagebox.show("El campo sólo admite números", "Advertencia", Messagebox.OK, Messagebox.EXCLAMATION);

	}
	
public void advertenciaSoloLetras(){
		
		Messagebox.show("El campo sólo admite letras", "Advertencia", Messagebox.OK, Messagebox.EXCLAMATION);

	}

public void advertenciaIngresarUsuario(){
	
	Messagebox.show("Debe ingresar un usuario", "Advertencia", Messagebox.OK, Messagebox.EXCLAMATION);

}

public void advertenciaIngresarContraseña(){
	
	Messagebox.show("Debe ingresar una contraseña", "Advertencia", Messagebox.OK, Messagebox.EXCLAMATION);

}

public void advertenciaSeleccionarOpcion(){
	
	Messagebox.show("Debe seleccionar alguna opción para continuar", "Advertencia", Messagebox.OK, Messagebox.EXCLAMATION);

}

public void advertenciaNoCaracteres(){
	
	Messagebox.show("El campo no permite caracteres especiales.", "Advertencia", Messagebox.OK, Messagebox.EXCLAMATION);

}
	
	public void informacionRegistroCorrecto(){
		
	    Messagebox.show("Se ha registrado correctamente", "Información", Messagebox.OK, Messagebox.INFORMATION);
	    
	}
	
public void informacionEliminarCorrecto(){
		
	    Messagebox.show("Se ha eliminado correctamente.", "Información", Messagebox.OK, Messagebox.INFORMATION);
	    
	}

public void ErrorImposibleGuardar(){
	
    Messagebox.show("Imposible guardar el registro", "Error",  Messagebox.OK, Messagebox.ERROR);
    
}

public void ErrorImposibleEliminar(){
	
    Messagebox.show("Imposible eliminar el registro.", "Error",  Messagebox.OK, Messagebox.ERROR);
    
}

public void ErrorNoExiste(){
	
    Messagebox.show("El elemento solicitado no existe.", "Error",  Messagebox.OK, Messagebox.ERROR);
    
}

public void confirmacionSalir(){
	
    Messagebox.show("¿Realmente desea salir?", "Confirmación", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION);
    
}

public void confirmacionCerrarSesion(){
	
    Messagebox.show("¿Esta seguro de querer cerrar sesión?", "Confirmación", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION);
    
}



/*public void guardarCambios(){
	
	Messagebox.show("¿Desea guardar los cambios realizados?", "Confirmación", Messagebox.OK | Messagebox.IGNORE | Messagebox.CANCEL, Messagebox.QUESTION, new org.zkoss.zk.ui.event.EventListener() {
		    public void onEvent(Event evt) throws InterruptedException {
		        if (evt.getName().equals("onOK")) {
		            alert("Data Saved !");
		        } else if (evt.getName().equals("onIgnore")) {
		            Messagebox.show("Ignore Save", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
		        } else {
		            alert("Save Canceled !");
		        }
		    }

}	

}*/
}
	