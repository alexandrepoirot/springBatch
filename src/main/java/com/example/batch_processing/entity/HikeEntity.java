package com.example.batch_processing.entity;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class HikeEntity {

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
    private String customfields ;
    private String categoryName ;

}
