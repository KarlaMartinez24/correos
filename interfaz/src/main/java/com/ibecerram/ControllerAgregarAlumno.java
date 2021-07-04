package com.ibecerram;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerAgregarAlumno
{
    @FXML
    private TextField txtFieldMatricula;

    @FXML
    private TextField txtFieldNombre;

    @FXML
    private TextField txtFieldCorreo;

    @FXML
    private TextField txtFieldCarrera;

    @FXML
    private TextField txtFieldNombreTutor;

    @FXML
    private TextField txtFieldCorreoTutor;

    @FXML
    private Button btnAgregarAlumno;

    @FXML
    public Alumnos agregarAlumno()
    {
        Stage stage = (Stage) btnAgregarAlumno.getScene().getWindow();
        Alumnos alumnos = null;
        String matricula = txtFieldMatricula.getText();
        String nombre = txtFieldNombre.getText();
        String correo = txtFieldCorreo.getText();
        String carrera = txtFieldCarrera.getText();
        String tutor = txtFieldNombreTutor.getText();
        String correoTutor = txtFieldCorreoTutor.getText();

        if(!matricula.isEmpty() && !nombre.isEmpty() && !correo.isEmpty() && !carrera.isEmpty() && !tutor.isEmpty() && !correoTutor.isEmpty())
        {
            alumnos = new Alumnos(nombre, matricula, correo, carrera, tutor, correoTutor);
        }
        stage.close();
        return alumnos;
    }
}
