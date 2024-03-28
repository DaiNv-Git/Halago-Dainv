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
import com.example.halagodainv.until.ConvertString;
import com.example.halagodainv.until.DateUtilFormat;
import com.example.halagodainv.until.FileImageUtil;
import com.example.halagodainv.until.FormatData;
import lombok.Builder;
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
@Builder
public class NewsServiceImpl implements NewsService {
    private final NewsLanguageRepository newsLanguageRepository;
    private final TopicRepository topicRepository;
    private final NewsRepository newsRepository;
    private final TagRepository tagRepository;
    private final FileImageUtil fileImageUtil;

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public BaseResponse<?> getNews(NewsFormSearch newsSearch) {
        int offset = 0;
        if (newsSearch.getPageNo() > 0) {
            offset = newsSearch.getPageNo() - 1;
        }
        StringBuilder selectNewAdmin = new StringBuilder();
        selectNewAdmin.append("select n.id_news as id,nl.title,n.thumbnail as img, DATE_FORMAT(n.created,'%Y-%m-%d') as created,n.tag_name as tagNames, ");
        selectNewAdmin.append(getTopicName());
        selectNewAdmin.append("from news n left join news_language nl on n.id_news = nl.new_id ");
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
        return new BaseResponse<>(200, "Lấy dữ liệu thành công", new PageResponse<>(new PageImpl<>(CollectionUtils.isEmpty(newsDtos) ? new ArrayList<>() : newsDtos, pageable, CollectionUtils.isEmpty(countQuery) ? 0 : countQuery.size())));
    }

    private String getTopicName() {
        return "CASE " +
                "WHEN n.topic_id = 1 THEN 'Các dự án đã triển khai' " +
                "WHEN n.topic_id = 2 THEN 'Dự án hợp tác cùng KOL,Celeb' " +
                "WHEN n.topic_id = 3 THEN 'Tin tức HOT về Influencer KOL' " +
                "WHEN n.topic_id = 4 THEN 'Cập nhật tin tức về thị trường Influencer marketing' " +
                "WHEN n.topic_id = 5 THEN 'Phương pháp tối ưu hiệu quả khi triển khai Influencer marketing' " +
                "WHEN n.topic_id = 6 THEN 'Case study cùng nhãn hàng' " +
                "WHEN n.topic_id = 7 THEN 'Lưu ý dành riêng cho các bạn' " +
                "WHEN n.topic_id = 8 THEN 'Halago - Hoạt động tiêu biểu' " +
                "ELSE '' " +
                "END as topicName ";
    }

