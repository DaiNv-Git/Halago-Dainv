package com.example.halagodainv.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "news")
public class NewsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_news")
    int idNews;
    @Column(name = "thumbnail")
    String thumbnail;
    @Column(name = "created")
    @JsonFormat(pattern = "dd-MM-yyyy", timezone = "Asia/Ho_Chi_Minh")
    private Date created;
    @Column(name = "type")
    int type;
    @Column(name = "title_seo")
    String titleSeo;
    @Column(name = "link_papers")
    String linkPapers;
    @Column(name = "topic_id")
    private Long topicId;
    @Column(name = "productId")
    private Integer productId;
    @Column(name = "is_product")
    private Integer isProduct;
    @Column(name = "tagId")
    private String tagId;
    @Column(name = "tag_name")
    private String tagName;
    @Column(name = "is_hot")
    private Boolean isHot;
    @Column(name = "author_name")
    private String authorName;
    @Column(name = "author_avatar")
    private String authorAvatar;
    @Column(name = "representative_id")
    private Long representativeId;
    @JsonIgnore
    @OneToMany(mappedBy = "newsEntity", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<NewsLanguageEntity> imageBrandMains = new ArrayList<>();

    public NewsEntity() {
    }

    public NewsEntity(int idNews, String thumbnail, Date created, int type, String titleSeo, String linkPapers, Long topicId, int productId, int isProduct, String tagId, String tagName, Boolean isHot, String authorName, String authorAvatar, Long representativeId, List<NewsLanguageEntity> imageBrandMains) {
        this.idNews = idNews;
        this.thumbnail = thumbnail;
        this.created = created;
        this.type = type;
        this.titleSeo = titleSeo;
        this.linkPapers = linkPapers;
        this.topicId = topicId;
        this.productId = productId;
        this.isProduct = isProduct;
        this.tagId = tagId;
        this.tagName = tagName;
        this.isHot = isHot;
        this.authorName = authorName;
        this.authorAvatar = authorAvatar;
        this.representativeId = representativeId;
        this.imageBrandMains = imageBrandMains;
    }

    public void setIdNews(int idNews) {
        this.idNews = idNews;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setTitleSeo(String titleSeo) {
        this.titleSeo = titleSeo;
    }

    public void setLinkPapers(String linkPapers) {
        this.linkPapers = linkPapers;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setIsProduct(int isProduct) {
        this.isProduct = isProduct;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public void setHot(Boolean hot) {
        isHot = hot;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public void setAuthorAvatar(String authorAvatar) {
        this.authorAvatar = authorAvatar;
    }

    public void setRepresentativeId(Long representativeId) {
        this.representativeId = representativeId;
    }

    public void setImageBrandMains(List<NewsLanguageEntity> imageBrandMains) {
        this.imageBrandMains = imageBrandMains;
    }

    public int getIdNews() {
        return idNews;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public Date getCreated() {
        return created;
    }

    public int getType() {
        return type;
    }

    public String getTitleSeo() {
        return titleSeo;
    }

    public String getLinkPapers() {
        return linkPapers;
    }

    public Long getTopicId() {
        return topicId;
    }

    public int getProductId() {
        return productId;
    }

    public int getIsProduct() {
        return isProduct;
    }

    public String getTagId() {
        return tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public Boolean getHot() {
        return isHot;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getAuthorAvatar() {
        return authorAvatar;
    }

    public Long getRepresentativeId() {
        return representativeId;
    }

    public List<NewsLanguageEntity> getImageBrandMains() {
        return imageBrandMains;
    }
}
