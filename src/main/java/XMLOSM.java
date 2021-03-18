import org.xml.sax.Attributes;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class XMLOSM implements XMLSubhandler.IXMLNodeHandler
{
    ArrayList<Node> nodes = new ArrayList<>();
    ArrayList<Way> ways = new ArrayList<>();

    class Node implements XMLSubhandler.IXMLNodeHandler {
        long id;
        double lat, lon;

        @Override
        public String toString() {
            return String.format("{id=%d, lat=%f, lon=%f}", id, lat, lon);
        }

        Node(Attributes atts) {
            //version = atts.getValue("version");
            //timestamp = atts.getValue("timestamp");
            id = Long.parseLong(atts.getValue("id"));
            lat = Double.parseDouble(atts.getValue("lat"));
            lon = Double.parseDouble(atts.getValue("lon"));
        }
    }

    class Way implements XMLSubhandler.IXMLNodeHandler {
        long id;
        ArrayList<Long> ref = new ArrayList<>();

        @Override
        public String toString() {
            return "id=" + id + ", " + ref.toString();
        }

        Way(Attributes atts) {
            //version = atts.getValue("version");
            //timestamp = atts.getValue("timestamp");
            id = Long.parseLong(atts.getValue("id"));
        }

        @Override
        public XMLSubhandler.IXMLNodeHandler startElement(String namespaceURI, String localName, String qName, Attributes atts) {
            int i = atts.getIndex("ref");
            if(i != -1)
                ref.add( Long.parseLong(atts.getValue(i)) );
            return null;
        }

        @Override
        public void addString(String s) {

        }
    }

    @Override
    public String toString() {
        return "ways = " + ways.toString() + ", nodes = " + nodes.toString();
    }

    @Override
    public XMLSubhandler.IXMLNodeHandler startElement(String uri, String localName,
                                                      String qName, Attributes attributes) {
        if( qName.equals("node" )) {
            Node n = new Node(attributes);
            nodes.add(n);
            return n;
        }
        else if( qName.equals("way")) {
            Way w = new Way(attributes);
            ways.add(w);
            return w;
        }
        else if( qName.equals("osm" ))
            return this;
        else
            return null;
    }

    @Override
    public void addString(String s)
    {

    }
}
