package com.example.batch_processing.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class HikeDTO {
    private int id ;
    private String title ;
    private String alias ; 
    private LocalDateTime created ;
    private String username ;
    private String createdAlias ;
    private String catid ;
    private LocalDateTime startDate ;
    private LocalDateTime endDate ;
    private String period ;
    private String dates ;
    private LocalDateTime next ;
    private String place ;
    private String city ;
    private String country ;
    private String address ;
    private double lat ;
    private double lng ;
    private String shortdesc ;
    private String desc ;
    private String categoryName ;
    private String physicalLevel;
    private String technicalLevel;
    private String massif;
    private String maxHeight;
    private String differenceHeight;
    private String mapNumber;
}
