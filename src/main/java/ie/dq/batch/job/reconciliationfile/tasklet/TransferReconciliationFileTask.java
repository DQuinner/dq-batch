package ie.dq.batch.job.reconciliationfile.tasklet;

import ie.dq.batch.util.DataFileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TransferReconciliationFileTask implements Tasklet {

    private static final Logger logger = LoggerFactory.getLogger(TransferReconciliationFileTask.class);

    @Value("${reconciliation.file.local}")
    private String localFile;

    @Value("${reconciliation.file.remote}")
    private String remoteFile;

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        logger.info("Executing TransferReconciliationFileTask from {} to {} ", remoteFile, localFile);
        DataFileUtils.copyFile(remoteFile, localFile);
        return RepeatStatus.FINISHED;
    }
}
