package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions)
    {
        //add an image to the blog
        Blog blog=blogRepository2.findById(blogId).get();
        //let's Image Entity.. here we go...
        Image image=new Image();
        image.setDescription(description);
        image.setDimensions(dimensions);

        image.setBlog(blog);
        blog.getImageList().add(image);

        //save the parent will cascade it..
        blogRepository2.save(blog);
        return image;

    }

    public void deleteImage(Integer id){
        imageRepository2.deleteById(id);
    }

    public int countImagesInScreen(Integer id, String screenDimensions)
    {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        Image image=imageRepository2.findById(id).get();

        String givenDimensions= image.getDimensions();
        String[]givenDimension=givenDimensions.split("X");
        String[]screenDimension=screenDimensions.split("X");

       int givenWidth=Integer.parseInt(givenDimension[0]);
       int givenHeight=Integer.parseInt(givenDimension[1]);

       int screenWidth=Integer.parseInt(screenDimension[0]);
       int screenHeight=Integer.parseInt(screenDimension[1]);

       int width=screenWidth/givenWidth;
       int height=screenHeight/givenHeight;
       return width*height;

    }
}
