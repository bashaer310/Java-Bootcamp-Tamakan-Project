package com.example.tamakanfp;

import com.example.tamakanfp.ApiResponse.ApiException;
import com.example.tamakanfp.Model.Job;
import com.example.tamakanfp.Model.JobProvider;
import com.example.tamakanfp.Repository.JobProviderRepository;
import com.example.tamakanfp.Repository.JobRepository;
import com.example.tamakanfp.Service.JobService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class JobServiceTest {


    @InjectMocks
    JobService jobService;

    @Mock
    JobRepository jobRepository;

    @Mock
    JobProviderRepository jobProviderRepository;



    Job job1;
    Job job2;



    JobProvider jobProvider;
    JobProvider jobProvider2;
    List<Job>jobs;



    @BeforeEach
    void setUp() {

        job1=new Job(null,"developer","development",60,120,null,null,"Riyadh","aulya",1000.0,"IT",null,null,jobProvider);
        job2=new Job(null,"developer","development",60,120,null,null,"Riyadh","aulya",1000.0,"IT",null,null,jobProvider);
        jobProvider=new JobProvider(1,"SITE","0541594246","jeddah","aulya","it","12345","in progress",null,null);
        jobProvider2=new JobProvider(2,"SITE","0541594246","jeddah","aulya","it","12345","in progress",null,null);
        jobProviderRepository.save(jobProvider);
        jobs=new ArrayList<>();
//
        jobs.add(job1);
        jobs.add(job2);






    }


    @Test
    public void addjob(){
    when(jobProviderRepository.findJopProviderById(jobProvider.getId())).thenReturn(jobProvider);
    jobService.addJob(job1,jobProvider.getId());
    verify(jobProviderRepository,times(1)).findJopProviderById(jobProvider.getId());
    verify(jobRepository,times(1)).save(job1);

    }



    @Test
    public void getjobs(){
        when(jobRepository.findAll()).thenReturn(jobs);
        List<Job>jobList=jobService.getJobs();
        Assertions.assertEquals(jobList,jobs);
        verify(jobRepository,times(1)).findAll();

    }



    @Test
    public void getJobByJobStatus(){
     when(jobRepository.findJobByStatus(job1.getStatus())).thenReturn(jobs);
     jobService.getJobByJobStatus(job1.getStatus());
     verify(jobRepository,times(1)).findJobByStatus(job1.getStatus());

    }


}
