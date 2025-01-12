package com.example.batch_processing.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

import com.example.batch_processing.dto.HikeDTO;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Component
public class HikeWriter implements ItemWriter<HikeDTO> {

    private static final String CSQ = ",";
    private static final String CSV_HEADER = "id,titre,alias,catégorie,créer le,nom,createdAlias,date de début,date de fin,periode,dates,autre date,place,ville,pays,adresse,lat,lng,descrition courte,descscrition longue,niveau physique,niveau technique,massif, altitude max ,dénivelé,num carte";
    private static final String FILE_PATH = "hikes.csv";
        private static final Logger LOGGER = LoggerFactory.getLogger(HikeWriter.class);

    @Override
    public void write(@NonNull Chunk<? extends HikeDTO> chunk) throws Exception {
        List<? extends HikeDTO> hikes = chunk.getItems();
        try (FileWriter writer = new FileWriter(FILE_PATH, true)) {
            // Write the header if the file is empty
            if (writer.getEncoding().isEmpty()) {
                writer.append(CSV_HEADER).append("\n");
            }
            for (HikeDTO hike : hikes) {
                writer.append(String.valueOf(hike.getId())).append(CSQ)
                      .append(hike.getTitle()).append(CSQ)
                      .append(hike.getAlias()).append(CSQ)
                      .append(hike.getCategoryName()).append(CSQ)
                      .append(hike.getCreated().toString()).append(CSQ)
                      .append(hike.getUsername()).append(CSQ)
                      .append(hike.getCreatedAlias()).append(CSQ)
                      .append(hike.getStartDate().toString()).append(CSQ)
                      .append(hike.getEndDate().toString()).append(CSQ)
                      .append(hike.getPeriod()).append(CSQ)
                      .append(hike.getDates()).append(CSQ)
                      .append(hike.getNext().toString()).append(CSQ)
                      .append(hike.getPlace()).append(CSQ)
                      .append(hike.getCity()).append(CSQ)
                      .append(hike.getCountry()).append(CSQ)
                      .append(hike.getAddress()).append(CSQ)
                      .append(String.valueOf(hike.getLat())).append(CSQ)
                      .append(String.valueOf(hike.getLng())).append(CSQ)
                      .append(hike.getShortdesc()).append(CSQ)
                      .append(hike.getDesc()).append(CSQ)
                      .append(hike.getPhysicalLevel()).append(CSQ)
                      .append(hike.getTechnicalLevel()).append(CSQ)
                      .append(hike.getMassif()).append(CSQ)
                      .append(hike.getMaxHeight()).append(CSQ)
                      .append(hike.getDifferenceHeight()).append(CSQ)
                      .append(hike.getMapNumber())
                      .append("\n");
            }
        } catch (IOException e) {
            LOGGER.error("Error writing to CSV file", e);
        }
    }
}
