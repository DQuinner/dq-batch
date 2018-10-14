package ie.dq.batch.util;

import ie.dq.batch.api.vm.JobExecution;
import ie.dq.batch.api.vm.StepExecution;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class BatchMetaDataMapper {

    private static final String ID = "id";
    private static final String VERSION = "version";
    private static final String START_TIME = "startTime";
    private static final String END_TIME = "endTime";
    private static final String LAST_UPDATED = "lastUpdated";
    private static final String STATUS = "status";
    private static final String EXIT_STATUS = "exitStatus";
    private static final String EXIT_CODE = "exitCode";
    private static final String EXIT_DESC = "exitDescription";
    private static final String END_JOB = "job=[JobInstance:";
    private static final String NAME = "name";
    private static final String READ_COUNT = "readCount";
    private static final String FILTER_COUNT = "filterCount";
    private static final String WRITE_COUNT = "writeCount";
    private static final String READ_SKIP_COUNT = "readSkipCount";
    private static final String WRITE_SKIP_COUNT = "writeSkipCount";
    private static final String PROCESS_SKIP_COUNT = "processSkipCount";
    private static final String COMMIT_COUNT = "commitCount";
    private static final String ROLLBACK_COUNT = "rollbackCount";

    private static final String JOBNAME_START = "Job=[";
    private static final String JOBNAME_END = "]],";

    private static final String JOBPARAM_START = "jobParameters=[{";
    private static final String JOBPARAM_END = "}]";

    public static JobExecution mapJobExecutionString(String jobExecutionString){
        JobExecution jobExecution = new JobExecution();
        jobExecution.setId(extractPropertyValue(jobExecutionString, ID, VERSION));
        jobExecution.setVersion(extractPropertyValue(jobExecutionString, VERSION, START_TIME));
        jobExecution.setStartTime(extractPropertyValue(jobExecutionString, START_TIME, END_TIME));
        jobExecution.setEndTime(extractPropertyValue(jobExecutionString, END_TIME, LAST_UPDATED));
        jobExecution.setLastUpdated(extractPropertyValue(jobExecutionString, LAST_UPDATED, STATUS));
        jobExecution.setStatus(extractPropertyValue(jobExecutionString, STATUS, EXIT_STATUS));
        jobExecution.setExitStatus(extractPropertyValue(jobExecutionString, EXIT_STATUS, EXIT_CODE));
        jobExecution.setExitCode(extractPropertyValue(jobExecutionString, EXIT_CODE, EXIT_DESC));
        jobExecution.setExitDescription(extractPropertyValue(jobExecutionString, EXIT_DESC, END_JOB));
        return jobExecution;
    }

    public static StepExecution mapStepExecutionString(String stepExecutionString){
        StepExecution stepExecution = new StepExecution();
        stepExecution.setId(extractPropertyValue(stepExecutionString, ID, VERSION));
        stepExecution.setVersion(extractPropertyValue(stepExecutionString, VERSION, NAME));
        stepExecution.setName(extractPropertyValue(stepExecutionString, NAME, STATUS));
        stepExecution.setStatus(extractPropertyValue(stepExecutionString, STATUS, EXIT_STATUS));
        stepExecution.setExitStatus(extractPropertyValue(stepExecutionString, EXIT_STATUS, READ_COUNT));
        stepExecution.setReadCount(extractPropertyValue(stepExecutionString, READ_COUNT, FILTER_COUNT));
        stepExecution.setFilterCount(extractPropertyValue(stepExecutionString, FILTER_COUNT, WRITE_COUNT));
        stepExecution.setWriteCount(extractPropertyValue(stepExecutionString, WRITE_COUNT, READ_SKIP_COUNT));
        stepExecution.setReadSkipCount(extractPropertyValue(stepExecutionString, READ_SKIP_COUNT, WRITE_SKIP_COUNT));
        stepExecution.setWriteSkipCount(extractPropertyValue(stepExecutionString, WRITE_SKIP_COUNT, PROCESS_SKIP_COUNT));
        stepExecution.setProcessSkipCount(extractPropertyValue(stepExecutionString, PROCESS_SKIP_COUNT, COMMIT_COUNT));
        stepExecution.setCommitCount(extractPropertyValue(stepExecutionString, COMMIT_COUNT, ROLLBACK_COUNT));
        stepExecution.setRollbackCount(extractPropertyValue(stepExecutionString, ROLLBACK_COUNT, EXIT_DESC));
        stepExecution.setExitDescription(extractLastPropertyValue(stepExecutionString, EXIT_DESC));
        return stepExecution;
    }

    public static List<StepExecution> mapStepExecutions(Map<Long, String> stepExecutions){
        List<StepExecution> stepExecutionList = new LinkedList<>();
        stepExecutions.forEach((k,v) -> {
            stepExecutionList.add(BatchMetaDataMapper.mapStepExecutionString(v));
        });
        return stepExecutionList;
    }

    public static String getJobName(String jobExecution){
        StringBuilder sb = new StringBuilder(jobExecution);
        return sb.substring(sb.indexOf(JOBNAME_START), sb.indexOf(JOBNAME_END))
                .replace(',',';')
                .replace(JOBNAME_START,"")
                .replace(';', ' ')
                .trim();
    }

    public static String getJobParams(String jobExecution){
        StringBuilder sb = new StringBuilder(jobExecution);
        return sb.substring(sb.indexOf(JOBPARAM_START), sb.indexOf(JOBPARAM_END))
                .replace(',',';')
                .replace(JOBPARAM_START,"")
                .replace(';', ' ')
                .trim();
    }

    private static String extractPropertyValue(String jobExecution, String propName, String nextPropName){
        StringBuilder sb = new StringBuilder(jobExecution);
        return sb.substring(sb.indexOf(propName), sb.indexOf(nextPropName))
                .replace(',',';')
                .replace(propName+"=","")
                .replace(';', ' ')
                .trim();
    }

    private static String extractLastPropertyValue(String execution, String propName){
        StringBuilder sb = new StringBuilder(execution);
        return sb.substring(sb.indexOf(propName), sb.length())
                .replace(',',';')
                .replace(propName+"=","")
                .replace(';', ' ')
                .trim();
    }

}
