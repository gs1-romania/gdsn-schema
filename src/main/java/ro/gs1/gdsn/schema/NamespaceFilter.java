package ro.gs1.gdsn.schema;

import org.glassfish.jaxb.runtime.util.AttributesImpl;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.XMLFilterImpl;

import java.util.HashSet;
import java.util.Set;

public class NamespaceFilter extends XMLFilterImpl {

   private final Set<String> xmlnsAdded = new HashSet<>();

   @Override
   public void startElement(String uri, String localName, String qName,
                            Attributes attrs) throws SAXException {
      String[] splitQName = qName.split(":");
      if (splitQName.length > 1 && attrs instanceof AttributesImpl) {
         String namespace = splitQName[0];
         AttributesImpl attrsImpl = (AttributesImpl) attrs;
         GdsnNamespaces gdsnNamespaces = GdsnNamespaces.findByNamespace(namespace);
         if (gdsnNamespaces != null) {
            if (gdsnNamespaces.isMessage() && localName.toLowerCase().contains("message")) {
               attrsImpl.addAttribute("", "xsi", "xmlns:xsi", "", "http://www.w3.org/2001/XMLSchema-instance");
               attrsImpl.addAttribute("", "sh", "xmlns:sh", "", "http://www.unece.org/cefact/namespaces/StandardBusinessDocumentHeader");
            }
            if (gdsnNamespaces.isModule() || !xmlnsAdded.contains(gdsnNamespaces.getNamespace())) {
               attrsImpl.addAttribute("", gdsnNamespaces.getNamespace(), "xmlns:" + gdsnNamespaces.getNamespace(), "", gdsnNamespaces.getUri());
               attrsImpl.addAttribute("", "schemaLocation", "xsi:schemaLocation", "", gdsnNamespaces.getUri() + " " + gdsnNamespaces.getSchemaLocation());
               xmlnsAdded.add(gdsnNamespaces.getNamespace());
            }
         }
      }
      super.startElement(null, localName, qName, attrs);
   }

   @Override
   public void endElement(String uri, String localName, String qName) throws SAXException {
      super.endElement(null, localName, qName);
   }

   @Override
   public void startPrefixMapping(String prefix, String url) {
   }
}