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

public class CatalogueItemRegistrationResponseTest {

   private static final Logger logger = LoggerFactory.getLogger(CatalogueItemRegistrationResponseTest.class);

   private CatalogueItemRegistrationResponseMessage generateCatalogueItemRegistrationResponseMessage() {
      logger.debug("generateCatalogueItemRegistrationResponseMessage() - start");
      ObjectFactory of = new ObjectFactory();
      CatalogueItemRegistrationResponseMessage expected = of.createCatalogueItemRegistrationResponseMessage();
      {
         StandardBusinessDocumentHeader standardBusinessDocumentHeader = of.createStandardBusinessDocumentHeader();
         standardBusinessDocumentHeader.setHeaderVersion("1.0");
         standardBusinessDocumentHeader.setBusinessScope(null);
         DocumentIdentification documentIdentification = of.createDocumentIdentification();
         documentIdentification.setType("catalogueItemRegistrationResponse");
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

         CatalogueItemRegistrationResponseType catalogueItemRegistrationResponseType =
               of.createCatalogueItemRegistrationResponseType();
         catalogueItemRegistrationResponseType.setCreationDateTime(ZonedDateTime.parse("2011-03-11T11:00:00.000-05:00"));
         catalogueItemRegistrationResponseType.setDocumentStatusCode(DocumentStatusEnumerationType.ORIGINAL);


         EntityIdentificationType catEntityIdentificationType = of.createEntityIdentificationType();
         PartyIdentificationType partyIdentificationType3 = of.createPartyIdentificationType();
         partyIdentificationType3.setGln("8712345678913");
         catEntityIdentificationType.setContentOwner(partyIdentificationType3);
         catEntityIdentificationType.setEntityIdentification("20051101");
         catalogueItemRegistrationResponseType.setCatalogueItemRegistrationResponseIdentification(catEntityIdentificationType);

         catalogueItemRegistrationResponseType.setLastChangedDateTime(ZonedDateTime.parse("2013-02-10T12:00:01.000-05:00"));
         catalogueItemRegistrationResponseType.setRegistrationDateTime(ZonedDateTime.parse("2013-02-10T12:00:01.000-05:00"));

         catalogueItemRegistrationResponseType.setResponseStatus(ResponseStatusEnumerationType.ACCEPTED);

         CatalogueItemReferenceType catalogueItemReferenceType1 = of.createCatalogueItemReferenceType();
         catalogueItemReferenceType1.setDataSource("8712345678913");
         catalogueItemReferenceType1.setGtin("00074562000525");
         CountryCodeType countryCodeType1 = of.createCountryCodeType();
         countryCodeType1.setValue("124");
         catalogueItemReferenceType1.setTargetMarketCountryCode(countryCodeType1);
         catalogueItemRegistrationResponseType.setCatalogueItemReference(catalogueItemReferenceType1);

         documentCommandType.getDocuments().add(of.createCatalogueItemRegistrationResponse(catalogueItemRegistrationResponseType));
         transactionType.setDocumentCommand(documentCommandType);
         expected.getTransactions().add(transactionType);
      }
      logger.debug("generateCatalogueItemRegistrationResponseMessage() - end");
      return expected;
   }

