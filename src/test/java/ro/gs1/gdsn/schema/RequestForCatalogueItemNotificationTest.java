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

public class RequestForCatalogueItemNotificationTest {

   private static final Logger logger = LoggerFactory.getLogger(RequestForCatalogueItemNotificationTest.class);

   private RequestForCatalogueItemNotificationMessage generateRequestForCatalogueItemNotificationMessage() {
      logger.debug("generateRequestForCatalogueItemNotificationMessage() - start");
      ObjectFactory of = new ObjectFactory();
      RequestForCatalogueItemNotificationMessage expected = of.createRequestForCatalogueItemNotificationMessage();
      {
         StandardBusinessDocumentHeader standardBusinessDocumentHeader = of.createStandardBusinessDocumentHeader();
         standardBusinessDocumentHeader.setHeaderVersion("1.0");
         standardBusinessDocumentHeader.setBusinessScope(null);
         DocumentIdentification documentIdentification = of.createDocumentIdentification();
         documentIdentification.setType("requestForCatalogueItemNotification");
         documentIdentification.setTypeVersion("3.1");
         documentIdentification.setStandard("GS1");
         documentIdentification.setInstanceIdentifier("20051101");
         documentIdentification.setCreationDateAndTime(ZonedDateTime.parse("2011-03-11T11:00:00.000-05:00"));
         standardBusinessDocumentHeader.setDocumentIdentification(documentIdentification);
         Partner receiver = of.createPartner();
         PartnerIdentification receiverIdentification = of.createPartnerIdentification();
         receiverIdentification.setAuthority("GS1");
         receiverIdentification.setValue("9712345678912");
         receiver.setIdentifier(receiverIdentification);
         Partner sender = of.createPartner();
         PartnerIdentification senderIdentification = of.createPartnerIdentification();
         senderIdentification.setAuthority("GS1");
         senderIdentification.setValue("8712345678913");
         sender.setIdentifier(senderIdentification);
         standardBusinessDocumentHeader.getReceivers()
               .add(receiver);
         standardBusinessDocumentHeader.getSenders()
               .add(sender);
         expected.setStandardBusinessDocumentHeader(standardBusinessDocumentHeader);
      }
      {
         TransactionType transactionType = of.createTransactionType();
         EntityIdentificationType entityIdentificationType = of.createEntityIdentificationType();
         PartyIdentificationType partyIdentificationType = of.createPartyIdentificationType();
         partyIdentificationType.setGln("8712345678913");
         entityIdentificationType.setContentOwner(partyIdentificationType);
         entityIdentificationType.setEntityIdentification("20051101");
         transactionType.setTransactionIdentification(entityIdentificationType);
         DocumentCommandType documentCommandType = of.createDocumentCommandType();
         DocumentCommandHeaderType documentCommandHeaderType = of.createDocumentCommandHeaderType();
         documentCommandHeaderType.setType(DocumentCommandEnumerationType.ADD);
         EntityIdentificationType docEntityIdentificationType = of.createEntityIdentificationType();
         PartyIdentificationType docPartyIdentificationType = of.createPartyIdentificationType();
         docPartyIdentificationType.setGln("8712345678913");
         docEntityIdentificationType.setContentOwner(docPartyIdentificationType);
         docEntityIdentificationType.setEntityIdentification("20051101");
         documentCommandHeaderType.setDocumentCommandIdentification(docEntityIdentificationType);
         documentCommandType.setDocumentCommandHeader(documentCommandHeaderType);


         RequestForCatalogueItemNotificationType requestForCatalogueItemNotificationType =
               of.createRequestForCatalogueItemNotificationType();
         requestForCatalogueItemNotificationType.setCreationDateTime(ZonedDateTime.parse("2011-03-11T11:00:00.000-05:00"));
         requestForCatalogueItemNotificationType.setDocumentStatusCode(DocumentStatusEnumerationType.ORIGINAL);


         EntityIdentificationType catEntityIdentificationType = of.createEntityIdentificationType();
         PartyIdentificationType partyIdentificationType3 = of.createPartyIdentificationType();
         partyIdentificationType3.setGln("8712345678913");
         catEntityIdentificationType.setContentOwner(partyIdentificationType3);
         catEntityIdentificationType.setEntityIdentification("20051101");
         requestForCatalogueItemNotificationType.setCatalogueItemSubscriptionIdentification(catEntityIdentificationType);
         requestForCatalogueItemNotificationType.setIsReload(false);


         requestForCatalogueItemNotificationType.setDataRecipient("8222345678913");
         requestForCatalogueItemNotificationType.setGpcCategoryCode("10000028");

         documentCommandType.getDocuments().add(of.createRequestForCatalogueItemNotification(requestForCatalogueItemNotificationType));
         transactionType.setDocumentCommand(documentCommandType);
         expected.getTransactions().add(transactionType);
      }
      logger.debug("generateRequestForCatalogueItemNotificationMessage() - end");
      return expected;
   }

