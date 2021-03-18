import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

public class main {

    public static void main(String[] args) {

        try {
            long time_start = System.currentTimeMillis();
            SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser parser = spf.newSAXParser();

            // https://download.geofabrik.de/antarctica-latest.osm.bz2
            String filename = "/Users/martinrupp/Downloads/antarctica-latest.osm";

            File file = new File(filename);
            //XMLSubhandler handler = new XMLSubhandler( new XMLPrinterHandler2() );
            XMLSubhandler.IXMLNodeHandler h = new XMLAnalyzer();
//            XMLSubhandler.IXMLNodeHandler h = new XMLOSM();
            //parser.parse(file, new XMLPrinterHandler());
            parser.parse(file, new XMLSubhandler(h));

            if( h instanceof XMLAnalyzer) {
                System.out.print(h.toString());
            }
            long time_end = System.currentTimeMillis();

            System.out.println("parsed " + file.length()/(1024*1024) + " MB in " + (time_end-time_start)/1000.0 + " s");
        }
        catch( Exception e )
        {
            System.out.println( e );
        }
    }
}