   @Test
   public void unmarshalCatalogueItemRegistrationResponseMessage() throws JAXBException {
      logger.debug("unmarshalCatalogueItemRegistrationResponseMessage() - start");
      InputStream resource = getClass().getResourceAsStream("/ro/gs1/gdsn/schema/CatalogueItemRegistrationResponse.xml");
      GdsnXmlFactory xmlFactory = new GdsnXmlFactory();
      Source source = new StreamSource(resource);
      CatalogueItemConfirmationMessage unmarshal = xmlFactory.readCatalogueItemConfirmation(source);

      CatalogueItemRegistrationResponseMessage generateCatalogueItemRegistrationResponseMessage =
            generateCatalogueItemRegistrationResponseMessage();
      /*
       * Unfortunately JAXBElement does not have equals implemented. We need to
       * verify one by one.
       */
      assertEquals(generateCatalogueItemRegistrationResponseMessage.getStandardBusinessDocumentHeader()
                  .getDocumentIdentification(),
            unmarshal.getStandardBusinessDocumentHeader()
                  .getDocumentIdentification());
      assertEquals(generateCatalogueItemRegistrationResponseMessage.getStandardBusinessDocumentHeader()
                  .getHeaderVersion(),
            unmarshal.getStandardBusinessDocumentHeader()
                  .getHeaderVersion());
      assertEquals(generateCatalogueItemRegistrationResponseMessage.getStandardBusinessDocumentHeader()
                  .getReceivers(),
            unmarshal.getStandardBusinessDocumentHeader()
                  .getReceivers());
      assertEquals(generateCatalogueItemRegistrationResponseMessage.getStandardBusinessDocumentHeader()
                  .getSenders(),
            unmarshal.getStandardBusinessDocumentHeader()
                  .getSenders());
      assertEquals(generateCatalogueItemRegistrationResponseMessage.getStandardBusinessDocumentHeader()
                  .getHeaderVersion(),
            unmarshal.getStandardBusinessDocumentHeader()
                  .getHeaderVersion());
      assertEquals(generateCatalogueItemRegistrationResponseMessage.getTransactions().get(0).getTransactionIdentification(),
            unmarshal.getTransactions().get(0).getTransactionIdentification());
      assertEquals(generateCatalogueItemRegistrationResponseMessage.getTransactions().get(0).getDocumentCommand().getDocumentCommandHeader(),
            unmarshal.getTransactions().get(0).getDocumentCommand().getDocumentCommandHeader());
      assertEquals(generateCatalogueItemRegistrationResponseMessage.getTransactions().get(0).getDocumentCommand().getDocuments().get(0).getValue(),
            unmarshal.getTransactions().get(0).getDocumentCommand().getDocuments().get(0).getValue());
      logger.debug("unmarshalCatalogueItemRegistrationResponseMessage() - end");
   }

   @Test
   public void marshalCatalogueItemRegistrationResponseMessage() throws JAXBException, IOException {
      try (InputStream compareTo =
                 getClass().getResourceAsStream("/ro/gs1/gdsn/schema/CatalogueItemRegistrationResponse.xml")) {
         logger.debug("marshalCatalogueItemRegistrationResponseMessage() - start");
         Assertions.assertNotNull(compareTo);
         String compareToString = new String(compareTo.readAllBytes());
         CatalogueItemRegistrationResponseMessage generateCatalogueItemRegistrationResponseMessage =
               generateCatalogueItemRegistrationResponseMessage();
         GdsnXmlFactory gdsnXmlFactory = new GdsnXmlFactory();
         String outString = new String(gdsnXmlFactory.generate(generateCatalogueItemRegistrationResponseMessage));
         logger.info("marshalCatalogueItemRegistrationResponseMessage() - xml: {}", outString);
         assertThat(outString, CompareMatcher.isSimilarTo(compareToString)
               .ignoreWhitespace()
               .ignoreComments()
               .ignoreElementContentWhitespace());
         logger.debug("marshalCatalogueItemRegistrationResponseMessage() - end");
      }
   }

   @Test
   public void isWellFormedCatalogueItemRegistrationResponseMessage() throws IOException, SAXException, JAXBException, ParserConfigurationException {
      logger.debug("isWellFormedCatalogueItemRegistrationResponseMessage() - start");
      CatalogueItemRegistrationResponseMessage expected =
            generateCatalogueItemRegistrationResponseMessage();
      GdsnXmlFactory gdsnXmlFactory = new GdsnXmlFactory();
      byte[] xmlContent = gdsnXmlFactory.generate(expected);
      logger.debug("isWellFormedCatalogueItemRegistrationResponseMessage() - xml: {}", xmlContent);
      gdsnXmlFactory.isWellFormedGDSNXml(new ByteArrayInputStream(xmlContent));
      logger.debug("isWellFormedCatalogueItemRegistrationResponseMessage() - end");

   }


}
