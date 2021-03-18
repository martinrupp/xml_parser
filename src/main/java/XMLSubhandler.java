import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.Stack;

public class XMLSubhandler extends DefaultHandler {
    IXMLNodeHandler currentNode;
    Stack<IXMLNodeHandler> nodes = new Stack<IXMLNodeHandler>();

    // XmlHandlerWithSubHandlers will use this for sub-nodes of the XML tree
    interface IXMLNodeHandler
    {
        /**
         * for the parameters @see org.xml.sax.helpers.DefaultHandler#startElement
         * @return a newly created IXMLNodeHandler handling this element
         * all future calls to startElement/addString will go to that element, until endElement is called
         * startElement may return null, meaning we don't want to process anything from this element or its subelements
         */
        default IXMLNodeHandler startElement(String namespaceURI, String localName, String qName, Attributes atts) {
            // ignore sub-nodes
            return null;
        }
        // end the current parsing of this element type
        // for parameters @see org.xml.sax.helpers.DefaultHandler#endElement
        default void endElement(String uri, String localName, String qName) {}

        // this function will be called by XmlHandlerWithSubHandlers for all
        // calls of @see org.xml.sax.helpers.DefaultHandler#characters
        // we will create a string for that, and then call addString
        default void addString(String s) {}
    }
    XMLSubhandler(IXMLNodeHandler rootHandler)
    {
        currentNode = rootHandler;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        nodes.push(currentNode);
        if(currentNode != null)
            currentNode = currentNode.startElement(uri, localName, qName, attributes);
    }

    @Override
    public void endElement(String uri, String localName, String qName)
    {
        currentNode = nodes.pop();
        if(currentNode != null)
            currentNode.endElement( uri, localName, qName );
    }

    public static int skipWhitespace( char[] ch, int start, int length )
    {
        int i = 0;
        while( i < length && (ch[start+i] == ' ' || ch[start+i] == '\r'
                || ch[start+i] == '\n' || ch[start+i] == '\t') )
            i++;
        return i;
    }
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if( currentNode == null ) return;

        int skipped = skipWhitespace(ch, start, length);
        start += skipped; length -= skipped;
        if( length == 0 ) return;
        String s = new String(ch, start, length);
        currentNode.addString( s );
    }
}
