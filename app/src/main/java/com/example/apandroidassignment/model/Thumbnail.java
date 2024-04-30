package com.example.apandroidassignment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Thumbnail {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("version")
    @Expose
    private Integer version;
    @SerializedName("domain")
    @Expose
    private String domain;
    @SerializedName("basePath")
    @Expose
    private String basePath;
    @SerializedName("key")
    @Expose
    private String key;
    @SerializedName("qualities")
    @Expose
    private List<Integer> qualities;
    @SerializedName("aspectRatio")
    @Expose
    private Integer aspectRatio;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<Integer> getQualities() {
        return qualities;
    }

    public void setQualities(List<Integer> qualities) {
        this.qualities = qualities;
    }

    public Integer getAspectRatio() {
        return aspectRatio;
    }

    public void setAspectRatio(Integer aspectRatio) {
        this.aspectRatio = aspectRatio;
    }
}
