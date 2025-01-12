package com.example.batch_processing.job;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import com.example.batch_processing.dto.HikeDTO;
import com.example.batch_processing.entity.HikeEntity;

@Component
public class HikeProcessor implements ItemProcessor<HikeEntity, HikeDTO> {

    @Override
    public HikeDTO process(@NonNull HikeEntity hikeEntity) throws Exception {
        HikeDTO hikeDTO = new HikeDTO();
        hikeDTO.setId(hikeEntity.getId());
        hikeDTO.setTitle(hikeEntity.getTitle());
        hikeDTO.setAlias(hikeEntity.getAlias());
        hikeDTO.setCreated(hikeEntity.getCreated());
        hikeDTO.setUsername(hikeEntity.getUsername());
        hikeDTO.setCreatedAlias(hikeEntity.getCreatedAlias());
        hikeDTO.setCatid(hikeEntity.getCatid());
        hikeDTO.setStartDate(hikeEntity.getStartDate());
        hikeDTO.setEndDate(hikeEntity.getEndDate());
        hikeDTO.setPeriod(hikeEntity.getPeriod());
        hikeDTO.setDates(hikeEntity.getDates());
        hikeDTO.setNext(hikeEntity.getEndDate());
        hikeDTO.setPlace(hikeEntity.getPlace());
        hikeDTO.setCity(hikeEntity.getCity());
        hikeDTO.setCountry(hikeEntity.getCountry());
        hikeDTO.setAddress(replacString(hikeEntity.getAddress()));
        hikeDTO.setLat(hikeEntity.getLat());
        hikeDTO.setLng(hikeEntity.getLng());
        hikeDTO.setShortdesc(replacString(hikeEntity.getShortdesc()));
        hikeDTO.setDesc(replacString(hikeEntity.getDesc()));
        hikeDTO.setCategoryName(hikeEntity.getCategoryName());
        return hikeDTO;
    }

    private String replacString (String input){
        return input.replaceAll("<[^>]*>", "").replaceAll("[\\r\\n\\t]", "").replaceAll("&nbsp;", "");
    } 

}
