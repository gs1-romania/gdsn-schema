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

public class CatalogueItemHierarchicalWithdrawalTest {

   private static final Logger logger = LoggerFactory.getLogger(CatalogueItemHierarchicalWithdrawalTest.class);

   private CatalogueItemHierarchicalWithdrawalMessage generateCatalogueItemHierarchicalWithdrawalMessage() {
      logger.debug("generateCatalogueItemHierarchicalWithdrawalMessage() - start");
      ObjectFactory of = new ObjectFactory();
      CatalogueItemHierarchicalWithdrawalMessage expected = of.createCatalogueItemHierarchicalWithdrawalMessage();
      {
         StandardBusinessDocumentHeader standardBusinessDocumentHeader = of.createStandardBusinessDocumentHeader();
         standardBusinessDocumentHeader.setHeaderVersion("1.0");
         standardBusinessDocumentHeader.setBusinessScope(null);
         DocumentIdentification documentIdentification = of.createDocumentIdentification();
         documentIdentification.setType("catalogueItemHierarchicalWithdrawal");
         documentIdentification.setTypeVersion("3.1");
         documentIdentification.setStandard("GS1");
         documentIdentification.setInstanceIdentifier("20071101");
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
         partyIdentificationType.setGln("1313131313131");
         entityIdentificationType.setContentOwner(partyIdentificationType);
         entityIdentificationType.setEntityIdentification("20071101");
         transactionType.setTransactionIdentification(entityIdentificationType);
         DocumentCommandType documentCommandType = of.createDocumentCommandType();
         DocumentCommandHeaderType documentCommandHeaderType = of.createDocumentCommandHeaderType();
         documentCommandHeaderType.setType(DocumentCommandEnumerationType.ADD);
         EntityIdentificationType docEntityIdentificationType = of.createEntityIdentificationType();
         PartyIdentificationType docPartyIdentificationType = of.createPartyIdentificationType();
         docPartyIdentificationType.setGln("8712345678913");
         docEntityIdentificationType.setContentOwner(docPartyIdentificationType);
         docEntityIdentificationType.setEntityIdentification("20071101");
         documentCommandHeaderType.setDocumentCommandIdentification(docEntityIdentificationType);
         documentCommandType.setDocumentCommandHeader(documentCommandHeaderType);

         CatalogueItemHierarchicalWithdrawalType catalogueItemHierarchicalWithdrawalType = of.createCatalogueItemHierarchicalWithdrawalType();
         catalogueItemHierarchicalWithdrawalType.setCreationDateTime(ZonedDateTime.parse("2011-03-11T11:00:00.000-05:00"));
         catalogueItemHierarchicalWithdrawalType.setDocumentStatusCode(DocumentStatusEnumerationType.ORIGINAL);


         EntityIdentificationType catEntityIdentificationType = of.createEntityIdentificationType();
         PartyIdentificationType catPartyIdentificationType = of.createPartyIdentificationType();
         catPartyIdentificationType.setGln("8712345678913");

         PartyIdentificationType partyIdentificationType3 = of.createPartyIdentificationType();
         partyIdentificationType3.setGln("8712345678913");
         catEntityIdentificationType.setContentOwner(partyIdentificationType3);
         catEntityIdentificationType.setEntityIdentification("20071101");
         catalogueItemHierarchicalWithdrawalType.setCatalogueItemHierarchicalWithdrawalIdentification(catEntityIdentificationType);
         catalogueItemHierarchicalWithdrawalType.setCatalogueItemStateCode(CatalogueItemStateEnumerationType.REGISTERED);

         HierarchyDeletionReasonCodeType hierarchyDeletionReasonCodeType = of.createHierarchyDeletionReasonCodeType();
         hierarchyDeletionReasonCodeType.setValue("HIERARCHY_LINK_CORRECTION");
         catalogueItemHierarchicalWithdrawalType.setHierarchyDeletionReasonCode(hierarchyDeletionReasonCodeType);

         CatalogueItemReferenceType catalogueItemReferenceType1 = of.createCatalogueItemReferenceType();
         catalogueItemReferenceType1.setDataSource("8712345678913");
         catalogueItemReferenceType1.setGtin("00074562000525");
         CountryCodeType countryCodeType1 = of.createCountryCodeType();
         countryCodeType1.setValue("528");
         catalogueItemReferenceType1.setTargetMarketCountryCode(countryCodeType1);
         catalogueItemHierarchicalWithdrawalType.setCatalogueItemReference(catalogueItemReferenceType1);

         PartyIdentificationType partyIdentificationType1 = of.createPartyIdentificationType();
         partyIdentificationType1.setGln("9712345678912");
         catalogueItemHierarchicalWithdrawalType.getDataRecipients().add(partyIdentificationType1);


         PartyIdentificationType partyIdentificationType2 = of.createPartyIdentificationType();
         partyIdentificationType2.setGln("8712345678912");
         catalogueItemHierarchicalWithdrawalType.setSourceDataPool(partyIdentificationType2);

         documentCommandType.getDocuments().add(of.createCatalogueItemHierarchicalWithdrawal(catalogueItemHierarchicalWithdrawalType));
         transactionType.setDocumentCommand(documentCommandType);
         expected.getTransactions().add(transactionType);
      }
      logger.debug("generateCatalogueItemHierarchicalWithdrawalMessage() - end");
      return expected;
   }

