package ie.dq.batch.service;

import ie.dq.batch.domain.SampleData;

import java.util.List;

public interface SampleDataServiceClient {

    List<SampleData> fetchData(int page, int pageSize);

}
