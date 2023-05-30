import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Categories {

    private Map<String, String> data;   // <предмет, категория>

    public Categories(Map<String, String> data) {
        this.data = data;
    }

    public static Categories loadFromTxtFile(File textFile) {
        Map<String, String> data = new HashMap<>();
        Categories categories = null;
        try (BufferedReader buffReader = new BufferedReader(new FileReader(textFile))) {
            String line;
            while (((line = buffReader.readLine()) != null)) {
                String[] record = line.split("\t");
                data.put(record[0], record[1]);
            }
            categories = new Categories(data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return categories;

    }

    public Map<String, String> getData() {
        return data;
    }

}