    @Override
    public Object getDetail(int newId) {
        try {
            List<NewDetails> detail = newsRepository.getHomeLanguage(newId);
            TopicEntity topic = topicRepository.findById(detail.get(0).getTopicId()).get();
            NewDtoDetails newewDtoDetailsss = NewDtoDetails.builder().idNews(detail.get(0).getIdNews())
                    .img(detail.get(0).getThumbnail())
                    .type(detail.get(0).getType())
                    .linkPost(detail.get(0).getLinkPost() == null ? "" : detail.get(0).getLinkPost())
                    .photoTitle(detail.get(0).getPhotoTitle() == null ? "" : detail.get(0).getPhotoTitle())
                    .topicId(detail.get(0).getTopicId())
                    .tagId(ConvertString.parseStringToListOfIntegers(detail.get(0).getTagId()))
                    .isHot(detail.get(0).getIsHot())
                    .authorName(detail.get(0).getAuthorName())
                    .authorAvatar(detail.get(0).getAuthorAvatar())
                    .tagNames(detail.get(0).getTagName())
                    .contentVN(detail.get(0).getContent())
                    .contentEN(detail.get(1).getContent())
                    .descriptionVN(detail.get(0).getDescription())
                    .descriptionEN(detail.get(1).getDescription())
                    .topicName(topic.getTopicName())
                    .titleVN(detail.get(0).getTitle())
                    .titleEN(detail.get(1).getTitle())
                    .build();
            return new BaseResponse<>(200, "lấy dữ liệu chi tiết thành công", newewDtoDetailsss);
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
        return new PageResponse<>(new PageImpl<>(CollectionUtils.isEmpty(newsDtos) ? new ArrayList<>() : newsDtos, pageable, CollectionUtils.isEmpty(countQuery) ? 0 : countQuery.size()));
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
        Optional<TopicEntity> topic = topicRepository.findById((long) viewNewsMaps.getTopicId());
        return ViewNewsDetailDto.builder()
                .id(viewNewsMaps.getId())
                .title(viewNewsMaps.getTitle())
                .img(viewNewsMaps.getImg())
                .content(viewNewsMaps.getContent())
                .createdDate(viewNewsMaps.getCreatedDate())
                .description(viewNewsMaps.getDescription())
                .tagId(viewNewsMaps.getTagId())
                .topicId(viewNewsMaps.getTopicId())
                .tagName(getTagName(viewNewsMaps.getTagId()))
                .topicName(topic.isPresent() ? topic.get().getTopicName() : "").build();
    }

    public ViewNewsAndHotDetailDto getViewNewsAndHots(String language) {
        Pageable pageableViewNews = PageRequest.of(0, 3, Sort.Direction.DESC, "created");
        Pageable pageableIsHot = PageRequest.of(0, 1);
        List<ViewNewAndHot> viewHotMaps = newsRepository.getViewhots(0L, "", language, pageableIsHot);
        List<ViewNewAndHot> viewNews = newsRepository.getViewNew(0L, "", language, pageableViewNews);
        List<ViewTopicDto> viewNewsTopicDto = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            ViewTopicDto viewTopicDto = new ViewTopicDto();
            TopicEntity topic = topicRepository.findAll().get(i);
            viewTopicDto.setId(topic.getId());
            viewTopicDto.setTitle(language.toUpperCase(Locale.ROOT).equals("VN") ? topic.getTopicName() : topic.getTopicNameEN());
            viewNewsTopicDto.add(viewTopicDto);
        }
        ViewTopicDto viewTopicDto = new ViewTopicDto();
        TopicEntity topic = topicRepository.findAll().get(7);
        viewTopicDto.setId(topic.getId());
        viewTopicDto.setTitle(language.toUpperCase(Locale.ROOT).equals("VN") ? topic.getTopicName() : topic.getTopicNameEN());
        viewNewsTopicDto.add(viewTopicDto);

        List<ViewNewsHotDto> viewNewDtos = new ArrayList<>();
        List<ViewNewsHotDto> viewNewHots = new ArrayList<>();
        viewNews.forEach(viewMap -> {
            ViewNewsHotDto viewNew = new ViewNewsHotDto();
            viewNew.setId(viewMap.getId());
            viewNew.setTitle(viewMap.getTitle());
            viewNew.setImg(viewMap.getImage());
            viewNew.setCreated(DateUtilFormat.convertDateToString(viewMap.getCreated(), "yyyy-MM-dd"));
            viewNewDtos.add(viewNew);
        });

        viewHotMaps.forEach(viewMap -> {
            ViewNewsHotDto viewNewsHotDto = new ViewNewsHotDto();
            viewNewsHotDto.setId(viewMap.getId());
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
            if (Boolean.TRUE.equals(request.getIsHot()) && !isCheckHot.isEmpty()) {
                return new ErrorResponse<>(500, "Đã tồn tại tin tức nổi bật", null);
            }
            //add news
            NewsEntity newsEntity = saveNew(request);
            newsRepository.save(newsEntity);
            //add en
            newsLanguageRepository.save(saveDetail(newsEntity, request, "EN"));
            //add vn
            newsLanguageRepository.save(saveDetail(newsEntity, request, "VN"));
            return new BaseResponse<>(200, "Thêm tin tức  thành công", getDetail(newsEntity.getIdNews()));
        } catch (Exception e) {
            return new BaseResponse<>(500, "Thêm tin tức  thất bại", null);
        }
    }

    @Override
    @Modifying
    public Object update(NewsAddRequest request) {
        try {
            List<NewsEntity> isCheckHot = newsRepository.findByIsHot(request.getIsHot());
            if (Boolean.TRUE.equals(request.getIsHot()) && !isCheckHot.isEmpty()) {
                return new ErrorResponse<>(500, "Đã tồn tại tin tức nổi bật", null);
            }
            Optional<NewsEntity> news = newsRepository.findById(request.getIdNews());
            if (news.isEmpty()) {
                return new ErrorResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Sửa tin tức  thất bại", null);
            }
            //add
            NewsEntity newsEntity = saveNew(request);
            newsRepository.save(news.get());
            //delete all detail
            newsLanguageRepository.deleteByNewId(request.getIdNews());
            //add en
            newsLanguageRepository.save(saveDetail(newsEntity, request, "EN"));
            //add vn
            newsLanguageRepository.save(saveDetail(newsEntity, request, "VN"));
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
        convertSql.append("SELECT n.id_news as newId ,nl.title as title,n.thumbnail as img,DATE_FORMAT(n.created, '%Y-%m-%d') as created from news n left join news_language nl")
                .append(" on n.id_news = nl.new_id and nl.`language` = '").append(language).append("'").
                append(" WHERE n.topic_id = ").append(topicId).append(" AND  n.id_news <> ").append(newId).append(" AND n.news_from_kol <> 1 ").append(" order by n.created DESC limit 4");
        Query query = entityManager.createNativeQuery(convertSql.toString());
        return query.unwrap(NativeQuery.class).setResultTransformer(Transformers.aliasToBean(NewRelationTopicDto.class)).getResultList();
    }

    private String getTagName(NewsAddRequest request) {
        if (!request.getTagId().isEmpty()) {
            StringJoiner stringJoiner = new StringJoiner(", ");
            List<TagEntity> tagEntities = tagRepository.findByIdIn(request.getTagId());
            for (TagEntity tagEntity : tagEntities) {
                stringJoiner.add(tagEntity.getTagName());
            }
            return stringJoiner.toString();
        }
        return "";
    }

    private String getTagName(List<Integer> tagNames) {
        if (!tagNames.isEmpty()) {
            StringJoiner stringJoiner = new StringJoiner(", ");
            List<TagEntity> tagEntities = tagRepository.findByIdIn(tagNames);
            for (TagEntity tagEntity : tagEntities) {
                stringJoiner.add(tagEntity.getTagName());
            }
            return stringJoiner.toString();
        }
        return "";
    }

    private NewsLanguageEntity saveDetail(NewsEntity newsEntity, NewsAddRequest request, String language) {
        return NewsLanguageEntity.builder()
                .title(request.getTitleVN()).content(request.getContentVN())
                .description(request.getDescriptionVN())
                .language(language)
                .newsEntity(newsEntity).build();
    }

    private NewsEntity saveNew(NewsAddRequest request) {
        NewsEntity newsEntity = new NewsEntity();
        if (request.getIdNews() != null && request.getIdNews() > 0) {
            newsEntity.setIdNews(request.getIdNews());
        }
        newsEntity = NewsEntity.builder()
                .thumbnail(fileImageUtil.uploadImage(request.getImg()))
                .created(new Date())
                .titleSeo(request.getPhotoTitle())
                .linkPapers(request.getLinkPost())
                .type(FormatData.checkNull(request.getType()))
                .authorName(request.getAuthorName())
                .authorAvatar(fileImageUtil.uploadImage(request.getAuthorAvatar()))
                .topicId(FormatData.checkNull(request.getTopicId()))
                .productId(0)
                .newsFromKol(0L)
                .tagId(ConvertString.parseListIntegerToString(request.getTagId()))
                .tagName(getTagName(request))
                .isHot(request.getIsHot()).build();
        return newsEntity;
    }
}
