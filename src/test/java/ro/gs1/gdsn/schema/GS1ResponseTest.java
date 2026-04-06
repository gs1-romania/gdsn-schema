package ro.gs1.gdsn.schema;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;
import org.xmlunit.matchers.CompareMatcher;

import jakarta.xml.bind.JAXBException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.ZonedDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GS1ResponseTest {

   private static final Logger logger = LoggerFactory.getLogger(GS1ResponseTest.class);

   private GS1ResponseMessage generateGS1ResponseMessage() {
      logger.debug("generateGS1ResponseMessage() - start");
      ObjectFactory of = new ObjectFactory();
      GS1ResponseMessage expected = of.createGS1ResponseMessage();
      {
         StandardBusinessDocumentHeader standardBusinessDocumentHeader = of.createStandardBusinessDocumentHeader();
         standardBusinessDocumentHeader.setHeaderVersion("1.0");
         standardBusinessDocumentHeader.setBusinessScope(null);
         DocumentIdentification documentIdentification = of.createDocumentIdentification();
         documentIdentification.setType("gS1Response");
         documentIdentification.setTypeVersion("3.1");
         documentIdentification.setStandard("GS1");
         documentIdentification.setInstanceIdentifier("20051101");
         documentIdentification.setCreationDateAndTime(ZonedDateTime.parse("2011-03-11T11:00:00.000-05:00"));
         standardBusinessDocumentHeader.setDocumentIdentification(documentIdentification);
         BusinessScope businessScope = of.createBusinessScope();
         Scope scope = of.createScope();
         scope.setType("GDSN");
         scope.setInstanceIdentifier("20051101");
         CorrelationInformation correlationInformation = of.createCorrelationInformation();
         correlationInformation
               .setRequestingDocumentCreationDateTime(ZonedDateTime.parse("2011-03-10T10:15:01.000-05:00"));
         correlationInformation.setRequestingDocumentInstanceIdentifier("100002");
         scope.setCorrelationInformation(correlationInformation);
         businessScope.getScopes()
               .add(scope);
         standardBusinessDocumentHeader.setBusinessScope(businessScope);
         Partner receiver = of.createPartner();
         PartnerIdentification receiverIdentification = of.createPartnerIdentification();
         receiverIdentification.setAuthority("GS1");
         receiverIdentification.setValue("8222345678913");
         receiver.setIdentifier(receiverIdentification);
         Partner sender = of.createPartner();
         PartnerIdentification senderIdentification = of.createPartnerIdentification();
         senderIdentification.setAuthority("GS1");
         senderIdentification.setValue("7222345678911");
         sender.setIdentifier(senderIdentification);
         standardBusinessDocumentHeader.getReceivers()
               .add(receiver);
         standardBusinessDocumentHeader.getSenders()
               .add(sender);
         expected.setStandardBusinessDocumentHeader(standardBusinessDocumentHeader);
      }
      {
         GS1ResponseType gs1ResponseType = of.createGS1ResponseType();
         EntityIdentificationType entityIdentificationType = of.createEntityIdentificationType();
         entityIdentificationType.setEntityIdentification("20051101");
         PartyIdentificationType partyIdentificationType = of.createPartyIdentificationType();
         partyIdentificationType.setGln("8712345678913");
         entityIdentificationType.setContentOwner(partyIdentificationType);
         gs1ResponseType.setOriginatingMessageIdentifier(entityIdentificationType);
         gs1ResponseType.setReceiver("8222345678913");
         gs1ResponseType.setSender("7222345678911");
         GS1ExceptionType gs1ExceptionType = of.createGS1ExceptionType();
         ExceptionMessageTypeCodeType exceptionMessageTypeCodeType = of.createExceptionMessageTypeCodeType();
         exceptionMessageTypeCodeType.setValue("GDSN");
         gs1ExceptionType.setExceptionMessageTypeCode(exceptionMessageTypeCodeType);
         MessageExceptionType messageExceptionType = of.createMessageExceptionType();
         GS1ErrorType gs1ErrorType = of.createGS1ErrorType();
         gs1ErrorType.setErrorCode("Err-075");
         gs1ErrorType.setErrorDateTime(ZonedDateTime.parse("2006-03-17T10:00:00.000-05:00"));
         gs1ErrorType.setErrorDescription("Invalid GLN");
         messageExceptionType.getGS1Errors()
               .add(gs1ErrorType);
         gs1ExceptionType.setMessageException(messageExceptionType);
         gs1ResponseType.getGS1Exceptions()
               .add(gs1ExceptionType);
         expected.getGS1Responses()
               .add(gs1ResponseType);
      }
      logger.debug("generateGS1ResponseMessage() - end");
      return expected;
   }

   @Test
   public void unmarshalGS1Response() throws JAXBException {
      logger.debug("unmarshalGS1Response() - start");
      InputStream resource = getClass().getResourceAsStream("/ro/gs1/gdsn/schema/GS1Response.xml");
      GdsnXmlFactory gdsnXmlFactory = new GdsnXmlFactory();
      Source source = new StreamSource(resource);
      GS1ResponseMessage unmarshal = gdsnXmlFactory.readGS1Response(source);
      GS1ResponseMessage generateGS1ResponseMessageType = generateGS1ResponseMessage();
      /*
       * Unfortunately JAXBElement does not have equals implemented. We need to
       * verify one by one.
       */
      assertEquals(generateGS1ResponseMessageType.getStandardBusinessDocumentHeader()
            .getDocumentIdentification(),
            unmarshal.getStandardBusinessDocumentHeader()
                  .getDocumentIdentification());
      assertEquals(generateGS1ResponseMessageType.getStandardBusinessDocumentHeader()
            .getHeaderVersion(),
            unmarshal.getStandardBusinessDocumentHeader()
                  .getHeaderVersion());
      assertEquals(generateGS1ResponseMessageType.getStandardBusinessDocumentHeader()
            .getReceivers(),
            unmarshal.getStandardBusinessDocumentHeader()
                  .getReceivers());
      assertEquals(generateGS1ResponseMessageType.getStandardBusinessDocumentHeader()
            .getSenders(),
            unmarshal.getStandardBusinessDocumentHeader()
                  .getSenders());
      assertEquals(generateGS1ResponseMessageType.getStandardBusinessDocumentHeader()
            .getHeaderVersion(),
            unmarshal.getStandardBusinessDocumentHeader()
                  .getHeaderVersion());
      assertEquals(generateGS1ResponseMessageType.getGS1Responses(), unmarshal.getGS1Responses());
      logger.debug("unmarshalGS1Response() - end");
   }

   @Test
   public void marshalGS1Response() throws JAXBException, IOException {
      try (InputStream compareTo = getClass().getResourceAsStream("/ro/gs1/gdsn/schema/GS1Response.xml")) {
         logger.debug("marshalGS1Response() - start");
         Assertions.assertNotNull(compareTo);
         GdsnXmlFactory gdsnXmlFactory = new GdsnXmlFactory();
         String compareToString = new String(compareTo.readAllBytes());
         GS1ResponseMessage generateGS1ResponseMessageType = generateGS1ResponseMessage();
         String outString = new String(gdsnXmlFactory.generate(generateGS1ResponseMessageType));
         logger.info("marshalGS1Response() - xml: {}", outString);
         assertThat(outString, CompareMatcher.isSimilarTo(compareToString)
               .ignoreWhitespace()
               .ignoreComments()
               .ignoreElementContentWhitespace());
         logger.debug("marshalGS1Response() - end");
      }
   }

   @Test
   public void isWellFormedGS1ResponseMessage() throws IOException, SAXException, JAXBException, ParserConfigurationException {
      logger.debug("isWellFormedGS1ResponseMessage() - start");

      GS1ResponseMessage expected = generateGS1ResponseMessage();
      GdsnXmlFactory gdsnXmlFactory = new GdsnXmlFactory();
      byte[] xmlContent = gdsnXmlFactory.generate(expected);
      logger.debug("isWellFormedGS1ResponseMessage() - xml: {}", xmlContent);

      gdsnXmlFactory.isWellFormedGDSNXml(new ByteArrayInputStream(xmlContent));

      logger.debug("isWellFormedGS1ResponseMessage() - end");

   }

}
