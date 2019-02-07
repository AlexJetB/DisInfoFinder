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
    private List<Node> sourceList;
    private Document sourceHTML;
    private Integer nHits; // The number of times this source is accessed.

    // Default Constructor for debugging
    public HyperLinkSource() {
        articleURL = "https://sputniknews.com/europe/201812161070737533-britain-spain-nuclear-submarine-interception/";
        articleText = "";
        articleName = "";
        articleAuthor = "";
        publisher = "";
        sourceList = new ArrayList<Node>();
        try {
            sourceHTML = Jsoup.connect(articleURL).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        nHits = 0;
    }

    public HyperLinkSource(String articleURL) {
        this.articleURL = articleURL;
        try {
            this.sourceHTML = Jsoup.connect(articleURL).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        findArticle();
    }

    private void findArticle() {
        String newArticle = "";
        articleText = "";
        sourceList = new ArrayList<>();

        for (Element paragraphs : sourceHTML.select("p > a")) {
            if(paragraphs.hasText() ) {
                sourceList.addAll(paragraphs.childNodes());
            }
        }

        //System.out.println(articleText);
    }
}
