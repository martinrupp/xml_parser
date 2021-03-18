import org.junit.Assert;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

public class XmlPrinterTest {

    String printFile( String filename )
            throws ParserConfigurationException, SAXException,
            IOException
    {
        File people = new File(filename);
        SAXParserFactory spf = SAXParserFactory.newInstance();
        SAXParser parser = spf.newSAXParser();
        ByteArrayOutputStream os = new ByteArrayOutputStream(1024);
        PrintStream ps = new PrintStream(os);
        XMLPrinterHandler handler = new XMLPrinterHandler(ps);
        parser.parse(people, new XMLSubhandler(handler));
        return os.toString();
    }

    @org.junit.Test
    public void simpleOsmTest()
    {
        try {
            Assert.assertEquals( "people.person.age.born = 1990-02-24\n" +
                            "people.person.age = 25\n" +
                            "people.person.name = Manuel\n" +
                            "people.person.age.born = 1985-01-01\n" +
                            "people.person.age = 30\n" +
                            "people.person.name = Lars\n" +
                            "people.person.age.born = 1980-01-01\n" +
                            "people.person.age = 30\n" +
                            "people.person.name = Leon\n",
                    printFile( "src/test/resources/people.xml" ));
        }
        catch( Exception e )
        {
            Assert.fail();
        }
    }
}
