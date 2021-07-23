package org.kyane;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class Intercambio {

    public List<String> lines = new ArrayList();

    public List<String> identificadores = new ArrayList<>();
    public List<String> valores = new ArrayList<>();


    public void linesFromFile() throws FileNotFoundException {


        try {

            File file = new File("interfaz/src/main/java/com/ibecerram/estiloCorreos.txt");
            Scanner myReader = new Scanner(file);


            while (myReader.hasNextLine()) {

                lines.add(myReader.nextLine());

            }

            myReader.close();
        } catch (FileNotFoundException e) {

            System.out.println("\nArchivo no encontrado verifica la direccion y el archivo");

        }

    }

    public void writeInFile(List <String> iden, List <String> vals) throws IOException {

        FileWriter fichero = null;
        PrintWriter pw = null;

        identificadores = iden;
        valores = vals;


        try
        {
            fichero = new FileWriter("interfaz/target/classes/configuracion.css");
            pw = new PrintWriter(fichero);


            for (int i = 0; i < lines.size(); i++) {

                pw.println(recorrido(lines.get(i), identificadores, valores));

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



    public String recorrido(String cadena, List <String> iden, List <String> vals){
        String aux;

        for (int j = 0; j < iden.size(); j++) {


            if (cadena.contains(iden.get(j))) {


                aux = cadena.replace(iden.get(j), vals.get(j));

                return aux;

            }

        }

        return cadena;
    }
}