   @Test
   public void unmarshalCatalogueItemHierarchicalWithdrawalMessage() throws JAXBException {
      logger.debug("unmarshalCatalogueItemHierarchicalWithdrawalMessage() - start");
      InputStream resource = getClass().getResourceAsStream("/ro/gs1/gdsn/schema/CatalogueItemHierarchicalWithdrawal.xml");
      GdsnXmlFactory xmlFactory = new GdsnXmlFactory();
      Source source = new StreamSource(resource);
      CatalogueItemConfirmationMessage unmarshal = xmlFactory.readCatalogueItemConfirmation(source);

      CatalogueItemHierarchicalWithdrawalMessage generateCatalogueItemHierarchicalWithdrawalMessage =
            generateCatalogueItemHierarchicalWithdrawalMessage();
      /*
       * Unfortunately JAXBElement does not have equals implemented. We need to
       * verify one by one.
       */
      assertEquals(generateCatalogueItemHierarchicalWithdrawalMessage.getStandardBusinessDocumentHeader()
                  .getDocumentIdentification(),
            unmarshal.getStandardBusinessDocumentHeader()
                  .getDocumentIdentification());
      assertEquals(generateCatalogueItemHierarchicalWithdrawalMessage.getStandardBusinessDocumentHeader()
                  .getHeaderVersion(),
            unmarshal.getStandardBusinessDocumentHeader()
                  .getHeaderVersion());
      assertEquals(generateCatalogueItemHierarchicalWithdrawalMessage.getStandardBusinessDocumentHeader()
                  .getReceivers(),
            unmarshal.getStandardBusinessDocumentHeader()
                  .getReceivers());
      assertEquals(generateCatalogueItemHierarchicalWithdrawalMessage.getStandardBusinessDocumentHeader()
                  .getSenders(),
            unmarshal.getStandardBusinessDocumentHeader()
                  .getSenders());
      assertEquals(generateCatalogueItemHierarchicalWithdrawalMessage.getStandardBusinessDocumentHeader()
                  .getHeaderVersion(),
            unmarshal.getStandardBusinessDocumentHeader()
                  .getHeaderVersion());
      assertEquals(generateCatalogueItemHierarchicalWithdrawalMessage.getTransactions().get(0).getTransactionIdentification(),
            unmarshal.getTransactions().get(0).getTransactionIdentification());
      assertEquals(generateCatalogueItemHierarchicalWithdrawalMessage.getTransactions().get(0).getDocumentCommand().getDocumentCommandHeader(),
            unmarshal.getTransactions().get(0).getDocumentCommand().getDocumentCommandHeader());
      assertEquals(generateCatalogueItemHierarchicalWithdrawalMessage.getTransactions().get(0).getDocumentCommand().getDocuments().get(0).getValue(),
            unmarshal.getTransactions().get(0).getDocumentCommand().getDocuments().get(0).getValue());
      logger.debug("unmarshalCatalogueItemHierarchicalWithdrawalMessage() - end");
   }

   @Test
   public void marshalCatalogueItemHierarchicalWithdrawalMessage() throws JAXBException, IOException {
      try (InputStream compareTo =
                 getClass().getResourceAsStream("/ro/gs1/gdsn/schema/CatalogueItemHierarchicalWithdrawal.xml")) {
         logger.debug("marshalCatalogueItemHierarchicalWithdrawalMessage() - start");
         Assertions.assertNotNull(compareTo);
         String compareToString = new String(compareTo.readAllBytes());
         CatalogueItemHierarchicalWithdrawalMessage generateCatalogueItemHierarchicalWithdrawalMessage =
               generateCatalogueItemHierarchicalWithdrawalMessage();
         GdsnXmlFactory gdsnXmlFactory = new GdsnXmlFactory();
         String outString = new String(gdsnXmlFactory.generate(generateCatalogueItemHierarchicalWithdrawalMessage));
         logger.info("marshalCatalogueItemHierarchicalWithdrawalMessage() - xml: {}", outString);
         assertThat(outString, CompareMatcher.isSimilarTo(compareToString)
               .ignoreWhitespace()
               .ignoreComments()
               .ignoreElementContentWhitespace());
         logger.debug("marshalCatalogueItemHierarchicalWithdrawalMessage() - end");
      }
   }

   @Test
   public void isWellFormedCatalogueItemHierarchicalWithdrawalMessage() throws IOException, SAXException, JAXBException, ParserConfigurationException {
      logger.debug("isWellFormedCatalogueItemHierarchicalWithdrawalMessage() - start");
      CatalogueItemHierarchicalWithdrawalMessage expected =
            generateCatalogueItemHierarchicalWithdrawalMessage();
      GdsnXmlFactory gdsnXmlFactory = new GdsnXmlFactory();
      byte[] xmlContent = gdsnXmlFactory.generate(expected);
      logger.debug("isWellFormedCatalogueItemHierarchicalWithdrawalMessage() - xml: {}", xmlContent);
      gdsnXmlFactory.isWellFormedGDSNXml(new ByteArrayInputStream(xmlContent));
      logger.debug("isWellFormedCatalogueItemHierarchicalWithdrawalMessage() - end");

   }


}
