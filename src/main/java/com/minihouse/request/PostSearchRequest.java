package com.minihouse.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PostSearchRequest {

    private int pageNum;
    private int pageSize;
    private String searchType;
    private String searchContent;

    public PostSearchRequest() {
    }

    @Builder
    public PostSearchRequest(int pageNum, int pageSize, String searchType, String searchContent) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.searchType = searchType;
        this.searchContent = searchContent;
    }
}
