//package io.spring.batch.configuration;
//
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.StepContribution;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepScope;
//import org.springframework.batch.core.scope.context.ChunkContext;
//import org.springframework.batch.core.step.tasklet.Tasklet;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class JobParameterConfiguration {
//
//    @Autowired
//    public JobBuilderFactory jobBuilderFactory;
//
//    @Autowired
//    public StepBuilderFactory stepBuilderFactory;
//
//    @Bean
//    @StepScope
//    public Tasklet helloWorldTasklet(@Value("#{jobParameters['message']}") String message) {
//        return (StepContribution stepContribution, ChunkContext chunkContext) -> {
//            System.out.println(message);
//            return RepeatStatus.FINISHED;
//        };
//    }
//
//    @Bean
//    public Step step1x(){
//        return stepBuilderFactory.get("step1x")
//                .tasklet(helloWorldTasklet(null))
//                .build();
//    }
//
//    @Bean
//    public Job jobParametersJob(){
//        return jobBuilderFactory.get("jobParameterJob")
//                .start(step1x())
//                .build();
//    }
//}
