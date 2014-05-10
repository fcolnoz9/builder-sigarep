package sigarep.herramientas;


import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;
import org.zkoss.zul.Messagebox.ClickEvent;


/*
 * Debe declarar una variable tipo MensajesAlUsuario como estándar
 *  mensajeAlUsuario(MensajesAlUsuario mensajeAlUsuario = new MensajesAlUsuario();) 
 *  en donde usaría los mensajes (su clase viewmodels), 
 * primero importe el paquete herramientas
 * Cuando vaya a usar algún mensaje coloca mensajeAlUsuario.NombreDelMetodo();
 * por ejemplo: mensajeAlUsuario.advertenciaLlenarCampos();
 * Si necesita alguien otro mensaje agréguelo a esta clase, en orden.
 * por ejemplo: si es de advertencia, debajo del último de advertencia.
 * 
 * */

public class MensajesAlUsuario {
	
	

	/*-------------------------------------------Mensajes de Advertencia-----------------------------------*/
	public void advertenciaLlenarCampos() {
      Messagebox.show("¡Debe llenar todos los campos!", "Advertencia",
				Messagebox.OK, Messagebox.EXCLAMATION);

	}

	public void advertenciaIngresarCedula() {
      Messagebox.show("¡Debe ingresar una cédula!", "Advertencia",
				Messagebox.OK, Messagebox.EXCLAMATION);

	}

	public void advertenciaMenudelGrupoVacio() {
      Messagebox.show("¡Debe agregar al menos una función al menú del grupo!",
				"Advertencia", Messagebox.OK, Messagebox.EXCLAMATION);
	}

	public void advertenciaSeleccionarGrupoUsuario() {
      Messagebox.show("¡Debe seleccionar al menos un grupo!", "Advertencia",
				Messagebox.OK, Messagebox.EXCLAMATION);
	}

	public void advertenciaSeleccionarOpcion() {
		Messagebox.show("¡Debe seleccionar alguna opción para continuar!",
				"Advertencia", Messagebox.OK, Messagebox.EXCLAMATION);
	}

	public void advertenciaCargarImagen() {
        Messagebox.show("¡Debe Cargar una Imagen!", "Advertencia",
				Messagebox.OK, Messagebox.EXCLAMATION);
	}

	public void advertenciaTamannoImagen(int pesoKB) {
       Messagebox.show("¡Debe seleccionar una imagen con tamaño menor a "
				+ pesoKB + " Kbytes!", "Advertencia", Messagebox.OK,
				Messagebox.EXCLAMATION);
	}
	
	public void advertenciaTamannoArchivo(int pesoKB) {
		Messagebox.show("¡Debe seleccionar un archivo con tamaño menor a "
				+ pesoKB + " Kbytes!", "Advertencia", Messagebox.OK,
				Messagebox.EXCLAMATION);
	}

	public void advertenciaCargarDocumento() {
      Messagebox.show("¡Debe Cargar un Documento!", "Advertencia",
				Messagebox.OK, Messagebox.EXCLAMATION);
	}
	
	public void advertenciaDocumentoNOdisponible() {

		Messagebox.show("¡No hay documento disponible!", "Advertencia",
				Messagebox.OK, Messagebox.EXCLAMATION);
	}

	public void advertenciaSeleccionarParaEliminar() {

		Messagebox.show("¡Debe seleccionar un registro para eliminarlo!",
				"Advertencia", Messagebox.OK, Messagebox.EXCLAMATION);
	}

	public void advertenciaSeleccionarTodo() {
    Messagebox.show("¡Debe seleccionar todas las opciones!", "Advertencia",
				Messagebox.OK, Messagebox.EXCLAMATION);
	}

	public void advertenciaFormatoNoSoportado() {
     Messagebox.show("¡El formato no es soportado!", "Advertencia",
				Messagebox.OK, Messagebox.EXCLAMATION);
	}

	public void advertenciaGuardarVeredicto() {

		Messagebox.show("¡Debe especificar un veredicto para este caso!",
				"Advertencia", Messagebox.OK, Messagebox.EXCLAMATION);
	}

	public void advertenciaSeleccionarAlMenosUnRecaudoEntregado() {
        Messagebox.show("¡Debe seleccionar al menos un recaudo entregado!",
				"Advertencia", Messagebox.OK, Messagebox.EXCLAMATION);
	}

