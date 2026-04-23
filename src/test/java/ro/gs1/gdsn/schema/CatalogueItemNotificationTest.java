package ro.gs1.gdsn.schema;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;
import org.xmlunit.matchers.CompareMatcher;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import ro.gs1.gdsn.BasicExtensionType;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.ZonedDateTime;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CatalogueItemNotificationTest {

   private static final Logger logger = LoggerFactory.getLogger(CatalogueItemNotificationTest.class);

   private CatalogueItemNotificationMessage generateCatalogueItemNotificationMessage() {
      logger.debug("generateCatalogueItemNotificationMessage() - start");
      ObjectFactory of = new ObjectFactory();
      CatalogueItemNotificationMessage expected = of.createCatalogueItemNotificationMessage();
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
         CatalogueItemNotificationType catalogueItemNotificationType = of.createCatalogueItemNotificationType();
         catalogueItemNotificationType.setCreationDateTime(ZonedDateTime.parse("2011-03-11T11:00:00.000-05:00"));
         catalogueItemNotificationType.setDocumentStatusCode(DocumentStatusEnumerationType.ORIGINAL);
         EntityIdentificationType catEntityIdentificationType = of.createEntityIdentificationType();
         PartyIdentificationType catPartyIdentificationType = of.createPartyIdentificationType();
         catPartyIdentificationType.setGln("8712345678913");
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
         PartyInRoleType partyInRoleType = of.createPartyInRoleType();
         partyInRoleType.setGln("6612345678944");
         partyInRoleType.setPartyName("Food Service Distributor");
         PartyRoleCodeType partyRoleCodeType = of.createPartyRoleCodeType();
         partyRoleCodeType.setValue("INFORMATION_PROVIDER");
         partyInRoleType.setPartyRoleCode(partyRoleCodeType);
         tradeItemType.setInformationProviderOfTradeItem(partyInRoleType);
         PartyInRoleType partyInRoleType2 = of.createPartyInRoleType();
         partyInRoleType2.setGln("6612345678944");
         partyInRoleType2.setPartyName("Camp");
         tradeItemType.getManufacturerOfTradeItems()
               .add(partyInRoleType2);
         GDSNTradeItemClassificationType gdsnTradeItemClassificationType = of.createGDSNTradeItemClassificationType();
         gdsnTradeItemClassificationType.setGpcCategoryCode("10000028");
         gdsnTradeItemClassificationType.setGpcCategoryName("Cheese/Cheese Substitutes (Perishable)");
         tradeItemType.setGdsnTradeItemClassification(gdsnTradeItemClassificationType);
         TargetMarketType targetMarketType = of.createTargetMarketType();
         CountryCodeType countryCodeType = of.createCountryCodeType();
         countryCodeType.setValue("124");
         targetMarketType.setTargetMarketCountryCode(countryCodeType);
         tradeItemType.getTargetMarkets()
               .add(targetMarketType);
         TradeItemContactInformationType tradeItemContactInformationType = of.createTradeItemContactInformationType();
         tradeItemContactInformationType.setGln("8712345678913");
         ContactTypeCodeType contactTypeCodeType = of.createContactTypeCodeType();
         contactTypeCodeType.setValue("CYC");
         tradeItemContactInformationType.setContactTypeCode(contactTypeCodeType);
         TradeItemContactInformationType tradeItemContactInformationType2 = of.createTradeItemContactInformationType();
         tradeItemContactInformationType2.setGln("8712345678913");
         ContactTypeCodeType contactTypeCodeType2 = of.createContactTypeCodeType();
         contactTypeCodeType2.setValue("BVP");
         tradeItemContactInformationType2.setContactTypeCode(contactTypeCodeType2);
         tradeItemType.getTradeItemContactInformations()
               .add(tradeItemContactInformationType);
         tradeItemType.getTradeItemContactInformations()
               .add(tradeItemContactInformationType2);
         TradeItemInformationType tradeItemInformationType = of.createTradeItemInformationType();
         tradeItemInformationType.setConsumerProductVariantStartEffectiveDateTime(ZonedDateTime.parse("2013-01-10T12:00:01.000-05:00"));
         ExtensionType extensionType = of.createExtensionType();
         {
            FoodAndBeverageIngredientModule foodAndBeverageIngredientModule = of
                  .createFoodAndBeverageIngredientModule();
            AdditiveInformationType additiveInformationType = of.createAdditiveInformationType();
            additiveInformationType.setAdditiveName("Sodium Nitrate");
            LevelOfContainmentCodeType levelOfContainmentCodeType = of.createLevelOfContainmentCodeType();
            levelOfContainmentCodeType.setValue("CONTAINS");
            additiveInformationType.setLevelOfContainmentCode(levelOfContainmentCodeType);
            foodAndBeverageIngredientModule.getAdditiveInformations()
                  .add(additiveInformationType);
            FoodAndBeverageIngredientType foodAndBeverageIngredientType = of.createFoodAndBeverageIngredientType();
            foodAndBeverageIngredientType.setIngredientSequence("1");
            foodAndBeverageIngredientType.setIngredientContentPercentage(BigDecimal.valueOf(80.00)
                  .setScale(2, RoundingMode.HALF_DOWN));
            Description70Type description70Type = of.createDescription70Type();
            description70Type.setValue("Milk");
            description70Type.setLanguageCode("en");
            foodAndBeverageIngredientType.getIngredientNames()
                  .add(description70Type);
            foodAndBeverageIngredientModule.getFoodAndBeverageIngredients()
                  .add(foodAndBeverageIngredientType);
            extensionType.getAnies()
                  .add(foodAndBeverageIngredientModule);
         }
         {
            DietInformationModule dietInformationModule = of.createDietInformationModule();
            DietInformationType dietInformationType = of.createDietInformationType();
            Description70Type description70Type = of.createDescription70Type();
            description70Type.setValue(" Suitable for Gluten Free Diets ");
            description70Type.setLanguageCode("en");
            dietInformationType.getDietTypeDescriptions()
                  .add(description70Type);
            DietTypeInformationType dietTypeInformationType = of.createDietTypeInformationType();
            DietTypeCodeType dietTypeCodeType = of.createDietTypeCodeType();
            dietTypeCodeType.setValue("FREE_FROM_GLUTEN");
            dietTypeInformationType.setDietTypeCode(dietTypeCodeType);
            dietInformationType.getDietTypeInformations()
                  .add(dietTypeInformationType);
            dietInformationModule.setDietInformation(dietInformationType);
            extensionType.getAnies()
                  .add(dietInformationModule);
         }
         {
            FarmingAndProcessingInformationModule farmingAndProcessingInformationModule = of
                  .createFarmingAndProcessingInformationModule();
            farmingAndProcessingInformationModule.setRawMaterialIrradiatedCode(NonBinaryLogicEnumerationType.FALSE);
            OrganicInformationType organicInformationType = of.createOrganicInformationType();
            OrganicClaimType organicClaimType = of.createOrganicClaimType();
            OrganicClaimAgencyCodeType organicClaimAgencyCodeType = of.createOrganicClaimAgencyCodeType();
            organicClaimAgencyCodeType.setValue("999");
            organicClaimType.getOrganicClaimAgencyCodes()
                  .add(organicClaimAgencyCodeType);
            organicClaimType.setOrganicPercentClaim(BigDecimal.valueOf(100));
            OrganicTradeItemCodeType organicTradeItemCodeType = of.createOrganicTradeItemCodeType();
            organicTradeItemCodeType.setValue("1");
            organicClaimType.setOrganicTradeItemCode(organicTradeItemCodeType);
            organicInformationType.getOrganicClaims()
                  .add(organicClaimType);
            farmingAndProcessingInformationModule.setTradeItemOrganicInformation(organicInformationType);
            FarmingAndProcessingInformationType farmingAndProcessingInformationType = of
                  .createFarmingAndProcessingInformationType();
            PreservationTechniqueTypeCodeType preservationTechniqueTypeCodeType = of
                  .createPreservationTechniqueTypeCodeType();
            preservationTechniqueTypeCodeType.setValue("FREEZING");
            farmingAndProcessingInformationType.getPreservationTechniqueCodes()
                  .add(preservationTechniqueTypeCodeType);
            farmingAndProcessingInformationModule.setTradeItemFarmingAndProcessing(farmingAndProcessingInformationType);
            extensionType.getAnies()
                  .add(farmingAndProcessingInformationModule);
         }
         {
            DairyFishMeatPoultryItemModule dairyFishMeatPoultryItemModule = of.createDairyFishMeatPoultryItemModule();
            DairyFishMeatPoultryInformationType dairyFishMeatPoultryInformationType = of
                  .createDairyFishMeatPoultryInformationType();
            dairyFishMeatPoultryInformationType.setFatInMilkContent(BigDecimal.valueOf(10.00)
                  .setScale(2, RoundingMode.HALF_DOWN));
            dairyFishMeatPoultryInformationType.setIsHomogenised(NonBinaryLogicEnumerationType.TRUE);
            dairyFishMeatPoultryItemModule.setDairyFishMeatPoultryInformation(dairyFishMeatPoultryInformationType);
            extensionType.getAnies()
                  .add(dairyFishMeatPoultryItemModule);
         }
         {
            FoodAndBeveragePreparationServingModule foodAndBeveragePreparationServingModule = of
                  .createFoodAndBeveragePreparationServingModule();
            PreparationTypeCodeType preparationTypeCodeType = of.createPreparationTypeCodeType();
            preparationTypeCodeType.setValue("UNPREPARED");
            foodAndBeveragePreparationServingModule.getManufacturerPreparationTypeCodes()
                  .add(preparationTypeCodeType);
            ServingQuantityInformationType servingQuantityInformationType = of.createServingQuantityInformationType();
            servingQuantityInformationType.setNumberOfServingsPerPackage(BigDecimal.valueOf(6));
            MeasurementPrecisionCodeType measurementPrecisionCodeType = of.createMeasurementPrecisionCodeType();
            measurementPrecisionCodeType.setValue("APPROXIMATELY");
            servingQuantityInformationType
                  .setNumberOfServingsPerPackageMeasurementPrecisionCode(measurementPrecisionCodeType);
            foodAndBeveragePreparationServingModule.setServingQuantityInformation(servingQuantityInformationType);
            PreparationServingType preparationServingType = of.createPreparationServingType();
            preparationServingType.setConvenienceLevelPercent(BigDecimal.valueOf(100));
            Description1000Type description1000Type = of.createDescription1000Type();
            description1000Type.setValue("Do Not Freeze");
            description1000Type.setLanguageCode("en");
            preparationServingType.getPreparationConsumptionPrecautions()
                  .add(description1000Type);
            PreparationTypeCodeType preparationTypeCodeType2 = of.createPreparationTypeCodeType();
            preparationTypeCodeType2.setValue("BOIL");
            preparationServingType.setPreparationTypeCode(preparationTypeCodeType2);
            Description1000Type description1000Type2 = of.createDescription1000Type();
            description1000Type2.setValue(
                  "As a soup, or ingredient in many of your favourite recipes. Add simple garnishes to create " +
                        "signature soups");
            description1000Type2.setLanguageCode("en");
            preparationServingType.getServingSuggestions()
                  .add(description1000Type2);
            foodAndBeveragePreparationServingModule.getPreparationServings()
                  .add(preparationServingType);
            extensionType.getAnies()
                  .add(foodAndBeveragePreparationServingModule);
         }
         {
            HealthRelatedInformationModule healthRelatedInformationModule = of.createHealthRelatedInformationModule();
            HealthRelatedInformationType healthRelatedInformationType = of.createHealthRelatedInformationType();
            NutritionalLabelTypeCodeType nutritionalLabelTypeCodeType = of.createNutritionalLabelTypeCodeType();
            nutritionalLabelTypeCodeType.setValue("1");
            healthRelatedInformationType.getNutritionalLabelTypeCodes()
                  .add(nutritionalLabelTypeCodeType);
            healthRelatedInformationModule.setHealthRelatedInformation(healthRelatedInformationType);
            extensionType.getAnies()
                  .add(healthRelatedInformationModule);
         }
         {
            NutritionalInformationModule nutritionalInformationModule = of.createNutritionalInformationModule();
            NutrientHeaderType nutrientHeaderType = of.createNutrientHeaderType();
            PreparationTypeCodeType preparationTypeCodeType = of.createPreparationTypeCodeType();
            preparationTypeCodeType.setValue("AS_DRAINED");
            nutrientHeaderType.setPreparationStateCode(preparationTypeCodeType);
            MeasurementType measurementType = of.createMeasurementType();
            measurementType.setValue(BigDecimal.valueOf(100));
            measurementType.setMeasurementUnitCode("GRM");
            nutrientHeaderType.setNutrientBasisQuantity(measurementType);
            {
               NutrientDetailType nutrientDetailType = of.createNutrientDetailType();
               NutrientTypeCodeType nutrientTypeCodeType = of.createNutrientTypeCodeType();
               nutrientTypeCodeType.setValue("FIB-");
               nutrientDetailType.setNutrientTypeCode(nutrientTypeCodeType);
               nutrientDetailType.setDailyValueIntakePercent(BigDecimal.valueOf(20));
               MeasurementPrecisionCodeType measurementPrecisionCodeType = of.createMeasurementPrecisionCodeType();
               measurementPrecisionCodeType.setValue("APPROXIMATELY");
               nutrientDetailType.setMeasurementPrecisionCode(measurementPrecisionCodeType);
               MeasurementType measurementType2 = of.createMeasurementType();
               measurementType2.setValue(BigDecimal.valueOf(4.5));
               measurementType2.setMeasurementUnitCode("GRM");
               nutrientDetailType.getQuantityContaineds()
                     .add(measurementType2);
               nutrientHeaderType.getNutrientDetails()
                     .add(nutrientDetailType);
            }
            {
               NutrientDetailType nutrientDetailType = of.createNutrientDetailType();
               NutrientTypeCodeType nutrientTypeCodeType = of.createNutrientTypeCodeType();
               nutrientTypeCodeType.setValue("ENERC");
               nutrientDetailType.setNutrientTypeCode(nutrientTypeCodeType);
               nutrientDetailType.setDailyValueIntakePercent(BigDecimal.valueOf(10));
               MeasurementPrecisionCodeType measurementPrecisionCodeType = of.createMeasurementPrecisionCodeType();
               measurementPrecisionCodeType.setValue("APPROXIMATELY");
               nutrientDetailType.setMeasurementPrecisionCode(measurementPrecisionCodeType);
               MeasurementType measurementType2 = of.createMeasurementType();
               measurementType2.setValue(BigDecimal.valueOf(96));
               measurementType2.setMeasurementUnitCode("E14");
               nutrientDetailType.getQuantityContaineds()
                     .add(measurementType2);
               nutrientHeaderType.getNutrientDetails()
                     .add(nutrientDetailType);
            }
            {
               NutrientDetailType nutrientDetailType = of.createNutrientDetailType();
               NutrientTypeCodeType nutrientTypeCodeType = of.createNutrientTypeCodeType();
               nutrientTypeCodeType.setValue("FAT");
               nutrientDetailType.setNutrientTypeCode(nutrientTypeCodeType);
               nutrientDetailType.setDailyValueIntakePercent(BigDecimal.valueOf(10));
               MeasurementPrecisionCodeType measurementPrecisionCodeType = of.createMeasurementPrecisionCodeType();
               measurementPrecisionCodeType.setValue("APPROXIMATELY");
               nutrientDetailType.setMeasurementPrecisionCode(measurementPrecisionCodeType);
               MeasurementType measurementType2 = of.createMeasurementType();
               measurementType2.setValue(BigDecimal.valueOf(0.3));
               measurementType2.setMeasurementUnitCode("GRM");
               nutrientDetailType.getQuantityContaineds()
                     .add(measurementType2);
               nutrientHeaderType.getNutrientDetails()
                     .add(nutrientDetailType);
            }
            nutritionalInformationModule.getNutrientHeaders()
                  .add(nutrientHeaderType);
            extensionType.getAnies()
                  .add(nutritionalInformationModule);
         }
         {
            PackagingInformationModule packagingInformationModule = of.createPackagingInformationModule();
            PackagingType packagingType = of.createPackagingType();
            packagingType.setIsPackagingSuitableForAirShipment(NonBinaryLogicEnumerationType.FALSE);
            PackagingShapeCodeType packagingShapeCodeType = of.createPackagingShapeCodeType();
            packagingShapeCodeType.setValue("RECTANGULAR");
            packagingType.setPackagingShapeCode(packagingShapeCodeType);
            PackageTypeCodeType packageTypeCodeType = of.createPackageTypeCodeType();
            packageTypeCodeType.setValue("BG");
            packagingType.setPackagingTypeCode(packageTypeCodeType);
            Description200Type description200Type = of.createDescription200Type();
            description200Type.setLanguageCode("en");
            description200Type.setValue("Bag");
            packagingType.getPackagingTypeDescriptions()
                  .add(description200Type);
            packagingInformationModule.getPackagings()
                  .add(packagingType);
            extensionType.getAnies()
                  .add(packagingInformationModule);
         }
         {
            PackagingMarkingModule packagingMarkingModule = of.createPackagingMarkingModule();
            PackagingMarkingType packagingMarkingType = of.createPackagingMarkingType();
            packagingMarkingType.setHasBatchNumber(false);
            packagingMarkingType.setIsNetContentDeclarationIndicated(true);
            packagingMarkingType.setIsPackagingMarkedReturnable(false);
            packagingMarkingType.setIsPriceOnPack(false);
            PackagingMarkedLabelAccreditationCodeType packagingMarkedLabelAccreditationCodeType = of
                  .createPackagingMarkedLabelAccreditationCodeType();
            packagingMarkedLabelAccreditationCodeType.setValue("UNIQUELY_FINNISH");
            packagingMarkingType.getPackagingMarkedLabelAccreditationCodes()
                  .add(packagingMarkedLabelAccreditationCodeType);
            packagingMarkingModule.setPackagingMarking(packagingMarkingType);
            extensionType.getAnies()
                  .add(packagingMarkingModule);
         }
         {
            TradeItemDescriptionModule tradeItemDescriptionModule = of.createTradeItemDescriptionModule();
            TradeItemDescriptionInformationType tradeItemDescriptionInformationType = of
                  .createTradeItemDescriptionInformationType();
            Description2000Type description2000Type = of.createDescription2000Type();
            description2000Type.setLanguageCode("en");
            description2000Type
                  .setValue("An Italian tradition full of diced carrots, kidney beans, peas and penne pasta");
            tradeItemDescriptionInformationType.getAdditionalTradeItemDescriptions()
                  .add(description2000Type);
            Description40Type description35Type = of.createDescription40Type();
            description35Type.setValue("Soup");
            description35Type.setLanguageCode("en");
            tradeItemDescriptionInformationType.getDescriptionShorts()
                  .add(description35Type);
            Description35Type description35Type2 = of.createDescription35Type();
            description35Type2.setValue("Soup");
            description35Type2.setLanguageCode("en");
            tradeItemDescriptionInformationType.getFunctionalNames()
                  .add(description35Type2);
            tradeItemDescriptionInformationType.setProductRange("Healthy Options  ");
            Description200Type description200Type = of.createDescription200Type();
            description200Type.setValue("Camp's Frozen Soup Homestyle Minestrone");
            description200Type.setLanguageCode("en");
            tradeItemDescriptionInformationType.getTradeItemDescriptions()
                  .add(description200Type);
            Description500Type description500Type = of.createDescription500Type();
            description500Type.setValue("Homestyle Minestrone");
            description500Type.setLanguageCode("en");
            tradeItemDescriptionInformationType.getVariantDescriptions()
                  .add(description500Type);
            BrandNameInformationType brandNameInformationType = of.createBrandNameInformationType();
            brandNameInformationType.setBrandName("Camp's Frozen");
            tradeItemDescriptionInformationType.setBrandNameInformation(brandNameInformationType);
            tradeItemDescriptionModule.setTradeItemDescriptionInformation(tradeItemDescriptionInformationType);
            extensionType.getAnies()
                  .add(tradeItemDescriptionModule);
         }
         {
            MarketingInformationModule marketingInformationModule = of.createMarketingInformationModule();
            MarketingInformationType marketingInformationType = of.createMarketingInformationType();
            FormattedDescription500Type formattedDescription250Type = of.createFormattedDescription500Type();
            formattedDescription250Type.setValue(
                  "Consistent fresh-frozen quality featuring traditional and on-trend varieties. Easy to " +
                        "prepare-simply add water. Less waste-use as little as half a tray. More than forty varieties" +
                        " to meet customer taste preferences.");
            formattedDescription250Type.setLanguageCode("en");
            marketingInformationType.getTradeItemFeatureBenefits()
                  .add(formattedDescription250Type);
            marketingInformationModule.setMarketingInformation(marketingInformationType);
            extensionType.getAnies()
                  .add(marketingInformationModule);
         }
         {
            TradeItemMeasurementsModule tradeItemMeasurementsModule = of.createTradeItemMeasurementsModule();
            TradeItemMeasurementsType tradeItemMeasurementsType = of.createTradeItemMeasurementsType();
            MeasurementType measurementType = of.createMeasurementType();
            measurementType.setMeasurementUnitCode("CMT");
            measurementType.setValue(BigDecimal.valueOf(30));
            tradeItemMeasurementsType.setDepth(measurementType);
            MeasurementType measurementType2 = of.createMeasurementType();
            measurementType2.setMeasurementUnitCode("CMT");
            measurementType2.setValue(BigDecimal.valueOf(15));
            tradeItemMeasurementsType.setDiameter(measurementType2);
            MeasurementType measurementType3 = of.createMeasurementType();
            measurementType3.setMeasurementUnitCode("CMT");
            measurementType3.setValue(BigDecimal.valueOf(20));
            tradeItemMeasurementsType.setHeight(measurementType3);
            MeasurementType measurementType4 = of.createMeasurementType();
            measurementType4.setMeasurementUnitCode("GRM");
            measurementType4.setValue(BigDecimal.valueOf(30));
            tradeItemMeasurementsType.getNetContents()
                  .add(measurementType4);
            MeasurementType measurementType5 = of.createMeasurementType();
            measurementType5.setMeasurementUnitCode("CMT");
            measurementType5.setValue(BigDecimal.valueOf(50));
            tradeItemMeasurementsType.setWidth(measurementType5);
            tradeItemMeasurementsModule.setTradeItemMeasurements(tradeItemMeasurementsType);
            extensionType.getAnies()
                  .add(tradeItemMeasurementsModule);
         }
         {
            VariableTradeItemInformationModule variableTradeItemInformationModule = of
                  .createVariableTradeItemInformationModule();
            VariableTradeItemInformationType variableTradeItemInformationType = of
                  .createVariableTradeItemInformationType();
            variableTradeItemInformationType.setIsTradeItemAVariableUnit(false);
            variableTradeItemInformationModule.setVariableTradeItemInformation(variableTradeItemInformationType);
            extensionType.getAnies()
                  .add(variableTradeItemInformationModule);
         }
         tradeItemInformationType.setExtension(extensionType);
         tradeItemType.getTradeItemInformations()
               .add(tradeItemInformationType);
         TradeItemSynchronisationDatesType tradeItemSynchronisationDatesType = of
               .createTradeItemSynchronisationDatesType();
         tradeItemSynchronisationDatesType.setLastChangeDateTime(ZonedDateTime.parse("2013-01-10T12:00:01.000-05:00"));
         tradeItemSynchronisationDatesType.setEffectiveDateTime(ZonedDateTime.parse("2013-02-10T12:00:01.000-05:00"));
         tradeItemType.setTradeItemSynchronisationDates(tradeItemSynchronisationDatesType);
         catalogueItemType.setTradeItem(tradeItemType);
         catalogueItemNotificationType.setCatalogueItem(catalogueItemType);
         documentCommandType.getDocuments()
               .add(of.createCatalogueItemNotification(catalogueItemNotificationType));
         transactionType.setDocumentCommand(documentCommandType);
         expected.getTransactions()
               .add(transactionType);
      }
      logger.debug("generateCatalogueItemNotificationMessage() - end");
      return expected;
   }

   @Test
   public void unmarshalCatalogueItemNotificationMessage() throws JAXBException {
      logger.debug("unmarshalCatalogueItemNotificationMessage() - start");
      InputStream resource = getClass().getResourceAsStream("/ro/gs1/gdsn/schema/CatalogueItemNotification.xml");
      final JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
      final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
      Source source = new StreamSource(resource);
      CatalogueItemNotificationMessage unmarshal = unmarshaller
            .unmarshal(source, CatalogueItemNotificationMessage.class)
            .getValue();
      CatalogueItemNotificationMessage generateCatalogueItemNotificationMessage =
            generateCatalogueItemNotificationMessage();
      /*
       * Unfortunately JAXBElement does not have equals implemented. We need to
       * verify one by one.
       */
      assertEquals(generateCatalogueItemNotificationMessage.getStandardBusinessDocumentHeader()
                  .getDocumentIdentification(),
            unmarshal.getStandardBusinessDocumentHeader()
                  .getDocumentIdentification());
      assertEquals(generateCatalogueItemNotificationMessage.getStandardBusinessDocumentHeader()
                  .getHeaderVersion(),
            unmarshal.getStandardBusinessDocumentHeader()
                  .getHeaderVersion());
      assertEquals(generateCatalogueItemNotificationMessage.getStandardBusinessDocumentHeader()
                  .getReceivers(),
            unmarshal.getStandardBusinessDocumentHeader()
                  .getReceivers());
      assertEquals(generateCatalogueItemNotificationMessage.getStandardBusinessDocumentHeader()
                  .getSenders(),
            unmarshal.getStandardBusinessDocumentHeader()
                  .getSenders());
      assertEquals(generateCatalogueItemNotificationMessage.getStandardBusinessDocumentHeader()
                  .getHeaderVersion(),
            unmarshal.getStandardBusinessDocumentHeader()
                  .getHeaderVersion());
      assertEquals(generateCatalogueItemNotificationMessage.getTransactions().get(0).getTransactionIdentification(),
            unmarshal.getTransactions().get(0).getTransactionIdentification());
      assertEquals(generateCatalogueItemNotificationMessage.getTransactions().get(0).getDocumentCommand().getDocumentCommandHeader(),
            unmarshal.getTransactions().get(0).getDocumentCommand().getDocumentCommandHeader());
      CatalogueItemNotificationType cin = (CatalogueItemNotificationType) unmarshal.getTransactions()
            .get(0).getDocumentCommand().getDocuments().get(0).getValue();
      List<BasicExtensionType> anies = cin.getCatalogueItem()
            .getTradeItem().getTradeItemInformations().get(0).getExtension().getAnies();
      assertEquals(13, anies.size(), "expected 13 extension modules after unmarshal");

      FoodAndBeverageIngredientModule ingredientModule =
            Assertions.assertInstanceOf(FoodAndBeverageIngredientModule.class, anies.get(0));
      assertEquals("Sodium Nitrate",
            ingredientModule.getAdditiveInformations().get(0).getAdditiveName());
      assertEquals("CONTAINS",
            ingredientModule.getAdditiveInformations().get(0).getLevelOfContainmentCode().getValue());
      assertEquals("Milk",
            ingredientModule.getFoodAndBeverageIngredients().get(0).getIngredientNames().get(0).getValue());

      DietInformationModule dietModule =
            Assertions.assertInstanceOf(DietInformationModule.class, anies.get(1));
      assertEquals("FREE_FROM_GLUTEN",
            dietModule.getDietInformation().getDietTypeInformations().get(0).getDietTypeCode().getValue());

      DairyFishMeatPoultryItemModule dairyModule =
            Assertions.assertInstanceOf(DairyFishMeatPoultryItemModule.class, anies.get(3));
      assertEquals(BigDecimal.valueOf(10.00).setScale(2, RoundingMode.HALF_DOWN),
            dairyModule.getDairyFishMeatPoultryInformation().getFatInMilkContent());
      assertEquals(NonBinaryLogicEnumerationType.TRUE,
            dairyModule.getDairyFishMeatPoultryInformation().getIsHomogenised());

      TradeItemDescriptionModule descriptionModule =
            Assertions.assertInstanceOf(TradeItemDescriptionModule.class, anies.get(9));
      assertEquals("Camp's Frozen",
            descriptionModule.getTradeItemDescriptionInformation().getBrandNameInformation().getBrandName());

      TradeItemMeasurementsModule measurementsModule =
            Assertions.assertInstanceOf(TradeItemMeasurementsModule.class, anies.get(11));
      assertEquals(BigDecimal.valueOf(30),
            measurementsModule.getTradeItemMeasurements().getDepth().getValue());
      assertEquals("CMT",
            measurementsModule.getTradeItemMeasurements().getDepth().getMeasurementUnitCode());
      assertEquals(BigDecimal.valueOf(50),
            measurementsModule.getTradeItemMeasurements().getWidth().getValue());

      VariableTradeItemInformationModule variableModule =
            Assertions.assertInstanceOf(VariableTradeItemInformationModule.class, anies.get(12));
      assertEquals(false,
            variableModule.getVariableTradeItemInformation().isIsTradeItemAVariableUnit());

      logger.debug("unmarshalCatalogueItemNotificationMessage() - end");
   }

   @Test
   public void marshalCatalogueItemNotificationMessage() throws JAXBException, IOException {
      try (InputStream compareTo =
                 getClass().getResourceAsStream("/ro/gs1/gdsn/schema/CatalogueItemNotification.xml")) {
         logger.debug("marshalCatalogueItemNotificationMessage() - start");
         Assertions.assertNotNull(compareTo);
         String compareToString = new String(compareTo.readAllBytes());
         CatalogueItemNotificationMessage generateCatalogueItemNotificationMessage =
               generateCatalogueItemNotificationMessage();
         GdsnXmlFactory gdsnXmlFactory = new GdsnXmlFactory();
         String outString = new String(gdsnXmlFactory.generate(generateCatalogueItemNotificationMessage));
         logger.info("marshalCatalogueItemNotificationMessage() - xml: {}", outString);
         assertThat(outString, CompareMatcher.isSimilarTo(compareToString)
               .ignoreWhitespace()
               .ignoreComments()
               .ignoreElementContentWhitespace());
         logger.debug("marshalCatalogueItemNotificationMessage() - end");
      }
   }

   @Test
   public void isWellFormedCatalogueItemNotificationMessage() throws IOException, SAXException, JAXBException, ParserConfigurationException {
      logger.debug("isWellFormedCatalogueItemNotificationMessage() - start");

      CatalogueItemNotificationMessage expected = generateCatalogueItemNotificationMessage();
      GdsnXmlFactory gdsnXmlFactory = new GdsnXmlFactory();
      byte[] xmlContent = gdsnXmlFactory.generate(expected);
      logger.debug("isWellFormedCatalogueItemNotificationMessage() - xml: {}", xmlContent);

      gdsnXmlFactory.isWellFormedGDSNXml(new ByteArrayInputStream(xmlContent));

      logger.debug("isWellFormedCatalogueItemNotificationMessage() - end");

   }

}
