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

public class CatalogueItemPublicationTest {

   private static final Logger logger = LoggerFactory.getLogger(CatalogueItemPublicationTest.class);

   private CatalogueItemPublicationMessage generateCatalogueItemPublicationMessage() {
      logger.debug("generateCatalogueItemPublicationMessage() - start");
      ObjectFactory of = new ObjectFactory();
      CatalogueItemPublicationMessage expected = of.createCatalogueItemPublicationMessage();
      {
         StandardBusinessDocumentHeader standardBusinessDocumentHeader = of.createStandardBusinessDocumentHeader();
         standardBusinessDocumentHeader.setHeaderVersion("1.0");
         standardBusinessDocumentHeader.setBusinessScope(null);
         DocumentIdentification documentIdentification = of.createDocumentIdentification();
         documentIdentification.setType("catalogueItemPublication");
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

         CatalogueItemPublicationType catalogueItemPublicationType = of.createCatalogueItemPublicationType();
         catalogueItemPublicationType.setCreationDateTime(ZonedDateTime.parse("2011-03-11T11:00:00.000-05:00"));
         catalogueItemPublicationType.setDocumentStatusCode(DocumentStatusEnumerationType.ORIGINAL);


         EntityIdentificationType catEntityIdentificationType = of.createEntityIdentificationType();
         PartyIdentificationType partyIdentificationType3 = of.createPartyIdentificationType();
         partyIdentificationType3.setGln("8712345678913");
         catEntityIdentificationType.setContentOwner(partyIdentificationType3);
         catEntityIdentificationType.setEntityIdentification("20051101");
         catalogueItemPublicationType.setCatalogueItemPublicationIdentification(catEntityIdentificationType);
         TradeItemContextCodeType tradeItemContextCodeType = of.createTradeItemContextCodeType();
         tradeItemContextCodeType.setValue("2");
         catalogueItemPublicationType.getBusinessContextIdentifications().add(tradeItemContextCodeType);

         catalogueItemPublicationType.setPublishToGLN("8222345678913");

         CatalogueItemReferenceType catalogueItemReferenceType1 = of.createCatalogueItemReferenceType();
         catalogueItemReferenceType1.setDataSource("8712345678913");
         catalogueItemReferenceType1.setGtin("00074562000525");
         CountryCodeType countryCodeType1 = of.createCountryCodeType();
         countryCodeType1.setValue("124");
         catalogueItemReferenceType1.setTargetMarketCountryCode(countryCodeType1);
         catalogueItemPublicationType.setCatalogueItemReference(catalogueItemReferenceType1);

         documentCommandType.getDocuments().add(of.createCatalogueItemPublication(catalogueItemPublicationType));
         transactionType.setDocumentCommand(documentCommandType);
         expected.getTransactions().add(transactionType);
      }
      logger.debug("generateCatalogueItemPublicationMessage() - end");
      return expected;
   }

   @Test
   public void unmarshalCatalogueItemPublicationMessage() throws JAXBException {
      logger.debug("unmarshalCatalogueItemPublicationMessage() - start");
      InputStream resource = getClass().getResourceAsStream("/ro/gs1/gdsn/schema/CatalogueItemPublication.xml");
      GdsnXmlFactory xmlFactory = new GdsnXmlFactory();
      Source source = new StreamSource(resource);
      CatalogueItemConfirmationMessage unmarshal = xmlFactory.readCatalogueItemConfirmation(source);

      CatalogueItemPublicationMessage generateCatalogueItemPublicationMessage =
            generateCatalogueItemPublicationMessage();
      /*
       * Unfortunately JAXBElement does not have equals implemented. We need to
       * verify one by one.
       */
      assertEquals(generateCatalogueItemPublicationMessage.getStandardBusinessDocumentHeader()
                  .getDocumentIdentification(),
            unmarshal.getStandardBusinessDocumentHeader()
                  .getDocumentIdentification());
      assertEquals(generateCatalogueItemPublicationMessage.getStandardBusinessDocumentHeader()
                  .getHeaderVersion(),
            unmarshal.getStandardBusinessDocumentHeader()
                  .getHeaderVersion());
      assertEquals(generateCatalogueItemPublicationMessage.getStandardBusinessDocumentHeader()
                  .getReceivers(),
            unmarshal.getStandardBusinessDocumentHeader()
                  .getReceivers());
      assertEquals(generateCatalogueItemPublicationMessage.getStandardBusinessDocumentHeader()
                  .getSenders(),
            unmarshal.getStandardBusinessDocumentHeader()
                  .getSenders());
      assertEquals(generateCatalogueItemPublicationMessage.getStandardBusinessDocumentHeader()
                  .getHeaderVersion(),
            unmarshal.getStandardBusinessDocumentHeader()
                  .getHeaderVersion());
      assertEquals(generateCatalogueItemPublicationMessage.getTransactions().get(0).getTransactionIdentification(),
            unmarshal.getTransactions().get(0).getTransactionIdentification());
      assertEquals(generateCatalogueItemPublicationMessage.getTransactions().get(0).getDocumentCommand().getDocumentCommandHeader(),
            unmarshal.getTransactions().get(0).getDocumentCommand().getDocumentCommandHeader());
      assertEquals(generateCatalogueItemPublicationMessage.getTransactions().get(0).getDocumentCommand().getDocuments().get(0).getValue(),
            unmarshal.getTransactions().get(0).getDocumentCommand().getDocuments().get(0).getValue());
      logger.debug("unmarshalCatalogueItemPublicationMessage() - end");
   }

   @Test
   public void marshalCatalogueItemPublicationMessage() throws JAXBException, IOException {
      try (InputStream compareTo =
                 getClass().getResourceAsStream("/ro/gs1/gdsn/schema/CatalogueItemPublication.xml")) {
         logger.debug("marshalCatalogueItemPublicationMessage() - start");
         Assertions.assertNotNull(compareTo);
         String compareToString = new String(compareTo.readAllBytes());
         CatalogueItemPublicationMessage generateCatalogueItemPublicationMessage =
               generateCatalogueItemPublicationMessage();
         GdsnXmlFactory gdsnXmlFactory = new GdsnXmlFactory();
         String outString = new String(gdsnXmlFactory.generate(generateCatalogueItemPublicationMessage));
         logger.info("marshalCatalogueItemPublicationMessage() - xml: {}", outString);
         assertThat(outString, CompareMatcher.isSimilarTo(compareToString)
               .ignoreWhitespace()
               .ignoreComments()
               .ignoreElementContentWhitespace());
         logger.debug("marshalCatalogueItemPublicationMessage() - end");
      }
   }

   @Test
   public void isWellFormedCatalogueItemPublicationMessage() throws IOException, SAXException, JAXBException, ParserConfigurationException {
      logger.debug("isWellFormedCatalogueItemPublicationMessage() - start");
      CatalogueItemPublicationMessage expected =
            generateCatalogueItemPublicationMessage();
      GdsnXmlFactory gdsnXmlFactory = new GdsnXmlFactory();
      byte[] xmlContent = gdsnXmlFactory.generate(expected);
      logger.debug("isWellFormedCatalogueItemPublicationMessage() - xml: {}", xmlContent);
      gdsnXmlFactory.isWellFormedGDSNXml(new ByteArrayInputStream(xmlContent));
      logger.debug("isWellFormedCatalogueItemPublicationMessage() - end");

   }


}
