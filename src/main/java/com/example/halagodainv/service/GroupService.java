package com.example.halagodainv.service;

import com.example.halagodainv.request.group.GroupAddRequest;
import com.example.halagodainv.request.group.GroupEditRequest;
import com.example.halagodainv.request.group.GroupSearch;

public interface GroupService {
    Object getGroupAll(GroupSearch groupSearch);

    Object detail(long groupId);

    Object insert(GroupAddRequest groupAddRequest);

    Object edit(GroupEditRequest groupEditRequest);

    Object delete(long groupId);
}