   @Test
   public void unmarshalRequestForCatalogueItemNotificationMessage() throws JAXBException {
      logger.debug("unmarshalRequestForCatalogueItemNotificationMessage() - start");
      InputStream resource = getClass().getResourceAsStream("/ro/gs1/gdsn/schema/RequestForCatalogueItemNotification.xml");
      GdsnXmlFactory xmlFactory = new GdsnXmlFactory();
      Source source = new StreamSource(resource);
      CatalogueItemConfirmationMessage unmarshal = xmlFactory.readCatalogueItemConfirmation(source);

      RequestForCatalogueItemNotificationMessage requestForCatalogueItemNotificationMessage =
            generateRequestForCatalogueItemNotificationMessage();
      /*
       * Unfortunately JAXBElement does not have equals implemented. We need to
       * verify one by one.
       */
      assertEquals(requestForCatalogueItemNotificationMessage.getStandardBusinessDocumentHeader()
                  .getDocumentIdentification(),
            unmarshal.getStandardBusinessDocumentHeader()
                  .getDocumentIdentification());
      assertEquals(requestForCatalogueItemNotificationMessage.getStandardBusinessDocumentHeader()
                  .getHeaderVersion(),
            unmarshal.getStandardBusinessDocumentHeader()
                  .getHeaderVersion());
      assertEquals(requestForCatalogueItemNotificationMessage.getStandardBusinessDocumentHeader()
                  .getReceivers(),
            unmarshal.getStandardBusinessDocumentHeader()
                  .getReceivers());
      assertEquals(requestForCatalogueItemNotificationMessage.getStandardBusinessDocumentHeader()
                  .getSenders(),
            unmarshal.getStandardBusinessDocumentHeader()
                  .getSenders());
      assertEquals(requestForCatalogueItemNotificationMessage.getStandardBusinessDocumentHeader()
                  .getHeaderVersion(),
            unmarshal.getStandardBusinessDocumentHeader()
                  .getHeaderVersion());
      assertEquals(requestForCatalogueItemNotificationMessage.getTransactions().get(0).getTransactionIdentification(),
            unmarshal.getTransactions().get(0).getTransactionIdentification());
      assertEquals(requestForCatalogueItemNotificationMessage.getTransactions().get(0).getDocumentCommand().getDocumentCommandHeader(),
            unmarshal.getTransactions().get(0).getDocumentCommand().getDocumentCommandHeader());
      assertEquals(requestForCatalogueItemNotificationMessage.getTransactions().get(0).getDocumentCommand().getDocuments().get(0).getValue(),
            unmarshal.getTransactions().get(0).getDocumentCommand().getDocuments().get(0).getValue());
      logger.debug("unmarshalRequestForCatalogueItemNotificationMessage() - end");
   }

   @Test
   public void marshalRequestForCatalogueItemNotificationMessage() throws JAXBException, IOException {
      try (InputStream compareTo =
                 getClass().getResourceAsStream("/ro/gs1/gdsn/schema/RequestForCatalogueItemNotification.xml")) {
         logger.debug("marshalRequestForCatalogueItemNotificationMessage() - start");
         Assertions.assertNotNull(compareTo);
         String compareToString = new String(compareTo.readAllBytes());
         RequestForCatalogueItemNotificationMessage requestForCatalogueItemNotificationMessage =
               generateRequestForCatalogueItemNotificationMessage();
         GdsnXmlFactory gdsnXmlFactory = new GdsnXmlFactory();
         String outString = new String(gdsnXmlFactory.generate(requestForCatalogueItemNotificationMessage));
         logger.info("marshalRequestForCatalogueItemNotificationMessage() - xml: {}", outString);
         assertThat(outString, CompareMatcher.isSimilarTo(compareToString)
               .ignoreWhitespace()
               .ignoreComments()
               .ignoreElementContentWhitespace());
         logger.debug("marshalRequestForCatalogueItemNotificationMessage() - end");
      }
   }

   @Test
   public void isWellFormedRequestForCatalogueItemNotificationMessage() throws IOException, SAXException, JAXBException, ParserConfigurationException {
      logger.debug("isWellFormedRequestForCatalogueItemNotificationMessage() - start");
      RequestForCatalogueItemNotificationMessage expected =
            generateRequestForCatalogueItemNotificationMessage();
      GdsnXmlFactory gdsnXmlFactory = new GdsnXmlFactory();
      byte[] xmlContent = gdsnXmlFactory.generate(expected);
      logger.debug("isWellFormedRequestForCatalogueItemNotificationMessage() - xml: {}", xmlContent);
      gdsnXmlFactory.isWellFormedGDSNXml(new ByteArrayInputStream(xmlContent));
      logger.debug("isWellFormedRequestForCatalogueItemNotificationMessage() - end");

   }


}
