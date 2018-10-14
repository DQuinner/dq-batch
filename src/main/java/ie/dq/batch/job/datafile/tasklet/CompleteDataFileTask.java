package ie.dq.batch.job.datafile.tasklet;

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
public class CompleteDataFileTask implements Tasklet {

    private static final Logger logger = LoggerFactory.getLogger(CompleteDataFileTask.class);

    @Value("${data.file.local}")
    private String localFile;

    @Value("${data.file.remote}")
    private String remoteFile;

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        if(DataFileUtils.fileExists(remoteFile)){
            logger.info("Verified transfer and removing local files");
            DataFileUtils.deleteFile(localFile);
            return RepeatStatus.FINISHED;
        }else {
            throw new Exception("File not transferred - Configure job to retry!");
        }
    }
}