	public void advertenciaSeleccionarSugerencia() {
       Messagebox.show("¡Debe seleccionar una sugerencia del caso!",
				"Advertencia", Messagebox.OK, Messagebox.EXCLAMATION);
	}

	public void advertenciaAgregarMotivo() {
		Messagebox.show("¡Debe agregar un motivo al caso!", "Advertencia",
				Messagebox.OK, Messagebox.EXCLAMATION);
	}

	public void advertenciaAgregarObservacionGeneral() {
		Messagebox.show("¡Debe agregar una observación general del caso!",
				"Advertencia", Messagebox.OK, Messagebox.EXCLAMATION);
	}

	public void advertenciaSeleccionarLapso() {
        Messagebox.show("¡Debe seleccionar un lapso académico!",
				"Advertencia", Messagebox.OK, Messagebox.EXCLAMATION);
	}

	public void advertenciaSeleccionarDestinoRespaldo() {
		Messagebox.show("¡Debe seleccionar el destino para el respaldo!",
				"Advertencia", Messagebox.OK, Messagebox.EXCLAMATION);
	}

	 public void advertenciaSeleccionarUbicacionRestauracion() {
	 Messagebox.show("¡Debe seleccionar la ubicación de origen de su archivo backup!",
	 "Advertencia", Messagebox.OK, Messagebox.EXCLAMATION);
	 }

	public void advertenciaEscribirNombreDeRespaldo() {
		Messagebox.show("¡Debe escribir el nombre del archivo de respaldo!",
				"Advertencia", Messagebox.OK, Messagebox.EXCLAMATION);
	}

	public void advertenciaContraseñasNoCoinciden() {
		Messagebox.show("¡Las contraseñas no coinciden!", "Advertencia",
				Messagebox.OK, Messagebox.EXCLAMATION);
	}

	public void advertenciaCorreosNoCoinciden() {
		Messagebox.show("¡Los correos no coinciden!", "Advertencia",
				Messagebox.OK, Messagebox.EXCLAMATION);
	}

	public void advertenciaNoExisteEstudianteSancionado() {
       Messagebox.show("¡Esta cédula no está en la lista de Estudiantes Sancionados!",
						"Advertencia", Messagebox.OK, Messagebox.EXCLAMATION);
	}

	public void advertenciaGrupoYaExistente(String nombreGrupo) {
        Messagebox.show("¡El grupo con nombre " + nombreGrupo
				+ " ya se encuentra registrado!", "Advertencia",
				Messagebox.OK, Messagebox.EXCLAMATION);
	}
	
	public void advertenciaIngresarAsignatura() {
        Messagebox.show("¡Debe ingresar  al menos una asignatura!",
				"Advertencia", Messagebox.OK,
				Messagebox.EXCLAMATION);
	}
	
	public void advertenciaIngresarCorreo() {
      Messagebox.show("¡Debe ingresar un correo!", "Advertencia",
				Messagebox.OK, Messagebox.EXCLAMATION);
     }
	
	public void advertenciaNoPuedeRegistrarRecursoReconsideracion() {

		Messagebox.show("¡No puede registrar Recursos de Reconsideración! No ha finalizado el proceso."
				+ "de apelación en la instancia anterior.", "Advertencia",
				Messagebox.OK, Messagebox.EXCLAMATION);

	}
	
	public void advertenciaNoPuedeRegistrarRecursoJerarquico() {

		Messagebox.show("¡No puede registrar Recursos Jerárquicos!. No ha finalizado el proceso "
				+ "de apelación en la instancia anterior.", "Advertencia",
				Messagebox.OK, Messagebox.EXCLAMATION);

	}
	
	public void advertenciaNoPuedeRegistrarRecursoReconsideracion2() {
      Messagebox.show("¡No puede registrar Recursos de Reconsideración! El proceso ha finalizado para esta Instancia.", 
    		  "Advertencia",Messagebox.OK, Messagebox.EXCLAMATION);
	}
	
	public void advertenciaNoPuedeRegistrarApelacionInicial() {
       Messagebox.show("¡No puede registrar Apelaciones! El proceso ha finalizado para esta Instancia.", 
				"Advertencia",Messagebox.OK, Messagebox.EXCLAMATION);
		}
	
	public void advertenciaProgramaNoRegistrado(Integer idPrograma) {
		Messagebox.show("¡El código de programa " + idPrograma
				+ " no se encuentra registrado!", "Advertencia",
				Messagebox.OK, Messagebox.EXCLAMATION);
	}
	
