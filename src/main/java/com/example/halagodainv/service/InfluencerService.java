package com.example.halagodainv.service;


import com.example.halagodainv.exception.GeneralException;
import com.example.halagodainv.request.excel.InfluceRequestExportExcel;
import com.example.halagodainv.request.influencer.InfluencerAddRequest;
import com.example.halagodainv.request.influencer.InfluencerSearch;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface InfluencerService {
    Object getInfluMenu(InfluencerSearch search);

    Object getInfluSubMenu(InfluencerSearch search);

    Object findInfluencerById(long id);

    Object add(InfluencerAddRequest request);

    Object edit(InfluencerAddRequest request);

    Object delete(long id);

    byte[] exportExcel(InfluceRequestExportExcel search);

    void importExcel(MultipartFile file) throws GeneralException, IOException;

    byte[] downFileImportExcel();
}
