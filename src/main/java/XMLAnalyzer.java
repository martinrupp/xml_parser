import org.xml.sax.Attributes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class XMLAnalyzer implements XMLSubhandler.IXMLNodeHandler
{
    AnalyzingType type = new AnalyzingType();
    public Map<String, XMLAnalyzer> subnodes = new HashMap<String, XMLAnalyzer>();
    public int count = 0;

    XMLAnalyzer getOrCreate( String name )
    {
        XMLAnalyzer n = subnodes.computeIfAbsent( name, k -> new XMLAnalyzer() );
        n.count ++;
        return n;
    }

    @Override
    public XMLSubhandler.IXMLNodeHandler startElement(String uri, String localName,
                                                      String qName, Attributes attributes) {
        XMLAnalyzer node = getOrCreate( qName );
        for( int i=0; i < attributes.getLength(); i++ )
        {
            node.getOrCreate( attributes.getQName(i) ).addString( attributes.getValue(i) );
        }
        return node;
    }

    @Override
    public void endElement(String uri, String localName, String qName)
    {
    }

    @Override
    public void addString(String s)
    {
        type.analyze( s );
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        toStringBuilder("", sb);
        return sb.toString();
    }

    private void toStringBuilder( String name, StringBuilder sb )
    {
        if( !name.isEmpty() ) {
            sb.append(name);
            sb.append(", count = " + count + " ");

            type.toStringBuilder(sb);
        }
        subnodes.forEach( (k, v) -> v.toStringBuilder( name.isEmpty() ? k : name + "." + k , sb));
    }

}
