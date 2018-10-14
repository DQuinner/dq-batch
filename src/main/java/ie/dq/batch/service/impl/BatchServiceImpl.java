package ie.dq.batch.service.impl;

import ie.dq.batch.api.vm.JobSummary;
import ie.dq.batch.service.BatchService;
import ie.dq.batch.util.BatchMetaDataMapper;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BatchServiceImpl implements BatchService {

    @Autowired
    private JobOperator jobOperator;

    @Override
    public Set<String> getRegisteredJobNames(){
        return jobOperator.getJobNames();
    }

    @Override
    public String startJob(String jobName) {
        try {
            Set<Long> runningJobExecutions = jobOperator.getRunningExecutions(jobName);
            if(runningJobExecutions.isEmpty()){
                Long jobId = jobOperator.start(jobName, getDefaultJobParameters());
                return "Started job "+jobName+" with id "+jobId;
            }else{
                return "Job "+jobName+" already running with id "+runningJobExecutions.iterator().next();
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    public JobSummary getJobSummary(Long jobId) {
        JobSummary jobSummary = new JobSummary();
        jobSummary.setJobId(jobId);
        try {
            String jobExecution = jobOperator.getSummary(jobId);
            jobSummary.setJobName(BatchMetaDataMapper.getJobName(jobExecution));
            jobSummary.setJobParameters(BatchMetaDataMapper.getJobParams(jobExecution));
            jobSummary.setJobExecution(BatchMetaDataMapper.mapJobExecutionString(jobExecution));
            jobSummary.setStepExecutions(BatchMetaDataMapper.mapStepExecutions(jobOperator.getStepExecutionSummaries(jobId)));
        }catch (Exception e){
            jobSummary.setJobName(e.getMessage());
        }
        return jobSummary;
    }

    private String getDefaultJobParameters(){
        return new JobParametersBuilder()
                .addDate("date", new Date())
                .toJobParameters()
                .toString();
    }
}
