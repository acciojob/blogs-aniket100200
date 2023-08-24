package com.driver.services;

import com.driver.models.Blog;
import com.driver.models.Image;
import com.driver.models.User;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.ImageRepository;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BlogService {
    @Autowired
    BlogRepository blogRepository1;

    @Autowired
    UserRepository userRepository1;

    public Blog createAndReturnBlog(Integer userId, String title, String content)
    {
        //create a blog at the current time

        Blog blog=new Blog();
       Optional<User>optional =userRepository1.findById(userId);
       if(optional.isPresent()==false)return blog;
       User user=optional.get();

       blog.setPubDate(new Date());

        blog.setTitle(title);
        blog.setContent(content);
        blog.setImageList(new ArrayList<>());

        //bidirectional mapping ka istemaal..
        blog.setUser(user);
        List<Blog>blogList=user.getBlogList();
        if(blogList==null)blogList=new ArrayList<>();
        blogList.add(blog);
        user.setBlogList(blogList);

       userRepository1.save(user);
       blog=blogRepository1.save(blog);
        return blog;

    }

    public void deleteBlog(int blogId)
    {
        //delete blog and corresponding images
        blogRepository1.deleteById(blogId);

    }
}
