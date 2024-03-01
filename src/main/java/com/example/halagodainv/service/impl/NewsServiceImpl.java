package com.example.halagodainv.service.impl;

import com.example.halagodainv.common.Language;
import com.example.halagodainv.dto.news.NewDetails;
import com.example.halagodainv.dto.news.NewDto;
import com.example.halagodainv.dto.news.NewDtoDetails;
import com.example.halagodainv.dto.news.NewRelationTopicDto;
import com.example.halagodainv.dto.topic.TopicDto;
import com.example.halagodainv.dto.viewnews.*;
import com.example.halagodainv.exception.ErrorResponse;
import com.example.halagodainv.exception.GeneralException;
import com.example.halagodainv.model.NewsEntity;
import com.example.halagodainv.model.NewsLanguageEntity;
import com.example.halagodainv.model.TopicEntity;
import com.example.halagodainv.model.viewdisplayentity.TagEntity;
import com.example.halagodainv.repository.NewsLanguageRepository;
import com.example.halagodainv.repository.NewsRepository;
import com.example.halagodainv.repository.viewdisplay.TagRepository;
import com.example.halagodainv.repository.viewdisplay.TopicRepository;
import com.example.halagodainv.request.news.NewsAddRequest;
import com.example.halagodainv.request.news.NewsFormSearch;
import com.example.halagodainv.response.BaseResponse;
import com.example.halagodainv.response.PageResponse;
import com.example.halagodainv.service.NewsService;
import com.example.halagodainv.until.DateUtilFormat;
import com.example.halagodainv.until.FileImageUtil;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.*;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {
    private final NewsLanguageRepository newsLanguageRepository;
    private final TopicRepository topicRepository;
    private final NewsRepository newsRepository;
    private final TagRepository tagRepository;
    private final FileImageUtil fileImageUtil;

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public  BaseResponse<?> getNews(NewsFormSearch newsSearch) {
//        try {
            int offset = 0;
            if (newsSearch.getPageNo() > 0) {
                offset = newsSearch.getPageNo() - 1;
            }
            StringBuilder selectNewAdmin= new StringBuilder();
            selectNewAdmin.append("select n.id_news as id,nl.title,n.thumbnail as img, DATE_FORMAT(n.created,'%Y-%m-%d') as created, n.tag_name as tagNames, ")
                    .append("CASE " )
                    .append("WHEN n.topic_id = 1 THEN 'Các dự án đã triển khai' " )
                    .append("WHEN n.topic_id = 2 THEN 'Dự án hợp tác cùng KOL,Celeb' " )
                    .append("WHEN n.topic_id = 3 THEN 'Tin tức HOT về Influencer KO' " )
                    .append("WHEN n.topic_id = 4 THEN 'Cập nhật tin tức về thị trường Influencer marketing' " )
                    .append("WHEN n.topic_id = 5 THEN 'Phương pháp tối ưu hiệu quả khi triển khai Influencer marketing' " )
                    .append("WHEN n.topic_id = 6 THEN 'Case study cùng nhãn hàng' " )
                    .append("WHEN n.topic_id = 7 THEN 'Lưu ý dành riêng cho các bạn' " )
                    .append("WHEN n.topic_id = 8 THEN 'Halago - Hoạt động tiêu biểu' " )
                    .append("ELSE '' " )
                    .append("END  as topicName from news n left join news_language nl on n.id_news = nl.new_id ");
            StringBuilder querySql = new StringBuilder();
            querySql.append(selectNewAdmin);
            sqlNew(querySql, "vn", newsSearch.getTopicId(), newsSearch.getTagId());
            querySql.append(" and (nl.title like '%").append(newsSearch.getTitle()).append("%') ");
            querySql.append(" and n.is_hot = ").append(newsSearch.getIsHot());
            querySql.append(" order by n.created desc limit ").append(newsSearch.getPageSize()).append(" offset ").append(offset * 10);
            Query nativeQuery = entityManager.createNativeQuery(querySql.toString());
            StringBuilder count = new StringBuilder();
            count.append(selectNewAdmin);
            sqlNew(count, "vn", newsSearch.getTopicId(), newsSearch.getTagId());
            count.append(" and (nl.title like '%").append(newsSearch.getTitle()).append("%') ");
            count.append(" and n.is_hot = ").append(newsSearch.getIsHot());
            Query totalNews = entityManager.createNativeQuery(count.toString());
            List<ViewNewsDto> newsDtos = nativeQuery.unwrap(NativeQuery.class).setResultTransformer(Transformers.aliasToBean(NewDto.class)).getResultList();
            List<ViewNewsDto> countQuery = totalNews.unwrap(NativeQuery.class).setResultTransformer(Transformers.aliasToBean(NewDto.class)).getResultList();
            Pageable pageable = PageRequest.of(offset, newsSearch.getPageSize());
            return new BaseResponse<>(200, "Lấy dữ liệu thành công", new PageResponse<>(new PageImpl<>(newsDtos, pageable, CollectionUtils.isEmpty(countQuery) ? 0 : countQuery.size())));
//        } catch (Exception e) {
//            return new ErrorResponse<>(500, "Lấy dữ liệu thất bại", null);
//        }
    }

    @Override
    public Object getDetail(int newId) {
        try {
            List<NewDetails> newsRepositoryDetail = newsRepository.getHomeLanguage(newId);
            Set<NewDtoDetails> newDtoDetail = new HashSet<>();
            NewDtoDetails newewDtoDetailsss = new NewDtoDetails();
            newsRepositoryDetail.forEach(
                    i -> {
                        newewDtoDetailsss.setIdNews(i.getIdNews());
                        newewDtoDetailsss.setImg(i.getThumbnail());
                        newewDtoDetailsss.setType(i.getType());
                        newewDtoDetailsss.setLinkPost(i.getLinkPost() == null ? "" : i.getLinkPost());
                        newewDtoDetailsss.setPhotoTitle(i.getPhotoTitle() == null ? "" : i.getPhotoTitle());
                        newewDtoDetailsss.setTopicId(i.getTopicId());
                        newewDtoDetailsss.setTagId(InfluencerServiceImpl.parseStringToListOfIntegers(i.getTagId()));
                        newewDtoDetailsss.setIsHot(i.getIsHot());
                        newewDtoDetailsss.setAuthorName(i.getAuthorName());
                        newewDtoDetailsss.setAuthorAvatar(i.getAuthorAvatar());
                        newewDtoDetailsss.setTagNames(i.getTagName());
                        TopicEntity topic = topicRepository.findById(i.getTopicId()).get();
                        if (i.getLanguage().equalsIgnoreCase("VN")) {
                            newewDtoDetailsss.setContentVN(i.getContent());
                            newewDtoDetailsss.setDescriptionVN(i.getDescription());
                            newewDtoDetailsss.setTopicName(topic.getTopicName());
                            newewDtoDetailsss.setTitleVN(i.getTitle());

                        } else if (i.getLanguage().equalsIgnoreCase("EN")) {
                            newewDtoDetailsss.setTitleEN(i.getTitle());
                            newewDtoDetailsss.setDescriptionEN(i.getDescription());
                            newewDtoDetailsss.setContentEN(i.getContent());
                            newewDtoDetailsss.setTopicName(topic.getTopicNameEN());
                        }
                    });
            newDtoDetail.add(newewDtoDetailsss);
            return new BaseResponse<>(200, "lấy dữ liệu chi tiết thành công", newDtoDetail);
        } catch (Exception e) {
            return new ErrorResponse<>(500, "Lấy dữ liệu chi tiết thất bại", null);
        }
    }

    public PageResponse<?> getViewNews(int pageNo, int pageSize, String language, Long topicId, Long tagId) {
        String getNewsConverterSql = "select n.id_news as id,nl.title,n.thumbnail as img, DATE_FORMAT(n.created,'%Y-%m-%d') as createdDate from news n left join news_language nl on n.id_news = nl.new_id ";
        int offset = pageNo > 0 ? pageNo - 1 : 0;
        Pageable pageable = PageRequest.of(offset, pageSize);
        StringBuilder sql = new StringBuilder();
        sql.append(getNewsConverterSql);
        sqlNew(sql, language, topicId, tagId);
        sql.append(" order by n.created desc limit ").append(pageSize).append(" offset ").append(offset * 10);
        Query nativeQuery = entityManager.createNativeQuery(sql.toString());
        StringBuilder count = new StringBuilder();
        count.append(getNewsConverterSql);
        sqlNew(count, language, topicId, tagId);
        Query totalNews = entityManager.createNativeQuery(count.toString());
        List<ViewNewsDto> newsDtos = nativeQuery.unwrap(NativeQuery.class).setResultTransformer(Transformers.aliasToBean(ViewNewsDto.class)).getResultList();
        List<ViewNewsDto> countQuery = totalNews.unwrap(NativeQuery.class).setResultTransformer(Transformers.aliasToBean(ViewNewsDto.class)).getResultList();
        return new PageResponse<>(new PageImpl<>(newsDtos, pageable, CollectionUtils.isEmpty(countQuery) ? 0 : countQuery.size()));
    }

    private static StringBuilder sqlNew(StringBuilder sql, String language, Long topicId, Long tagId) {
        sql.append(" where nl.language = '").append(language).append("' and n.news_from_kol <> 1 ");
        if (topicId != null && topicId > 0L) {
            sql.append(" and n.topic_id = ").append(topicId);
        }

        if (tagId != null && tagId > 0L) {
            sql.append(" and (n.tag_id LIKE '").append(tagId)
                    .append(",%' OR n.tag_id LIKE '%, ").append(tagId)
                    .append(",%' OR n.tag_id LIKE '%, ").append(tagId)
                    .append("' OR n.tag_id like ").append("'%").append(tagId).append("%')");
        }
        return sql;
    }

    public ViewNewsDetailDto getViewNewsDetail(int id, String language) {
        ViewNewsMap viewNewsMaps = newsRepository.getDetailView(language, id);

        if (ObjectUtils.isEmpty(viewNewsMaps)) {
            return null;
        }
        ViewNewsDetailDto viewNewsDetailDto = new ViewNewsDetailDto();
        viewNewsDetailDto.setId(viewNewsMaps.getId());
        viewNewsDetailDto.setTitle(viewNewsMaps.getTitle());
        viewNewsDetailDto.setContent(viewNewsMaps.getContent());
        viewNewsDetailDto.setCreatedDate(viewNewsMaps.getCreatedDate());
        viewNewsDetailDto.setTagId(viewNewsMaps.getTagId());
        viewNewsDetailDto.setTopicId(viewNewsMaps.getTopicId());
        TopicEntity topic = topicRepository.findById((long) viewNewsMaps.getTopicId()).get();
        viewNewsDetailDto.setTopicName(topic.getTopicName());
        return viewNewsDetailDto;
    }

    public ViewNewsAndHotDetailDto getViewNewsAndHots(String language) {
        Pageable pageableViewNews = PageRequest.of(0, 3, Sort.Direction.DESC, "created");
        Pageable pageableIsHot = PageRequest.of(0, 1);
        List<ViewNewAndHot> viewHotMaps = newsRepository.getViewhots(0L, "", language, pageableIsHot);
        List<ViewNewAndHot> viewNews = newsRepository.getViewNew(0L, "", language, pageableViewNews);
        ViewTopicDto viewTopicDto = new ViewTopicDto();
        ViewTopicDto viewTopicDto1 = new ViewTopicDto();
        ViewTopicDto viewTopicDto2 = new ViewTopicDto();
        ViewTopicDto viewTopicDto3 = new ViewTopicDto();
        ViewTopicDto viewTopicDto4 = new ViewTopicDto();
        ViewTopicDto viewTopicDto5 = new ViewTopicDto();
        ViewTopicDto viewTopicDto6 = new ViewTopicDto();
        List<ViewTopicDto> viewNewsTopicDto = new ArrayList<>();
        viewTopicDto.setId(topicRepository.findAll().get(0).getId());
        viewTopicDto1.setId(topicRepository.findAll().get(1).getId());
        viewTopicDto2.setId(topicRepository.findAll().get(2).getId());
        viewTopicDto3.setId(topicRepository.findAll().get(3).getId());
        viewTopicDto4.setId(topicRepository.findAll().get(4).getId());
        viewTopicDto5.setId(topicRepository.findAll().get(5).getId());
        viewTopicDto6.setId(topicRepository.findAll().get(7).getId());

        viewTopicDto.setTitle(language.equals("VN") ? topicRepository.findAll().get(0).getTopicName() : topicRepository.findAll().get(0).getTopicNameEN());
        viewTopicDto1.setTitle(language.equals("VN") ? topicRepository.findAll().get(1).getTopicName() : topicRepository.findAll().get(1).getTopicNameEN());
        viewTopicDto2.setTitle(language.equals("VN") ? topicRepository.findAll().get(2).getTopicName() : topicRepository.findAll().get(2).getTopicNameEN());
        viewTopicDto3.setTitle(language.equals("VN") ? topicRepository.findAll().get(3).getTopicName() : topicRepository.findAll().get(3).getTopicNameEN());
        viewTopicDto4.setTitle(language.equals("VN") ? topicRepository.findAll().get(4).getTopicName() : topicRepository.findAll().get(4).getTopicNameEN());
        viewTopicDto5.setTitle(language.equals("VN") ? topicRepository.findAll().get(5).getTopicName() : topicRepository.findAll().get(5).getTopicNameEN());
        viewTopicDto6.setTitle(language.equals("VN") ? topicRepository.findAll().get(7).getTopicName() : topicRepository.findAll().get(7).getTopicNameEN());

        viewNewsTopicDto.add(viewTopicDto);
        viewNewsTopicDto.add(viewTopicDto1);
        viewNewsTopicDto.add(viewTopicDto2);
        viewNewsTopicDto.add(viewTopicDto3);
        viewNewsTopicDto.add(viewTopicDto4);
        viewNewsTopicDto.add(viewTopicDto6);

        List<ViewNewsHotDto> viewNewDtos = new ArrayList<>();
        List<ViewNewsHotDto> viewNewHots = new ArrayList<>();
        viewNews.forEach(viewMap -> {
            ViewNewsHotDto viewNew = new ViewNewsHotDto();
            viewNew.setTitle(viewMap.getTitle());
            viewNew.setImg(viewMap.getImage());
            viewNew.setCreated(DateUtilFormat.convertDateToString(viewMap.getCreated(), "yyyy-MM-dd"));
            viewNewDtos.add(viewNew);
        });

        viewHotMaps.forEach(viewMap -> {
            ViewNewsHotDto viewNewsHotDto = new ViewNewsHotDto();
            viewNewsHotDto.setTitle(viewMap.getTitle());
            viewNewsHotDto.setImg(viewMap.getImage());
            viewNewsHotDto.setCreated(DateUtilFormat.convertDateToString(viewMap.getCreated(), "yyyy-MM-dd"));
            viewNewHots.add(viewNewsHotDto);
        });
        return new ViewNewsAndHotDetailDto(viewNewsTopicDto, viewNewDtos, viewNewHots);
    }

    @Override
    @Transactional
    public Object insertNews(NewsAddRequest request) {
        try {
            List<NewsEntity> isCheckHot = newsRepository.findByIsHot(request.getIsHot());
            if (Boolean.TRUE.equals(request.getIsHot()) && isCheckHot.size() != 0) {
                return new ErrorResponse<>(500, "Đã tồn tại tin tức nổi bật", null);
            }
            //add news
            NewsEntity newsEntity = new NewsEntity();
            NewsLanguageEntity newsEN = new NewsLanguageEntity();
            NewsLanguageEntity newsVN = new NewsLanguageEntity();
            newsEntity.setThumbnail(fileImageUtil.uploadImage(request.getImg()));
            newsEntity.setCreated(new Date());
            newsEntity.setTitleSeo(request.getPhotoTitle());
            newsEntity.setLinkPapers(request.getLinkPost());
            newsEntity.setType(request.getType());
            newsEntity.setAuthorName(request.getAuthorName());
            newsEntity.setAuthorAvatar(fileImageUtil.uploadImage(request.getAuthorAvatar()));
            newsEntity.setTopicId(request.getTopicId());
            if (!request.getTagId().isEmpty()) {
                newsEntity.setTagId(InfluencerServiceImpl.parseListIntegerToString(request.getTagId()));
                StringJoiner stringJoiner = new StringJoiner(", ");
                List<TagEntity> tagEntities = tagRepository.findByIdIn(request.getTagId());
                for (TagEntity tagEntity : tagEntities) {
                    stringJoiner.add(tagEntity.getTagName());
                }
                newsEntity.setTagName(stringJoiner.toString());
            } else {
                newsEntity.setTagId("");
            }
            newsEntity.setIsHot(request.getIsHot());
            newsRepository.save(newsEntity);
            //add news language
            //add en
            newsEN.setTitle(request.getTitleEN());
            newsEN.setContent(request.getContentEN());
            newsEN.setDescription(request.getDescriptionEN());
            newsEN.setLanguage(String.valueOf(Language.EN));
            newsEN.setNewsEntity(newsEntity);
            newsLanguageRepository.save(newsEN);
            //add vn
            newsVN.setTitle(request.getTitleVN());
            newsVN.setContent(request.getContentVN());
            newsVN.setDescription(request.getDescriptionVN());
            newsVN.setLanguage(String.valueOf(Language.VN));
            newsVN.setNewsEntity(newsEntity);
            newsLanguageRepository.save(newsVN);
            return new BaseResponse<>(200, "Thêm tin tức  thành công", getDetail(newsEntity.getIdNews()));
        } catch (Exception e) {
            return new BaseResponse<>(500, "Thêm tin tức  thất bại", null);
        }
    }

    @Override
    @Modifying
    @Transactional
    public Object update(NewsAddRequest newsAddRequest) {
        try {
            List<NewsEntity> isCheckHot = newsRepository.findByIsHot(newsAddRequest.getIsHot());
            if (Boolean.TRUE.equals(newsAddRequest.getIsHot()) && isCheckHot.size() != 0) {
                return new ErrorResponse<>(500, "Đã tồn tại tin tức nổi bật", null);
            }
            Optional<NewsEntity> news = newsRepository.findById(newsAddRequest.getIdNews());
            if (news.isEmpty()) {
                return new ErrorResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Sửa tin tức  thất bại", null);
            }
            //xoa detail
            //add
            news.get().setThumbnail(fileImageUtil.uploadImage(newsAddRequest.getImg()));
            news.get().setTitleSeo(newsAddRequest.getPhotoTitle());
            news.get().setLinkPapers(newsAddRequest.getLinkPost());
            news.get().setType(newsAddRequest.getType());
            news.get().setTopicId(newsAddRequest.getTopicId());
            news.get().setAuthorName(newsAddRequest.getAuthorName());
            news.get().setAuthorAvatar(fileImageUtil.uploadImage(newsAddRequest.getAuthorAvatar()));
            if (!newsAddRequest.getTagId().isEmpty()) {
                news.get().setTagId(InfluencerServiceImpl.parseListIntegerToString(newsAddRequest.getTagId()));
                StringJoiner stringJoiner = new StringJoiner(", ");
                List<TagEntity> tagEntities = tagRepository.findByIdIn(newsAddRequest.getTagId());
                for (TagEntity tagEntity : tagEntities) {
                    stringJoiner.add(tagEntity.getTagName());
                }
                news.get().setTagName(stringJoiner.toString());
            } else {
                news.get().setTagId("");
                news.get().setTagName("");
            }
            news.get().setIsHot(newsAddRequest.getIsHot());
            newsRepository.save(news.get());
            //add detail
            newsLanguageRepository.deleteByNewId(news.get().getIdNews());
            NewsLanguageEntity newsEN = new NewsLanguageEntity();
            NewsLanguageEntity newsVN = new NewsLanguageEntity();
            newsEN.setTitle(newsAddRequest.getTitleEN());
            newsEN.setContent(newsAddRequest.getContentEN());
            newsEN.setDescription(newsAddRequest.getDescriptionEN());
            newsEN.setLanguage(String.valueOf(Language.EN));
            newsEN.setNewsEntity(news.get());
            newsLanguageRepository.save(newsEN);

            newsVN.setTitle(newsAddRequest.getTitleVN());
            newsVN.setContent(newsAddRequest.getContentVN());
            newsVN.setDescription(newsAddRequest.getDescriptionVN());
            newsVN.setLanguage(String.valueOf(Language.VN));
            newsVN.setNewsEntity(news.get());
            newsLanguageRepository.save(newsVN);
            return new BaseResponse<>(200, "Sửa tin tức  thành công", getDetail(news.get().getIdNews()));
        } catch (Exception e) {
            return new BaseResponse<>(500, "Sửa tin tức  thất bại", null);
        }
    }

    @Override
    public Object update(List<NewsAddRequest> newsAddRequests) {
        List<NewsLanguageEntity> newsList = new ArrayList<>();
        for (NewsAddRequest request : newsAddRequests) {
            NewsEntity newsEntity = new NewsEntity();
            newsEntity.setTitleSeo(request.getTitleVN());
            newsEntity.setIsProduct(1);
            newsEntity.setTopicId(8l);
            newsEntity.setThumbnail(fileImageUtil.uploadImage(request.getImg()));
            newsEntity.setRepresentativeId(request.getRepresentativeId());
            newsEntity.setIsHot(false);
            newsEntity.setTagId("");
            newsEntity.setCreated(new Date());
            newsEntity.setNewsFromKol(1l);
            newsRepository.save(newsEntity);
            NewsLanguageEntity newsEN = new NewsLanguageEntity();
            newsEN.setTitle(request.getTitleEN());
            newsEN.setContent(request.getContentEN());
            newsEN.setLanguage(String.valueOf(Language.EN));
            newsEN.setNewsEntity(newsEntity);
            newsList.add(newsEN);
            NewsLanguageEntity newsVN = new NewsLanguageEntity();
            newsVN.setTitle(request.getTitleVN());
            newsVN.setContent(request.getContentVN());
            newsVN.setLanguage(String.valueOf(Language.VN));
            newsVN.setNewsEntity(newsEntity);
            newsList.add(newsVN);
        }
        newsLanguageRepository.saveAll(newsList);
        return new BaseResponse<>(200, "Sửa tin tức  thành công", null);
    }


    @Override
    @Transactional
    @Modifying
    public Object delete(Integer id) {
        try {
            newsLanguageRepository.deleteByNewId(id);
            newsRepository.deleteById(id);
            return new BaseResponse<>(200, "Xóa tin tức  thành công", null);
        } catch (Exception e) {
            return new BaseResponse<>(500, "Xóa tin tức  thất bại", null);
        }

    }

    public List<TopicDto> getTopic(String language) {
        List<TopicEntity> entities = topicRepository.findAll();
        List<com.example.halagodainv.dto.topic.TopicDto> topicDtos = new ArrayList<>();
        entities.forEach(map -> {
            com.example.halagodainv.dto.topic.TopicDto topicDto = new com.example.halagodainv.dto.topic.TopicDto();
            if ("VN".equals(language)) {
                topicDto.setId(map.getId());
                topicDto.setTopicName(map.getTopicName());
            } else if ("EN".equals(language)) {
                topicDto.setId(map.getId());
                topicDto.setTopicName(map.getTopicNameEN());
            }
            topicDtos.add(topicDto);
        });
        return topicDtos;
    }

    public List<TagEntity> getTag() {
        return tagRepository.findAll();
    }

    public void setIsHot(int idNew) throws GeneralException {
        List<NewsEntity> newsEntities = newsRepository.findAll();
        int count = 1;
        for (NewsEntity news : newsEntities) {
            if (Boolean.TRUE.equals(news.getIsHot())) {
                count++;
            }
        }
        if (count >= 2) {
            throw new GeneralException("new hot only 1 articles");
        }
        Optional<NewsEntity> entity = newsRepository.findById(idNew);
        if (entity.isPresent()) {
            entity.get().setIsHot(true);
            newsRepository.save(entity.get());
        } else {
            throw new GeneralException("New is not exits");
        }
    }

    public void setIsNotHot(int idNew) throws GeneralException {
        Optional<NewsEntity> entity = newsRepository.findById(idNew);
        if (entity.isPresent()) {
            entity.get().setIsHot(false);
            newsRepository.save(entity.get());
        } else {
            throw new GeneralException("New is not exits");
        }
    }

    public List<NewRelationTopicDto> getNewRelationTopics(int topicId, int newId, String language) {
        StringBuilder convertSql = new StringBuilder();
        convertSql.append("SELECT n.id_news as newId ,IFNULL(nl.title,'') as title,n.thumbnail as img,DATE_FORMAT(n.created, '%Y-%m-%d') as created from news n left join news_language nl")
                .append(" on n.id_news = nl.new_id and nl.`language` = '").append(language).append("'").
                append(" WHERE n.topic_id = ").append(topicId).append(" AND  n.id_news <> ").append(newId).append(" AND n.news_from_kol <> 1 ").append(" order by n.created limit 4");
        Query query = entityManager.createNativeQuery(convertSql.toString());
        return query.unwrap(NativeQuery.class).setResultTransformer(Transformers.aliasToBean(NewRelationTopicDto.class)).getResultList();
    }
}
