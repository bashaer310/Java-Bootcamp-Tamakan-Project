package com.example.tamakanfp;

import com.example.tamakanfp.Model.*;
import com.example.tamakanfp.Repository.JobApplicationRepository;
import com.example.tamakanfp.Repository.JobRepository;
import com.example.tamakanfp.Repository.JobSeekerProfileRepository;
import com.example.tamakanfp.Repository.JobSeekerRepository;
import com.example.tamakanfp.Service.JobApplicationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class JobApplicationServiceTest {


    @InjectMocks
    JobApplicationService jobApplicationService;

    @Mock
    JobApplicationRepository jobApplicationRepository;

    @Mock
    JobSeekerProfileRepository jobSeekerProfileRepository;

    @Mock
    JobSeekerRepository jobSeekerRepository;

    @Mock
    JobRepository jobRepository;


    JobApplication jobApplication;
    JobApplication jobApplication2;

    JobSeekerProfile jobSeekerProfile;

    Job job;

    JobProvider jobProvider;

    JobSeeker jobSeeker;


    List<JobApplication> jobApplicationList;



    @BeforeEach
    void setUp() {

        jobApplication = new JobApplication(null, "accepted", job, jobSeeker, null, null);
       jobApplication2 = new JobApplication(null, "accepted", null, jobSeeker, null, null);
        jobProvider = new JobProvider(null, "Site", "966541594246", "Riyadh", "aulya", "IT", "12345", null, null, null);
        job = new Job(null, "QA", " check quality", null, null, null, null, "riyadh", "aulya", 1000.0, "IT", "accepted", null, null);

        jobApplicationList=new ArrayList<>();

        jobApplicationList.add(jobApplication);
        jobApplicationList.add(jobApplication2);

        jobSeekerRepository.save(jobSeeker);
        
    }

    @Test
        public void getJobApplication(){
            when(jobApplicationRepository.findAll()).thenReturn(jobApplicationList);
            List<JobApplication> jobApplicationList1=jobApplicationService.getJobApplications();
            Assertions.assertEquals(jobApplicationList1,jobApplicationList);
            verify(jobApplicationRepository,times(1)).findAll();

        }



}