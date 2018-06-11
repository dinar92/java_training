package sqlru.core;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A LocalDateTime extractor.
 */
public class DateTimeExtractor {

    /**
     * Extracts from input string date and time and returns this in a LocalDateTime instance.
     * Сan only work with the date and time taken on the sql.ru site.
     *
     * @param input - some string, must contains the data and time information.
     * @return - extracted date and time, or null if data and time cannot be extract.
     */
    public LocalDateTime extract(String input) {
        LocalDateTime localDateTime = null;
        Matcher matcher = Pattern.compile("((([12]?[0-9]|3[01]) .{3} [0-9][0-9])|сегодня|вчера), ([01]?[0-9]|2[0-3]):[0-5][0-9]").matcher(input);

        if (matcher.find()) {
            String timeDate = matcher.group(0);
            Matcher timeMatcher = Pattern.compile("([01]?[0-9]|2[0-3]):[0-5][0-9]").matcher(timeDate);
            String time = null;
            if (timeMatcher.find()) {
                time = timeMatcher.group(0);
            }
            LocalTime localTime;
            LocalDate localDate;
            Locale locale = new Locale("ru", "RU");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yy, HH:mm").withLocale(locale);

            if (timeDate.contains("сегодня")) {
                localDate = LocalDate.now();
            } else if (timeDate.contains("вчера")) {
                localDate = LocalDate.now().minusDays(1);
            } else {
                if (timeDate.contains("май")) {
                    timeDate = timeDate.replace("май", "мая");
                }
                localDate = LocalDate.parse(timeDate, formatter);
            }
            localTime = LocalTime.parse(time);
            localDateTime = LocalDateTime.of(localDate, localTime);
        }
        return localDateTime;
    }
}
