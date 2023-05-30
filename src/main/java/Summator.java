import java.util.Map;

public class Summator {
    private Map<String, Integer> dataSumm;   // <категория, сумма>


    public Summator(Map<String, Integer> dataSumm) {
        this.dataSumm = dataSumm;
    }

    public String add(String category, int sum) {
        //if (!dataSumm.containsKey(category)) category = "другое";
        int oldSumm = 0;
        if (dataSumm.containsKey(category)) {
            oldSumm = dataSumm.get(category);
        }
        dataSumm.put(category, oldSumm + sum);
        return getMax();
    }

    public String getMax() {
        String maxCategory = "";
        int maxSumm = 0;

        for (Map.Entry entry : dataSumm.entrySet()) {
            if ((Integer)entry.getValue() > maxSumm) {
                maxSumm = (Integer)entry.getValue();
                maxCategory = (String) entry.getKey();

            }
        }
        return "{\"maxCategory\": {\"category\": \"" + maxCategory + "\", \"sum\": " + maxSumm + "\"}}";
    }
}
