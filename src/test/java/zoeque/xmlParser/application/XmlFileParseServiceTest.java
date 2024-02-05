package zoeque.xmlParser.application;

import java.io.File;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import zoeque.xmlparser.adapter.FileReadAdapter;
import zoeque.xmlparser.application.XmlFileParseService;

public class XmlFileParseServiceTest {
  @Test
  @Disabled
  public void givenXmlFile_whenParse_thenReturnValue(){
    XmlFileParseService service = new XmlFileParseService(new FileReadAdapter(new File("src/test/resources/test.xml")));
    String value = service.parse("property", "key", "test2", "value").get();
    Assertions.assertEquals(value, "bar");
  }
}
