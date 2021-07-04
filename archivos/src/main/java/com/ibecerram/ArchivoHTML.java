package com.ibecerram;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class ArchivoHTML
{
    private String path;
    private File file;

    public ArchivoHTML(File file)
    {
        this.file = file;
    }

    public String leerHTML()
    {
        String cuerpoHTML = "";
        Scanner sc = null;
        try
        {
            sc = new Scanner(file);
            while(sc.hasNextLine())
            {
                cuerpoHTML += sc.nextLine();
            }
        }
        catch (Exception e)
        {
            //System.out.println("Error: No se encontró al archivo");
        }
        finally
        {
            sc.close();
        }

        return cuerpoHTML;
    }
}
