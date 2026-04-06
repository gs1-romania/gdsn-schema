package ro.gs1.gdsn.schema;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class SchemaErrorHandler implements ErrorHandler {

   private final List<String> errors = new ArrayList<>();

   @Override
   public void warning(SAXParseException exception) throws SAXException {
      errors.add(printify(exception));
   }

   @Override
   public void error(SAXParseException exception) throws SAXException {
      errors.add(printify(exception));
   }

   @Override
   public void fatalError(SAXParseException exception) throws SAXException {
      errors.add(printify(exception));
   }

   private String printify(SAXParseException exception) {
      StringJoiner buf = new StringJoiner("; ");
      String message = exception.getLocalizedMessage();
      if (exception.getPublicId() != null) buf.add("publicId: " + exception.getPublicId());
      if (exception.getSystemId() != null) buf.add("systemId: " + exception.getSystemId());
      if (exception.getLineNumber() != -1) buf.add("lineNumber: " + exception.getLineNumber());
      if (exception.getColumnNumber() != -1) buf.add("columnNumber: " + exception.getColumnNumber());
      if (message != null) buf.add(message);
      return buf.toString();
   }

   public List<String> getErrors() {
      return errors;
   }
}
