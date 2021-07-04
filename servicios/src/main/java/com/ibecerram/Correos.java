package com.ibecerram;

import java.io.File;
import java.util.ArrayList;
import org.apache.commons.mail.*;

public class Correos
{
    private CuentaPrincipal cuentaPrincipal;
    private String hostName = "smtp.gmail.com";
    private int numeroPuerto = 465;
    private ArrayList<File> listaArchivos = new ArrayList<File>();
    private HtmlEmail email = new HtmlEmail();

    public Correos(CuentaPrincipal cuentaPrincipal)
    {
        this.cuentaPrincipal = cuentaPrincipal;
    }

    public void inicializarCorreo() throws EmailException
    {
        email.setHostName(hostName);
        email.setSmtpPort(465);
        email.setSSLOnConnect(true);
        email.setAuthentication(cuentaPrincipal.getCorreo(), cuentaPrincipal.getPassword());
        email.setFrom(cuentaPrincipal.getCorreo(), cuentaPrincipal.getNombre());
    }

    public void setDestinatario(String destinatario) throws EmailException
    {
        email.addTo(destinatario);
    }

    public void setAsunto(String asunto)
    {
        email.setSubject(asunto);
    }

    public void setTextoPlano(String textoPlano) throws EmailException
    {
        email.setMsg(textoPlano);
    }

    public void setTextoHTML(String textoHTML) throws EmailException
    {
        email.setHtmlMsg(textoHTML);
    }

    public void enviar() throws EmailException
    {
        email.send();
    }

    public void agregarArchivo(File file) throws EmailException
    {
        EmailAttachment archivo = new EmailAttachment();
        archivo.setPath(file.getAbsolutePath());
        archivo.setDisposition(EmailAttachment.ATTACHMENT);
        archivo.setDescription("Archivo");
        archivo.setName(file.getName());
        email.attach(archivo);
    }


}
