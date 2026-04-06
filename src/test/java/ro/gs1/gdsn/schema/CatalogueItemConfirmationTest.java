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

public class CatalogueItemConfirmationTest {

   private static final Logger logger = LoggerFactory.getLogger(CatalogueItemConfirmationTest.class);

   private CatalogueItemConfirmationMessage generateCatalogueItemConfirmationMessage() {
      logger.debug("generateCatalogueItemConfirmationMessage() - start");
      ObjectFactory of = new ObjectFactory();
      CatalogueItemConfirmationMessage expected = of.createCatalogueItemConfirmationMessage();
      {
         StandardBusinessDocumentHeader standardBusinessDocumentHeader = of.createStandardBusinessDocumentHeader();
         standardBusinessDocumentHeader.setHeaderVersion("1.0");
         standardBusinessDocumentHeader.setBusinessScope(null);
         DocumentIdentification documentIdentification = of.createDocumentIdentification();
         documentIdentification.setType("catalogueItemConfirmation");
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
         docEntityIdentificationType.setContentOwner(partyIdentificationType);
         docEntityIdentificationType.setEntityIdentification("20051101");
         documentCommandHeaderType.setDocumentCommandIdentification(docEntityIdentificationType);
         documentCommandType.setDocumentCommandHeader(documentCommandHeaderType);

         CatalogueItemConfirmationType catalogueItemConfirmationType = of.createCatalogueItemConfirmationType();
         catalogueItemConfirmationType.setCreationDateTime(ZonedDateTime.parse("2011-03-11T11:00:00.000-05:00"));
         catalogueItemConfirmationType.setDocumentStatusCode(DocumentStatusEnumerationType.ORIGINAL);
         EntityIdentificationType catEntityIdentificationType = of.createEntityIdentificationType();
         PartyIdentificationType catPartyIdentificationType = of.createPartyIdentificationType();
         catPartyIdentificationType.setGln("8712345678913");
         catEntityIdentificationType.setContentOwner(partyIdentificationType);
         catEntityIdentificationType.setEntityIdentification("20051101");
         catalogueItemConfirmationType.setCatalogueItemConfirmationIdentification(catEntityIdentificationType);

         CatalogueItemConfirmationStateType catalogueItemConfirmationStateType =
               of.createCatalogueItemConfirmationStateType();
         catalogueItemConfirmationStateType.setCatalogueItemConfirmationStateCode(SynchronisationConfirmationStateEnumerationType.REVIEW);
         catalogueItemConfirmationStateType.setRecipientGLN("9712345678912");
         catalogueItemConfirmationStateType.setMessageCreatorGLN("8712345678913");
         catalogueItemConfirmationStateType.setRecipientDataPool("7712345678944");
         catalogueItemConfirmationType.setCatalogueItemConfirmationState(catalogueItemConfirmationStateType);

         CatalogueItemReferenceType catalogueItemReferenceType1 = of.createCatalogueItemReferenceType();
         catalogueItemReferenceType1.setDataSource("8712345678913");
         catalogueItemReferenceType1.setGtin("00074562000525");
         CountryCodeType countryCodeType1 = of.createCountryCodeType();
         countryCodeType1.setValue("528");
         catalogueItemReferenceType1.setTargetMarketCountryCode(countryCodeType1);
         catalogueItemConfirmationType.setCatalogueItemReference(catalogueItemReferenceType1);

         CatalogueItemConfirmationStatusDetailType catalogueItemConfirmationStatusDetailType =
               of.createCatalogueItemConfirmationStatusDetailType();

         CatalogueItemReferenceType catalogueItemReferenceType2 = of.createCatalogueItemReferenceType();
         catalogueItemReferenceType2.setDataSource("8712345678913");
         catalogueItemReferenceType2.setGtin("00074562000525");
         CountryCodeType countryCodeType2 = of.createCountryCodeType();
         countryCodeType2.setValue("528");
         catalogueItemReferenceType2.setTargetMarketCountryCode(countryCodeType2);
         catalogueItemConfirmationStatusDetailType.setConfirmationStatusCatalogueItem(catalogueItemReferenceType2);

         CatalogueItemConfirmationStatusType catalogueItemConfirmationStatusType =
               of.createCatalogueItemConfirmationStatusType();
         ConfirmationStatusReasonCodeType confirmationStatusReasonCodeType =
               of.createConfirmationStatusReasonCodeType();
         confirmationStatusReasonCodeType.setValue("CIC102");
         Description1000Type description1000Type = of.createDescription1000Type();
         description1000Type.setLanguageCode("en");
         description1000Type.setValue("Unable to Synchronize");
         catalogueItemConfirmationStatusType.setConfirmationStatusCodeDescription(description1000Type);
         CorrectiveActionType correctiveActionType = of.createCorrectiveActionType();
         CorrectiveActionCodeType correctiveActionCodeType = of.createCorrectiveActionCodeType();
         correctiveActionCodeType.setValue("CONTACT_TRADING_PARTNER");
         correctiveActionType.setCorrectiveActionCode(correctiveActionCodeType);
         catalogueItemConfirmationStatusType.setCorrectiveAction(correctiveActionType);
         catalogueItemConfirmationStatusType.setConfirmationStatusCode(confirmationStatusReasonCodeType);

         catalogueItemConfirmationStatusDetailType.getCatalogueItemConfirmationStatuses().add(catalogueItemConfirmationStatusType);
         catalogueItemConfirmationType.getCatalogueItemConfirmationStatusDetails().add(catalogueItemConfirmationStatusDetailType);

         documentCommandType.getDocuments().add(of.createCatalogueItemConfirmation(catalogueItemConfirmationType));
         transactionType.setDocumentCommand(documentCommandType);
         expected.getTransactions().add(transactionType);
      }
      logger.debug("generateCatalogueItemConfirmationMessage() - end");
      return expected;
   }

