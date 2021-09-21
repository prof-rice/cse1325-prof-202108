import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Phrases {
    public Phrases(String filename) 
      throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        while((line=br.readLine())!=null) phrases.add(line);        
    }
    public String getPhrase() {
        return phrases.get((int) (Math.random() * phrases.size()));
    }
    private ArrayList<String> phrases = new ArrayList<>();
}