	public void advertenciaSeleccionarEstadoApelacion() {
		Messagebox.show("¡Debe seleccionar un Estado de la Lista de Apelación!", "Advertencia",
				Messagebox.OK, Messagebox.EXCLAMATION);
	}
	
	public void advertenciaLapsoAcademicoNoActivo(Window ventana) {
		Messagebox.show("No puede realizar esta transacción. ¡No ha registrado el lapso académico actual!",
						"Advertencia",Messagebox.OK, Messagebox.EXCLAMATION);
	}
	
	public void advertenciaContraseñaVacia() {
		Messagebox.show("¡Debe ingresar alguna contraseña!", "Advertencia",
				Messagebox.OK, Messagebox.EXCLAMATION);
	}
	
	public void advertenciaFormatoImagenNoSoportado(Media mediaUsuario) {
	     Messagebox.show("¡El archivo: "+mediaUsuario+" no tiene un formato válido!", "Advertencia",
					Messagebox.OK, Messagebox.EXCLAMATION);
		}
	
	public void advertenciaNoELiminar() {
		Messagebox.show("¡No puede ser eliminado ya que tiene una solicitud de Apelación!", "Advertencia",
				Messagebox.OK, Messagebox.EXCLAMATION);
	}
	
	public void advertenciaValidacionNumeroSesion(){
		Messagebox.show("El número de sesión ya esta en uso", "Advertencia", Messagebox.OK, Messagebox.EXCLAMATION);
	}
	
	public void advertenciaFormatoNumeroSesion(){
		Messagebox.show("El formato del código es inválido, solo debe usar numeros y/o el caracter guión(-)", "Advertencia", Messagebox.OK, Messagebox.EXCLAMATION);
	}
	
	public void advertenciaNoPuedeEliminarRegistro(){
		Messagebox.show("¡No puede eliminar este registro!", "Advertencia", Messagebox.OK, Messagebox.EXCLAMATION);
	}
	
/*-------------------------------------------Mensajes de Información-----------------------------------*/
	public void informacionHemosEnviadoCorreo() {
          Messagebox.show("Hemos enviado un email con su nombre de usuario y contraseña.",
						"Información", Messagebox.OK, Messagebox.INFORMATION);
	}

	public void informacionContrasennaAtualizada() {
         Messagebox.show("Se ha actualizado su contraseña con éxito.",
				"Información", Messagebox.OK, Messagebox.INFORMATION);
	}

	public void informacionContrasennaRecuperada() {

		Messagebox.show("Hemos enviado un email con su contraseña.",
				"Información", Messagebox.OK, Messagebox.INFORMATION);
	}

	public void informacionCorreoEnviado() {

		Messagebox.show("Su correo ha sido enviado satisfactoriamente.",
				"Información", Messagebox.OK, Messagebox.INFORMATION);
	}

	public static void informacionRegistroCorrectoStatic() {
      Messagebox.show("Se ha registrado correctamente.", "Información",
					Messagebox.OK, Messagebox.INFORMATION);
		}
	
