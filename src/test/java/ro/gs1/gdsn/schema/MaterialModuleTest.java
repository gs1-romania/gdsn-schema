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
import java.math.BigDecimal;
import java.time.ZonedDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MaterialModuleTest {

   private static final Logger logger = LoggerFactory.getLogger(MaterialModuleTest.class);

   private CatalogueItemNotificationMessage generateCatalogueItemNotificationWithMaterialModule() {
      logger.debug("generateCatalogueItemNotificationWithMaterialModule() - start");
      ObjectFactory of = new ObjectFactory();
      CatalogueItemNotificationMessage message = of.createCatalogueItemNotificationMessage();
      {
         StandardBusinessDocumentHeader standardBusinessDocumentHeader = of.createStandardBusinessDocumentHeader();
         standardBusinessDocumentHeader.setHeaderVersion("1.0");
         standardBusinessDocumentHeader.setBusinessScope(null);
         DocumentIdentification documentIdentification = of.createDocumentIdentification();
         documentIdentification.setType("catalogueItemNotification");
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
         standardBusinessDocumentHeader.getReceivers().add(receiver);
         standardBusinessDocumentHeader.getSenders().add(sender);
         message.setStandardBusinessDocumentHeader(standardBusinessDocumentHeader);
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
         docEntityIdentificationType.setContentOwner(partyIdentificationType);
         docEntityIdentificationType.setEntityIdentification("20051101");
         documentCommandHeaderType.setDocumentCommandIdentification(docEntityIdentificationType);
         documentCommandType.setDocumentCommandHeader(documentCommandHeaderType);

         CatalogueItemNotificationType catalogueItemNotificationType = of.createCatalogueItemNotificationType();
         catalogueItemNotificationType.setCreationDateTime(ZonedDateTime.parse("2011-03-11T11:00:00.000-05:00"));
         catalogueItemNotificationType.setDocumentStatusCode(DocumentStatusEnumerationType.ORIGINAL);
         EntityIdentificationType catEntityIdentificationType = of.createEntityIdentificationType();
         catEntityIdentificationType.setContentOwner(partyIdentificationType);
         catEntityIdentificationType.setEntityIdentification("20051101");
         catalogueItemNotificationType.setCatalogueItemNotificationIdentification(catEntityIdentificationType);
         catalogueItemNotificationType.setIsReload(false);

         CatalogueItemType catalogueItemType = of.createCatalogueItemType();
         catalogueItemType.setDataRecipient("9712345678912");
         catalogueItemType.setSourceDataPool("7712345678944");
         CatalogueItemStateType catalogueItemStateType = of.createCatalogueItemStateType();
         catalogueItemStateType.setCatalogueItemStateCode(CatalogueItemStateEnumerationType.REGISTERED);
         catalogueItemType.setCatalogueItemState(catalogueItemStateType);

         TradeItemType tradeItemType = of.createTradeItemType();
         tradeItemType.setGtin("00074562000525");
         TradeItemContextCodeType tradeItemContextCodeType = of.createTradeItemContextCodeType();
         tradeItemContextCodeType.setValue("1");
         tradeItemType.setContextIdentification(tradeItemContextCodeType);
         tradeItemType.setIsTradeItemABaseUnit(true);
         tradeItemType.setIsTradeItemAConsumerUnit(true);
         tradeItemType.setIsTradeItemADespatchUnit(false);
         tradeItemType.setIsTradeItemAnInvoiceUnit(false);
         tradeItemType.setIsTradeItemAnOrderableUnit(true);
         TradeItemUnitDescriptorCodeType tradeItemUnitDescriptorCodeType = of.createTradeItemUnitDescriptorCodeType();
         tradeItemUnitDescriptorCodeType.setValue("BASE_UNIT_OR_EACH");
         tradeItemType.setTradeItemUnitDescriptorCode(tradeItemUnitDescriptorCodeType);

         PartyInRoleType informationProvider = of.createPartyInRoleType();
         informationProvider.setGln("6612345678944");
         PartyRoleCodeType partyRoleCodeType = of.createPartyRoleCodeType();
         partyRoleCodeType.setValue("INFORMATION_PROVIDER");
         informationProvider.setPartyRoleCode(partyRoleCodeType);
         tradeItemType.setInformationProviderOfTradeItem(informationProvider);

         GDSNTradeItemClassificationType gdsnTradeItemClassificationType = of.createGDSNTradeItemClassificationType();
         gdsnTradeItemClassificationType.setGpcCategoryCode("10000028");
         gdsnTradeItemClassificationType.setGpcCategoryName("Clothing (General)");
         tradeItemType.setGdsnTradeItemClassification(gdsnTradeItemClassificationType);

         TargetMarketType targetMarketType = of.createTargetMarketType();
         CountryCodeType countryCodeType = of.createCountryCodeType();
         countryCodeType.setValue("528");
         targetMarketType.setTargetMarketCountryCode(countryCodeType);
         tradeItemType.getTargetMarkets().add(targetMarketType);

         TradeItemInformationType tradeItemInformationType = of.createTradeItemInformationType();
         ExtensionType extensionType = of.createExtensionType();

         {
            MaterialModule materialModule = of.createMaterialModule();

            MaterialType material = of.createMaterialType();
            Description500Type designationDescription = of.createDescription500Type();
            designationDescription.setValue("Cotton blend fabric");
            designationDescription.setLanguageCode("en");
            material.getTradeItemMaterialDesignationDescriptions().add(designationDescription);
            material.setIsDesignationIntendedForSingleUse(false);
            material.setCanMaterialsBeSeparatedManually(true);

            MaterialCompositionType cottonComposition = of.createMaterialCompositionType();
            cottonComposition.setMaterialCode("COT");
            Description70Type cottonContent = of.createDescription70Type();
            cottonContent.setValue("Cotton");
            cottonContent.setLanguageCode("en");
            cottonComposition.getMaterialContents().add(cottonContent);
            cottonComposition.setMaterialPercentage(BigDecimal.valueOf(80));
            material.getMaterialCompositions().add(cottonComposition);

            MaterialCompositionType polyesterComposition = of.createMaterialCompositionType();
            polyesterComposition.setMaterialCode("PES");
            Description70Type polyesterContent = of.createDescription70Type();
            polyesterContent.setValue("Polyester");
            polyesterContent.setLanguageCode("en");
            polyesterComposition.getMaterialContents().add(polyesterContent);
            polyesterComposition.setMaterialPercentage(BigDecimal.valueOf(20));
            material.getMaterialCompositions().add(polyesterComposition);

            materialModule.getMaterials().add(material);
            extensionType.getAnies().add(materialModule);
         }

         tradeItemInformationType.setExtension(extensionType);
         tradeItemType.getTradeItemInformations().add(tradeItemInformationType);
         TradeItemSynchronisationDatesType tradeItemSynchronisationDatesType = of.createTradeItemSynchronisationDatesType();
         tradeItemSynchronisationDatesType.setLastChangeDateTime(ZonedDateTime.parse("2013-01-10T12:00:01.000-05:00"));
         tradeItemSynchronisationDatesType.setEffectiveDateTime(ZonedDateTime.parse("2013-02-10T12:00:01.000-05:00"));
         tradeItemType.setTradeItemSynchronisationDates(tradeItemSynchronisationDatesType);

         catalogueItemType.setTradeItem(tradeItemType);
         catalogueItemNotificationType.setCatalogueItem(catalogueItemType);
         documentCommandType.getDocuments().add(of.createCatalogueItemNotification(catalogueItemNotificationType));
         transactionType.setDocumentCommand(documentCommandType);
         message.getTransactions().add(transactionType);
      }
      logger.debug("generateCatalogueItemNotificationWithMaterialModule() - end");
      return message;
   }

   @Test
   public void unmarshalCatalogueItemNotificationWithMaterialModule() throws JAXBException {
      logger.debug("unmarshalCatalogueItemNotificationWithMaterialModule() - start");
      InputStream resource = getClass().getResourceAsStream("/ro/gs1/gdsn/schema/MaterialModule.xml");
      GdsnXmlFactory xmlFactory = new GdsnXmlFactory();
      Source source = new StreamSource(resource);
      CatalogueItemNotificationMessage unmarshal = xmlFactory.readCatalogueItemNotification(source);
      CatalogueItemNotificationMessage expected = generateCatalogueItemNotificationWithMaterialModule();
      assertEquals(expected.getStandardBusinessDocumentHeader().getDocumentIdentification(),
            unmarshal.getStandardBusinessDocumentHeader().getDocumentIdentification());
      assertEquals(expected.getStandardBusinessDocumentHeader().getReceivers(),
            unmarshal.getStandardBusinessDocumentHeader().getReceivers());
      assertEquals(expected.getStandardBusinessDocumentHeader().getSenders(),
            unmarshal.getStandardBusinessDocumentHeader().getSenders());
      assertEquals(expected.getTransactions().get(0).getTransactionIdentification(),
            unmarshal.getTransactions().get(0).getTransactionIdentification());
      CatalogueItemNotificationType cin = (CatalogueItemNotificationType) unmarshal.getTransactions().get(0)
            .getDocumentCommand().getDocuments().get(0).getValue();
      assertEquals(1, cin.getCatalogueItem().getTradeItem().getTradeItemInformations().get(0)
            .getExtension().getAnies().size());
      logger.debug("unmarshalCatalogueItemNotificationWithMaterialModule() - end");
   }

   @Test
   public void marshalCatalogueItemNotificationWithMaterialModule() throws JAXBException, IOException {
      try (InputStream compareTo = getClass().getResourceAsStream("/ro/gs1/gdsn/schema/MaterialModule.xml")) {
         logger.debug("marshalCatalogueItemNotificationWithMaterialModule() - start");
         Assertions.assertNotNull(compareTo);
         String compareToString = new String(compareTo.readAllBytes());
         GdsnXmlFactory gdsnXmlFactory = new GdsnXmlFactory();
         String outString = new String(gdsnXmlFactory.generate(generateCatalogueItemNotificationWithMaterialModule()));
         logger.info("marshalCatalogueItemNotificationWithMaterialModule() - xml: {}", outString);
         assertThat(outString, CompareMatcher.isSimilarTo(compareToString)
               .ignoreWhitespace()
               .ignoreComments()
               .ignoreElementContentWhitespace());
         logger.debug("marshalCatalogueItemNotificationWithMaterialModule() - end");
      }
   }

   @Test
   public void isWellFormedCatalogueItemNotificationWithMaterialModule()
         throws IOException, SAXException, JAXBException, ParserConfigurationException {
      logger.debug("isWellFormedCatalogueItemNotificationWithMaterialModule() - start");
      GdsnXmlFactory gdsnXmlFactory = new GdsnXmlFactory();
      byte[] xmlContent = gdsnXmlFactory.generate(generateCatalogueItemNotificationWithMaterialModule());
      logger.debug("isWellFormedCatalogueItemNotificationWithMaterialModule() - xml: {}", new String(xmlContent));
      gdsnXmlFactory.isWellFormedGDSNXml(new ByteArrayInputStream(xmlContent));
      logger.debug("isWellFormedCatalogueItemNotificationWithMaterialModule() - end");
   }

}
