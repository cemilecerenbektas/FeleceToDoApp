package basic.todo.app;

import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

@Component
public class Util {

    public String formatDate(Instant instant) {
        DateTimeFormatter formatter =
                DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)
                        .withZone(ZoneId.systemDefault());
        return formatter.format(instant);
    }

}
