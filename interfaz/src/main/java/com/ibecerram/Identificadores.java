package org.kyane;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Identificadores {

    public List<String> lines = new ArrayList();
    public List<String> identificadores = new ArrayList();

    public List<String> expressionFromFile() throws FileNotFoundException, IOException {

        String delimitador = "=";
        String linea;

        try {

            File file = new File("interfaz/src/main/java/com/ibecerram/coloresCorreos.txt");
            Scanner myReader = new Scanner(file);


            while (myReader.hasNextLine()) {

                this.lines.add(myReader.nextLine());

            }

            for(int i=0; i<this.lines.size(); i++){

                linea= lines.get(i);

                int position = linea.indexOf(delimitador);

                identificadores.add(i, linea.substring(0, position));

            }

            myReader.close();

        } catch (FileNotFoundException e) {

            System.out.println("\nArchivo no encontrado verifica la direccion y el archivo");

        } catch (IOException e) {

            System.out.println("\nVerifique el estado del archivo");

        }

        return identificadores;

    }

    public void showIdentificators() {

        System.out.println("Los identificadores son: \n");

        for (int i = 0; i < lines.size(); i++) {

            System.out.println(identificadores.get(i));

        }
    }



}
