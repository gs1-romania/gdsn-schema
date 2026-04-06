package ro.gs1.gdsn.schema;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.glassfish.jaxb.core.marshaller.XMLWriter;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class GdsnXmlFactory {

   public byte[] generate(CatalogueItemNotificationMessage message) throws JAXBException, IOException {
      return generateXml(message);
   }

   public byte[] generate(GS1ResponseMessage message) throws JAXBException, IOException {
      return generateXml(message);
   }

   public byte[] generate(CatalogueItemConfirmationMessage message) throws JAXBException, IOException {
      return generateXml(message);
   }

   public byte[] generate(CatalogueItemHierarchicalWithdrawalMessage message) throws JAXBException, IOException {
      return generateXml(message);
   }

   public byte[] generate(CatalogueItemPublicationMessage message) throws JAXBException, IOException {
      return generateXml(message);
   }

   public byte[] generate(CatalogueItemRegistrationResponseMessage message) throws JAXBException, IOException {
      return generateXml(message);
   }

   public byte[] generate(CatalogueItemSubscriptionMessage message) throws JAXBException, IOException {
      return generateXml(message);
   }

   public byte[] generate(RegistryCatalogueItemMessage message) throws JAXBException, IOException {
      return generateXml(message);
   }

   public byte[] generate(RequestForCatalogueItemNotificationMessage message) throws JAXBException, IOException {
      return generateXml(message);
   }

   private byte[] generateXml(Object message) throws JAXBException, IOException {
      Path gdsnFile = Files.createTempFile("gdsn-", "-xml");
      try (FileWriter fileWriter = new FileWriter(gdsnFile.toFile())) {
         Marshaller jaxbMarshaller = buildMarshaller();
         NamespaceFilter outFilter = new NamespaceFilter();
         XMLWriter writer = new XMLWriter(fileWriter, "UTF-8");
         outFilter.setContentHandler(writer);
         jaxbMarshaller.marshal(message, outFilter);
         fileWriter.flush();
         return VkBeautifyXml.formatXml(Files.readAllBytes(gdsnFile), StandardCharsets.UTF_8);
      }
   }

   public CatalogueItemNotificationMessage readCatalogueItemNotification(Source source) throws JAXBException {
      return read(source, CatalogueItemNotificationMessage.class);
   }

   public GS1ResponseMessage readGS1Response(Source source) throws JAXBException {
      return read(source, GS1ResponseMessage.class);
   }

   public CatalogueItemHierarchicalWithdrawalMessage readCatalogueItemHierarchicalWithdrawal(Source source) throws JAXBException {
      return read(source, CatalogueItemHierarchicalWithdrawalMessage.class);
   }

   public CatalogueItemPublicationMessage readCatalogueItemPublication(Source source) throws JAXBException {
      return read(source, CatalogueItemPublicationMessage.class);
   }

   public CatalogueItemRegistrationResponseMessage readCatalogueItemRegistrationResponse(Source source) throws JAXBException {
      return read(source, CatalogueItemRegistrationResponseMessage.class);
   }

   public CatalogueItemSubscriptionMessage readCatalogueItemSubscription(Source source) throws JAXBException {
      return read(source, CatalogueItemSubscriptionMessage.class);
   }

   public RegistryCatalogueItemMessage readRegistryCatalogueItem(Source source) throws JAXBException {
      return read(source, RegistryCatalogueItemMessage.class);
   }

   public RequestForCatalogueItemNotificationMessage readRequestForCatalogueItemNotification(Source source) throws JAXBException {
      return read(source, RequestForCatalogueItemNotificationMessage.class);
   }

   public CatalogueItemConfirmationMessage readCatalogueItemConfirmation(Source source) throws JAXBException {
      return read(source, CatalogueItemConfirmationMessage.class);
   }

   private <T> T read(Source source, Class<T> expectedType) throws JAXBException {
      Unmarshaller jaxbUnmarshaller = buildUnmarshaller();
      return jaxbUnmarshaller
          .unmarshal(source, expectedType)
          .getValue();
   }

   private Marshaller buildMarshaller() throws JAXBException {
      JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
      return jaxbContext.createMarshaller();
   }

   private Unmarshaller buildUnmarshaller() throws JAXBException {
      final JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
      return jaxbContext.createUnmarshaller();
   }

   @Deprecated
   public List<String> isWellFormedCatalogueItemNotificationXml(InputStream xml) throws IOException, SAXException {
      return isWellFormedGDSNXml(xml);
   }

   @Deprecated
   public List<String> isWellFormedCatalogueItemConfirmationXml(InputStream xml) throws IOException, SAXException {
      return isWellFormedGDSNXml(xml);
   }

   @Deprecated
   public List<String> isWellFormedCatalogueItemHierarchicalWithdrawalXml(InputStream xml) throws IOException, SAXException {
      return isWellFormedGDSNXml(xml);
   }

   @Deprecated
   public List<String> isWellFormedCatalogueItemPublicationXml(InputStream xml) throws IOException, SAXException {
      return isWellFormedGDSNXml(xml);
   }

   @Deprecated
   public List<String> isWellFormedCatalogueItemRegistrationResponseXml(InputStream xml) throws IOException, SAXException {
      return isWellFormedGDSNXml(xml);
   }

   @Deprecated
   public List<String> isWellFormedCatalogueItemSubscriptionXml(InputStream xml) throws IOException, SAXException {
      return isWellFormedGDSNXml(xml);
   }

   @Deprecated
   public List<String> isWellFormedGS1ResponseXml(InputStream xml) throws IOException, SAXException {
      return isWellFormedGDSNXml(xml);
   }

   @Deprecated
   public List<String> isWellFormedRegistryCatalogueItemXml(InputStream xml) throws IOException, SAXException {
      return isWellFormedGDSNXml(xml);
   }

   @Deprecated
   public List<String> isWellFormedRequestForCatalogueItemNotificationXml(InputStream xml) throws IOException, SAXException {
      return isWellFormedGDSNXml(xml);
   }

   public List<String> isWellFormedGDSNXml(InputStream xml) throws SAXException, IOException {
      SchemaFactory schemaFactory = SchemaFactory
          .newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
      Schema schema = schemaFactory.newSchema();
      Validator validator = schema.newValidator();
      SchemaErrorHandler schemaErrorHandler = new SchemaErrorHandler();
      validator.setErrorHandler(schemaErrorHandler);
      validator.validate(new StreamSource(xml));
      return schemaErrorHandler.getErrors();
   }

}
