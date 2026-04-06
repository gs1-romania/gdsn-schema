package ro.gs1.gdsn.schema;

import java.util.Arrays;

public enum GdsnNamespaces {
   duty_fee_tax_information("dutyFeeTaxInformationModule", "duty_fee_tax_information", "urn:gs1:gdsn:duty_fee_tax_information:xsd:3", "http://www.gs1globalregistry.net/3.1/schemas/gs1/gdsn/DutyFeeTaxInformationModule.xsd", true, false),
   packaging_marking("packagingMarkingModule", "packaging_marking", "urn:gs1:gdsn:packaging_marking:xsd:3", "http://www.gs1globalregistry.net/3.1/schemas/gs1/gdsn/PackagingMarkingModule.xsd", true, false),
   plumbing_hvac_pipe_information("plumbingHVACPipeInformationModule", "plumbing_hvac_pipe_information", "urn:gs1:gdsn:plumbing_hvac_pipe_information:xsd:3", "http://www.gs1globalregistry.net/3.1/schemas/gs1/gdsn/PlumbingHVACPipeInformationModule.xsd", true, false),
   trade_item_size("tradeItemSizeModule", "trade_item_size", "urn:gs1:gdsn:trade_item_size:xsd:3", "http://www.gs1globalregistry.net/3.1/schemas/gs1/gdsn/TradeItemSizeModule.xsd", true, false),
   catalogue_item_registration_response("catalogueItemRegistrationResponseMessage", "catalogue_item_registration_response", "urn:gs1:gdsn:catalogue_item_registration_response:xsd:3", "http://www.gs1globalregistry.net/3.1/schemas/gs1/gdsn/CatalogueItemRegistrationResponse.xsd", false, true),
   certification_information("certificationInformationModule", "certification_information", "urn:gs1:gdsn:certification_information:xsd:3", "http://www.gs1globalregistry.net/3.1/schemas/gs1/gdsn/CertificationInformationModule.xsd", true, false),
   product_information("productInformationModule", "product_information", "urn:gs1:gdsn:product_information:xsd:3", "http://www.gs1globalregistry.net/3.1/schemas/gs1/gdsn/ProductInformationModule.xsd", true, false),
   durable_goods_characteristics("durableGoodsCharacteristicsModule", "durable_goods_characteristics", "urn:gs1:gdsn:durable_goods_characteristics:xsd:3", "http://www.gs1globalregistry.net/3.1/schemas/gs1/gdsn/DurableGoodsCharacteristicsModule.xsd", true, false),
   chemical_regulation_information("chemicalRegulationInformationModule", "chemical_regulation_information", "urn:gs1:gdsn:chemical_regulation_information:xsd:3", "http://www.gs1globalregistry.net/3.1/schemas/gs1/gdsn/ChemicalRegulationInformationModule.xsd", true, false),
   dairy_fish_meat_poultry("dairyFishMeatPoultryItemModule", "dairy_fish_meat_poultry", "urn:gs1:gdsn:dairy_fish_meat_poultry:xsd:3", "http://www.gs1globalregistry.net/3.1/schemas/gs1/gdsn/DairyFishMeatPoultryItemModule.xsd", true, false),
   apparel_information("apparelInformationModule", "apparel_information", "urn:gs1:gdsn:apparel_information:xsd:3", "http://www.gs1globalregistry.net/3.1/schemas/gs1/gdsn/ApparelInformationModule.xsd", true, false),
   trade_item_description("tradeItemDescriptionModule", "trade_item_description", "urn:gs1:gdsn:trade_item_description:xsd:3", "http://www.gs1globalregistry.net/3.1/schemas/gs1/gdsn/TradeItemDescriptionModule.xsd", true, false),
   catalogue_item_notification("catalogueItemNotificationMessage", "catalogue_item_notification", "urn:gs1:gdsn:catalogue_item_notification:xsd:3", "http://www.gs1globalregistry.net/3.1/schemas/gs1/gdsn/CatalogueItemNotification.xsd", false, true),
   trade_item_measurements("tradeItemMeasurementsModule", "trade_item_measurements", "urn:gs1:gdsn:trade_item_measurements:xsd:3", "http://www.gs1globalregistry.net/3.1/schemas/gs1/gdsn/TradeItemMeasurementsModule.xsd", true, false),
   trade_item_lifespan("tradeItemLifespanModule", "trade_item_lifespan", "urn:gs1:gdsn:trade_item_lifespan:xsd:3", "http://www.gs1globalregistry.net/3.1/schemas/gs1/gdsn/TradeItemLifespanModule.xsd", true, false),
   referenced_file_detail_information("referencedFileDetailInformationModule", "referenced_file_detail_information", "urn:gs1:gdsn:referenced_file_detail_information:xsd:3", "http://www.gs1globalregistry.net/3.1/schemas/gs1/gdsn/ReferencedFileDetailInformationModule.xsd", true, false),
   alcohol_information("alcoholInformationModule", "alcohol_information", "urn:gs1:gdsn:alcohol_information:xsd:3", "http://www.gs1globalregistry.net/3.1/schemas/gs1/gdsn/AlcoholInformationModule.xsd", true, false),
   propellant_information("propellantInformationModule", "propellant_information", "urn:gs1:gdsn:propellant_information:xsd:3", "http://www.gs1globalregistry.net/3.1/schemas/gs1/gdsn/PropellantInformationModule.xsd", true, false),
   delivery_purchasing_information("deliveryPurchasingInformationModule", "delivery_purchasing_information", "urn:gs1:gdsn:delivery_purchasing_information:xsd:3", "http://www.gs1globalregistry.net/3.1/schemas/gs1/gdsn/DeliveryPurchasingInformationModule.xsd", true, false),
   gs1_response("gS1ResponseMessage", "gs1_response", "urn:gs1:gdsn:gs1_response:xsd:3", "http://www.gs1globalregistry.net/3.1/schemas/gs1/gdsn/GS1Response.xsd", false, true),
   packaging_sustainability("packagingSustainabilityModule", "packaging_sustainability", "urn:gs1:gdsn:packaging_sustainability:xsd:3", "http://www.gs1globalregistry.net/3.1/schemas/gs1/gdsn/PackagingSustainabilityModule.xsd", true, false),
   food_and_beverage_preparation_serving("foodAndBeveragePreparationServingModule", "food_and_beverage_preparation_serving", "urn:gs1:gdsn:food_and_beverage_preparation_serving:xsd:3", "http://www.gs1globalregistry.net/3.1/schemas/gs1/gdsn/FoodAndBeveragePreparationServingModule.xsd", true, false),
   packaging_information("packagingInformationModule", "packaging_information", "urn:gs1:gdsn:packaging_information:xsd:3", "http://www.gs1globalregistry.net/3.1/schemas/gs1/gdsn/PackagingInformationModule.xsd", true, false),
   request_for_catalogue_item_notification("requestForCatalogueItemNotificationMessage", "request_for_catalogue_item_notification", "urn:gs1:gdsn:request_for_catalogue_item_notification:xsd:3", "http://www.gs1globalregistry.net/3.1/schemas/gs1/gdsn/RequestForCatalogueItemNotification.xsd", false, true),
   food_and_beverage_ingredient("foodAndBeverageIngredientModule", "food_and_beverage_ingredient", "urn:gs1:gdsn:food_and_beverage_ingredient:xsd:3", "http://www.gs1globalregistry.net/3.1/schemas/gs1/gdsn/FoodAndBeverageIngredientModule.xsd", true, false),
   nutritional_information("nutritionalInformationModule", "nutritional_information", "urn:gs1:gdsn:nutritional_information:xsd:3", "http://www.gs1globalregistry.net/3.1/schemas/gs1/gdsn/NutritionalInformationModule.xsd", true, false),
   trade_item_disposal_information("tradeItemDisposalInformationModule", "trade_item_disposal_information", "urn:gs1:gdsn:trade_item_disposal_information:xsd:3", "http://www.gs1globalregistry.net/3.1/schemas/gs1/gdsn/TradeItemDisposalInformationModule.xsd", true, false),
   sustainability_module("sustainabilityModule", "sustainability_module", "urn:gs1:gdsn:sustainability_module:xsd:3", "http://www.gs1globalregistry.net/3.1/schemas/gs1/gdsn/SustainabilityModule.xsd", true, false),
   consumer_instructions("consumerInstructionsModule", "consumer_instructions", "urn:gs1:gdsn:consumer_instructions:xsd:3", "http://www.gs1globalregistry.net/3.1/schemas/gs1/gdsn/ConsumerInstructionsModule.xsd", true, false),
   medical_device_trade_item("medicalDeviceTradeItemModule", "medical_device_trade_item", "urn:gs1:gdsn:medical_device_trade_item:xsd:3", "http://www.gs1globalregistry.net/3.1/schemas/gs1/gdsn/MedicalDeviceTradeItemModule.xsd", true, false),
   video_display_device_information("videoDisplayDeviceInformationModule", "video_display_device_information", "urn:gs1:gdsn:video_display_device_information:xsd:3", "http://www.gs1globalregistry.net/3.1/schemas/gs1/gdsn/VideoDisplayDeviceInformationModule.xsd", true, false),
   trade_item_temperature_information("tradeItemTemperatureInformationModule", "trade_item_temperature_information", "urn:gs1:gdsn:trade_item_temperature_information:xsd:3", "http://www.gs1globalregistry.net/3.1/schemas/gs1/gdsn/TradeItemTemperatureInformationModule.xsd", true, false),
   trade_item_hierarchy("tradeItemHierarchyModule", "trade_item_hierarchy", "urn:gs1:gdsn:trade_item_hierarchy:xsd:3", "http://www.gs1globalregistry.net/3.1/schemas/gs1/gdsn/TradeItemHierarchyModule.xsd", true, false),
   controlled_substance("controlledSubstanceModule", "controlled_substance", "urn:gs1:gdsn:controlled_substance:xsd:3", "http://www.gs1globalregistry.net/3.1/schemas/gs1/gdsn/ControlledSubstanceModule.xsd", true, false),
   safety_data_sheet("safetyDataSheetModule", "safety_data_sheet", "urn:gs1:gdsn:safety_data_sheet:xsd:3", "http://www.gs1globalregistry.net/3.1/schemas/gs1/gdsn/SafetyDataSheetModule.xsd", true, false),
   audio_visual_media_product_description_information("audioVisualMediaProductDescriptionInformationModule", "audio_visual_media_product_description_information", "urn:gs1:gdsn:audio_visual_media_product_description_information:xsd:3", "http://www.gs1globalregistry.net/3.1/schemas/gs1/gdsn/AudioVisualMediaProductDescriptionInformationModule.xsd", true, false),
   place_of_item_activity("placeOfItemActivityModule", "place_of_item_activity", "urn:gs1:gdsn:place_of_item_activity:xsd:3", "http://www.gs1globalregistry.net/3.1/schemas/gs1/gdsn/PlaceOfItemActivityModule.xsd", true, false),
   promotional_item_information("promotionalItemInformationModule", "promotional_item_information", "urn:gs1:gdsn:promotional_item_information:xsd:3", "http://www.gs1globalregistry.net/3.1/schemas/gs1/gdsn/PromotionalItemInformationModule.xsd", true, false),
   nonfood_ingredient("nonfoodIngredientModule", "nonfood_ingredient", "urn:gs1:gdsn:nonfood_ingredient:xsd:3", "http://www.gs1globalregistry.net/3.1/schemas/gs1/gdsn/NonfoodIngredientModule.xsd", true, false),
   sales_information("salesInformationModule", "sales_information", "urn:gs1:gdsn:sales_information:xsd:3", "http://www.gs1globalregistry.net/3.1/schemas/gs1/gdsn/SalesInformationModule.xsd", true, false),
   onix_publication_file_information("oNIXPublicationFileInformationModule", "onix_publication_file_information", "urn:gs1:gdsn:onix_publication_file_information:xsd:3", "http://www.gs1globalregistry.net/3.1/schemas/gs1/gdsn/ONIXPublicationFileInformationModule.xsd", true, false),
   audio_visual_media_production_information("audioVisualMediaProductionInformationModule", "audio_visual_media_production_information", "urn:gs1:gdsn:audio_visual_media_production_information:xsd:3", "http://www.gs1globalregistry.net/3.1/schemas/gs1/gdsn/AudioVisualMediaProductionInformationModule.xsd", true, false),
   catalogue_item_hierarchical_withdrawal("catalogueItemHierarchicalWithdrawalMessage", "catalogue_item_hierarchical_withdrawal", "urn:gs1:gdsn:catalogue_item_hierarchical_withdrawal:xsd:3", "http://www.gs1globalregistry.net/3.1/schemas/gs1/gdsn/CatalogueItemHierarchicalWithdrawal.xsd", false, true),
   marketing_information("marketingInformationModule", "marketing_information", "urn:gs1:gdsn:marketing_information:xsd:3", "http://www.gs1globalregistry.net/3.1/schemas/gs1/gdsn/MarketingInformationModule.xsd", true, false),
   material("materialModule", "material", "urn:gs1:gdsn:material:xsd:3", "http://www.gs1globalregistry.net/3.1/schemas/gs1/gdsn/MaterialModule.xsd", true, false),
   healthcare_item_information("healthcareItemInformationModule", "healthcare_item_information", "urn:gs1:gdsn:healthcare_item_information:xsd:3", "http://www.gs1globalregistry.net/3.1/schemas/gs1/gdsn/HealthcareItemInformationModule.xsd", true, false),
   movie_revenue_information("movieRevenueInformationModule", "movie_revenue_information", "urn:gs1:gdsn:movie_revenue_information:xsd:3", "http://www.gs1globalregistry.net/3.1/schemas/gs1/gdsn/MovieRevenueInformationModule.xsd", true, false),
   security_tag_information("securityTagInformationModule", "security_tag_information", "urn:gs1:gdsn:security_tag_information:xsd:3", "http://www.gs1globalregistry.net/3.1/schemas/gs1/gdsn/SecurityTagInformationModule.xsd", true, false),
   food_and_beverage_properties_information("foodAndBeveragePropertiesInformationModule", "food_and_beverage_properties_information", "urn:gs1:gdsn:food_and_beverage_properties_information:xsd:3", "http://www.gs1globalregistry.net/3.1/schemas/gs1/gdsn/FoodAndBeveragePropertiesInformationModule.xsd", true, false),
   trade_item("tradeItem", "trade_item", "urn:gs1:gdsn:trade_item:xsd:3", "http://www.gs1globalregistry.net/3.1/schemas/gs1/gdsn/TradeItem.xsd", false, false),
   warranty_information("warrantyInformationModule", "warranty_information", "urn:gs1:gdsn:warranty_information:xsd:3", "http://www.gs1globalregistry.net/3.1/schemas/gs1/gdsn/WarrantyInformationModule.xsd", true, false),
   variable_trade_item_information("variableTradeItemInformationModule", "variable_trade_item_information", "urn:gs1:gdsn:variable_trade_item_information:xsd:3", "http://www.gs1globalregistry.net/3.1/schemas/gs1/gdsn/VariableTradeItemInformationModule.xsd", true, false),
   catalogue_item_publication("catalogueItemPublicationMessage", "catalogue_item_publication", "urn:gs1:gdsn:catalogue_item_publication:xsd:3", "http://www.gs1globalregistry.net/3.1/schemas/gs1/gdsn/CatalogueItemPublication.xsd", false, true),
   farming_and_processing_information("farmingAndProcessingInformationModule", "farming_and_processing_information", "urn:gs1:gdsn:farming_and_processing_information:xsd:3", "http://www.gs1globalregistry.net/3.1/schemas/gs1/gdsn/FarmingAndProcessingInformationModule.xsd", true, false),
   physical_resource_usage("physicalResourceUsageInformationModule", "physical_resource_usage", "urn:gs1:gdsn:physical_resource_usage:xsd:3", "http://www.gs1globalregistry.net/3.1/schemas/gs1/gdsn/PhysicalResourceUsageInformationModule.xsd", true, false),
   electronic_device_characteristics_information("electronicDeviceCharacteristicsInformationModule", "electronic_device_characteristics_information", "urn:gs1:gdsn:electronic_device_characteristics_information:xsd:3", "http://www.gs1globalregistry.net/3.1/schemas/gs1/gdsn/ElectronicDeviceCharacteristicsInformationModule.xsd", true, false),
   child_nutrition_information("childNutritionInformationModule", "child_nutrition_information", "urn:gs1:gdsn:child_nutrition_information:xsd:3", "http://www.gs1globalregistry.net/3.1/schemas/gs1/gdsn/ChildNutritionInformationModule.xsd", true, false),
   trade_item_handling("tradeItemHandlingModule", "trade_item_handling", "urn:gs1:gdsn:trade_item_handling:xsd:3", "http://www.gs1globalregistry.net/3.1/schemas/gs1/gdsn/TradeItemHandlingModule.xsd", true, false),
   health_related_information("healthRelatedInformationModule", "health_related_information", "urn:gs1:gdsn:health_related_information:xsd:3", "http://www.gs1globalregistry.net/3.1/schemas/gs1/gdsn/HealthRelatedInformationModule.xsd", true, false),
   regulated_trade_item("regulatedTradeItemModule", "regulated_trade_item", "urn:gs1:gdsn:regulated_trade_item:xsd:3", "http://www.gs1globalregistry.net/3.1/schemas/gs1/gdsn/RegulatedTradeItemModule.xsd", true, false),
   trade_item_data_carrier_and_identification("tradeItemDataCarrierAndIdentificationModule", "trade_item_data_carrier_and_identification", "urn:gs1:gdsn:trade_item_data_carrier_and_identification:xsd:3", "http://www.gs1globalregistry.net/3.1/schemas/gs1/gdsn/TradeItemDataCarrierAndIdentificationModule.xsd", true, false),
   registry_catalogue_item("registryCatalogueItemMessage", "registry_catalogue_item", "urn:gs1:gdsn:registry_catalogue_item:xsd:3", "http://www.gs1globalregistry.net/3.1/schemas/gs1/gdsn/RegistryCatalogueItem.xsd", false, true),
   catalogue_item_subscription("catalogueItemSubscriptionMessage", "catalogue_item_subscription", "urn:gs1:gdsn:catalogue_item_subscription:xsd:3", "http://www.gs1globalregistry.net/3.1/schemas/gs1/gdsn/CatalogueItemSubscription.xsd", false, true),
   textile_material("textileMaterialModule", "textile_material", "urn:gs1:gdsn:textile_material:xsd:3", "http://www.gs1globalregistry.net/3.1/schemas/gs1/gdsn/TextileMaterialModule.xsd", true, false),
   award_prize("awardPrizeModule", "award_prize", "urn:gs1:gdsn:award_prize:xsd:3", "http://www.gs1globalregistry.net/3.1/schemas/gs1/gdsn/AwardPrizeModule.xsd", true, false),
   publication_title_rating("publicationTitleRatingModule", "publication_title_rating", "urn:gs1:gdsn:publication_title_rating:xsd:3", "http://www.gs1globalregistry.net/3.1/schemas/gs1/gdsn/PublicationTitleRatingModule.xsd", true, false),
   product_formulation_statement("productFormulationStatementModule", "product_formulation_statement", "urn:gs1:gdsn:product_formulation_statement:xsd:3", "http://www.gs1globalregistry.net/3.1/schemas/gs1/gdsn/ProductFormulationStatementModule.xsd", true, false),
   product_characteristics("productCharacteristicsModule", "product_characteristics", "urn:gs1:gdsn:product_characteristics:xsd:3", "http://www.gs1globalregistry.net/3.1/schemas/gs1/gdsn/ProductCharacteristicsModule.xsd", true, false),
   optics_device_information("opticsDeviceInformationModule", "optics_device_information", "urn:gs1:gdsn:optics_device_information:xsd:3", "http://www.gs1globalregistry.net/3.1/schemas/gs1/gdsn/OpticsDeviceInformationModule.xsd", true, false),
   health_wellness_packaging_marking("healthWellnessPackagingMarkingModule", "health_wellness_packaging_marking", "urn:gs1:gdsn:health_wellness_packaging_marking:xsd:3", "http://www.gs1globalregistry.net/3.1/schemas/gs1/gdsn/HealthWellnessPackagingMarkingModule.xsd", true, false),
   dangerous_substance_information("dangerousSubstanceInformationModule", "dangerous_substance_information", "urn:gs1:gdsn:dangerous_substance_information:xsd:3", "http://www.gs1globalregistry.net/3.1/schemas/gs1/gdsn/DangerousSubstanceInformationModule.xsd", true, false),
   nongtin_logistics_unit_information("nonGTINLogisticsUnitInformationModule", "nongtin_logistics_unit_information", "urn:gs1:gdsn:nongtin_logistics_unit_information:xsd:3", "http://www.gs1globalregistry.net/3.1/schemas/gs1/gdsn/NonGTINLogisticsUnitInformationModule.xsd", true, false),
   animal_feeding("animalFeedingModule", "animal_feeding", "urn:gs1:gdsn:animal_feeding:xsd:3", "http://www.gs1globalregistry.net/3.1/schemas/gs1/gdsn/AnimalFeedingModule.xsd", true, false),
   organism_classification("organismClassificationModule", "organism_classification", "urn:gs1:gdsn:organism_classification:xsd:3", "http://www.gs1globalregistry.net/3.1/schemas/gs1/gdsn/OrganismClassificationModule.xsd", true, false),
   battery_information("batteryInformationModule", "battery_information", "urn:gs1:gdsn:battery_information:xsd:3", "http://www.gs1globalregistry.net/3.1/schemas/gs1/gdsn/BatteryInformationModule.xsd", true, false),
   lighting_device("lightingDeviceModule", "lighting_device", "urn:gs1:gdsn:lighting_device:xsd:3", "http://www.gs1globalregistry.net/3.1/schemas/gs1/gdsn/LightingDeviceModule.xsd", true, false),
   gdsn_common("", "gdsn_common", "urn:gs1:gdsn:gdsn_common:xsd:3", "http://www.gs1globalregistry.net/3.1/schemas/gs1/gdsn/GdsnCommon.xsd", false, false),
   trade_item_licensing("tradeItemLicensingModule", "trade_item_licensing", "urn:gs1:gdsn:trade_item_licensing:xsd:3", "http://www.gs1globalregistry.net/3.1/schemas/gs1/gdsn/TradeItemLicensingModule.xsd", true, false),
   transportation_hazardous_classification("transportationHazardousClassificationModule", "transportation_hazardous_classification", "urn:gs1:gdsn:transportation_hazardous_classification:xsd:3", "http://www.gs1globalregistry.net/3.1/schemas/gs1/gdsn/TransportationHazardousClassificationModule.xsd", true, false),
   trade_item_humidity_information("tradeItemHumidityInformationModule", "trade_item_humidity_information", "urn:gs1:gdsn:trade_item_humidity_information:xsd:3", "http://www.gs1globalregistry.net/3.1/schemas/gs1/gdsn/TradeItemHumidityInformationModule.xsd", true, false),
   audio_visual_media_content_information("audioVisualMediaContentInformationModule", "audio_visual_media_content_information", "urn:gs1:gdsn:audio_visual_media_content_information:xsd:3", "http://www.gs1globalregistry.net/3.1/schemas/gs1/gdsn/AudioVisualMediaContentInformationModule.xsd", true, false),
   copyright_information("copyrightInformationModule", "copyright_information", "urn:gs1:gdsn:copyright_information:xsd:3", "http://www.gs1globalregistry.net/3.1/schemas/gs1/gdsn/CopyrightInformationModule.xsd", true, false),
   diet_information("dietInformationModule", "diet_information", "urn:gs1:gdsn:diet_information:xsd:3", "http://www.gs1globalregistry.net/3.1/schemas/gs1/gdsn/DietInformationModule.xsd", true, false),
   pharmaceutical_item_information("pharmaceuticalItemInformationModule", "pharmaceutical_item_information", "urn:gs1:gdsn:pharmaceutical_item_information:xsd:3", "http://www.gs1globalregistry.net/3.1/schemas/gs1/gdsn/PharmaceuticalItemInformationModule.xsd", true, false),
   audience_or_player_information("audienceOrPlayerInformationModule", "audience_or_player_information", "urn:gs1:gdsn:audience_or_player_information:xsd:3", "http://www.gs1globalregistry.net/3.1/schemas/gs1/gdsn/AudienceOrPlayerInformationModule.xsd", true, false),
   software_system_requirements("softwareSystemRequirementsModule", "software_system_requirements", "urn:gs1:gdsn:software_system_requirements:xsd:3", "http://www.gs1globalregistry.net/3.1/schemas/gs1/gdsn/SoftwareSystemRequirementsModule.xsd", true, false),
   allergen_information("allergenInformationModule", "allergen_information", "urn:gs1:gdsn:allergen_information:xsd:3", "http://www.gs1globalregistry.net/3.1/schemas/gs1/gdsn/AllergenInformationModule.xsd", true, false),
   catalogue_item_confirmation("catalogueItemConfirmationMessage", "catalogue_item_confirmation", "urn:gs1:gdsn:catalogue_item_confirmation:xsd:3", "http://www.gs1globalregistry.net/3.1/schemas/gs1/gdsn/CatalogueItemConfirmation.xsd", false, true);

   private final String localName;

   private final String namespace;

   private final String uri;

   private final String schemaLocation;

   private final boolean module;

   private final boolean message;

   GdsnNamespaces(String localName, String namespace, String uri, String schemaLocation, boolean module, boolean message) {
      this.localName = localName;
      this.namespace = namespace;
      this.uri = uri;
      this.schemaLocation = schemaLocation;
      this.module = module;
      this.message = message;
   }

   public static GdsnNamespaces findByNamespace(String namespace) {
      return Arrays.stream(values()).filter(o -> o.getNamespace().equals(namespace)).findFirst().orElse(null);
   }

   public static GdsnNamespaces findByUri(String uri) {
      return Arrays.stream(values()).filter(o -> o.getUri().equals(uri)).findFirst().orElse(null);
   }

   public String getNamespace() {
      return namespace;
   }

   public String getUri() {
      return uri;
   }

   public String getLocalName() {
      return localName;
   }

   public String getSchemaLocation() {
      return schemaLocation;
   }

   public boolean isModule() {
      return module;
   }

   public boolean isMessage() {
      return message;
   }
}
