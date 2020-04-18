package com.kgisl.rssfeed;

import android.os.AsyncTask;
import android.util.Log;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class TOIFeedTask extends AsyncTask<Void, Void, List<News>> {
    List<News> newsList = new ArrayList<News>();
    @Override
    protected List<News> doInBackground(Void... voids) {
        try {
            //URL url = new URL("https://timesofindia.indiatimes.com/rssfeedstopstories.cms");
            URL url = new URL("https://www.thehindu.com/news/cities/Coimbatore/feeder/default.rss");
            URLConnection urlcon = url.openConnection();

            InputStream ins = urlcon.getInputStream();


            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(ins);

            document.getDocumentElement().normalize();

            Element root = document.getDocumentElement();
            System.out.println(root.getNodeName());

            NodeList nList = document.getElementsByTagName("item");


            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node node = nList.item(temp);
                System.out.println("");
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    String date = eElement.getElementsByTagName("pubDate").item(0).getTextContent();
                    String title = eElement.getElementsByTagName("title").item(0).getTextContent();
                    String description = eElement.getElementsByTagName("description").item(0).getTextContent();
                    newsList.add(new News(date,title,description));

                    Log.d("news",date);
                    Log.d("news",title);
                    Log.d("news",description);
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        for(News news:newsList){
            Log.d("arli",news.toString());
        }
        return newsList;
    }
}
