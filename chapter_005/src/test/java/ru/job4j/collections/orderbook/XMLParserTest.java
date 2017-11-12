package ru.job4j.collections.orderbook;

import org.junit.Test;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

/**
 * Tests the class XMLParser.
 */
public class XMLParserTest {

    /**
     * Measurements time of running application in milliseconds.
     */
    @Test
    public void measurementOfApplicationsRumTime() {
        try {
            long start = System.currentTimeMillis();
            OrderBookStore<Book, Order> bookStore = new OrderBookStore<>();
            File inputFile = new File("/home/pacman/Downloads/orders.xml");
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            XMLParser userhandler = new XMLParser(bookStore);
            saxParser.parse(inputFile, userhandler);
            TraidingOrderBookReader reader = new TraidingOrderBookReader();
            Manager<Order> manager = new Manager<>();
            manager.setBook(bookStore);
            manager.leaveProfitableTrades();
            for (Book book : bookStore) {
                reader.setBook((TradingOperationsBook) book);
                System.out.println(reader.getContent());
            }
            System.out.println(System.currentTimeMillis() - start);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}