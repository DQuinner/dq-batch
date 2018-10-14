package ie.dq.batch.job.datafile.processor;

import ie.dq.batch.domain.FileRow;
import ie.dq.batch.domain.SampleData;
import ie.dq.batch.util.DataFileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class DataFileProcessor implements ItemProcessor<SampleData, FileRow> {

    private static final Logger logger = LoggerFactory.getLogger(DataFileProcessor.class);

    public FileRow process(SampleData sampleData) {
        logger.info("Processing sampleData "+ sampleData.getId());
        return DataFileUtils.convertToFileRow(sampleData);
    }
}
