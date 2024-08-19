package com.example.tamakanfp;

import com.example.tamakanfp.ApiResponse.ApiResponse;
import com.example.tamakanfp.Controller.JobController;
import com.example.tamakanfp.Model.Job;
import com.example.tamakanfp.Model.JobProvider;
import com.example.tamakanfp.Model.User;
import com.example.tamakanfp.Service.JobService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static javax.swing.UIManager.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@ExtendWith(SpringExtension.class)
@WebMvcTest(value = JobController.class,excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class JobControllerTest {

    @MockBean
    JobService jobService;

    @Autowired
    MockMvc mockMvc;

    User user;
    JobProvider jobProvider;
    Job job1, job2;
    List<Job> jobs, jobList;
    ApiResponse apiResponse;


    @BeforeEach
    void setUp(){
         user=new User(null,"albashaer","12345678nh","albashaer@hotmail.com","provider",null,null);
         jobProvider=new JobProvider(null,"albashaer","0500599809","makkah","alawali","IT","4678","pending",user,null);
        job1=new Job(null,"Backend developer","ability to deal with spring boot and build java programs",90,240, LocalDate.parse("2023-01-13"), LocalDate.parse("2023-03-01"),"makkah","King Fahad Street",15000.0,"IT","available",null,jobProvider);
        job2=new Job(null,"Apple developer","ability to deal with swift programs",90,240, LocalDate.parse("2023-01-13"), LocalDate.parse("2023-03-01"),"makkah","King Fahad Street",10000.0,"IT","available",null,jobProvider);
        jobs= Arrays.asList(job1,job2);
        jobList=Arrays.asList(job1);
    }

    @Test
    public void getJobs() throws Exception{
        Mockito.when(jobService.getJobs()).thenReturn(jobs);
        mockMvc.perform(get("/api/v1/job/get-jobs"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].jobName").value("Backend developer"));
    }

    @Test
    public void getJobByJobStatus() throws Exception{
        Mockito.when(jobService.getJobByJobStatus(job1.getStatus())).thenReturn(jobs);
        mockMvc.perform(get("/api/v1/job/get-job-by-job-status/{status}",job1.getStatus()))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].status").value("available"));
    }

    @Test
    public void getJobByJobSector() throws Exception{
        Mockito.when(jobService.getJobByJobSector(job1.getSector())).thenReturn(jobs);
        mockMvc.perform(get("/api/v1/job/get-job-by-job-sector/{sector}",job1.getSector()))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].sector").value("IT"));
    }

    @Test
    public void getJobBySalary() throws Exception{
        Mockito.when(jobService.getJobBySalary(job1.getSalary())).thenReturn(jobList);
        mockMvc.perform(get("/api/v1/job/get-by-salary/{salary}",job1.getSalary()))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].salary").value(15000));
    }



    @Test
    public void countjobbycity() throws Exception{
        Mockito.when(jobService.countJobByCity(job1.getCity())).thenReturn(1);
        mockMvc.perform(get("/api/v1/job/count-job-city/{city}",job1.getCity()))
                .andExpect(status().isOk());
    }


}