   @Test
   public void unmarshalCatalogueItemConfirmationMessage() throws JAXBException {
      logger.debug("unmarshalCatalogueItemConfirmationMessage() - start");
      InputStream resource = getClass().getResourceAsStream("/ro/gs1/gdsn/schema/CatalogueItemConfirmation.xml");
      GdsnXmlFactory xmlFactory = new GdsnXmlFactory();
      Source source = new StreamSource(resource);
      CatalogueItemConfirmationMessage unmarshal = xmlFactory.readCatalogueItemConfirmation(source);

      CatalogueItemConfirmationMessage generateCatalogueItemConfirmationMessage =
            generateCatalogueItemConfirmationMessage();
      /*
       * Unfortunately JAXBElement does not have equals implemented. We need to
       * verify one by one.
       */
      assertEquals(generateCatalogueItemConfirmationMessage.getStandardBusinessDocumentHeader()
                  .getDocumentIdentification(),
            unmarshal.getStandardBusinessDocumentHeader()
                  .getDocumentIdentification());
      assertEquals(generateCatalogueItemConfirmationMessage.getStandardBusinessDocumentHeader()
                  .getHeaderVersion(),
            unmarshal.getStandardBusinessDocumentHeader()
                  .getHeaderVersion());
      assertEquals(generateCatalogueItemConfirmationMessage.getStandardBusinessDocumentHeader()
                  .getReceivers(),
            unmarshal.getStandardBusinessDocumentHeader()
                  .getReceivers());
      assertEquals(generateCatalogueItemConfirmationMessage.getStandardBusinessDocumentHeader()
                  .getSenders(),
            unmarshal.getStandardBusinessDocumentHeader()
                  .getSenders());
      assertEquals(generateCatalogueItemConfirmationMessage.getStandardBusinessDocumentHeader()
                  .getHeaderVersion(),
            unmarshal.getStandardBusinessDocumentHeader()
                  .getHeaderVersion());
      assertEquals(generateCatalogueItemConfirmationMessage.getTransactions().get(0).getTransactionIdentification(),
            unmarshal.getTransactions().get(0).getTransactionIdentification());
      assertEquals(generateCatalogueItemConfirmationMessage.getTransactions().get(0).getDocumentCommand().getDocumentCommandHeader(),
            unmarshal.getTransactions().get(0).getDocumentCommand().getDocumentCommandHeader());
      assertEquals(generateCatalogueItemConfirmationMessage.getTransactions().get(0).getDocumentCommand().getDocuments().get(0).getValue(),
            unmarshal.getTransactions().get(0).getDocumentCommand().getDocuments().get(0).getValue());
      logger.debug("unmarshalCatalogueItemConfirmationMessage() - end");
   }

   @Test
   public void marshalCatalogueItemConfirmationMessage() throws JAXBException, IOException {
      try (InputStream compareTo =
                 getClass().getResourceAsStream("/ro/gs1/gdsn/schema/CatalogueItemConfirmation.xml")) {
         logger.debug("marshalCatalogueItemConfirmationMessage() - start");
         Assertions.assertNotNull(compareTo);
         String compareToString = new String(compareTo.readAllBytes());
         CatalogueItemConfirmationMessage generateCatalogueItemNotificationMessage =
               generateCatalogueItemConfirmationMessage();
         GdsnXmlFactory gdsnXmlFactory = new GdsnXmlFactory();
         String outString = new String(gdsnXmlFactory.generate(generateCatalogueItemNotificationMessage));
         logger.info("marshalCatalogueItemConfirmationMessage() - xml: {}", outString);
         assertThat(outString, CompareMatcher.isSimilarTo(compareToString)
               .ignoreWhitespace()
               .ignoreComments()
               .ignoreElementContentWhitespace());
         logger.debug("marshalCatalogueItemConfirmationMessage() - end");
      }
   }

   @Test
   public void isWellFormedCatalogueItemConfirmationMessage() throws IOException, SAXException, JAXBException, ParserConfigurationException {
      logger.debug("isWellFormedCatalogueItemConfirmationMessage() - start");
      CatalogueItemConfirmationMessage expected =
            generateCatalogueItemConfirmationMessage();
      GdsnXmlFactory gdsnXmlFactory = new GdsnXmlFactory();
      byte[] xmlContent = gdsnXmlFactory.generate(expected);
      logger.debug("isWellFormedCatalogueItemConfirmationMessage() - xml: {}", xmlContent);
      gdsnXmlFactory.isWellFormedGDSNXml(new ByteArrayInputStream(xmlContent));
      logger.debug("isWellFormedCatalogueItemConfirmationMessage() - end");

   }


}
