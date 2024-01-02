package com.example.halagodainv.service.impl;

import com.example.halagodainv.common.Language;
import com.example.halagodainv.config.Constant;
import com.example.halagodainv.dto.news.NewDetails;
import com.example.halagodainv.dto.news.NewDto;
import com.example.halagodainv.dto.news.NewDtoDetails;
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
import com.example.halagodainv.repository.NewsTypeRepository;
import com.example.halagodainv.repository.viewdisplay.TagRepository;
import com.example.halagodainv.repository.viewdisplay.TopicRepository;
import com.example.halagodainv.request.news.NewsAddRequest;
import com.example.halagodainv.request.news.NewsFormSearch;
import com.example.halagodainv.response.BaseResponse;
import com.example.halagodainv.response.PageResponse;
import com.example.halagodainv.service.NewsService;
import com.example.halagodainv.until.FileImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    NewsLanguageRepository newsLanguageRepository;
    @Autowired
    NewsTypeRepository newsTypeRepository;
    @Autowired
    TopicRepository topicRepository;
    @Autowired
    NewsRepository newsRepository;
    @Autowired
    TagRepository tagRepository;
    @Autowired
    FileImageUtil fileImageUtil;

    @Override
    public Object getNews(NewsFormSearch newsSearch) {
        try {
            int offset = 0;
            if (newsSearch.getPageNo() > 0) {
                offset = newsSearch.getPageNo() - 1;
            }
            int totalCountNews = newsRepository.countByAll(newsSearch.getTitle(), newsSearch.getTopicId(), newsSearch.getTagId() != null ? String.valueOf(newsSearch.getTagId()) : "", newsSearch.getIsHot());
            Pageable pageable = PageRequest.of(offset, newsSearch.getPageSize());
            List<NewDto> newsEntityList = newsRepository.getNewList(newsSearch.getTitle(), newsSearch.getTopicId(), newsSearch.getTagId() != null ? String.valueOf(newsSearch.getTagId()) : "", newsSearch.getIsHot(), pageable);
            PageResponse pageResponse;
            if (CollectionUtils.isEmpty(newsEntityList)) {
                pageResponse = new PageResponse(new PageImpl(newsEntityList, pageable, 0));
                return new BaseResponse<>(200, "Lấy dữ liệu thành công", pageResponse);
            }
            pageResponse = new PageResponse(new PageImpl(newsEntityList, pageable, totalCountNews));
            return new BaseResponse<>(200, "Lấy dữ liệu thành công", pageResponse);
        } catch (Exception e) {
            return new ErrorResponse(500, "Lấy dữ liệu thất bại", null);
        }
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
                        if (i.getLanguage().equalsIgnoreCase("VN")) {
                            newewDtoDetailsss.setContentVN(i.getContent());
                            newewDtoDetailsss.setDescriptionVN(i.getDescription());
                            newewDtoDetailsss.setTitleVN(i.getTitle());
                        } else if (i.getLanguage().equalsIgnoreCase("EN")) {
                            newewDtoDetailsss.setTitleEN(i.getTitle());
                            newewDtoDetailsss.setDescriptionEN(i.getDescription());
                            newewDtoDetailsss.setContentEN(i.getContent());
                        }
                    });
            newDtoDetail.add(newewDtoDetailsss);
            return new BaseResponse<>(HttpStatus.OK.value(), "lấy dữ liệu chi tiết thành công", newDtoDetail);
        } catch (Exception e) {
            return new ErrorResponse(Constant.FAILED, "Lấy dữ liệu chi tiết thất bại", null);
        }
    }

    public PageResponse<?> getViewNews(int pageNo, int pageSize, String language, Long topicId, Long tagId) {
        PageResponse<?> pageResponse;
        int offset = pageNo > 0 ? pageNo - 1 : 0;
        Pageable pageable = PageRequest.of(offset, pageSize, Sort.Direction.DESC, "created");
        List<ViewNewsMap> viewNewsMaps = newsRepository.getPageViewNews(topicId, (tagId != 0L) ? String.valueOf(tagId) : "", language, pageable);
        List<ViewNewsDto> newsDtos = new ArrayList<>();
        viewNewsMaps.forEach(i -> {
            newsDtos.add(new ViewNewsDto(i.getImg(), i.getTitle(), i.getCreatedDate()));
        });
        if (CollectionUtils.isEmpty(viewNewsMaps)) {
            pageResponse = new PageResponse<>(new PageImpl<>(viewNewsMaps, pageable, 0));
            return pageResponse;
        }
        int viewCountNewsMaps = newsRepository.getCountPageViewNews(topicId, tagId != 0L ? String.valueOf(tagId) : "", language);
        pageResponse = new PageResponse<>(new PageImpl<>(newsDtos, pageable, viewCountNewsMaps));
        return pageResponse;
    }

    public ViewNewsDetailDto getViewNewsDetail(int id, String language, Long topicId, Long tagId) {
        ViewNewsMap viewNewsMaps = newsRepository.getDetailView(topicId, (tagId != 0L) ? String.valueOf(tagId) : "", language, id);
        if (ObjectUtils.isEmpty(viewNewsMaps)) {
            return null;
        }
        ViewNewsDetailDto viewNewsDetailDto = new ViewNewsDetailDto();
        viewNewsDetailDto.setId(viewNewsMaps.getId());
        viewNewsDetailDto.setTitle(viewNewsMaps.getTitle());
        viewNewsDetailDto.setContent(viewNewsMaps.getContent());
        viewNewsDetailDto.setCreatedDate(viewNewsMaps.getCreatedDate());
        viewNewsDetailDto.setTagId(viewNewsMaps.getTagId());
        return viewNewsDetailDto;
    }

    public ViewNewsAndHotDetailDto getViewNewsAndHots(String language) {
        Pageable pageableViewNews = PageRequest.of(0, 3, Sort.Direction.DESC, "created");
        Pageable pageableIsHot = PageRequest.of(0, 3);
        List<ViewNewAndHot> viewNewsMaps = newsRepository.getViewhots(0L, "", language, pageableIsHot);
        List<ViewNewsMap> viewNewHotsMap = newsRepository.getViewNews(0L, "", language);
        List<ViewNewAndHot> viewNews = newsRepository.getViewNew(0L, "", language, pageableViewNews);
        int count1 = 0;
        int count2 = 0;
        int count3 = 0;
        int count4 = 0;
        int count5 = 0;
        int count6 = 0;
        ViewTopicDto viewTopicDto = new ViewTopicDto();
        ViewTopicDto viewTopicDto1 = new ViewTopicDto();
        ViewTopicDto viewTopicDto2 = new ViewTopicDto();
        ViewTopicDto viewTopicDto3 = new ViewTopicDto();
        ViewTopicDto viewTopicDto4 = new ViewTopicDto();
        ViewTopicDto viewTopicDto5 = new ViewTopicDto();
        List<ViewTopicDto> viewNewsTopicDto = new ArrayList<>();
        for (ViewNewsMap viewMap : viewNewHotsMap) {
            if (viewMap.getTopicId() == 1) {
                count1++;
            } else if (viewMap.getTopicId() == 2) {
                count2++;
            } else if (viewMap.getTopicId() == 3) {
                count3++;
            } else if (viewMap.getTopicId() == 4) {
                count4++;
            } else if (viewMap.getTopicId() == 5) {
                count5++;
            } else if (viewMap.getTopicId() == 6) {
                count6++;
            }
        }
        viewTopicDto.setTitle(topicRepository.findAll().get(0).getTopicName());
        viewTopicDto1.setTitle(topicRepository.findAll().get(1).getTopicName());
        viewTopicDto2.setTitle(topicRepository.findAll().get(2).getTopicName());
        viewTopicDto3.setTitle(topicRepository.findAll().get(3).getTopicName());
        viewTopicDto4.setTitle(topicRepository.findAll().get(4).getTopicName());
        viewTopicDto5.setTitle(topicRepository.findAll().get(5).getTopicName());

        viewTopicDto.setCount(count1);
        viewTopicDto1.setCount(count2);
        viewTopicDto2.setCount(count3);
        viewTopicDto3.setCount(count4);
        viewTopicDto4.setCount(count5);
        viewTopicDto5.setCount(count6);

        viewNewsTopicDto.add(viewTopicDto);
        viewNewsTopicDto.add(viewTopicDto1);
        viewNewsTopicDto.add(viewTopicDto2);
        viewNewsTopicDto.add(viewTopicDto3);
        viewNewsTopicDto.add(viewTopicDto4);
        viewNewsTopicDto.add(viewTopicDto5);

        List<ViewNewsHotDto> viewNewDtos = new ArrayList<>();
        List<ViewNewsHotDto> viewNewHots = new ArrayList<>();
        viewNews.forEach(viewMap -> {
            ViewNewsHotDto viewNew = new ViewNewsHotDto();
            viewNew.setTitle(viewMap.getTitle());
            viewNew.setImg(viewMap.getImage());
            viewNewDtos.add(viewNew);
        });

        viewNewsMaps.forEach(viewMap -> {
            ViewNewsHotDto viewNewsHotDto = new ViewNewsHotDto();
            viewNewsHotDto.setTitle(viewMap.getTitle());
            viewNewsHotDto.setImg(viewMap.getImage());
            viewNewHots.add(new ViewNewsHotDto());
        });
        return new ViewNewsAndHotDetailDto(viewNewsTopicDto, viewNewDtos, viewNewHots);
    }

    @Override
    public Object insertNews(NewsAddRequest request) {
        try {
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
            if (request.getTagId().size() > 0) {
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
            return new BaseResponse(Constant.SUCCESS, "Thêm tin tức  thành công", getDetail(newsEntity.getIdNews()));
        } catch (Exception e) {
            return new BaseResponse(Constant.FAILED, "Thêm tin tức  thất bại", null);
        }
    }

    @Override
    @Modifying
    @Transactional
    public Object update(NewsAddRequest newsAddRequest) {
        try {
            Optional<NewsEntity> news = newsRepository.findById(newsAddRequest.getIdNews());
            if (news.isEmpty()) {
                return new ErrorResponse(Constant.FAILED, "Sửa tin tức  thất bại", null);
            }
            //xoa detail
            //add
            news.get().setThumbnail(fileImageUtil.uploadImage(newsAddRequest.getImg()));
            news.get().setTitleSeo(newsAddRequest.getPhotoTitle());
            news.get().setLinkPapers(newsAddRequest.getLinkPost());
            news.get().setType(newsAddRequest.getType());
            news.get().setType(newsAddRequest.getType());
            news.get().setTopicId(newsAddRequest.getTopicId());
            news.get().setAuthorName(newsAddRequest.getAuthorName());
            news.get().setAuthorAvatar(fileImageUtil.uploadImage(newsAddRequest.getAuthorAvatar()));
            if (newsAddRequest.getTagId().size() > 0) {
                news.get().setTagId(InfluencerServiceImpl.parseListIntegerToString(newsAddRequest.getTagId()));
                StringJoiner stringJoiner = new StringJoiner(", ");
                List<TagEntity> tagEntities = tagRepository.findByIdIn(newsAddRequest.getTagId());
                for (TagEntity tagEntity : tagEntities) {
                    stringJoiner.add(tagEntity.getTagName());
                }
                news.get().setTagName(stringJoiner.toString());
            } else {
                news.get().setTagId("");
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
            return new BaseResponse(Constant.SUCCESS, "Sửa tin tức  thành công", getDetail(news.get().getIdNews()));
        } catch (Exception e) {
            return new BaseResponse(Constant.FAILED, "Sửa tin tức  thất bại", null);
        }
    }

    @Override
    @Transactional
    @Modifying
    public Object delete(Integer id) {
        try {
            newsLanguageRepository.deleteByNewId(id);
            newsRepository.deleteById(id);
            return new BaseResponse(Constant.SUCCESS, "Xóa tin tức  thành công", new BaseResponse(1, "Xóa tin tức  thành công", null));
        } catch (Exception e) {
            return new BaseResponse(Constant.FAILED, "Xóa tin tức  thất bại", new BaseResponse(0, "Xóa tin tức  thất bại\"", null));
        }

    }

    public List<TopicDto> getTopic(String language) {
        List<TopicEntity> entities = topicRepository.findAll();
        List<com.example.halagodainv.dto.topic.TopicDto> topicDtos = new ArrayList<>();
        entities.forEach(map -> {
            com.example.halagodainv.dto.topic.TopicDto topicDto = new com.example.halagodainv.dto.topic.TopicDto();
            if (language.equals("VN")) {
                topicDto.setId(map.getId());
                topicDto.setTopicName(map.getTopicName());
            } else if (language.equals("EN")) {
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
        if (count > 3) {
            throw new GeneralException("new hot only 3 articles");
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
}
