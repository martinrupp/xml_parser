import org.junit.Assert;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class XMLAnalyzerTest {
    XMLAnalyzer analyzeFile( String filename )
            throws ParserConfigurationException, SAXException,
            IOException
    {
        File people = new File(filename);
        SAXParserFactory spf = SAXParserFactory.newInstance();
        SAXParser parser = spf.newSAXParser();
        XMLAnalyzer handler = new XMLAnalyzer();
        parser.parse(people, new XMLSubhandler(handler));
        return handler;
    }

    @org.junit.Test
    public void peopleTest()
    {
        try {
            XMLAnalyzer handler = analyzeFile( "src/test/resources/people.xml" );

            Assert.assertEquals(handler.toString(),
                    "people, count = 1 , type = empty\n" +
                    "people.person, count = 3 , type = empty\n" +
                    "people.person.name, count = 3 (e.g. Manuel), type = STRING\n" +
                    "people.person.age, count = 3 (e.g. 25), type = INTEGER\n" +
                    "people.person.age.born, count = 3 (e.g. 1990-02-24), type = STRING\n" );
        }
        catch( Exception e )
        {
            Assert.fail();
        }
    }

    @org.junit.Test
    public void simpleOsmTest()
    {
        try {
            XMLAnalyzer handler = analyzeFile( "src/test/resources/simple_osm.xml" );

            Assert.assertEquals(handler.toString(),
                            "osm, count = 1 , type = empty\n" +
                            "osm.node, count = 10 , type = empty\n" +
                            "osm.node.lon, count = 10 (e.g. 0.3149312), type = DOUBLE\n" +
                            "osm.node.tag, count = 3 , type = empty\n" +
                            "osm.node.tag.v, count = 3 (e.g. en:Antarctica), type = STRING\n" +
                            "osm.node.tag.k, count = 3 (e.g. wikipedia), type = STRING\n" +
                            "osm.node.id, count = 10 (e.g. 8518223538), type = DOUBLE\n" +
                            "osm.node.version, count = 10 (e.g. 66), type = INTEGER\n" +
                            "osm.node.lat, count = 10 (e.g. -79.4063075), type = DOUBLE\n" +
                            "osm.node.timestamp, count = 10 (e.g. 2020-11-25T15:27:25Z), type = STRING\n" +
                            "osm.bounds, count = 1 , type = empty\n" +
                            "osm.bounds.minlon, count = 1 (e.g. -180), type = INTEGER\n" +
                            "osm.bounds.maxlat, count = 1 (e.g. -60), type = INTEGER\n" +
                            "osm.bounds.minlat, count = 1 (e.g. -90), type = INTEGER\n" +
                            "osm.bounds.maxlon, count = 1 (e.g. 180), type = INTEGER\n" +
                            "osm.generator, count = 1 (e.g. osmium/1.8.0), type = STRING\n" +
                            "osm.version, count = 1 (e.g. 0.6), type = DOUBLE\n" +
                            "osm.way, count = 1 , type = empty\n" +
                            "osm.way.nd, count = 5 , type = empty\n" +
                            "osm.way.nd.ref, count = 5 (e.g. 275494491), type = INTEGER\n" +
                            "osm.way.tag, count = 5 , type = empty\n" +
                            "osm.way.tag.v, count = 5 (e.g. Faure Island), type = STRING\n" +
                            "osm.way.tag.k, count = 5 (e.g. name), type = STRING\n" +
                            "osm.way.id, count = 1 (e.g. 25291691), type = INTEGER\n" +
                            "osm.way.version, count = 1 (e.g. 3), type = INTEGER\n" +
                            "osm.way.timestamp, count = 1 (e.g. 2019-06-29T21:37:53Z), type = STRING\n" +
                            "osm.relation, count = 2 , type = empty\n" +
                            "osm.relation.member, count = 6 , type = empty\n" +
                            "osm.relation.member.ref, count = 6 (e.g. 38043843), type = INTEGER\n" +
                            "osm.relation.member.role, count = 6 (e.g. outer), type = STRING\n" +
                            "osm.relation.member.type, count = 6 (e.g. way), type = STRING\n" +
                            "osm.relation.tag, count = 2 , type = empty\n" +
                            "osm.relation.tag.v, count = 2 (e.g. land_area), type = STRING\n" +
                            "osm.relation.tag.k, count = 2 (e.g. boundary), type = STRING\n" +
                            "osm.relation.id, count = 2 (e.g. 188921), type = INTEGER\n" +
                            "osm.relation.version, count = 2 (e.g. 22), type = INTEGER\n" +
                            "osm.relation.timestamp, count = 2 (e.g. 2020-12-18T10:37:36Z), type = STRING\n" );
        }
        catch( Exception e )
        {
            Assert.fail();
        }
    }
}