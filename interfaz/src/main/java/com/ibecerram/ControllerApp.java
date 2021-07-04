package com.ibecerram;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.HTMLEditor;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.apache.commons.mail.EmailException;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerApp implements Initializable
{
    //Cuerpo del correo Cambiar HTML y TXT
    @FXML
    private Button btnCuerpoEmail;

    @FXML
    private TextArea txtAreaTextoEmail;

    @FXML
    private HTMLEditor htmlEditorTextoEmail;

    private boolean banderaTextoPlanoEmail = true;

    /**
     * Método que permite cambiar entre el tipo de cuerpo del correo, ya sea texto plano o como html.
     */
    @FXML
    public void cambiarTipoCuerpoEmail()
    {
        if(banderaTextoPlanoEmail)
        {
            banderaTextoPlanoEmail = false;
            txtAreaTextoEmail.clear();
            txtAreaTextoEmail.setVisible(false);
            htmlEditorTextoEmail.setVisible(true);
            btnCuerpoEmail.setText("Texto Plano");
            btnElegirHTMLDinamico.setVisible(true);
            btnElegirHTMLDinamico.setDisable(false);
        }
        else
        {
            banderaTextoPlanoEmail = true;
            htmlEditorTextoEmail.setVisible(false);
            txtAreaTextoEmail.setVisible(true);
            htmlEditorTextoEmail.setHtmlText("");
            btnCuerpoEmail.setText("HTML");
            btnElegirHTMLDinamico.setVisible(false);
            btnElegirHTMLDinamico.setDisable(true);
        }
    }
    //



    //Introducir Plantilla Dinámica
    private FileChooser fileChooserHTMLDinamico = new FileChooser();

    @FXML
    private Button btnElegirHTMLDinamico;

    @FXML
    public void elegirHTMLDinamico()
    {
        fileChooserHTMLDinamico.setTitle("Abrir HTML Dinamico");
        fileChooserHTMLDinamico.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivo HTML", "*.html"));
        Stage stage = new Stage();
        try
        {
            File selectedFile = fileChooserHTMLDinamico.showOpenDialog(stage);
            ArchivoHTML archivoHTML = new ArchivoHTML(selectedFile);
            htmlEditorTextoEmail.setHtmlText(archivoHTML.leerHTML());
            //htmlEditorHTMLDinamico.setText
        }
        catch (Exception e)
        {
            //System.out.println("ALERTA DE SELECCIONAR UN ARCHIVO");
            alertas.advertenciaSeleccionarArchivo();
        }
    }

    @FXML
    public String generarHTMLDinamico(Alumnos alumnos)
    {
        //System.out.println(htmlEditorHTMLDinamico.getHtmlText());
        String textoHTMLEditado = htmlEditorTextoEmail.getHtmlText();
        //System.out.println(reemplazar);

        textoHTMLEditado = textoHTMLEditado.replaceAll("#matricula#", alumnos.getMatricula());
        textoHTMLEditado = textoHTMLEditado.replaceAll("#alumno#", alumnos.getNombre());
        textoHTMLEditado = textoHTMLEditado.replaceAll("#correo#", alumnos.getEmail());
        textoHTMLEditado = textoHTMLEditado.replaceAll("#carrera#", alumnos.getCarrera());
        textoHTMLEditado = textoHTMLEditado.replaceAll("#tutor#", alumnos.getTutor());
        textoHTMLEditado = textoHTMLEditado.replaceAll("#correotutor#", alumnos.getEmailTutor());

        return textoHTMLEditado;
    }
    //



    // Generar PDF
    private FileChooser fileChooserHTMLPDF = new FileChooser();

    private String pathCarpetaEntrada;

    @FXML
    private HTMLEditor htmlEditorTextoPDF;

    @FXML
    private  Button btnGenerarPDF;

    File fileHTML = null;
    @FXML
    public void elegirHTMLPDF()
    {

        fileChooserHTMLPDF.setTitle("Abrir HTML a Convert");
        fileChooserHTMLPDF.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivo HTML", "*.html"));
        Stage stage = new Stage();
        try
        {
            fileHTML = fileChooserHTMLPDF.showOpenDialog(stage);
            if(fileHTML.exists())
            {
                ArchivoHTML archivoHTML = new ArchivoHTML(fileHTML);
                htmlEditorTextoPDF.setHtmlText(archivoHTML.leerHTML());
                btnGenerarPDF.setDisable(false);
            }

        }
        catch (NullPointerException e)
        {
            //System.out.println("ALERTA DE SELECCIONAR UN ARCHIVO");
            alertas.advertenciaSeleccionarArchivo();
        }
    }

    @FXML
    public void generarArchivoPDF()
    {
        File filePDF = new File(pathCarpetaEntrada + "/prueba.pdf");
        ConverterProperties converterProperties = new ConverterProperties();

        //htmlEditorTextoPDF.style
        try
        {
            HtmlConverter.convertToPdf(new FileInputStream(fileHTML), new FileOutputStream(filePDF), converterProperties);
            if(evitarDuplicidadArchivos(filePDF))
            {
                listViewArchivosAEnviar.getItems().add(filePDF);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    //



    // Mostrar archivos y botón de añadir archivo
    //private ArrayList<File> listaArchivosAEnviar = new ArrayList<>();

    @FXML
    private ListView<File>  listViewArchivosAEnviar= new ListView();

    @FXML
    private Button btnElegirArchivoAEnviar;

    private FileChooser fileChooserArchivoAEnviar = new FileChooser();

    File fileAEnviar = null;

    @FXML
    public void elegirArchivoAEnviar()
    {
        fileChooserArchivoAEnviar.setTitle("Abrir HTML a Convert");
        Stage stage = new Stage();
        try
        {
            fileAEnviar = fileChooserArchivoAEnviar.showOpenDialog(stage);
            if(fileAEnviar.exists() && evitarDuplicidadArchivos(fileAEnviar))
            {
                System.out.println("Archivo agregado");
                listViewArchivosAEnviar.getItems().add(fileAEnviar);
            }

        }
        catch (NullPointerException e)
        {
            System.out.println("ALERTA DE SELECCIONAR UN ARCHIVO");
            //e.printStackTrace();
        }

        //listaArchivosAEnviar = (ArrayList<File>) listViewArchivosAEnviar.getItems();
    }

    public boolean evitarDuplicidadArchivos(File fileAAgregar)
    {
        ObservableList<File> listaArchivosCargados = listViewArchivosAEnviar.getItems();
        for(File file : listaArchivosCargados)
        {
            if(file.getAbsolutePath().equals(fileAAgregar.getAbsolutePath()))
            {
                return false;
            }
        }
        return true;

    }

    //






    // Mostrar alumnos
    @FXML
    private TableView tableViewAlumnos;

    @FXML
    private Button btnAgregarAlumnos;

    private ArrayList<Alumnos> listaAlumnosAgregados = new ArrayList<>();

    @FXML
    public void seleccionarAlumnosExcel()
    {

    }

    @FXML
    public void mostrarAlumnosExcel()
    {
        //listaAlumnosAgregados.add(new Alumnos("Isaac de Jesus Becerra", "1930062", "1930062@upv.edu.mx", "ITI", "Said Polanco M.", "spolancom@upv.edu.mx"));

        limpiarTableViewAlumnos();

        for(Alumnos alumno : listaAlumnosAgregados)
        {
            tableViewAlumnos.getItems().add(alumno);
        }
    }

    @FXML
    private Button btnAgregarUnicoAlumno;

    @FXML
    public void agregarUnicoAlumno()
    {
        //Stage stage = null;
        /*FXMLLoader loader = new FXMLLoader();
        try
        {
            loader.setLocation(new URL("file:interfaz/src/main/resources/InterfazAgregarAlumno.fxml"));
            stage = new Stage();
            stage.setTitle("Agregar alumno");


            Scene scena = new Scene(loader.load(),500,400);
            scena.getStylesheets().add("file:interfaz/target/classes/estilos.css");
            stage.setScene(scena);


        } catch (Exception e)
        {
            e.printStackTrace();
        }*/

        FXMLLoader loader = new FXMLLoader();
        try {
            loader.setLocation(new URL("file:interfaz/src/main/resources/InterfazAgregarAlumno.fxml"));
            //loader.setLocation(new URL("file:interfaz/target/classes/estilos.css"));

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Stage stage = new Stage();
        stage.setTitle("Agregar Alumno");
        try {
            Scene scena = new Scene(loader.load(),500,400);
            scena.getStylesheets().add("file:interfaz/target/classes/estilos.css");
            stage.setScene(scena);

        } catch (IOException e) {
            e.printStackTrace();
        }


        stage.showAndWait();
        ControllerAgregarAlumno controllerAgregarAlumno = loader.getController();

        Alumnos alumno = controllerAgregarAlumno.agregarAlumno();

        if(alumno != null)
        {
            agregarAlumnoLista(alumno);
            mostrarAlumnosExcel();
        }
    }

    public void agregarAlumnoLista(Alumnos alumno)
    {
        listaAlumnosAgregados.add(alumno);
    }

    @FXML
    private Button btnLimpiarTableViewAlumnos;

    @FXML
    public void limpiarTableViewAlumnos()
    {
        tableViewAlumnos.getItems().clear();
    }

    //





    // Enviar Correos

    //public void enviarCorreo()
    @FXML
    private TextField txtFieldAsuntoCorreo;

    @FXML
    public void enviarCorreo()
    {
        Correos correos = new Correos(cuentaPrincipal);
        for(Alumnos alumno : listaAlumnosAgregados)
        {
            try
            {
                correos.inicializarCorreo();
                correos.setDestinatario(alumno.getEmail());
                correos.setAsunto(txtFieldAsuntoCorreo.getText());
                if(banderaTextoPlanoEmail)
                {
                    correos.setTextoPlano(txtAreaTextoEmail.getText());
                }
                else
                {
                    correos.setTextoHTML(generarHTMLDinamico(alumno));
                }

                if(!listViewArchivosAEnviar.getItems().isEmpty())
                {
                    agregarListaArchivos(correos);
                }

                correos.enviar();
                System.out.println("Correo enviado exitosamente");
            }
            catch (EmailException e)
            {
                System.out.println("Error: El email no se pudo enviar.");
                e.printStackTrace();
            }
        }
    }

    public void agregarListaArchivos(Correos correo)
    {
        List<File> listaArchivos = listViewArchivosAEnviar.getItems();
        for(File file : listaArchivos)
        {
            try
            {
                correo.agregarArchivo(file);
            } catch (EmailException e)
            {
                e.printStackTrace();
            }
        }
    }

    //





    // Configurar cuenta maestra o principal
    @FXML
    private Button btnElegirCorreoPrincipal;

    @FXML
    private TextField txtFieldUsuarioCorreoPrincipal;

    @FXML
    private TextField txtFieldNombreCorreoPrincipal;

    @FXML
    private PasswordField txtFieldPasswordCorreoPrincipal;

    private CuentaPrincipal cuentaPrincipal;

    @FXML
    public void elegirCorreoPrincipal()
    {
        /*String correoPrincipal = alertas.agregarCorreoPrincipal();
        if(correoPrincipal != null)
        {
            txtFieldCorreoPrincipal.setText(correoPrincipal);
        }*/
        FXMLLoader loader = new FXMLLoader();
        try {
            loader.setLocation(new URL("file:interfaz/src/main/resources/InterfazCuentaPrincipal.fxml"));
            //loader.setLocation(new URL("file:interfaz/target/classes/estilos.css"));

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Stage stage = new Stage();
        stage.setTitle("Prueba");
        try {
            Scene scena = new Scene(loader.load(),450,300);
            scena.getStylesheets().add("file:interfaz/target/classes/estilos.css");
            stage.setScene(scena);

        } catch (IOException e) {
            e.printStackTrace();
        }

        stage.showAndWait();
        ControllerCuentaPrincipal controllerCuentaPrincipal = loader.getController();

        cuentaPrincipal = controllerCuentaPrincipal.agregarConfiguracion();

        if(cuentaPrincipal != null)
        {
            txtFieldNombreCorreoPrincipal.setText(cuentaPrincipal.getNombre());
            txtFieldUsuarioCorreoPrincipal.setText(cuentaPrincipal.getCorreo());
            txtFieldPasswordCorreoPrincipal.setText(cuentaPrincipal.getPassword());
        }

        /*

        String nombre = txtFieldNombreCorreoPrincipal.getText();
        String usuario = txtFieldUsuarioCorreoPrincipal.getText();
        String password = txtFieldPasswordCorreoPrincipal.getText();

        if(!nombre.isEmpty() && !usuario.isEmpty() && !password.isEmpty())
        {
            cuentaPrincipal = new CuentaPrincipal(nombre, usuario, password);
        }*/


    }
    //


    private Alertas alertas = new Alertas();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        File carpetaArchivosEntrada = new File("ArchivosEntrada");
        if(!carpetaArchivosEntrada.exists())
        {
            carpetaArchivosEntrada.mkdirs();

        }
        pathCarpetaEntrada = carpetaArchivosEntrada.getAbsolutePath();

        listViewArchivosAEnviar.setCellFactory(new Callback<ListView<File>, ListCell<File>>() {
            @Override
            public ListCell<File> call(ListView<File> fileListView) {
                return new ListCell<>()
                {
                    @Override
                    protected void updateItem(File file, boolean empty)
                    {
                        super.updateItem(file, empty);
                        setText(file == null || empty ? null : file.getName());
                    }
                };
            }
        });



        listViewArchivosAEnviar.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent)
            {
                if(mouseEvent.getClickCount() == 2)
                {
                    if(listViewArchivosAEnviar.getSelectionModel().getSelectedItem() != null)
                    {
                        File file = listViewArchivosAEnviar.getSelectionModel().getSelectedItem();
                        if(alertas.confirmarEliminacionArchivo(file.getName()))
                        {
                            int index = listViewArchivosAEnviar.getSelectionModel().getSelectedIndex();
                            listViewArchivosAEnviar.getItems().remove(index);
                        }
                    }
                }
            }
        });

        TableColumn<String, Alumnos>  columnaMatricula = new TableColumn<>("Matricula");
        TableColumn<String, Alumnos>  columnaNombre = new TableColumn<>("Nombre");
        TableColumn<String, Alumnos>  columnaCorreo = new TableColumn<>("Correo");
        TableColumn<String, Alumnos>  columnaCarrera = new TableColumn<>("Carrera");
        TableColumn<String, Alumnos>  columnaNombreTutor = new TableColumn<>("Nombre Tutor");
        TableColumn<String, Alumnos>  columnaEmailTutor = new TableColumn<>("Email Tutor");

        columnaMatricula.setMinWidth(120);
        columnaNombre.setMinWidth(120);
        columnaCorreo.setMinWidth(120);
        columnaCarrera.setMinWidth(120);
        columnaNombreTutor.setMinWidth(120);
        columnaEmailTutor.setMinWidth(120);

        columnaMatricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnaCorreo.setCellValueFactory(new PropertyValueFactory<>("email"));
        columnaCarrera.setCellValueFactory(new PropertyValueFactory<>("carrera"));
        columnaNombreTutor.setCellValueFactory(new PropertyValueFactory<>("tutor"));
        columnaEmailTutor.setCellValueFactory(new PropertyValueFactory<>("emailTutor"));

        tableViewAlumnos.getColumns().addAll(columnaMatricula, columnaNombre, columnaCorreo, columnaCarrera, columnaNombreTutor, columnaEmailTutor);


    }

}
