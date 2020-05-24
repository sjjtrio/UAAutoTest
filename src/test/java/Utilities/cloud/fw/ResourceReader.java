package Utilities.cloud.fw;


import com.jcabi.xml.XMLDocument;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * ResourceReader provides class methods for reading files from the "resources" directory.
 * This makes reading resource files easier.
 */
public class ResourceReader {

    /**
     * Creates a File object for the resources file.
     *
     * @param path String path to file (with "resources" as parent)
     * @return File
     */
    public static File getFile(String path) {
        return new File(getFilePath(path));
    }

    /**
     * Gets the full ("absolute") file path for the resources file.
     *
     * @param path String path to file (with "resources" as parent)
     * @return String
     */
    public static String getFilePath(String path) {
        //noinspection ConstantConditions
        return ResourceReader.class.getClassLoader().getResource(path).getFile();
    }

    /**
     * Creates a buffered input stream to read the contents of a resources file.
     *
     * @param path String path to file (with "resources" as parent)
     * @return BufferedInputStream (cast as InputStream)
     */
    public static InputStream getInputStream(String path) throws FileNotFoundException {
        System.out.println("Try to get Stream for "+ path);
        //InputStream is = ResourceReader.class.getClassLoader().getResourceAsStream(path);
        InputStream is = new FileInputStream(path);
 /*       if(is == null){
            System.out.println("Stream is NUll");
        }else{
            System.out.println("Stream is not NUll");
        }*/
        return new BufferedInputStream(is);
    }

    /**
     * Reads any text file as a string.
     *
     * @param path String
     * @return String
     * @throws IOException for any IO exceptions
     */
    public static String readFileAsString(String path) throws IOException {
        String newPath = getFilePath(path).replaceAll("^/([A-Z]:/)", "$1");
        return new String(Files.readAllBytes(Paths.get(newPath)));
    }

    /**
     * Reads and parses an XML resource file.
     *
     * @param path String path to file (with "resources" as parent)
     * @return Document
     * @throws IOException                  e
     * @throws ParserConfigurationException e
     * @throws SAXException                 e
     */
    public static Document readXmlFile(String path) throws IOException, ParserConfigurationException, SAXException {
        return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(getFile(path));
    }

    /**
     * Reads and parses an XML resource file into a String.
     *
     * @param path String path to file (with "resources" as parent)
     * @return String
     * @throws IOException                  e
     * @throws ParserConfigurationException e
     * @throws SAXException                 e
     */
    public static String readXmlFileAsString(String path) throws IOException, ParserConfigurationException, SAXException {
        return xmlDocToString(readXmlFile(path));
    }

    /**
     * Converts a parsed XML document to a String.
     * Newline characters are removed, and whitespace on each line is trimmed
     * These characters will break certain requests.
     *
     * @param doc Document
     * @return String
     */
    public static String xmlDocToString(Document doc) {
        String[] lines = new XMLDocument(doc).toString().split("\r|\n");
        StringBuilder sb = new StringBuilder();

        for (String line : lines) {
            sb.append(line.trim());
        }

        return sb.toString();
    }

}
