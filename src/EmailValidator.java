import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {

    public boolean checkEmail(String email){

        Pattern p = Pattern.compile("[\\w-_\\.]+@([\\w-_]+\\.)+[\\w]{2,4}");
        Matcher m = p.matcher(email);
        return m.matches();
        
    }
    
}
