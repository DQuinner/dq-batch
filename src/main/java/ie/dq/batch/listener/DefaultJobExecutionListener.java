package ie.dq.batch.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class DefaultJobExecutionListener implements JobExecutionListener {

    private static final Logger logger = LoggerFactory.getLogger(DefaultJobExecutionListener.class);

    public void beforeJob(JobExecution jobExecution) {
        logger.info("Starting "+jobExecution.getJobInstance().getJobName() +" "+jobExecution.getCreateTime());
    }

    public void afterJob(JobExecution jobExecution) {
        logger.info("Finished "+jobExecution.getJobInstance().getJobName()+" "+jobExecution.getEndTime());
    }

}
