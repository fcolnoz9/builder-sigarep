package sigarep.herramientas;

import org.zkoss.zul.Messagebox;


/*
 * Debe declarar una variable tipo MensajesAlUsuario como estándar mensajeAlUsuario(MensajesAlUsuario mensajeAlUsuario = new MensajesAlUsuario();) en donde usará los mensajes (su clase viewmodels), 
 * primero importe el paquete herramientas
 * Cuando vaya a usar algún mensaje coloca mensajeAlUsuario.NombreDelMetodo();
 * por ejemplo: mensajeAlUsuario.advertenciaLlenarCampos();
 * Si necesita algún otro mensaje agréguelo a esta clase, en orden.
 * por ejemplo: si es de advertencia, debajo del último de advertencia.
 * 
 * */

public class MensajesAlUsuario {

	public void advertenciaLlenarCampos() {

		Messagebox.show("¡Debe llenar todos los campos!", "Advertencia",
				Messagebox.OK, Messagebox.EXCLAMATION);

	}

	public void advertenciaSoloNumeros() {

		Messagebox.show("El campo sólo admite números.", "Advertencia",
				Messagebox.OK, Messagebox.EXCLAMATION);

	}

	public void advertenciaSoloLetras() {

		Messagebox.show("El campo sólo admite letras.", "Advertencia",
				Messagebox.OK, Messagebox.EXCLAMATION);

	}

	public void advertenciaIngresarUsuario() {

		Messagebox.show("¡Debe ingresar un usuario!", "Advertencia",
				Messagebox.OK, Messagebox.EXCLAMATION);

	}

	public void advertenciaIngresarContraseña() {

		Messagebox.show("¡Debe ingresar una contraseña!", "Advertencia",
				Messagebox.OK, Messagebox.EXCLAMATION);

	}

	public void advertenciaSeleccionarOpcion() {

		Messagebox.show("Debe seleccionar alguna opción para continuar.",
				"Advertencia", Messagebox.OK, Messagebox.EXCLAMATION);

	}

	public void advertenciaNoCaracteres() {

		Messagebox.show("El campo no permite caracteres especiales.",
				"Advertencia", Messagebox.OK, Messagebox.EXCLAMATION);
	}

	public void advertenciaCargarImagen() {

		Messagebox.show("¡Debe Cargar una Imagen!", "Advertencia",
				Messagebox.OK, Messagebox.EXCLAMATION);
	}

	public void advertenciaSeleccionarParaEliminar() {

		Messagebox.show("Debe seleccionar un registro para eliminarlo.",
				"Advertencia", Messagebox.OK, Messagebox.EXCLAMATION);
	}
	public void  advertenciaFormatoNoSoportado() {

		Messagebox.show("El formato no es soportado.",
				"Advertencia", Messagebox.OK, Messagebox.EXCLAMATION);
	}
	public void advertenciaCargarDocumento() {

		Messagebox.show("¡Debe Cargar un Documento!", "Advertencia",
				Messagebox.OK, Messagebox.EXCLAMATION);
	}
	
	public static void informacionRegistroCorrecto() {

		Messagebox.show("Se ha registrado correctamente.", "Información",
				Messagebox.OK, Messagebox.INFORMATION);

	}

	public void informacionEliminarCorrecto() {

		Messagebox.show("Se ha eliminado correctamente.", "Información",
				Messagebox.OK, Messagebox.INFORMATION);

	}

	public void informacionFinalizarLapsoExitoso() {

		Messagebox.show("¡Lapso Académico finalizado exitosamente!",
				"Información", Messagebox.OK, Messagebox.INFORMATION);

	}

	public void ErrorImposibleGuardar() {

		Messagebox.show("Imposible guardar el registro.", "Error",
				Messagebox.OK, Messagebox.ERROR);

	}

	public void ErrorFinalizarLapsoVeredicto() {

		Messagebox.show("No puede finalizar el lapso actual. "
				+ "Existen apelaciones sin veredicto.", "Error", Messagebox.OK,
				Messagebox.ERROR);
	}

	public void ErrorFinalizarLapsoSesion() {

		Messagebox.show("No puede finalizar el lapso actual. "
				+ "Existen apelaciones sin número de sesión.", "Error",
				Messagebox.OK, Messagebox.ERROR);

	}

	public void ErrorFinalizarLapsoCronograma() {

		Messagebox.show("No puede finalizar el lapso actual. "
				+ "Existen actividades sin ejecutarse en el cronograma.",
				"Error", Messagebox.OK, Messagebox.ERROR);

	}
	
	public void ErrorLapsoActivoNoExistente() {

		Messagebox.show("No existe un lapso académico activo.",
				"Error", Messagebox.OK, Messagebox.ERROR);

	}
	public void ErrorLapsoActivoExistente() {

		Messagebox.show("Ya existe un lapso académico activo.",
				"Error", Messagebox.OK, Messagebox.ERROR);

	}

	public void informacionArchivoCargado() {

		Messagebox.show("Archivo cargado correctamente.", "Información",
				Messagebox.OK, Messagebox.INFORMATION);

	}

	public void informacionArchivoEliminado() {

		Messagebox.show("Archivo eliminado correctamente.", "Información",
				Messagebox.OK, Messagebox.INFORMATION);
	}
	
	public void informacionVeredictoRegistrado() {
		Messagebox.show("Veredicto registrado correctamente.", "Información",
				Messagebox.OK, Messagebox.INFORMATION);
	}
	
	public void informacionFinalizarVeredictoIApelacionesProcesadas() {
		Messagebox.show("No existen apelaciones para procesar.", "Información",
				Messagebox.OK, Messagebox.INFORMATION);
	}
	
	public void informacionGuardarDatosSesion(int contador) {
		Messagebox.show("Se almacenaron correctamente los datos de la sesión a " + contador + " apelaciones.", "Información",
				Messagebox.OK, Messagebox.INFORMATION);
	}

	public void ErrorImposibleEliminar() {

		Messagebox.show("Imposible eliminar el registro.", "Error",
				Messagebox.OK, Messagebox.ERROR);

	}

	public void ErrorRangoFechas() {

		Messagebox.show("Error de rango de fechas.", "Error", Messagebox.OK,
				Messagebox.ERROR);

	}

	public void ErrorNoExiste() {

		Messagebox.show("El elemento solicitado no existe.", "Error",
				Messagebox.OK, Messagebox.ERROR);

	}

	public void confirmacionSalir() {

		Messagebox.show("¿Realmente desea salir?", "Confirmación",
				Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION);

	}

	public void confirmacionCerrarSesion() {

		Messagebox.show("¿Esta seguro de querer cerrar sesión?",
				"Confirmación", Messagebox.OK | Messagebox.CANCEL,
				Messagebox.QUESTION);

	}


}
