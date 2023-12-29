package com.example.halagodainv.service.impl;


import com.example.halagodainv.model.ContactCustomerEntity;
import com.example.halagodainv.model.viewdisplayentity.FreeConsultationEntity;
import com.example.halagodainv.repository.ContactCustomerRepository;
import com.example.halagodainv.repository.FreeConsultationRepository;
import com.example.halagodainv.request.concatcustomer.ConcatCustomerRequest;
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
    private final ContactCustomerRepository contactCustomerRepository;
    private final FreeConsultationRepository freeConsultationRepository;
    private final JavaMailSender javaMailSender;

    public Object getListCustomers(int pageNo, int pageSize) {
        int offset = 0;
        if (pageNo > 0) {
            offset = pageNo - 1;
        }
        Pageable pageable = PageRequest.of(offset, pageSize);
        List<FreeConsultationEntity> entities = freeConsultationRepository.findAll(PageRequest.of(offset, pageSize, Sort.Direction.DESC, "id")).toList();
        int totalCount = (int) contactCustomerRepository.count();
        if (CollectionUtils.isEmpty(entities)) {
            PageResponse response = new PageResponse<>(new PageImpl<>(entities, pageable, 0));
            return response;
        }
        PageResponse response = new PageResponse<>(new PageImpl<>(entities, pageable, totalCount));
        return response;
    }

    public Object add(ConcatCustomerRequest customerRequest) throws MessagingException, UnsupportedEncodingException {
        ContactCustomerEntity contactCustomerEntity = new ContactCustomerEntity();
        if (contactCustomerRepository.findByPhoneOrEmail(customerRequest.getPhone(),customerRequest.getEmail()).isPresent()) {
            return new BaseResponse<>(HttpStatus.EXPECTATION_FAILED.value(), "Số điện thoại hoặc email đã được đăng ký", null);
        }
        contactCustomerEntity.setCreated(new Date());
        contactCustomerEntity.setEmail(customerRequest.getEmail());
        contactCustomerEntity.setPhone(customerRequest.getPhone());
        contactCustomerEntity.setName(customerRequest.getUserName());
        contactCustomerEntity.setProduct(customerRequest.getProduct());
        contactCustomerEntity.setNote(customerRequest.getNote());
        contactCustomerRepository.save(contactCustomerEntity);
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper mailMessage = new MimeMessageHelper(message, true);
        mailMessage.setFrom("halogohalogo939@gmail.com", "halago.contact");
        mailMessage.setSubject("Khách hàng đăng ký");
        mailMessage.setTo(contactCustomerEntity.getEmail());
        String content = "<div><h3>" + new Date() + " </h3>" +
                "<span>" + new String("Khách hàng đăng ký tư vấn trên website".getBytes(), StandardCharsets.UTF_8) + "</span></div>";
        message.setContent(content, "text/html; charset=UTF-8");
        javaMailSender.send(message);
        return new BaseResponse<>(HttpStatus.OK.value(), "Đăng ký thành công", null);
    }

    public BaseResponse<?> addFreeConsul(FreeConsultationRequest request) throws MessagingException, UnsupportedEncodingException {
        FreeConsultationEntity freeConsultationEntity = new FreeConsultationEntity();
        if (freeConsultationRepository.findByPhoneOrEmail(request.getPhone(), request.getEmail()).isPresent()) {
            return new BaseResponse<>(HttpStatus.EXPECTATION_FAILED.value(), "Số điện thoại hoặc email đã được đăng ký", null);
        }
        freeConsultationEntity.setName(request.getName());
        freeConsultationEntity.setEmail(request.getEmail());
        freeConsultationEntity.setPhone(request.getPhone());
        freeConsultationEntity.setWebsite(request.getWebsite());
        freeConsultationEntity.setIsAdvertisementVTC(request.getIsAdvertisementVTC());
        freeConsultationEntity.setIsBrandAmbassador(request.getIsBrandAmbassador());
        freeConsultationEntity.setIsEvent(request.getIsEvent());
        freeConsultationEntity.setIsLiveStream(request.getIsLiveStream());
        freeConsultationEntity.setIsReview(request.getIsReview());
        freeConsultationEntity.setIsOther(request.getIsOther());
        freeConsultationEntity = freeConsultationRepository.save(freeConsultationEntity);
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper mailMessage = new MimeMessageHelper(message, true);
        mailMessage.setFrom("halogohalogo939@gmail.com", "halago.contact");
        mailMessage.setSubject("Khách hàng đăng ký");
        mailMessage.setTo(freeConsultationEntity.getEmail());
        String content = "<div><h3>" + new Date() + " </h3>" +
                "<span>" + new String("Khách hàng đăng ký tư vấn trên website".getBytes(), StandardCharsets.UTF_8) + "</span></div>";
        message.setContent(content, "text/html; charset=UTF-8");
        javaMailSender.send(message);
        return new BaseResponse<>(HttpStatus.OK.value(), "Đăng ký thành công", freeConsultationEntity);

    }

}
