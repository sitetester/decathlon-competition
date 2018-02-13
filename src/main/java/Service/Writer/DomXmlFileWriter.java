package Service.Writer;

import Service.Entity.DecathlonEvent;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.Map;

public class DomXmlFileWriter implements OutputFileWriterInterface {

    /**
     * Generates XML based on output file path
     * Generated XML can be viewed in browser by applying <?xml-stylesheet type="text/xsl" href="xml_output.xsl"?>
     *
     * Corresponding XSL (xml_output.xsl) can be found in /src/main/resources/xml_output.xsl
     */
    public boolean write(
            Map<String, Integer> athleteSortedScoreResult,
            Map<String, Map<String, Float>> eventScoresByAthletes,
            Map<String, String> placeOfCompetition,
            String outputFilePath) {

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            // root element
            Element athletes = doc.createElement("athletes");
            doc.appendChild(athletes);

            // loop over sorted total score
            athleteSortedScoreResult.forEach((athleteName, totalEventsScore) -> {
                Element athlete = doc.createElement("athlete");

                Attr name = doc.createAttribute("name");
                name.setValue(athleteName);

                Attr totalScore = doc.createAttribute("totalScore");
                totalScore.setValue(String.valueOf(totalEventsScore));

                Attr place = doc.createAttribute("place");
                place.setValue(String.valueOf(placeOfCompetition.get(athleteName)));

                Element events = doc.createElement("events");

                athlete.setAttributeNode(name);
                athlete.setAttributeNode(totalScore);
                athlete.setAttributeNode(place);

                athletes.appendChild(athlete);
                athlete.appendChild(events);

                // generate each event specific elements
                for (DecathlonEvent decathlonEvent : DecathlonEvent.values()) {
                    Element eventElement = doc.createElement(decathlonEvent.name().toLowerCase());

                    Float eventScoreTemp = eventScoresByAthletes.get(athleteName).get(decathlonEvent.name());
                    Node eventScore = doc.createTextNode(eventScoreTemp.toString());

                    eventElement.appendChild(eventScore);
                    events.appendChild(eventElement);
                }
            });

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);

            StreamResult result = new StreamResult(new File(outputFilePath));
            transformer.transform(source, result);

            return true;

        } catch (Exception e) {
            return false;
        }
    }
}