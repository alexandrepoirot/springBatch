package com.example.batch_processing.job;


import javax.sql.DataSource;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.stereotype.Component;

import com.example.batch_processing.entity.HikeEntity;
import com.example.batch_processing.rowmapper.HikeRowMapper;


@Component
public class HikeReader implements ItemReader<HikeEntity> {

    private JdbcCursorItemReader<HikeEntity> jdbcCursorItemReader;

    public HikeReader(DataSource dataSource) {
       this.jdbcCursorItemReader = new JdbcCursorItemReaderBuilder<HikeEntity>()
                .dataSource(dataSource)
                .name("hikeReader")
                .sql("SELECT kcqd8_icagenda_events.id as idevent," +
                        "kcqd8_icagenda_events.title as titleEvent," +
                        "kcqd8_icagenda_events.alias," +
                        "kcqd8_icagenda_events.created," +
                        "kcqd8_icagenda_events.username," +
                        "kcqd8_icagenda_events.created_by_alias, " +
                        "kcqd8_icagenda_events.catid as categoryid, " +
                        "kcqd8_icagenda_events.startdate," +
                        "kcqd8_icagenda_events.enddate," +
                        "kcqd8_icagenda_events.period," +
                        "kcqd8_icagenda_events.dates," +
                        "kcqd8_icagenda_events.next," +
                        "kcqd8_icagenda_events.place, " +
                        "kcqd8_icagenda_events.city," +
                        "kcqd8_icagenda_events.country," +
                        "kcqd8_icagenda_events.address," +
                        "kcqd8_icagenda_events.lat," +
                        "kcqd8_icagenda_events.lng," +
                        "kcqd8_icagenda_events.shortdesc," +
                        "kcqd8_icagenda_events.desc, " +
                        "kcqd8_icagenda_events.version_customfields, " +
                        "kcqd8_icagenda_category.title as categoryname " +
                        "FROM kcqd8_icagenda_events, kcqd8_icagenda_category " +
                        "WHERE kcqd8_icagenda_events.catid in (1,2,3,4,6,10,11,12,13) " +
                        "AND kcqd8_icagenda_events.catid = kcqd8_icagenda_category.id " +
                        "ORDER BY kcqd8_icagenda_events.next DESC")
                .rowMapper(new HikeRowMapper())
                .build();
    }

    @Override
    public HikeEntity read() throws Exception {
        
        return jdbcCursorItemReader.read();
    }

}
