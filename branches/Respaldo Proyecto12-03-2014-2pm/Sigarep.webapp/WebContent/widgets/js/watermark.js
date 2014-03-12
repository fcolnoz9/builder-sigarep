        zk.afterMount(function() {
            jq("$txtInstancia").Watermark("Nombre de la Instancia","gray");
            jq("$txtRecurso").Watermark("Nombre del Recurso","gray");
            jq("$txtDescripcion").Watermark("Ingrese alguna descripcion","gray");
            jq("$txtPregunta").Watermark("Introduzca la pregunta","gray");
            jq("$txtRespuesta").Watermark("Introduzca la respuesta","gray");
            jq("$txtTitulo").Watermark("Introduzca el titulo","gray");
            jq("$txtContenido").Watermark("Introduzca el contenido","gray");
            jq("$txtNombreFoto").Watermark("Introduzca el nombre","gray");
            jq("$txtEnlace").Watermark("Introduzca el enlace","gray");
            jq("$txtCedula").Watermark("Introduzca la respuesta","gray");
            jq("$txtNombre").Watermark("Introduzca la respuesta","gray");
            jq("$txtApellido").Watermark("Introduzca la respuesta","gray");
            jq("$txtEmail").Watermark("Introduzca la respuesta","gray");
            jq("$txtTelefono").Watermark("Introduzca la respuesta","gray");
            jq("$txtPrograma").Watermark("Introduzca la respuesta","gray");
            jq("$txtNombreActividad").Watermark("Nombre de la Actividad","gray");
            jq("$txtDescripcionActividad").Watermark("Ejemplo: Publicacion de Cronograma","gray");
            jq("$txtImagen").Watermark("C:Imagenes..","gray");
            jq("$txtNombreSancion").Watermark("Nombre de la Sancion","gray");
            jq("$txtDescripcionSancion").Watermark("Ejemplo: Regimen de Permanencia.","gray");
            
            $.mask.definitions['A']='[A-Z]';
            $.mask.definitions['m']='[01]';
            $.mask.definitions['d']='[0123]';
            $.mask.definitions['y']='[12]';
             
            jq("$phone").mask("(999) 999-9999");
            jq("$date").mask("m9/d9/y999");
            jq("$country").mask("AA");
            jq("$cc").mask("9999-9999-9999-9999");
        });
