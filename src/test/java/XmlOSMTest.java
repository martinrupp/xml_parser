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
        Assert.assertEquals( "ways = [id=25291691, [275494491, 275503483, 275473051, 275463021, 275494491]], " +
                        "nodes = [{id=36966060, lat=-79.406308, lon=0.314931}, {id=98257134, lat=-54.726099, lon=-63.795741}, " +
                        "{id=248461592, lat=-62.159014, lon=-58.471936}, {id=248461594, lat=-62.478435, lon=-59.714083}, " +
                        "{id=275462210, lat=-70.306450, lon=12.464890}, {id=275462254, lat=-70.302582, lon=12.522486}, " +
                        "{id=275462346, lat=-70.817199, lon=-7.593200}, {id=8518223538, lat=-69.556953, lon=-72.872915}, " +
                        "{id=8518223539, lat=-69.556684, lon=-72.878494}, {id=8518223540, lat=-69.556444, lon=-72.886820}]",
                printFile( "src/test/resources/simple_osm.xml" ));
    }
}
