# XML parser
## Overview

## Usage
If you want get the value "foo" from following XML file;  
```xml
<test>
    <property key = "test1" value = "foo" />
    <property key = "test2" value = "bar" />
</test>
```

use `XmlFileParseService#parse()`with four arguments, that identify the value to be taken.

```java
XmlFileParseService service = new XmlFileParseService(new FileReadAdapter(new File("src/test/resources/test.xml")));
// tag name, key attribute name, key attribute value, value attribute name
String value = service.parse("property", "key", "test2", "value").get();
```

If there is no value in XML file, return "" with io.vavr.Try.

## Development
This application is built with the environment bellow;

- OpenJDK 17
- IntelliJ IDEA 2023.1.2

Note that this module is needed the gradle.properties on the root directory.
The property file is used when the module is published to GitHub Package manager.  
Here is an example of the gradle.properties bellow (name must be set as your name);

```properties
GITHUB_USERNAME = zoeque
GITHUB_TOKEN = [[TOKEN]]
```

