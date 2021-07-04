package com.ibecerram;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerCuentaPrincipal
{
    @FXML
    private Button btnAgregarConfiguracion;

    @FXML
    private TextField txtFieldNombre;

    @FXML
    private TextField txtFieldCorreo;

    @FXML
    private TextField txtFieldPassword;

    @FXML
    public CuentaPrincipal agregarConfiguracion()
    {
        Stage stage = (Stage) btnAgregarConfiguracion.getScene().getWindow();
        CuentaPrincipal cuentaPrincipal = null;
        String nombre = txtFieldNombre.getText();
        String usuario = txtFieldCorreo.getText();
        String password = txtFieldPassword.getText();

        if(!nombre.isEmpty() && !usuario.isEmpty() && !password.isEmpty())
        {
            cuentaPrincipal = new CuentaPrincipal(nombre, usuario, password);
        }
        stage.close();
        return cuentaPrincipal;
    }
}
