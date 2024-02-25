package com.example.halagodainv.service;


import com.example.halagodainv.exception.GeneralException;
import com.example.halagodainv.request.excel.InfluceRequestExportExcel;
import com.example.halagodainv.request.influencer.InfluencerAddRequest;
import com.example.halagodainv.request.influencer.InfluencerSearch;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface InfluencerService {
    Object getAll(InfluencerSearch search);

    Object getSubInflu(InfluencerSearch search);

    Object findInfluencerById(long id);

    Object add(InfluencerAddRequest request);

    Object edit(InfluencerAddRequest request);

    Object delete(long id);

    byte[] exportExcel(InfluencerSearch search);

    void importExcel(MultipartFile file) throws GeneralException, IOException;

    byte[] downFileImportExcel();
    boolean isCheckInforInflu(String email);
}
