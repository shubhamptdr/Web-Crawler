package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;

public class Crawler {
    /* declare the Hashset of string*/
    private HashSet<String> urlLink;
    /* declare the connection */
    public Connection connection;

    /* constructor */

    public Crawler(){

        /* Intializes the connection */
        connection = DatabaseConnection.getConnection();
        /* Intializes the Hashset */
        urlLink = new HashSet<String>();

    }
    public void getPageTextAndLink(String URL,int depth){
        if(!urlLink.contains(URL)){
            try{
                if(urlLink.add(URL)){
                    System.out.println(URL);
                }
                Document document = Jsoup.connect(URL).userAgent("chrome").timeout(5000).get();

                String text = document.text().length()<501?document.text():document.text().substring(0,500);

                /* store data to  database i.e sql12580037 in table name pages */
                PreparedStatement preparedStatement = connection.prepareStatement("Insert Into pages values (?,?,?)");
                preparedStatement.setString(1,document.title());
                preparedStatement.setString(2,URL);
                preparedStatement.setString(3,text);
                preparedStatement.executeUpdate();

                depth++;
                if(depth == 2){
                    return;
                }

                Elements availableLinkOnPage = document.select("a[href]");
                for(Element element:availableLinkOnPage){
                    getPageTextAndLink(element.attr("abs:href"),depth);
                }

            }
            /* Handle  exceptions */
            catch (IOException e){
                e.printStackTrace();
            }catch (SQLException sqlException){
                sqlException.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        /* create class object of crawler get its details */
        Crawler crawler = new Crawler();
        /* Invoked the “getPageTextAndLink()” method using the object of the “crawlerClass” */
        crawler.getPageTextAndLink("https://www.javatpoint.com",0);
    }
}