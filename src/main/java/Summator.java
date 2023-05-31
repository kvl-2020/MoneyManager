import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Summator {
    private static final String CATEGORIES_FILE = "categories.tsv";
    private static final String OTHER = "другое";
    Categories categories = Categories.loadFromTxtFile(new File(CATEGORIES_FILE));

    private final List<Buy> buys;

    private String actualDateD;
    private String actualDateM;
    private String actualDateY;

    public Summator(List<Buy> buys) {
        this.buys = buys;
    }

    public String add(Buy buy) {
        buys.add(buy);
        actualDateD = buy.getDateD();
        actualDateM = buy.getDateM();
        actualDateY = buy.getDateY();
        return getMax();
    }

    public String getMax() {
        return "{" + getMaxCategory() + "," + getMaxYearCategory() + "," + getMaxMonthCategory() + "," + getMaxDayCategory() + "}";
    }

    public String getMaxDayCategory() {
        Sum result = findSumForMaxCategory(buys.stream().filter(buy ->
                        buy.getDateD().equals(actualDateD) &&
                                buy.getDateM().equals(actualDateM) &&
                                buy.getDateY().equals(actualDateY)).collect(Collectors.toList()));
        return "\"maxDayCategory\": {\"category\": \"" + result.getCategory() + "\", \"sum\": " + result.getSum() + "\"}";
    }

    public String getMaxMonthCategory() {
        Sum result = findSumForMaxCategory(buys.stream().filter(buy ->
                        buy.getDateM().equals(actualDateM) &&
                        buy.getDateY().equals(actualDateY)).collect(Collectors.toList()));
        return "\"maxMonthCategory\": {\"category\": \"" + result.getCategory() + "\", \"sum\": " + result.getSum() + "\"}";
    }

    public String getMaxYearCategory() {
        Sum result = findSumForMaxCategory(buys.stream().filter(buy ->
                        buy.getDateY().equals(actualDateY)).collect(Collectors.toList()));
        return "\"maxYearCategory\": {\"category\": \"" + result.getCategory() + "\", \"sum\": " + result.getSum() + "\"}";
    }

    public String getMaxCategory() {
        Sum result = findSumForMaxCategory(buys);
        return "\"maxCategory\": {\"category\": \"" + result.getCategory() + "\", \"sum\": " + result.getSum() + "\"}";
    }

    // Получаем список отфильтрованных покупок, ищем максимальную сумму по категориям
    private Sum findSumForMaxCategory(List<Buy> buys) {
        String maxCategory = "";
        int maxSumm = 0;

        // Суммы по всем категориям
        Map<String, Integer> dataSumm = new HashMap<>();
        for (Buy buy : buys) {
            String category = getCategoriesByThing(buy.getTitle());
            if (!dataSumm.containsKey(category)) {
                dataSumm.put(category, buy.getSum());
            } else {
                dataSumm.put(category, dataSumm.get(category) + buy.getSum());
            }
        }

        // Ищем максимальную сумму
        for (var entry : dataSumm.entrySet()) {
            if (entry.getValue() > maxSumm) {
                maxSumm = entry.getValue();
                maxCategory = entry.getKey();
            }
        }

        return new Sum(maxCategory, maxSumm);
    }

    private String getCategoriesByThing(String thing) {
        if ( categories.getData().get(thing) != null ) {
            return categories.getData().get(thing);
        } else {
            return OTHER;
        }
    }

}
