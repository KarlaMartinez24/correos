package org.kyane;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EscribeFichero {
    public List<String> lines = new ArrayList();

    public List<String> identificadores = new ArrayList<>();
    public List<String> valores = new ArrayList<>();

    String delimitador2 = ";";
    int position;
    int position2;
    String delimitador = "=";


    public void ojala() throws FileNotFoundException {


        try {

            File myObj2 = new File("src/main/java/org/kyane/estiloCorreos.txt");
            Scanner myReader2 = new Scanner(myObj2);


            while (myReader2.hasNextLine()) {

                lines.add(myReader2.nextLine());

            }


        } catch (FileNotFoundException e) {

            System.out.println("\nArchivo no encontrado verifica la direccion y el archivo");

        }

    }

    public void creacion (List <String> iden, List <String> vals) throws IOException {

        FileWriter fichero = null;
        PrintWriter pw = null;
        PrintWriter pw2 = null;
        String delimitador = "=";



        identificadores = iden;
        valores = vals;

        String linea;
        //pw.println("Hola");

        try
        {
            fichero = new FileWriter("src/main/resources/org/kyane/prueba.css");
            pw = new PrintWriter(fichero);

            String val;


            for(int i=0; i<identificadores.size(); i++){  //recorre el array de los identificadores

                val = valores.get(i);

                for(int j=0; j<lines.size(); j++){  //recorre las lineas del archivo de configuracion

                    String lineas=lines.get(j);


                    //saca las posiciones de los delimitadores
                    //position = val.indexOf(delimitador);
                    //position2 = val.indexOf(delimitador2);

                    if(lineas.contains(identificadores.get(i))){  //verifica si las linea contiene el identificador

                        //si es así, intercambia el identificador por el valor y lo escribe en el documento
                        pw.println(lineas.replace(identificadores.get(i), val));

                    }else if (!lineas.contains(identificadores.get(i))){

                        //si no, escribe la linea tal cual
                        pw.println(lineas);
                    }


                }

            }

        } catch (Exception e) {

            e.printStackTrace();

        } finally {
            try {

                if (null != fichero)
                    fichero.close();

            } catch (Exception e2) {

                e2.printStackTrace();

            }
        }

    }
}