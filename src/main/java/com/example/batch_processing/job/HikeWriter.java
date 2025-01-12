package com.example.batch_processing.job;

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
    private static final String CSV_HEADER = "id,title,alias,created,username,createdAlias,catid,startDate,endDate,period,dates,next,place,city,country,address,lat,lng,shortdesc,desc,customfields,categoryName";
    private static final String FILE_PATH = "hikes.csv";

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
                      .append(hike.getCreated().toString()).append(CSQ)
                      .append(hike.getUsername()).append(CSQ)
                      .append(hike.getCreatedAlias()).append(CSQ)
                      .append(hike.getCatid()).append(CSQ)
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
                      .append(hike.getCategoryName()).append("\n");
            }
        } catch (IOException e) {
            throw new IOException("Error writing to CSV file", e);
        }
    }
}
