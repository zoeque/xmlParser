package zoeque.xmlparser.adapter;

import io.vavr.control.Try;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

/**
 * The file reader for xml.
 * Set {@link File} instance with the target xml path to init.
 */
@Slf4j
@AllArgsConstructor
public class FileReadAdapter {
  File file;

  /**
   * Get node list with given tag name.
   *
   * @param tagName The tag name of the xml file
   * @return {@link NodeList} with the result {@link Try}.
   */
  public Try<NodeList> getNodeListFromXmlFile(String tagName) {
    try {
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();
      Document document = builder.parse(this.file);

      document.getDocumentElement().normalize();

      return Try.success(document.getElementsByTagName(tagName));

    } catch (Exception e) {
      log.warn("Cannot get NodeList from the given XML file!");
      return Try.failure(e);
    }
  }

}
