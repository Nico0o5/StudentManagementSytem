import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TxtProcessor {

    void writeText(String result){
        File file = new File("out.txt");
        
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {

            bw.write(result);
            bw.newLine();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void readResult(){
        File file = new File("out.txt");
        if(!file.exists()){
            System.err.println("File not found.");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;

            while( ( line = br.readLine()) != null){
                System.out.println(line);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
