package ro.gs1.gdsn.schema;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * This is translated from Javascript to Java.
 * Javascript library: <a href="https://github.com/vkiryukhin/vkBeautify">vkBeautify</a>
 */
public class VkBeautifyXml {

   public static byte[] formatXml(byte[] text, Charset charset) {
      String transfromToString = new String(text, charset);
      return formatXml(transfromToString).getBytes(charset);
   }

   public static String formatXml(String text) {
      String[] ar = text.replaceAll(">\\s*<", "><")
          .replaceAll("<", "~::~<")
          .replaceAll("\\s*xmlns:", "~::~xmlns:")
          .replaceAll("\\s*xmlns=", "~::~xmlns=")
          .split("~::~");

      int len = ar.length;
      boolean inComment = false;
      int deep = 0;
      StringBuilder str = new StringBuilder();
      String[] shift = createShiftArray("\t");

      for (int ix = 0; ix < len; ix++) {
         // start comment or <![CDATA[...]]> or <!DOCTYPE //
         if (ar[ix].contains("<!")) {
            str.append(shift[deep]).append(ar[ix]);
            // end comment or <![CDATA[...]]> //
            inComment = !ar[ix].contains("-->") && !ar[ix].contains("]>") && !ar[ix].contains("!DOCTYPE");
         } else if (ar[ix].contains("-->") || ar[ix].contains("]>")) {
            // end comment or <![CDATA[...]]> //
            str.append(ar[ix]);
            inComment = false;
         } else if (ar[ix].matches("^</\\w[\\s\\S]*") && ix > 0 && ar[ix - 1].matches("^<\\w[\\s\\S]*") &&
             ar[ix - 1].startsWith(ar[ix].replaceAll("^</", "<").replaceAll(">$", ""))) {
            // <elm></elm> //
            str.append(ar[ix]);
            if (!inComment) deep--;
         } else if (ar[ix].matches("<\\w[\\s\\S]*") && !ar[ix].contains("</") && !ar[ix].contains("/>")) {
            // <elm> //
            str.append(!inComment ? shift[deep++] + ar[ix] : ar[ix]);
         } else if (ar[ix].matches("<\\w[\\s\\S]*") && ar[ix].contains("</")) {
            // <elm>...</elm> //
            str.append(!inComment ? shift[deep] + ar[ix] : ar[ix]);
         } else if (ar[ix].contains("</")) {
            // </elm> //
            str.append(!inComment ? shift[--deep] + ar[ix] : ar[ix]);
         } else if (ar[ix].contains("/>")) {
            // <elm/> //
            str.append(!inComment ? shift[deep] + ar[ix] : ar[ix]);
         } else if (ar[ix].contains("<?")) {
            // <? xml ... ?> //
            str.append(shift[deep]).append(ar[ix]);
         } else if (ar[ix].contains("xmlns:") || ar[ix].contains("xmlns=")) {
            // xmlns //
            str.append(shift[deep]).append(ar[ix]);
         } else {
            str.append(ar[ix]);
         }
      }

      return (str.length() != 0 && str.charAt(0) == '\n') ? str.substring(1) : str.toString();
   }

   public static String[] createShiftArray(String step) {
      List<String> shift = new ArrayList<>();
      shift.add("\n");
      for (int i = 0; i < 100; i++) {
         shift.add(shift.get(i) + step);
      }

      return shift.toArray(new String[]{});
   }
}
