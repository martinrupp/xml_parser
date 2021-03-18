import org.xml.sax.Attributes;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

class XMLPrinterHandler implements XMLSubhandler.IXMLNodeHandler {
    private ArrayList<String> hierarchy = new ArrayList<String>();
    private PrintStream out;

    XMLPrinterHandler() {
        this(System.out);
    }
    XMLPrinterHandler(PrintStream stream)
    {
        this.out = stream;
    }

    @Override
    public XMLSubhandler.IXMLNodeHandler startElement(String uri, String localName,
                                                      String qName, Attributes attributes) {
        hierarchy.add( qName );

        for( int i=0; i < attributes.getLength(); i++ )
        {
            out.println( getName() + "." + attributes.getQName(i) + " = "
                    + attributes.getValue(i) );
        }
        return this;
    }

    @Override
    public void endElement(String uri, String localName, String qName)
    {
        hierarchy.remove( hierarchy.size()-1 );
    }

    @Override
    public void addString(String s) {
        out.println( getName() + " = " + s );
    }

    private String getName()
    {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<hierarchy.size(); i++){
            if( i != 0 ) sb.append('.');
            sb.append( hierarchy.get(i) );
        }
        return sb.toString();
    }
}
