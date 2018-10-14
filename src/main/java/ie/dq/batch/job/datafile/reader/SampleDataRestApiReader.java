package ie.dq.batch.job.datafile.reader;

import ie.dq.batch.domain.SampleData;
import ie.dq.batch.service.SampleDataServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SampleDataRestApiReader implements ItemReader<SampleData> {

    private static final Logger logger = LoggerFactory.getLogger(SampleDataRestApiReader.class);

    @Value("${data.chunk.size}")
    private Integer pageSize;

    @Autowired
    private SampleDataServiceClient sampleDataServiceClient;

    private int nextDataIndex;
    private List<SampleData> sampleData;
    private int page;

    public SampleData read() {
        //fetch page data from rest api
        if (dataNotInitialized() || fetchNewPage()) {
            logger.info("Fetching data for page = "+page);
            nextDataIndex = 0;
            sampleData = sampleDataServiceClient.fetchData(page, pageSize);
        }
        //process fetched data and manage pagination
        SampleData nextSampleData = null;
        if (nextDataIndex < sampleData.size()) {
            nextSampleData = sampleData.get(nextDataIndex);
            nextDataIndex++;
        }else{
            logger.info("Finished Page "+page);
            this.sampleData = null;
            if(nextDataIndex < pageSize){
                logger.info("Finished Batch Reader");
                page = 0;
            }
        }
        return nextSampleData;
    }

    private boolean dataNotInitialized() {
        return this.sampleData == null;
    }

    private boolean fetchNewPage(){
        if(nextDataIndex == pageSize){
            page++;
            return true;
        }else{
            return false;
        }
    }
}
