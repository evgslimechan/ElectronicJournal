package com.slimechan.journal.server.models.news;

import com.slimechan.journal.server.models.users.User;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Document(collection = "news")
public class News implements Cloneable{

    @Id
    private int id;

    private String title;
    private String text;


    private User author;

    private Date date;

    private News(){}
    public News(String text, User author, Date date){
        this.text = text;
        this.author = author;
        this.date = date;
    }

    public User getAuthor() {
        return author;
    }
    public String getFormatedAuthor(){
        String author = this.author.getFio();
        String i = author.split(" ")[1].substring(0,1)+".";
        String o = author.split(" ")[2].substring(0,1)+".";
        return author.split(" ")[0]+" "+i+" "+o;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }
    public String getFormatedDate(){
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        return format.format(date);
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNewsBlock(){

        return "<div class='news'>"
                +"<h1>"+title+"</h1><br>"
                +"<a>"+text+"</a><br>"
                +"<a>"+getFormatedAuthor()+"</a><a>"+getFormatedDate()+"</a>"
                +"</div>";
    }

    @Override
    public News clone(){
        try {
            return (News) super.clone();
        }catch (CloneNotSupportedException e){
            e.printStackTrace();
            return null;
        }
    }
}
