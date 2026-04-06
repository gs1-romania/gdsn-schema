# GDSN XSD Schema - Jakarta XML Binding (JAXB) Generated

Jakarta XML Binding (JAXB) generated classes for GS1 GDSN 3.1 XSD schemas, with utilities for marshalling, unmarshalling and XML validation.

Supported GDSN XSD version: **3.1**

Requires Java 11+.

## Dependency

Available on [Maven Central](https://central.sonatype.com/artifact/ro.gs1/gdsn-schema) and [GitHub Packages](https://github.com/gs1-romania/gdsn-schema/packages).

```xml
<dependency>
    <groupId>ro.gs1</groupId>
    <artifactId>gdsn-schema</artifactId>
    <version>3.1.35.0</version>
</dependency>
```

## Usage

### Marshalling (Java → XML)

```java
GdsnXmlFactory factory = new GdsnXmlFactory();

CatalogueItemNotificationMessage message = new CatalogueItemNotificationMessage();
// ... populate message ...

byte[] xml = factory.generate(message);
```

### Unmarshalling (XML → Java)

```java
GdsnXmlFactory factory = new GdsnXmlFactory();

Source source = new StreamSource(new File("catalogue-item-notification.xml"));
CatalogueItemNotificationMessage message = factory.readCatalogueItemNotification(source);
```

### XML Validation

```java
GdsnXmlFactory factory = new GdsnXmlFactory();

List<String> errors = factory.isWellFormedGDSNXml(inputStream);
if (errors.isEmpty()) {
    // valid
}
```

## XSD Customisations

The GS1 XSDs require the following manual adjustments to work with the JAXB code generator:

- **Extension type mapping**: `ExtensionType` in TradeItem uses `xs:any`, which JAXB maps to `List<Object>`. A custom `BasicModuleType` base class is added in `SharedCommon.xsd` so all `*ModuleType` classes extend it, enabling type-safe extension handling.
- **Java time mapping**: `xs:date`, `xs:time`, and `xs:dateTime` are mapped to `java.time.LocalDate`, `java.time.LocalTime`, and `java.time.ZonedDateTime` via JAXB adapters.
- **Collision resolution**: A name collision in `SharedCommon` for `Description250Type` is resolved via JAXB bindings.

Generated with [jaxb-tools](https://github.com/gs1-romania/jaxb-tools) XJC plugins.