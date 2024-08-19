package com.example.tamakanfp.Controller;


import com.example.tamakanfp.ApiResponse.ApiResponse;
import com.example.tamakanfp.Model.Job;
import com.example.tamakanfp.Model.User;
import com.example.tamakanfp.Service.JobService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("api/v1/job")
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    @GetMapping("get-jobs")
    public ResponseEntity getJobs() {
        return ResponseEntity.status(200).body(jobService.getJobs());
    }

    @PostMapping("add-job")
    public ResponseEntity addJob(@RequestBody @Valid Job job,@AuthenticationPrincipal User user) {
        jobService.addJob(job, user.getId());
        return ResponseEntity.status(200).body(new ApiResponse("Job added"));
    }

    @PutMapping("update-job/{id}")
    public ResponseEntity updateJob(@RequestBody @Valid Job job,@PathVariable Integer id,@AuthenticationPrincipal User user) {
        jobService.updateJob(job,id, user.getId());
        return ResponseEntity.status(200).body(new ApiResponse("Job updated"));

    }

    @GetMapping("get-job-by-job-provider-id")
    public ResponseEntity getJobByJobProviderId(@AuthenticationPrincipal User user){
        return ResponseEntity.status(200).body(jobService.getJobByJobProviderId(user.getId()));

    }
    @GetMapping("get-job-by-job-status/{status}")
    public ResponseEntity getJobByJobStatus(@PathVariable String status){
        return ResponseEntity.status(200).body(jobService.getJobByJobStatus(status));
    }
    @GetMapping("get-job-by-job-sector/{sector}")
    public ResponseEntity getJobByJobSector(@PathVariable String sector){
        return ResponseEntity.status(200).body(jobService.getJobByJobSector(sector));
    }

    @PutMapping("update-job-status/{id}")
    public ResponseEntity updateJobByJobStatus(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(new ApiResponse(jobService.updateJobStatus(id)));

    }

    @PutMapping("stop-receiving-applications/{id}")
    public ResponseEntity stopReceivingApplications(@PathVariable Integer id,@AuthenticationPrincipal User user){
       jobService.stopReceivingApplications(id, user.getId());
        return ResponseEntity.status(200).body(new ApiResponse("Job status updated"));

    }


    ///

    @GetMapping("/get-by-salary/{salary}")
    private ResponseEntity getJobBySalary(@PathVariable Double salary){
        return ResponseEntity.status(200).body(jobService.getJobBySalary(salary));
    }

    @GetMapping("/get-by-id/{job_id}")
    private ResponseEntity getJobByJobId(@PathVariable Integer job_id){
        return ResponseEntity.status(200).body(jobService.getJobById(job_id));
    }

    @GetMapping("/get-by-name/{jobName}")
    private ResponseEntity getJobByJobName(@PathVariable String jobName){
        return ResponseEntity.status(200).body(jobService.getJobByName(jobName));
    }

    @GetMapping("/get-by-city/{city}")
    private ResponseEntity getJobByCity(@PathVariable String city){
        return ResponseEntity.status(200).body(jobService.getJobByCity(city));
    }

    @GetMapping("/get-by-endDate/{endDate}")
    private ResponseEntity getJobByEndDate(@PathVariable LocalDate endDate){
        return ResponseEntity.status(200).body(jobService.getJobByEndDate(endDate));
    }

    @GetMapping("/count-all-jobs")
    private ResponseEntity countAllJobs(){
        return ResponseEntity.status(200).body(jobService.countJobs());
    }


    @GetMapping("/count-job-salary/{salary}")
    private ResponseEntity countJobBySalary(@PathVariable Double salary){
        return ResponseEntity.status(200).body(jobService.countJobBySalary(salary));
    }
    @GetMapping("/count-job-name/{name}")
    private ResponseEntity countJobByName(@PathVariable String name){
        return ResponseEntity.status(200).body(jobService.countJobByName(name));
    }
    @GetMapping("/count-job-city/{city}")
    private ResponseEntity countJobByCity(@PathVariable String city){
        return ResponseEntity.status(200).body(jobService.countJobByCity(city));
    }

    @GetMapping("/count-job-end-date/{endDate}")
    private ResponseEntity countJobByEndDate(@PathVariable LocalDate endDate){
        return ResponseEntity.status(200).body(jobService.countByJobEndDate(endDate));
    }


}