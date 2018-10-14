package ie.dq.batch.job.datafile;

import ie.dq.batch.domain.FileRow;
import ie.dq.batch.domain.SampleData;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
@EnableBatchProcessing
public class DataFileJobConfig {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Value("${data.chunk.size}")
    private Integer chunkSize;

    @Value("${data.file.local}")
    private String fileLocation;

    @Bean
    public Job dataFileJob(JobExecutionListener listener, Step createDataFileStep, Step transferDataFileStep, Step completeDataFileStep) {
        return jobBuilderFactory.get("dataFileJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(createDataFileStep)
                .next(transferDataFileStep)
                .next(completeDataFileStep)
                .end()
                .build();
    }

    @Bean
    public Step createDataFileStep(ItemReader<SampleData> reader, ItemWriter<FileRow> writer, ItemProcessor<SampleData, FileRow> dataFileProcessor) {
        return stepBuilderFactory.get("createDataFileStep")
                .<SampleData, FileRow> chunk(chunkSize)
                .reader(reader)
                .processor(dataFileProcessor)
                .writer(writer)
                .build();
    }

    @Bean
    protected Step transferDataFileStep(Tasklet transferDataFileTask) {
        return stepBuilderFactory
                .get("transferDataFileStep")
                .tasklet(transferDataFileTask)
                .build();
    }

    @Bean
    protected Step completeDataFileStep(Tasklet completeDataFileTask) {
        return stepBuilderFactory
                .get("completeDataFileStep")
                .tasklet(completeDataFileTask)
                .build();
    }

    @Bean
    public ItemWriter<FileRow> writer() {
        FlatFileItemWriter<FileRow> writer = new FlatFileItemWriter();
        writer.setResource(new FileSystemResource(fileLocation));
        DelimitedLineAggregator<FileRow> delLineAgg = new DelimitedLineAggregator();
        delLineAgg.setDelimiter(",");
        BeanWrapperFieldExtractor<FileRow> fieldExtractor = new BeanWrapperFieldExtractor();
        fieldExtractor.setNames(new String[] {"position1", "position2", "position3"});
        delLineAgg.setFieldExtractor(fieldExtractor);
        writer.setLineAggregator(delLineAgg);
        return writer;
    }

}
