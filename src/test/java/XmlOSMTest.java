import org.junit.Assert;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

public class XmlOSMTest {

    String printFile( String filename )
            throws ParserConfigurationException, SAXException,
            IOException
    {
        File people = new File(filename);
        SAXParserFactory spf = SAXParserFactory.newInstance();
        SAXParser parser = spf.newSAXParser();
        XMLOSM h = new XMLOSM();
        parser.parse(people, new XMLSubhandler(h));
        return h.toString();
    }

    @org.junit.Test
    public void simpleOsmTest() throws IOException, SAXException, ParserConfigurationException {
//        try
        {
            Assert.assertEquals( "",
                    printFile( "src/test/resources/simple_osm.xml" ));
        }
//        catch( Exception e )
//        {
//            Assert.fail(e.toString());
//        }
    }
}
