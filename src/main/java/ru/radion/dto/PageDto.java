package ru.radion.dto;

import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
public class PageDto<T, P> {
    private List<T> items;
    private int currentPageNumber;
    private int totalPageCount;
    private long totalResultCount;
    private int itemsOnPage;

    public long getTotalResultCount() {
        return totalResultCount;
    }

    public int getItemsOnPage() {
        return itemsOnPage;
    }

    public PageDto(Page<T> page) {
        this.items = page.getContent();
        this.currentPageNumber = page.getNumber();
        this.totalPageCount = page.getTotalPages();
        this.totalResultCount = page.getTotalElements();
        this.itemsOnPage = page.getSize();
    }
}
