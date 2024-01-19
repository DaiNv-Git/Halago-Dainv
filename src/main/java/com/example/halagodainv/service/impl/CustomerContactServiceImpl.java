package com.example.halagodainv.service.impl;


import com.example.halagodainv.model.viewdisplayentity.FreeConsultationEntity;
import com.example.halagodainv.repository.ContactCustomerRepository;
import com.example.halagodainv.repository.FreeConsultationRepository;
import com.example.halagodainv.request.concatcustomer.FreeConsultationRequest;
import com.example.halagodainv.response.BaseResponse;
import com.example.halagodainv.response.PageResponse;
import com.example.halagodainv.service.ContactCustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerContactServiceImpl implements ContactCustomerService {
    private final FreeConsultationRepository freeConsultationRepository;
    private final JavaMailSender javaMailSender;

    @Override
    public PageResponse<FreeConsultationEntity> getListCustomers(int pageNo, int pageSize) {
        int offset = 0;
        if (pageNo > 0) {
            offset = pageNo - 1;
        }
        Pageable pageable = PageRequest.of(offset, pageSize);
        List<FreeConsultationEntity> entities = freeConsultationRepository.findAll(PageRequest.of(offset, pageSize, Sort.Direction.DESC, "id")).toList();
        int totalCount = (int) freeConsultationRepository.count();
        if (CollectionUtils.isEmpty(entities)) {
            PageResponse<FreeConsultationEntity> response = new PageResponse<>(new PageImpl<>(entities, pageable, 0));
            return response;
        }
        PageResponse<FreeConsultationEntity> response = new PageResponse<>(new PageImpl<>(entities, pageable, totalCount));
        return response;
    }

    public Object add(FreeConsultationRequest request) throws MessagingException, UnsupportedEncodingException {
        FreeConsultationEntity freeConsultationEntity = new FreeConsultationEntity();
        freeConsultationEntity.setName(request.getName());
        freeConsultationEntity.setEmail(request.getEmail());
        freeConsultationEntity.setPhone(request.getPhone());
        freeConsultationEntity.setWebsite(request.getWebsite());
        freeConsultationEntity.setAdvertisementVTC(request.getIsAdvertisementVTC());
        freeConsultationEntity.setBrandAmbassador(request.getIsBrandAmbassador());
        freeConsultationEntity.setEvent(request.getIsEvent());
        freeConsultationEntity.setLiveStream(request.getIsLiveStream());
        freeConsultationEntity.setReview(request.getIsReview());
        freeConsultationEntity.setOther(request.getIsOther());
        freeConsultationEntity.setNote(request.getNote());
        freeConsultationEntity.setCreated(new Date());
        freeConsultationEntity = freeConsultationRepository.save(freeConsultationEntity);
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper mailMessage = new MimeMessageHelper(message, true);
        mailMessage.setFrom("halogohalogo939@gmail.com", "halago.contact");
        mailMessage.setSubject("Khách hàng đăng ký");
        mailMessage.setTo(freeConsultationEntity.getEmail());
        String content = "<div><h3>" + new Date() + " </h3>" +
                "<span>" + new String("Cảm ơn khách hàng đăng ký tư vấn trên website".getBytes(), StandardCharsets.UTF_8) + "</span></div>";
        message.setContent(content, "text/html; charset=UTF-8");
        javaMailSender.send(message);
        return new BaseResponse<>(HttpStatus.OK.value(), "Đăng ký thành công", freeConsultationEntity);
    }

    public BaseResponse<?> addFreeConsul(FreeConsultationRequest request) throws MessagingException, UnsupportedEncodingException {
        FreeConsultationEntity freeConsultationEntity = new FreeConsultationEntity();
        freeConsultationEntity.setName(request.getName());
        freeConsultationEntity.setEmail(request.getEmail());
        freeConsultationEntity.setPhone(request.getPhone());
        freeConsultationEntity.setWebsite(request.getWebsite());
        freeConsultationEntity.setAdvertisementVTC(request.getIsAdvertisementVTC());
        freeConsultationEntity.setBrandAmbassador(request.getIsBrandAmbassador());
        freeConsultationEntity.setEvent(request.getIsEvent());
        freeConsultationEntity.setLiveStream(request.getIsLiveStream());
        freeConsultationEntity.setReview(request.getIsReview());
        freeConsultationEntity.setOther(request.getIsOther());
        freeConsultationEntity.setNote(request.getNote());
        freeConsultationEntity.setCreated(new Date());
        freeConsultationEntity = freeConsultationRepository.save(freeConsultationEntity);
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper mailMessage = new MimeMessageHelper(message, true);
        mailMessage.setFrom("halogohalogo939@gmail.com", "halago.contact");
        mailMessage.setSubject("Khách hàng đăng ký");
        mailMessage.setTo(freeConsultationEntity.getEmail());
        String content = "<div><h3>" + new Date() + " </h3>" +
                "<span>" + new String("Cảm ơn khách hàng đăng ký tư vấn trên website".getBytes(), StandardCharsets.UTF_8) + "</span></div>";
        message.setContent(content, "text/html; charset=UTF-8");
        javaMailSender.send(message);
        return new BaseResponse<>(HttpStatus.OK.value(), "Đăng ký thành công", freeConsultationEntity);

    }

}
