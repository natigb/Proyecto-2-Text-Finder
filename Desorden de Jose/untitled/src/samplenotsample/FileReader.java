package samplenotsample;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {

    private Scanner x;

    public void openFile(){
        try{
            String path = "C:\\Users\\Jose\\Documents\\TEC\\Semestre II\\Datos I\\Proyecto 2\\Proyecto-2-Text-Finder\\Desorden de Jose\\untitled\\src\\samplenotsample\\holanothola.txt";
            x = new Scanner(new File("sampleee/holanothola.txt"));
        }catch(FileNotFoundException e){
            System.out.println(e);
        }
    }

    public void readFile(){
        while (x.hasNext()){
            System.out.print(x.next());
        }
    }

    public void closeFile(){
        x.close();
    }

}
