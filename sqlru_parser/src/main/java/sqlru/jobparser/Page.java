package sqlru.jobparser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sqlru.core.DateTimeExtractor;
import sqlru.core.Resource;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * A page implementation of sql.ru.
 */
public class Page {

    /**
     * The logger of errors.
     */
    private final Logger logger = LoggerFactory.getLogger(Page.class);

    /**
     * The URL of the first page with the job offers.
     */
    private final String mainPage = "http://www.sql.ru/forum/job-offers";

    /**
     * HTML of the page.
     */
    private Document page;

    private Resource resource = new Resource();
    /**
     * Sets page number, that will be parse.
     *
     * @param numberOfPage - the page number. If input is 0, then will be setted first page.
     * @return - success of page getting.
     */
    public boolean setPageNumber(int numberOfPage) {
        boolean setSuccess = true;
        String number = (numberOfPage == 0) ? "" : String.valueOf(numberOfPage);
        String address = String.format("%s/%s", mainPage, number);

        try {
            page = Jsoup.connect(address).get();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            setSuccess = false;
        }
        return setSuccess;
    }

    /**
     * Returns list of topics from the specified page, that contains offers for java programmers.
     *
     * @return - empty list if the page not contains offers or list of the job topics.
     * @throws Exception - if the page number is not installed.
     */
    public List<Topic> topicsList() throws Exception {
        List<Topic> topicsList = new ArrayList<>();
        if (page == null) {
            throw new Exception("The page number is not installed!");
        }
        String linkToOffer;
        LocalDateTime dateTime;
        String author;
        String title;
        Elements elements = page.select(String.format("tr:has(a:matches(%s)))", resource.content("jobsRegex.config")));
        DateTimeExtractor extractor = new DateTimeExtractor();

        for (Element topic
                :
                elements) {
            linkToOffer = topic.selectFirst(".postslisttopic a").attr("abs:href");
            dateTime = extractor.extract(topic.selectFirst("td[style] + td.altcol").text());
            author = topic.selectFirst(".altcol").text();
            title = topic.selectFirst(".postslisttopic a").text();
            topicsList.add(new Topic(title, author, dateTime, linkToOffer));
        }
        return topicsList;
    }
}
