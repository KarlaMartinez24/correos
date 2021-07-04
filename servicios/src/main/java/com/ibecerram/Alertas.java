package com.ibecerram;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;

import java.util.Optional;

public class Alertas
{
    public boolean confirmarEliminacionArchivo(String archivo)
    {
        Alert alertaEliminacionArchivo = new Alert(Alert.AlertType.CONFIRMATION);
        alertaEliminacionArchivo.setTitle("Confirma tu Solicitud");
        alertaEliminacionArchivo.setHeaderText("¿Deseas eliminar el archivo?");
        alertaEliminacionArchivo.setContentText("Archivo a eliminar: " + archivo);
        if(alertaEliminacionArchivo.showAndWait().get() == ButtonType.OK)
        {
            return true;
        }
        return false;
    }

    public void advertenciaSeleccionarArchivo()
    {
        Alert alertaSeleccionarArchivo = new Alert(Alert.AlertType.WARNING);
        alertaSeleccionarArchivo.setTitle("Archivo no seleccionado");
        alertaSeleccionarArchivo.setHeaderText("No se seleccionó ningún archivo");
        alertaSeleccionarArchivo.setContentText("No puedo realizar la operacion porque necesitas\nagregar un archivo");
        alertaSeleccionarArchivo.showAndWait();
    }

    public String agregarCorreoPrincipal()
    {
        TextInputDialog inputDialog = new TextInputDialog();
        inputDialog.setTitle("Añadir correo principal");
        inputDialog.setHeaderText("Debes ingresar tu correo electronico");
        inputDialog.setContentText("Ingresa tu correo: ");

        Optional<String> contenido = inputDialog.showAndWait();
        if(contenido.isPresent())
        {
            return contenido.get();
        }
        return null;
    }
}
