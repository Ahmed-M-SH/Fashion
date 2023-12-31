
package com.example.fashion.Domain;

import java.util.List;

import com.example.fashion.Domain.SubCategory;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class Category {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("sub_category")
    @Expose
    private List<SubCategory> subCategory;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("category_image")
    @Expose
    private String categoryImage;
    @SerializedName("lft")
    @Expose
    private Integer lft;
    @SerializedName("rght")
    @Expose
    private Integer rght;
    @SerializedName("tree_id")
    @Expose
    private Integer treeId;
    @SerializedName("level")
    @Expose
    private Integer level;
    @SerializedName("parent")
    @Expose
    private Object parent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<SubCategory> getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(List<SubCategory> subCategory) {
        this.subCategory = subCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategoryImage() {
        return categoryImage;
    }

    public void setCategoryImage(String categoryImage) {
        this.categoryImage = categoryImage;
    }

    public Integer getLft() {
        return lft;
    }

    public void setLft(Integer lft) {
        this.lft = lft;
    }

    public Integer getRght() {
        return rght;
    }

    public void setRght(Integer rght) {
        this.rght = rght;
    }

    public Integer getTreeId() {
        return treeId;
    }

    public void setTreeId(Integer treeId) {
        this.treeId = treeId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Object getParent() {
        return parent;
    }

    public void setParent(Object parent) {
        this.parent = parent;
    }

}
