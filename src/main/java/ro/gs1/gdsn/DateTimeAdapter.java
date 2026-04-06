package ro.gs1.gdsn;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

public class DateTimeAdapter extends XmlAdapter<String, ZonedDateTime> {

   private static final DateTimeFormatter FORMATTER = new DateTimeFormatterBuilder()
       .append(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX"))
       .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
       .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
       .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
       .toFormatter();

   @Override
   public String marshal(ZonedDateTime v) throws Exception {
      return v.format(FORMATTER);
   }

   @Override
   public ZonedDateTime unmarshal(String v) throws Exception {
      return ZonedDateTime.from(FORMATTER.parse(v));
   }
}
