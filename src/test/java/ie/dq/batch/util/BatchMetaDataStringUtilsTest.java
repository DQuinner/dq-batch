package ie.dq.batch.util;

import ie.dq.batch.api.vm.JobExecution;
import ie.dq.batch.api.vm.StepExecution;
import org.junit.Assert;
import org.junit.Test;

public class BatchMetaDataStringUtilsTest {

    @Test
    public void mapJobExecutionString(){
        String testJobExecution = "JobExecution: id=0, version=2, startTime=2018-10-13 19:06:50.908, endTime=2018-10-13 19:07:42.545, lastUpdated=2018-10-13 19:07:42.545, status=COMPLETED, exitStatus=exitCode=COMPLETED;exitDescription=, job=[JobInstance: id=0, version=0, Job=[dataFileJob]], jobParameters=[{{date=1539454010871}}]";
        JobExecution jobExecution = BatchMetaDataStringUtils.mapJobExecutionString(testJobExecution);
        Assert.assertNotNull(jobExecution);
        Assert.assertEquals("0", jobExecution.getId());
        Assert.assertEquals("2", jobExecution.getVersion());
        Assert.assertEquals("2018-10-13 19:06:50.908", jobExecution.getStartTime());
        Assert.assertEquals("2018-10-13 19:07:42.545", jobExecution.getEndTime());
        Assert.assertEquals("2018-10-13 19:07:42.545", jobExecution.getLastUpdated());
        Assert.assertEquals("COMPLETED", jobExecution.getStatus());
        Assert.assertEquals("", jobExecution.getExitStatus());
        Assert.assertEquals("COMPLETED", jobExecution.getExitCode());
        Assert.assertEquals("", jobExecution.getExitDescription());
    }

    @Test
    public void mapStepExecutionString(){
        String testStepExecution = "StepExecution: id=0, version=503, name=createFileStep, status=COMPLETED, exitStatus=COMPLETED, readCount=50000, filterCount=0, writeCount=50000 readSkipCount=0, writeSkipCount=0, processSkipCount=0, commitCount=501, rollbackCount=0, exitDescription=";
        StepExecution stepExecution = BatchMetaDataStringUtils.mapStepExecutionString(testStepExecution);
        Assert.assertNotNull(stepExecution);
        Assert.assertEquals("0", stepExecution.getId());
        Assert.assertEquals("503", stepExecution.getVersion());
        Assert.assertEquals("createFileStep", stepExecution.getName());
        Assert.assertEquals("COMPLETED", stepExecution.getStatus());
        Assert.assertEquals("COMPLETED", stepExecution.getExitStatus());
        Assert.assertEquals("50000", stepExecution.getReadCount());
        Assert.assertEquals("0", stepExecution.getFilterCount());
        Assert.assertEquals("50000", stepExecution.getWriteCount());
        Assert.assertEquals("0", stepExecution.getReadSkipCount());
        Assert.assertEquals("0", stepExecution.getWriteSkipCount());
        Assert.assertEquals("0", stepExecution.getProcessSkipCount());
        Assert.assertEquals("501", stepExecution.getCommitCount());
        Assert.assertEquals("0", stepExecution.getRollbackCount());
        Assert.assertEquals("", stepExecution.getExitDescription());
    }

    @Test
    public void getJobName(){
        String testJobExecution = "JobExecution: id=0, version=2, startTime=2018-10-13 19:06:50.908, endTime=2018-10-13 19:07:42.545, lastUpdated=2018-10-13 19:07:42.545, status=COMPLETED, exitStatus=exitCode=COMPLETED;exitDescription=, job=[JobInstance: id=0, version=0, Job=[dataFileJob]], jobParameters=[{{date=1539454010871}}]";
        Assert.assertEquals("dataFileJob", BatchMetaDataStringUtils.getJobName(testJobExecution));
    }

    @Test
    public void getJobParams(){
        String testJobExecution = "JobExecution: id=0, version=2, startTime=2018-10-13 19:06:50.908, endTime=2018-10-13 19:07:42.545, lastUpdated=2018-10-13 19:07:42.545, status=COMPLETED, exitStatus=exitCode=COMPLETED;exitDescription=, job=[JobInstance: id=0, version=0, Job=[dataFileJob]], jobParameters=[{{date=1539454010871}}]";
        Assert.assertEquals("{date=1539454010871}", BatchMetaDataStringUtils.getJobParams(testJobExecution));
    }

}
