package com.miniproject.Quizziz.config;

import java.io.IOException;

import javax.sql.DataSource;

import org.apache.poi.ss.usermodel.Row;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.configuration.support.DefaultBatchConfiguration;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.miniproject.Quizziz.model.User;
import com.miniproject.Quizziz.repository.BatchRegistrationRepo;

@Configuration
@EnableAutoConfiguration
@EnableBatchProcessing
public class BatchConfiguration extends DefaultBatchConfiguration{
	@Autowired
	BatchRegistrationRepo batchRegistrationRepo;

	
	@Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public Job excelJob(JobRepository jobRepository,
                       JobCompletionNotificationImpl listener,
                       Step steps
    ) {
        return new JobBuilder("excelJob", jobRepository)
                .listener(listener)
                .start(steps)
                .build();
    }

    @Bean
    public Step steps(
            JobRepository jobRepository,
            DataSourceTransactionManager transactionManager,
            ItemReader<Row> itemReader,
            ItemProcessor<Row, User> itemProcessor,
            ItemWriter<User> itemWriter
    ) {
        return new StepBuilder("jobStep", jobRepository)
                .<Row,User>chunk(5, transactionManager)
                .reader(itemReader)
                .processor(itemProcessor)
                .writer(itemWriter)
                .build();
    }
    
//    @Bean
//    @StepScope
//    public ItemReader<Row> itemReader(@Value("#{jobParameters[fullPathFileName]}") String pathToFile) throws IOException {
//        return new ExcelItemReader(pathToFile);
//    }
    @Bean
    @StepScope
    public ExcelItemReader excelItemReader(@Value("#{jobParameters[fullPathFileName]}") String pathToFile) throws IOException {
        return new ExcelItemReader(pathToFile);
    }


    
    @Bean
    public ItemProcessor<Row, User> itemProcessor() {
        return new MyDataModelProcessor();
    }

    @Bean
    public ItemWriter<User> itemWriter(DataSource dataSource) {
        return new MyProcessedDataModelWriter();
    }
}
