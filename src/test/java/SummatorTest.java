import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class SummatorTest {

    List<Buy> buys;
    Summator summator;
    Buy buy1 = new Buy("шапка", "2021", "01","01", 10000);
    Buy buy2 = new Buy("булка", "2022", "02","07", 200);
    Buy buy3 = new Buy("телевизор", "2022", "03","03", 5000);
    Buy buy4 = new Buy("мыло", "2022", "02","08", 700);
    Buy buy5 = new Buy("булка", "2022", "02","08", 200);
    Buy buy6 = new Buy("колбаса", "2022", "02","08", 600);

    @BeforeEach
    public void initEach() {
        buys = new ArrayList<>(List.of(buy1, buy2, buy3, buy4, buy5));
        summator = new Summator(buys);
        summator.add(buy6);
    }


    @Test
    void getMaxDayCategory() {
        String expect = "\"maxDayCategory\": {\"category\": \"еда\", \"sum\": 800\"}";
        String result = summator.getMaxDayCategory();
        Assertions.assertEquals(expect, result);
    }

    @Test
    void getMaxMonthCategory() {
        String expect = "\"maxMonthCategory\": {\"category\": \"еда\", \"sum\": 1000\"}";
        String result = summator.getMaxMonthCategory();
        Assertions.assertEquals(expect, result);

    }

    @Test
    void getMaxYearCategory() {
        String expect = "\"maxYearCategory\": {\"category\": \"другое\", \"sum\": 5000\"}";
        String result = summator.getMaxYearCategory();
        Assertions.assertEquals(expect, result);

    }

    @Test
    void getMaxCategory() {
        String expect = "\"maxCategory\": {\"category\": \"одежда\", \"sum\": 10000\"}";
        String result = summator.getMaxCategory();
        Assertions.assertEquals(expect, result);

    }

}