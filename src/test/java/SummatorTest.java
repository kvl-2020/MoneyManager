import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class SummatorTest {

    private Map<String, Integer> dataSumm = Map.of(
            "одежда", 777,
            "еда", 1000,
            "другое", 400
    );

    @Test
    void getMax() {
        Summator summator = new Summator(dataSumm);
        String expect = "{\"maxCategory\": {\"category\": \"еда\", \"sum\": 1000\"}}";
        String result = summator.getMax();
        Assertions.assertEquals(expect, result);
    }
}