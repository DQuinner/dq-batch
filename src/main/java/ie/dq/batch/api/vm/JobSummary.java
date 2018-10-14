package ie.dq.batch.api.vm;

import java.util.List;

public class JobSummary {

    private Long jobId;
    private String jobName;
    private String jobParameters;
    private JobExecution jobExecution;
    private List<StepExecution> stepExecutions;

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobParameters() {
        return jobParameters;
    }

    public void setJobParameters(String jobParameters) {
        this.jobParameters = jobParameters;
    }

    public JobExecution getJobExecution() {
        return jobExecution;
    }

    public void setJobExecution(JobExecution jobExecution) {
        this.jobExecution = jobExecution;
    }

    public List<StepExecution> getStepExecutions() {
        return stepExecutions;
    }

    public void setStepExecutions(List<StepExecution> stepExecutions) {
        this.stepExecutions = stepExecutions;
    }
}
