package io.spring.batch.configuration;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import io.spring.batch.domain.Customer;
import io.spring.batch.domain.CustomerRowMapper;

import io.spring.batch.listener.ChunkListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class JobReaderDB {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;
//
    @Autowired
    public DataSource dataSource;
//
//	@Bean
//	public JdbcCursorItemReader<Customer> cursorItemReader() {
//		JdbcCursorItemReader<Customer> reader = new JdbcCursorItemReader<>();
//
//		reader.setSql("select id, firstName, lastName, birthdate from customer order by lastName, firstName");
//		reader.setDataSource(this.dataSource);
//		reader.setRowMapper(new CustomerRowMapper());
//
//		return reader;
//	}

    @Primary
    @Bean
    public JdbcPagingItemReader<Customer> pagingItemReader() {
        JdbcPagingItemReader<Customer> reader = new JdbcPagingItemReader<>();

        reader.setDataSource(this.dataSource);
        reader.setFetchSize(10);
        reader.setRowMapper(new CustomerRowMapper());

        MySqlPagingQueryProvider queryProvider = new MySqlPagingQueryProvider();
        queryProvider.setSelectClause("id, firstName, lastName, birthdate");
        queryProvider.setFromClause("from customer");

        Map<String, Order> sortKeys = new HashMap<>(1);

        sortKeys.put("id", Order.ASCENDING);

        queryProvider.setSortKeys(sortKeys);

        reader.setQueryProvider(queryProvider);

        return reader;
    }

    @Bean
    public ItemWriter<Customer> customerItemWriter() {
        return items -> {
            for (Customer item : items) {
                System.out.println(item.toString());
            }
        };
    }

    @Bean
    public Step step5cuy() {
        return stepBuilderFactory.get("step5cuy")
                .<Customer, Customer>chunk(10)
                .reader(pagingItemReader())
                .faultTolerant()
                .listener(new ChunkListener())
                .writer(customerItemWriter())
                .build();
    }

    @Bean
    public Job job() {
        return jobBuilderFactory.get("job")
                .start(step5cuy())
                .build();
    }
}
