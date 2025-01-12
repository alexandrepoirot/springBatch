package com.example.batch_processing.job;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import com.example.batch_processing.dto.HikeDTO;
import com.example.batch_processing.dto.HikeDetailsDTO;
import com.example.batch_processing.entity.HikeEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class HikeProcessor implements ItemProcessor<HikeEntity, HikeDTO> {

    private static final Logger LOGGER = LoggerFactory.getLogger(HikeProcessor.class);

    @Override
    public HikeDTO process(@NonNull HikeEntity hikeEntity) throws Exception {
        HikeDTO hikeDTO = new HikeDTO();
        hikeDTO.setId(hikeEntity.getId());
        hikeDTO.setTitle(hikeEntity.getTitle().replace(",", ""));
        hikeDTO.setAlias(hikeEntity.getAlias().replace(",", ""));
        hikeDTO.setCreated(hikeEntity.getCreated());
        hikeDTO.setUsername(hikeEntity.getUsername());
        hikeDTO.setCreatedAlias(hikeEntity.getCreatedAlias().replace(",", ""));
        hikeDTO.setCatid(hikeEntity.getCatid());
        hikeDTO.setStartDate(hikeEntity.getStartDate());
        hikeDTO.setEndDate(hikeEntity.getEndDate());
        hikeDTO.setPeriod(hikeEntity.getPeriod().replace(",", ""));
        hikeDTO.setDates(hikeEntity.getDates().replace(",", ""));
        hikeDTO.setNext(hikeEntity.getEndDate());
        hikeDTO.setPlace(hikeEntity.getPlace().replace(",", ""));
        hikeDTO.setCity(hikeEntity.getCity().replace(",", ""));
        hikeDTO.setCountry(hikeEntity.getCountry().replace(",", ""));
        hikeDTO.setAddress(replacString(hikeEntity.getAddress()));
        hikeDTO.setLat(hikeEntity.getLat());
        hikeDTO.setLng(hikeEntity.getLng());
        hikeDTO.setShortdesc(replacString(hikeEntity.getShortdesc()));
        hikeDTO.setDesc(replacString(hikeEntity.getDesc()));
        hikeDTO.setCategoryName(hikeEntity.getCategoryName());
        mappHikeDetail(hikeEntity.getCustomfields(), hikeDTO);
        return hikeDTO;
    }

    private String replacString (String input){
        return input.replaceAll("<[^>]*>", "").replaceAll("[\\r\\n\\t]", "").replace("&nbsp;", "").replace(",", "");
    } 

    private  void mappHikeDetail (String  hikeDetail,HikeDTO hikeDTO ) {

        if (StringUtils.isEmpty(hikeDetail)) {
            hikeDTO.setPhysicalLevel("");
            hikeDTO.setTechnicalLevel("");
            hikeDTO.setMassif("");
            hikeDTO.setMaxHeight("");
            hikeDTO.setDifferenceHeight("");
            hikeDTO.setMapNumber("");
            return;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        HikeDetailsDTO hikeDetails;
        try {
            hikeDetails = objectMapper.readValue(hikeDetail, HikeDetailsDTO.class);
            hikeDTO.setPhysicalLevel(hikeDetails.getNiveauPhysique());
            hikeDTO.setTechnicalLevel(hikeDetails.getNiveauTechnique());
            hikeDTO.setMassif(hikeDetails.getMassif());
            hikeDTO.setMaxHeight(hikeDetails.getAltitudeMaxi());
            hikeDTO.setDifferenceHeight(hikeDetails.getDenivele());
            hikeDTO.setMapNumber(hikeDetails.getNDeCarte());
        } catch (JsonProcessingException e) {
            LOGGER.error("Json deserialization error", e);
        }
    }

}
