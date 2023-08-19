package com.driver.models;



import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table
public  class Image
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;

    private String description;
    private String dimension;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    //child of blog so we'll do bidirectional mapping..
    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private Blog blog;
}