import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CsvProcessor {

    void writeStudent(Student student){
        File file = new File("class.csv");
        
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {

            if(file.length() == 0){
                bw.write("ID,FIRSTNAME,LASTNAME,AGE,GENDER,GRADE,SECTION,EMAIL,WEBSITE");
                bw.newLine();
            }
            bw.write(student.getId() + ",");
            bw.write(student.getFirstName() + ",");
            bw.write(student.getLastName() + ",");
            bw.write(student.getAge() + ",");
            bw.write(student.getGender() + ",");
            bw.write(Float.toString(student.getGrade()) + ",");
            bw.write(student.getSection() + ",");
            bw.write(student.getEmail() + ",");
            bw.write(student.getWebsite());
            bw.newLine();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    ArrayList<Student> readStudent(){
        ArrayList<Student> students = new ArrayList<>();

        File file = new File("class.csv");
        if(!file.exists()){
            System.err.println("File not found.");
            return new ArrayList<>();
        }

        try (BufferedReader br = new BufferedReader(new FileReader("class.csv"))) {
            String line;
            boolean isHeader = true;



            while( ( line = br.readLine()) != null){

                if(isHeader == true){
                    isHeader = false;
                    continue;
                }


                
                String[] result = line.split(",");

                if(result.length < 4)
                    continue;

                long id = Long.parseLong(result[0]);

                String name = result[1];
                String[] nameResult = name.split(" ");
                String firstname = nameResult[0];
                String lastname = nameResult[1];

                float grade = Float.parseFloat(result[2]);
                String email = result.length > 3 ? result[3] : "";
                String website = result.length > 4 ? result[4] : "";

                /* 
                 * int age = Integer.parseInt(result[3]);
                 * char gender = result[4].charAt(0);
                 * String section = result[6];
                 */
                

                Student student = new Student( id, firstname, lastname, 0, ' ', grade, "", email, website);
                students.add(student);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return students;

    }
    
    
 
}
