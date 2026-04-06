package ro.gs1.gdsn;

import java.time.Instant;
import java.time.format.DateTimeFormatter;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

public class DateAdapter extends XmlAdapter<String, Instant> {

   @Override
   public String marshal(Instant v) throws Exception {
      return DateTimeFormatter.ISO_DATE.format(v);
   }

   @Override
   public Instant unmarshal(String v) throws Exception {
      return Instant.parse(v.trim());
   }
}
