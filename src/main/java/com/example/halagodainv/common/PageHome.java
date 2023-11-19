package com.example.halagodainv.common;

import lombok.Data;

@Data

public class PageHome {
    private int pageSize = 10;
    private int pageNo = 1;

    public int getPageNo() {
        if (pageNo > 0) {
            return pageNo - 1;
        }
        return 0;
    }
}
