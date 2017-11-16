package ru.job4j.collections.orderbook;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * The parser for order books from XML.
 */
public class XMLParser extends DefaultHandler {

    /**
     * The instance of the book store.
     */
    private final BookStore store;

    /**
     * The string view for adding orders.
     */
    private final static String addOrder = "AddOrder";

    /**
     * The string view for removing orders.
     */
    private final static String deleteOrder = "DeleteOrder";

    /**
     * Sets the book store.
     * @param store - the book store.
     */
    public XMLParser(BookStore store) {
        this.store = store;
    }


    /**
     * Receive notification of the start of an element.
     *
     * @param uri The Namespace URI, or the empty string if the
     *        element has no Namespace URI or if Namespace
     *        processing is not being performed.
     * @param localName The local name (without prefix), or the
     *        empty string if Namespace processing is not being
     *        performed.
     * @param qName The qualified name (with prefix), or the
     *        empty string if qualified names are not available.
     * @param attributes The attributes attached to the element.  If
     *        there are no attributes, it shall be an empty
     *        Attributes object.
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (addOrder.equalsIgnoreCase(qName)) {
            Book book = store.getBookByName(attributes.getValue("book"));
            if (book == null) {
                book = new OrderBook(attributes.getValue("book"));
                store.addBook(book);
            }
            book.addOrder(new Order(Status.getByName(attributes.getValue("operation")),
                    Integer.parseInt(attributes.getValue("orderId")),
                    Double.parseDouble(attributes.getValue("price")),
                    Integer.parseInt(attributes.getValue("volume"))));
        } else if (deleteOrder.equalsIgnoreCase(qName)) {
            Book book = store.getBookByName(attributes.getValue("book"));
            book.deleteOrder(book.getById(Integer.parseInt(attributes.getValue("orderId"))));
        }
    }
}
