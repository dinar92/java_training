package sqlru.core;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class DateTimeExtractorTest {

    @Test
    public void whenInputIsTodayThenOutputIsRelevant() {
        DateTimeExtractor extractor = new DateTimeExtractor();
        String inputDate = "сегодня, 12:00";
        LocalDateTime dateTime =  extractor.extract(inputDate);
        assertThat(dateTime.toLocalDate(), is(LocalDate.now()));
        assertThat(dateTime.toLocalTime(), is(LocalTime.of(12, 00)));
    }

    @Test
    public void whenInputIsYesterdayThenOutputIsRelevant() {
        DateTimeExtractor extractor = new DateTimeExtractor();
        String inputDate = "вчера, 12:00";
        LocalDateTime dateTime =  extractor.extract(inputDate);
        assertThat(dateTime.toLocalDate(), is(LocalDate.now().minusDays(1)));
        assertThat(dateTime.toLocalTime(), is(LocalTime.of(12, 00)));
    }

    @Test
    public void whenInputIsArbitraryDateThenOutputIsRelevant() {
        DateTimeExtractor extractor = new DateTimeExtractor();
        String inputDate = "12 мая 18, 12:00";
        LocalDateTime dateTime =  extractor.extract(inputDate);
        assertThat(dateTime.toLocalDate(), is(LocalDate.of(2018, 5, 12)));
        assertThat(dateTime.toLocalTime(), is(LocalTime.of(12, 00)));
    }
}