package com.example.batch_processing.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import com.example.batch_processing.entity.HikeEntity;

public class HikeRowMapper implements RowMapper<HikeEntity> {

    @Override
    @Nullable
    public HikeEntity mapRow(@NonNull ResultSet rs, int rowNum) throws SQLException {

        HikeEntity hikeEntity = new HikeEntity();
        hikeEntity.setId(rs.getInt("idevent"));
        hikeEntity.setTitle(rs.getString("titleEvent"));
        hikeEntity.setAlias(rs.getString("alias"));
        if (rs.getTimestamp("created") != null){
            hikeEntity.setCreated(rs.getTimestamp("created").toLocalDateTime());
        }else{
            hikeEntity.setCreated(LocalDateTime.now());
        }
        hikeEntity.setUsername(rs.getString("username"));
        hikeEntity.setCreatedAlias(rs.getString("created_by_alias"));
        hikeEntity.setCatid(rs.getString("categoryid"));
        if (rs.getTimestamp("startdate") != null){
            hikeEntity.setStartDate(rs.getTimestamp("startdate").toLocalDateTime());
        }else{
            hikeEntity.setStartDate(LocalDateTime.now());
        }
        if (rs.getTimestamp("enddate") != null){
            hikeEntity.setEndDate(rs.getTimestamp("enddate").toLocalDateTime());
        }else{
            hikeEntity.setEndDate(LocalDateTime.now());
        }
        hikeEntity.setPeriod(rs.getString("period"));
        hikeEntity.setDates(rs.getString("dates"));

        if (rs.getTimestamp("next") != null){
            hikeEntity.setNext(rs.getTimestamp("next").toLocalDateTime());
        }else{
            hikeEntity.setNext(LocalDateTime.now());
        }
        hikeEntity.setPlace(rs.getString("place"));
        hikeEntity.setCity(rs.getString("city"));
        hikeEntity.setCountry(rs.getString("country"));
        hikeEntity.setAddress(rs.getString("address"));
        hikeEntity.setLat(rs.getDouble("lat"));
        hikeEntity.setLng(rs.getDouble("lng"));
        hikeEntity.setShortdesc(rs.getString("shortdesc"));
        hikeEntity.setDesc(rs.getString("desc"));
        hikeEntity.setCustomfields(rs.getString("version_customfields"));
        hikeEntity.setCategoryName(rs.getString("categoryName"));
        
        return hikeEntity;
    }

}
