package com.example.batch_processing.configuration;


import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.support.JdbcTransactionManager;

import com.example.batch_processing.dto.HikeDTO;
import com.example.batch_processing.entity.HikeEntity;
import com.example.batch_processing.job.HikeProcessor;
import com.example.batch_processing.job.HikeWriter;
import com.example.batch_processing.job.JobCompletionNotificationListener;
import com.example.batch_processing.rowmapper.HikeRowMapper;

@Configuration
public class BatchConfiguration {


    private static final Logger LOGGER = LoggerFactory.getLogger(BatchConfiguration.class);

    @Bean
    public JdbcTransactionManager transactionManager(DataSource dataSource) {
        return new JdbcTransactionManager(dataSource);
    }
    
    @Bean
    Job job( JobRepository jobRepository,  JdbcTransactionManager transactionManager,DataSource dataSource){

        LOGGER.info("Creating job");
        return new JobBuilder("job", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(step1(jobRepository,transactionManager,dataSource ))
                .listener(new JobCompletionNotificationListener())
                .build();
    }

    @Bean
    Step step1( JobRepository jobRepository,  JdbcTransactionManager transactionManager,DataSource dataSource){
        LOGGER.info("Creating step");
        return new StepBuilder("step1", jobRepository)
                .<HikeEntity, HikeDTO >chunk(10,transactionManager)
                .reader(reader(dataSource))
                .processor(processor())
                .writer(writer())
                .build();
    }


    @Bean
    public JdbcCursorItemReader<HikeEntity> reader(DataSource dataSource) {
        return new JdbcCursorItemReaderBuilder<HikeEntity>()
                .name("hikeReader")
                .dataSource(dataSource)
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

    @Bean
    HikeProcessor processor() {
        return new HikeProcessor();
    }

    @Bean
    HikeWriter writer() {
        return new HikeWriter();
    }

}
