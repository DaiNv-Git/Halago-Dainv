package com.example.halagodainv.service;

import com.example.halagodainv.request.solution.livestream.SolutionLiveStreamImageEdit;
import com.example.halagodainv.request.solution.livestream.SolutionLiveStreamEdit;

import java.util.List;

public interface SolutionLiveStreamService {
    Object getSolution(String language);

    Object getSolutionDetail();

    Object update(SolutionLiveStreamEdit solutionLiveStreamEdit);

    Object addImage(List<SolutionLiveStreamImageEdit> edits);

    void deleteImage(long imageId);
}
