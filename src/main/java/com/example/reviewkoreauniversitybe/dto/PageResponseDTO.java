package com.example.reviewkoreauniversitybe.dto;

import org.springframework.data.domain.Page;

import java.util.List;

public class PageResponseDTO<T> {
    private List<T> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
    
    // Constructors
    public PageResponseDTO() {
    }
    
    public PageResponseDTO(List<T> content, int pageNo, int pageSize, long totalElements, int totalPages, boolean last) {
        this.content = content;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.last = last;
    }
    
    // Getters
    public List<T> getContent() {
        return content;
    }
    
    public int getPageNo() {
        return pageNo;
    }
    
    public int getPageSize() {
        return pageSize;
    }
    
    public long getTotalElements() {
        return totalElements;
    }
    
    public int getTotalPages() {
        return totalPages;
    }
    
    public boolean isLast() {
        return last;
    }
    
    // Setters
    public void setContent(List<T> content) {
        this.content = content;
    }
    
    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }
    
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    
    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }
    
    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
    
    public void setLast(boolean last) {
        this.last = last;
    }
    
    public static <T> PageResponseDTO<T> of(Page<T> page) {
        return new PageResponseDTO<>(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.isLast()
        );
    }
    
    public static <T> PageResponseDTO<T> of(List<T> list, int page, int size) {
        int fromIndex = page * size;
        int toIndex = Math.min(fromIndex + size, list.size());
        
        List<T> content = fromIndex < list.size() ? list.subList(fromIndex, toIndex) : List.of();
        int totalPages = (int) Math.ceil((double) list.size() / size);
        
        return new PageResponseDTO<>(
                content,
                page,
                size,
                list.size(),
                totalPages,
                page >= totalPages - 1
        );
    }
} 