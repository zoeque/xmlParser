package zoeque.xmlparser.application;

import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import zoeque.xmlparser.adapter.FileReadAdapter;

/**
 * The service for XML parse easily.
 */
@Slf4j
@Service
public class XmlFileParseService {

  FileReadAdapter adapter;

  public XmlFileParseService(FileReadAdapter adapter) {
    this.adapter = adapter;
  }

  /**
   * Parse xml and get the value with given arguments.
   * For example, if the xml is given by the following value;<b>
   * < property key="test" value="foo" />
   *
   * @param tagName        Set the tag name, "property" for the example
   * @param keyAttribute   Set key name, "key" for the example
   * @param keyValue       Set the value of key, "test" for the example
   * @param valueAttribute Set the value, "value" for the example
   * @return Return "foo" for the example, with the result {@link Try}
   */
  public Try<String> parse(String tagName,
                           String keyAttribute,
                           String keyValue,
                           String valueAttribute) {
    try {
      NodeList nodeList = adapter.getNodeListFromXmlFile(tagName).get();
      for (int idx = 0; idx < nodeList.getLength(); idx++) {
        Node node = nodeList.item(idx);
        if (node.getNodeType() == Node.ELEMENT_NODE) {
          Element element = (Element) node;
          if (element.getAttribute(keyAttribute).equals(keyValue)) {
            return Try.success(element.getAttribute(valueAttribute));
          }
        }
      }
      log.info("Cannot find the attribute value with the key:{}", keyValue);
      return Try.success("");
    } catch (Exception e) {
      return Try.failure(e);
    }
  }
}
