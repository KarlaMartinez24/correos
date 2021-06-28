package org.kyane;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Valores {

    public List<String> lines = new ArrayList();
    //public List<String> identificadores = new ArrayList();
    public List<String> valores = new ArrayList();

    public List<String> valueFromFile() throws FileNotFoundException, IOException {

        String delimitador = "=";
        String linea;
        int position;

        try {

            File file = new File("src/main/java/org/kyane/coloresCorreos.txt");
            Scanner myReader = new Scanner(file);


            while (myReader.hasNextLine()) {

                lines.add(myReader.nextLine());

            }

            for(int i=0; i<lines.size(); i++){

                linea= lines.get(i);
                position = linea.indexOf(delimitador);
                String delimitador2 = ";";
                int position2 = linea.indexOf(delimitador2);

                //identificadores.add(i, linea.substring(0, position));
                valores.add(i, linea.substring(position + 1,position2));
            }

            myReader.close();

        } catch (FileNotFoundException e) {

            System.out.println("\nArchivo no encontrado verifica la direccion y el archivo");

        } catch (IOException e) {

            System.out.println("\nVerifique el estado del archivo");

        }

        return valores;

    }

    public void showValues() {
        System.out.println("\nLos valores son: \n");
        for (int i = 0; i < lines.size(); i++) {
            //System.out.println(Expressions.get(i));
            //System.out.println(identificadores.get(i));
            System.out.println(valores.get(i));

        }
    }

}
