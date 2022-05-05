package com.example.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class BatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BatchApplication.class, args);
	}

	@Autowired
	JobLauncher jobLauncher;
	@Autowired
	Job job;

	@Override
	public void run(String... args) throws Exception {
		// Pass the required Job Parameters from here to read it anywhere within
		// Spring Batch infrastructure
		JobParameters jobParameters = new JobParametersBuilder()
				.addDate("data",new Date())
				.toJobParameters();
		JobExecution execution = jobLauncher.run(job, jobParameters);
		System.out.println("STATUS :: " + execution.getStatus());
	}
}
