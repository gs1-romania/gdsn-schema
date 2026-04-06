package ro.gs1.gdsn.schema;

import org.junit.jupiter.api.Test;

public class VkBeautifyXmlTest {

   @Test
   public void beautify() {
      String xml = "<root><child attr=\"test\">value\ntest</child></root>";
      System.out.println(VkBeautifyXml.formatXml(xml));
   }
}
