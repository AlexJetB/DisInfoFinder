package com.baker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

public class HyperLinkSource {
    private String articleURL, articleText, articleName, articleAuthor, publisher;
    private List<HyperLinkSource> sourceList;
    private Document sourceHTML;
    private Integer depthN;
    private Integer nHits; // The number of times this source is accessed.

    // Default Constructor for debugging
    public HyperLinkSource() {
        articleURL = "https://sputniknews.com/europe/201812161070737533-britain-spain-nuclear-submarine-interception/";
        articleText = "";
        articleName = "";
        articleAuthor = "";
        publisher = "";
        sourceList = new ArrayList<HyperLinkSource>();
        depthN = 0;
        try {
            sourceHTML = Jsoup.connect(articleURL).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        nHits = 0;
    }

    public HyperLinkSource(String articleURL, Integer depthN) {
        this.articleURL = articleURL;
        this.depthN = depthN;
        try {
            this.sourceHTML = Jsoup.connect(articleURL).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        findArticle();
    }

    private void findArticle() {
        String subURL = "";
        HyperLinkSource tempHolder;
        articleText = "";
        sourceList = new ArrayList<>();

        for (Element link : sourceHTML.select("p > a")) {
            if(link.hasText() && !depthN.equals(3)) {
                subURL = link.attr("abs:href");
                depthN++;
                tempHolder = new HyperLinkSource(subURL, depthN);
                sourceList.add(tempHolder);
                // Temp checkers/debugging
                System.out.println("Op Completed. Depth = " + depthN);
                System.out.println(subURL);
            }
            else {
                System.out.println("Op Completed. Depth = " + depthN);
                System.out.println(subURL);
                return;
            }
        }
        //System.out.println(articleText);
    }
}
