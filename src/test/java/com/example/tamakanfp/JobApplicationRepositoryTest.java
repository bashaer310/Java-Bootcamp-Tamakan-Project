package com.example.tamakanfp;

import com.example.tamakanfp.Model.JobApplication;
import com.example.tamakanfp.Model.JobSeeker;
import com.example.tamakanfp.Model.User;
import com.example.tamakanfp.Repository.JobApplicationRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class JobApplicationRepositoryTest {
       @Autowired
       JobApplicationRepository jobApplicationRepository;


        JobApplication jobApplication1;
        JobApplication jobApplication2;

        User user1 ;
        JobSeeker jobSeeker1;

        User user2 ;
        JobSeeker jobSeeker2;

        @BeforeEach
        void setUp() {
//         job1 = new Job(null,"Software engineering","ability to deal with spring boot and build java programs.",90,240, LocalDate.parse("2023-09-10"), LocalDate.parse("2023-03-01"),"Riyadh","King Fahad Street",10000.0,"cs","available",null,null);
//         job2 = new Job(null,"Software engineering","ability to deal with spring boot and build java programs.",90,240, LocalDate.parse("2023-09-10"), LocalDate.parse("2023-03-01"),"Riyadh","King Fahad Street",10000.0,"cs","available",null,null);

            user1 = new User(1,"Mariam22", "pass-123123", "Meme@gmail.com","seeker",null,null);
            jobSeeker1=new JobSeeker(1,"Mariam Almesfer","+966508874443","female","Riyadh","Riyadh",22,user1,null,null);
            jobApplication1=new JobApplication (null,"in-progress",null,jobSeeker1,null,null);

            user2 = new User(1,"Mariam33", "pass-123123", "Meme33@gmail.com","seeker",null,null);
            jobSeeker2=new JobSeeker(1,"Mariam Almesfer","+966508878443","female","Riyadh","Riyadh",22,user1,null,null);
            jobApplication2=new JobApplication (null,"in-progress",null,jobSeeker1,null,null);



        }

        @Test
        public void  findJobApplicationById(){
            jobApplicationRepository.save(jobApplication1);
            jobApplication2=jobApplicationRepository.findJobApplicationById(jobApplication1.getId());
            Assertions.assertThat(jobApplication2).isEqualTo(jobApplication1);

        }



        @Test
        public void  findJobApplicationByIdAndJobSeeker(){
            jobApplicationRepository.save(jobApplication1);
            jobApplication2=jobApplicationRepository.findJobApplicationByIdAndJobSeeker(jobApplication1.getId(),jobApplication1.getJobSeeker());
            Assertions.assertThat(jobApplication2).isEqualTo(jobApplication1);

        }



        @Test
        public void  findJobApplicationByJobSeeker(){
            jobApplicationRepository.save(jobApplication1);
            jobApplicationRepository.save(jobApplication2);

            List<JobApplication> jobApplications= jobApplicationRepository.findJobApplicationByJobSeeker(jobSeeker1);
            Assertions.assertThat(jobApplications.get(0).getJobSeeker().getId()).isEqualTo(jobSeeker1.getId());

        }


        @Test
        public void  findJobApplicationByJobSeekerAndStatus(){
            jobApplicationRepository.save(jobApplication1);
            jobApplicationRepository.save(jobApplication2);

            List<JobApplication> jobApplications= jobApplicationRepository.findJobApplicationByJobSeekerAndStatus(jobSeeker1, jobApplication1.getStatus());
            Assertions.assertThat(jobApplications.get(0).getJobSeeker().getId()).isEqualTo(jobSeeker1.getId());

        }


        @Test
        public void  findJobApplicationByJob(){
            jobApplicationRepository.save(jobApplication1);
            jobApplicationRepository.save(jobApplication2);

            List<JobApplication> jobApplications= jobApplicationRepository.findJobApplicationByJob(jobApplication1.getJob());
            Assertions.assertThat(jobApplications.get(0).getJobSeeker().getId()).isEqualTo(jobSeeker1.getId());

        }



    }
