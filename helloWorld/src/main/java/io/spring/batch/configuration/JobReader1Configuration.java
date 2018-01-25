//package io.spring.batch.configuration;
//
//import io.spring.batch.reader.StatelessItemReader;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Configuration
//public class JobReader1Configuration {
//
//    @Autowired
//    public JobBuilderFactory jobBuilderFactory;
//
//    @Autowired
//    public StepBuilderFactory stepBuilderFactory;
//
//    @Bean
//    public StatelessItemReader statelessItemReader(){
//        List<String> data = new ArrayList<>(3);
//        data.add("Nikolaus");
//        data.add("Aldo");
//        data.add("Halim");
//
//        return new StatelessItemReader(data);
//    }
//
//    @Bean
//    public Step stepz1(){
//        return stepBuilderFactory.get("stepz1")
//                .<String, String>chunk(2)   //datatype before chunks means the type for reader and writer
//                .reader(statelessItemReader())
//                .writer(list -> {
//                    for (String curItem : list){
//                        System.out.println("curItem = "+curItem);
//                    }
//                }).build();
//    }
//
//    @Bean
//    public Job interfacesJob(){
//        return jobBuilderFactory.get("interfacesJob")
//                .start(stepz1())
//                .build();
//    }
//
//}
