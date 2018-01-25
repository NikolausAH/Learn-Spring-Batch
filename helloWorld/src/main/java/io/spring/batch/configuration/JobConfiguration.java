//package io.spring.batch.configuration;
//
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.StepContribution;
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.job.builder.FlowBuilder;
//import org.springframework.batch.core.job.flow.Flow;
//import org.springframework.batch.core.scope.context.ChunkContext;
//import org.springframework.batch.core.step.tasklet.Tasklet;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//
//@Configuration
//@EnableBatchProcessing
//public class JobConfiguration {
//    @Autowired
//    private StepBuilderFactory stepBuilderFactory;
//
//    @Bean
//    public Step stepx(){
//        return stepBuilderFactory.get("stepx")
//                .tasklet(new Tasklet() {
//                    @Override
//                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
//                        System.out.println("Step X from inside the flow");
//                        return RepeatStatus.FINISHED;
//                    }
//                }).build();
//    }
//
//    public Step stepy(){
//        return stepBuilderFactory.get("stepy")
//                .tasklet(new Tasklet() {
//                    @Override
//                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
//                        System.out.println("Step Y from inside the flow");
//                        return RepeatStatus.FINISHED;
//                    }
//                }).build();
//    }
//
//    @Bean
//    public Flow foo(){
//        FlowBuilder<Flow> flowBuilder = new FlowBuilder<>("foo"); //the name is foo
//
//        flowBuilder.start(stepx())
//                .next(stepy())
//                .end();
//
//        return flowBuilder.build();
//
//    }
//}
