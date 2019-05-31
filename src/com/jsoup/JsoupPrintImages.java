package com.jsoup;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import org.jsoup.Jsoup;  
import org.jsoup.nodes.Document;  
import org.jsoup.nodes.Element;  
import org.jsoup.select.Elements; 

public class JsoupPrintImages {  
     public static void main( String[] args ) throws IOException
     {  
    	 Document doc = Jsoup.connect("https://www.amazon.in/s?k=laptop&ref=nb_sb_noss_2").get();  //fetching all Image files from given URL.
         Elements images = doc.select("img[src~=(?i)\\.(png|jpe?g|gif)]");  //Checking imgae's extensions
         
         
         try
    	 {
        	 int i=0;
        	 for (Element image : images) {  
        		 if(image.attr("src").contains("https://")) //checking "https:" protocol is available or not in image url
        		 {
        			 String imageUrl = image.attr("src");	//storing image url in string format
        			 String destinationFile = "image"+(i++)+".jpg"; // naming the destination file name for images
        			 saveImage(imageUrl, destinationFile); //storing image file in local system 
        			 //System.out.println("src : " + image.attr("src")); 
        		 }
        		 else
        			 continue; 
        	 }
        	 System.out.println(i+" images downloaded!!");
    	 }
    	 catch(Exception e)
    	 {
    		 System.out.println(e);
    	 }
     }  
     public static void saveImage(String imageUrl, String destinationFile) throws IOException {
 		URL url = new URL(imageUrl);
 		InputStream is = url.openStream();
 		OutputStream os = new FileOutputStream(destinationFile);

 		byte[] b = new byte[2048];
 		int length;

 		while ((length = is.read(b)) != -1) {
 			os.write(b, 0, length);
 		}

 		is.close();
 		os.close();
 	}
}  