package ie.dq.batch.service;

import ie.dq.batch.api.vm.JobSummary;

import java.util.Set;

public interface BatchService {

    Set<String> getRegisteredJobNames();

    String startJob(String jobName);

    JobSummary getJobSummary(Long jobId);

}
