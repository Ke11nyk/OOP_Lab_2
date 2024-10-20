package lab.transform;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class xslTransformer {
    public static String transform(String xmlFile, String xslFile) {
        try {
            TransformerFactory factory = TransformerFactory.newInstance();
            System.out.println("Using XSLT processor: " + factory.getClass().getName());

            Transformer transformer = factory.newTransformer(new StreamSource(new File(xslFile)));
            System.out.println("XSL file loaded successfully");

            String outputFile = xmlFile.replace(".xml", "_transformed.xml");
            transformer.transform(new StreamSource(new File(xmlFile)), new StreamResult(new File(outputFile)));

            System.out.println("Transformation completed successfully");
            return outputFile;
        } catch (Exception e) {
            System.out.println("Transformation failed: " + e.getMessage());
            e.printStackTrace();
            return "";
        }
    }
}