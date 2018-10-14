package ie.dq.batch.job.reconciliationfile;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReconciliationJobConfig {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job reconciliationFileJob(JobExecutionListener listener, Step transferReconciliationFileStep) {
        return jobBuilderFactory.get("reconciliationFileJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(transferReconciliationFileStep)
                .end()
                .build();
    }

    @Bean
    protected Step transferReconciliationFileStep(Tasklet transferReconciliationFileTask) {
        return stepBuilderFactory
                .get("transferReconciliationFileStep")
                .tasklet(transferReconciliationFileTask)
                .build();
    }



}
