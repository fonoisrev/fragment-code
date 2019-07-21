package com.github.fonoisrev.listener;

import com.github.fonoisrev.entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Override
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("!!! JOB FINISHED! Time to verify the results");
            
            jdbcTemplate.query("SELECT txn_id,amount,status,ip_address FROM order_info",
                               (rs, row) -> new Order(
                                       rs.getString(1),
                                       rs.getString(2),
                                       rs.getString(3),
                                       rs.getString(4)
                                       )
                              ).forEach(order -> log.info("Found <" + order + "> in the database."));
        }
    }
}
