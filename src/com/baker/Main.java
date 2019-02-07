package com.baker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter the article's URL: ");
        String urlName = input.next();

        HyperLinkSource test = new HyperLinkSource(urlName);
    }
}
