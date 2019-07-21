package com.github.fonoisrev;

import com.github.fonoisrev.entity.Order;
import com.github.fonoisrev.listener.JobCompletionNotificationListener;
import com.github.fonoisrev.processor.OrderProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

import javax.sql.DataSource;

@SpringBootApplication
@EnableBatchProcessing
public class SpringBatchDemoApplication {
    
    @Autowired
    public JobBuilderFactory jobBuilderFactory;
    
    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    public static void main(String[] args) {
        SpringApplication.run(SpringBatchDemoApplication.class, args);
    }
    
    @Bean
    public FlatFileItemReader<Order> reader() {
        return new FlatFileItemReaderBuilder<Order>()
                .name("orderItemReader")
                .resource(new ClassPathResource("order.csv"))
                .delimited()
                .names(new String[]{"txn_id","amount","status","ip_address"})
                .fieldSetMapper(new BeanWrapperFieldSetMapper<Order>(){{
                    setTargetType(Order.class);
                }})
                .build();
    }
    
    @Bean
    public OrderProcessor processor() {
        return new OrderProcessor();
    }
    
    
    @Bean
    public JdbcBatchItemWriter<Order> writer(DataSource dataSource){
        return new JdbcBatchItemWriterBuilder<Order>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("insert into order_info (txn_id,amount,status,ip_address) values (:txnId,:amount,:status,:ipAddress);")
                .dataSource(dataSource)
                .build();
    }
    
    @Bean
    public Job importOrderJob(JobCompletionNotificationListener listener, Step step1) {
        return jobBuilderFactory.get("importOrderJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1)
                .end()
                .build();
    }
    
    @Bean
    public Step step1(JdbcBatchItemWriter<Order> writer) {
        return stepBuilderFactory.get("step1")
                .<Order, Order> chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer)
                .build();
    }

}