	public void informacionRegistroCorrecto() {
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

	public void informacionOperacionExitosa() {
       Messagebox.show("¡Operación realizada exitosamente!",
			"Información", Messagebox.OK, Messagebox.INFORMATION);
     }

	public void informacionCreacionHistorico(String nombreHistorico) {
     Messagebox.show("Se ha Creado un archivo histórico bajo el nombre de: " + ""
						+ nombreHistorico + ".sql", "Información",
				Messagebox.OK, Messagebox.INFORMATION);
     }

	public void informacionArchivoCargado() {
      Messagebox.show("Archivo cargado correctamente.", "Información",
				Messagebox.OK, Messagebox.INFORMATION);
      }

    public void informacionVeredictoRegistrado() {
	Messagebox.show("Veredicto registrado correctamente.", "Información",
				Messagebox.OK, Messagebox.INFORMATION);
	}


	public void informacionRespaldoNoExitosa() {
		Messagebox.show("El intento de respaldo no fue exitoso.",
				"Información", Messagebox.OK, Messagebox.INFORMATION);
	}

	public void informacionRespaldoLocalExitoso() {
		Messagebox.show("Respaldo satisfactorio, en unos segundos culminará su creación " +
				"y se actualizará su tamaño en la lista, luego de esto prodrá utilizarla " +
				"para restaurar la BD.",
				"Información", Messagebox.OK, Messagebox.INFORMATION);
	}
	
	public void informacionRespaldoExternoExitoso() {
		Messagebox.show("Respaldo realizado satisfactoriamente en su ruta especificada.",
				"Información", Messagebox.OK, Messagebox.INFORMATION);
	}

	public void informacionNoHayCoincidencias() {
		Messagebox.show("No Hay coincidencias para mostrar.", "Información",
				Messagebox.OK, Messagebox.INFORMATION);
	}

	public void informacionEstudianteSinSolicitudApelacion() {
		Messagebox.show("Usted no ha realizado solicitudes de apelación. Diríjase a la sección de " +
				"Guía Paso a Paso en la página de inicio para " +
       		"conocer todo lo referente al proceso de apelación.",
				"Información", Messagebox.OK, Messagebox.INFORMATION);
	}
	
	public void informacionRestauracionEnProceso() {

		Messagebox.show("Restauración en proceso, debe esperar mientras el proceso es completado.",
						"Información", Messagebox.OK, Messagebox.INFORMATION);
	}
	
	public void informacionRestauracionNoExitosa() {
		Messagebox.show("El intento de restauración no fue exitoso.",
				"Información", Messagebox.OK, Messagebox.INFORMATION);
	}
	/*-------------------------------------------Mensajes de Error-----------------------------------*/
	

	public void errorUsuarioEmailNoRegistrado() {
       Messagebox.show("Usuario o email no registrado.", "Error",
				Messagebox.OK, Messagebox.ERROR);
	}

	public void errorFinalizarLapsoVeredicto() {

		Messagebox.show("No puede finalizar el lapso actual. "
				+ "Existen apelaciones sin veredicto.", "Error", Messagebox.OK,
				Messagebox.ERROR);
	}

	public void errorFinalizarLapsoSesion() {

		Messagebox.show("No puede finalizar el lapso actual. "
				+ "Existen apelaciones sin número de sesión.", "Error",
				Messagebox.OK, Messagebox.ERROR);

	}

	public void errorFinalizarLapsoCronograma() {

		Messagebox.show("No puede finalizar el lapso actual. "
				+ "Existen actividades sin ejecutarse en el cronograma.",
				"Error", Messagebox.OK, Messagebox.ERROR);

	}

	public void errorLapsoActivoExistente() {
     Messagebox.show("Ya existe un lapso académico activo.", "Error",
				Messagebox.OK, Messagebox.ERROR);
     }

	public void errorLapsoFinalizadoNoModificable() {
     Messagebox.show("Lapso académico finalizado. No puede realizar cambios sobre él.",
						"Error", Messagebox.OK, Messagebox.ERROR);
     }

	public void errorRangoFechas() {
      Messagebox.show("Error de rango de fechas.", "Error", Messagebox.OK,
				Messagebox.ERROR);
		}

    public void errorNoHayResgistrosParaRespaldo() {
     Messagebox.show("No hay nada a lo que hacer respaldo en el lapso seleccionado.",
						"Error", Messagebox.OK, Messagebox.ERROR);
     }

	public void errorNoEsXML() {
       Messagebox.show("La Extensión del Archivo no es XML.", "Error",
				Messagebox.OK, Messagebox.ERROR);
	}
	
	public void errorNoEliminarMotivoGeneral() {

		Messagebox.show("El motivo de tipo general no se puede eliminar.", "Error",
				Messagebox.OK, Messagebox.ERROR);
	}
	
	public void errorNoModificarMotivoGeneral() {

		Messagebox.show("El motivo de tipo general no se puede modificar.", "Error",
				Messagebox.OK, Messagebox.ERROR);
	}
	
	public void errorContenidoXMLNoValido() {

		Messagebox.show("El Archivo XML no tiene datos válidos.", "Error",
				Messagebox.OK, Messagebox.ERROR);
	}
	
	public void errorUsuarioNoValido() {
		Messagebox.show("El nombre de usuario ya está en uso, escriba otro.", "Error", Messagebox.OK, Messagebox.ERROR);
	}
	
	
	public boolean errorValidarCorreo(String comparar){
		boolean respuesta = false;
		
		if(comparar.equals("") || comparar == null){ respuesta = true;}
		else{
			String palabra = comparar;
			String separador = "[A-Za-z0-9]+@[A-Za-z0-9]+\\.com";
	        String[] palabraArray = palabra.split(separador);
			String separador2 = "@";
	        String[] palabra2Array =palabra.split(separador2);
	        char[] letras = palabra.toCharArray();       
		    char letra = letras[letras.length-1];
		    if(palabraArray.length > 0 ){	            
		    }else if(palabra2Array.length > 2 || letra == '@' ){
		    }else{ respuesta = true;}
		    if(!respuesta)Messagebox.show("¡Debe ingresar un correo válido! ejemplo: abc123@abc.com", "Error",
				Messagebox.OK, Messagebox.ERROR);
		}
		return respuesta;
	}
	

	/*-------------------------------------------Mensajes de Confirmación-----------------------------------*/
	public void confirmacionCerrarSesion() {

		Messagebox.show("¿Está seguro de querer cerrar sesión?",
				"Confirmación", new Messagebox.Button[] {
						Messagebox.Button.YES, Messagebox.Button.CANCEL },
				Messagebox.QUESTION, new EventListener<ClickEvent>() {
					@SuppressWarnings("incomplete-switch")
					public void onEvent(ClickEvent e) throws Exception {
						if(e.getButton() == e.getButton().YES)Executions.sendRedirect("j_spring_security_logout");
						else e.getTarget().detach();
						
					}
				});

	}

	public void confirmacionCerrarVentanaMaestros(final Window ventana,
			boolean condicion) {
		if (condicion == true) {
			Messagebox.show("¿Realmente desea cerrar la ventana sin guardar los cambios?",
							"Confirmar",
							new Messagebox.Button[] { Messagebox.Button.YES,
									Messagebox.Button.NO },
							Messagebox.QUESTION,
							new EventListener<ClickEvent>() {
								@SuppressWarnings("incomplete-switch")
								public void onEvent(ClickEvent e)
										throws Exception {
									if(e.getButton() == e.getButton().YES)ventana.detach();
									else e.getTarget().detach();
								}
							});
		} else
			Messagebox.show("¿Realmente desea cerrar la ventana?", "Confirmar",
					new Messagebox.Button[] { Messagebox.Button.YES,
							Messagebox.Button.NO }, Messagebox.QUESTION,
					new EventListener<ClickEvent>() {
						@SuppressWarnings("incomplete-switch")
						public void onEvent(ClickEvent e) throws Exception {
							if(e.getButton() == e.getButton().YES)ventana.detach();
							else e.getTarget().detach();
						}
					});
	}

	public void confirmacionCerrarVentanaTransacciones(final Window ventana,
			boolean condicion) {
		if (condicion == true) {
			Messagebox.show(
					"¿Realmente desea cerrar la ventana sin realizar cambios?",
					"Confirmar", new Messagebox.Button[] {
							Messagebox.Button.YES, Messagebox.Button.NO },
					Messagebox.QUESTION, new EventListener<ClickEvent>() {
						@SuppressWarnings("incomplete-switch")
						public void onEvent(ClickEvent e) throws Exception {
							if(e.getButton() == e.getButton().YES)ventana.detach();
							else e.getTarget().detach();
						}
					});
		}
	}


	public void confirmacionCerrarVentanaSimple(final Window ventana,
			boolean condicion) {
		if (condicion == true) {
			Messagebox.show("¿Realmente desea cerrar la ventana?", "Confirmar",
					new Messagebox.Button[] { Messagebox.Button.YES,
							Messagebox.Button.NO }, Messagebox.QUESTION,
					new EventListener<ClickEvent>() {
						@SuppressWarnings("incomplete-switch")
						public void onEvent(ClickEvent e) throws Exception {
							if(e.getButton() == e.getButton().YES)ventana.detach();
							else e.getTarget().detach();
						}
					});
		}
	}
	
	
	public void cerrarVentanaSinVeredicto(final Window ventana, boolean condicion) {

		Messagebox.show("No se ha dictado veredicto a las solicitudes de apelación", "Información",
				new Messagebox.Button[] { Messagebox.Button.OK
						 }, Messagebox.INFORMATION,
				new EventListener<ClickEvent>() {
					@SuppressWarnings("incomplete-switch")
					public void onEvent(ClickEvent e) throws Exception {
						if(e.getButton() == e.getButton().YES)ventana.detach();
						else e.getTarget().detach();
					}
				});
	}
}
