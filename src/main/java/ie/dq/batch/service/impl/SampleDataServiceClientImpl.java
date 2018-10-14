package ie.dq.batch.service.impl;

import ie.dq.batch.domain.SampleData;
import ie.dq.batch.service.SampleDataServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class SampleDataServiceClientImpl implements SampleDataServiceClient {

    private static final Logger logger = LoggerFactory.getLogger(SampleDataServiceClientImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Value("${data.service.url}")
    private String url;

    @Override
    public List<SampleData> fetchData(int page, int pageSize) {
        ResponseEntity<SampleData[]> response = restTemplate.getForEntity(url+"?page="+page+"&size="+pageSize+"&sort=id,ASC", SampleData[].class);
        SampleData[] sampleData = response.getBody();
        logger.info("Found {} sample data records", sampleData.length);
        return Arrays.asList(sampleData);
    }
}
