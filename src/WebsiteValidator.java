import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebsiteValidator {

    public boolean checkWebsite(String website){
        Pattern p = Pattern.compile("((https?://)?[\\w_]+\\.)+[\\w]{2,3}(/[\\w-_]+)?");
        Matcher m = p.matcher(website);
        return m.matches();
        
    }
    
}
