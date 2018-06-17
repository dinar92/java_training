package sqlru.jobparser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sqlru.core.DateTimeExtractor;

import java.io.IOException;
import java.time.LocalDateTime;

public class Topic {

    private String title;
    private String author;
    private LocalDateTime dateTime;
    private String offerLink;
    private Offer offer = new Offer();
    private final Logger logger = LoggerFactory.getLogger(Topic.class);

    public Topic(String title, String author, LocalDateTime dateTime, String offerLink) {
        this.title = title;
        this.author = author;
        this.dateTime = dateTime;
        this.offerLink = offerLink;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public Offer getContent() {
        Document offerPage = null;
        try {
            offerPage = Jsoup.connect(offerLink).get();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        DateTimeExtractor extractor = new DateTimeExtractor();

        offer.setTitle(offerPage.selectFirst(".messageHeader").text());
        Element msgBody = offerPage.selectFirst(".msgBody");
        offer.setAuthor(msgBody.select("a").text());
        offer.setContent(msgBody.nextElementSibling().text());
        String footer = offerPage.selectFirst(".msgFooter").text();
        offer.setPostTimeDate(extractor.extract(footer));
        offer.setUrl(offerLink);
        return offer;
    }
}
