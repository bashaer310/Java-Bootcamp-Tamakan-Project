package com.example.tamakanfp.Controller;


import com.example.tamakanfp.ApiResponse.ApiResponse;
import com.example.tamakanfp.Model.JobApplication;
import com.example.tamakanfp.Model.User;
import com.example.tamakanfp.Service.JobApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/job-application")
@RequiredArgsConstructor
public class JobApplicationController {

    private final JobApplicationService jobApplicationService;


    @GetMapping("get-job-applications")
    private ResponseEntity getJobApplications() { //deleted
        return ResponseEntity.status(200).body(jobApplicationService.getJobApplications());
    }

    @PostMapping("add-job-application/{jobId}")
    private ResponseEntity addJobApplication(@PathVariable Integer jobId,@AuthenticationPrincipal User user) {
        jobApplicationService.addJobApplication(jobId,user.getId());
        return ResponseEntity.status(200).body(new ApiResponse("Job Application added"));
    }
    @PutMapping("update-job-application-status/{id}")
    private ResponseEntity updateJobApplicationStatus(@PathVariable Integer id) {

        return ResponseEntity.status(200).body(new ApiResponse(jobApplicationService.updateJobApplicationStatus(id)));

    }

    @PutMapping("reject-job-application-by-job-provider/{id}")
    private ResponseEntity rejectJobApplicationByJobProvider(@AuthenticationPrincipal User user, @PathVariable Integer id) {
        jobApplicationService.rejectJobApplicationByJobProvider(user.getId(), id);
        return ResponseEntity.status(200).body(new ApiResponse("Job Application status updated"));

    }
    @PutMapping("reject-job-application-by-job-seeker/{id}")
    private ResponseEntity rejectJobApplicationByJobSeeker(@AuthenticationPrincipal User user, @PathVariable Integer id) {
        jobApplicationService.rejectJobApplicationByJobSeeker(user.getId(), id);
        return ResponseEntity.status(200).body(new ApiResponse("Job Application status updated"));

    }

    @PutMapping("process-job-application/{id}")
    private ResponseEntity processJobApplication(@AuthenticationPrincipal User user, @PathVariable Integer id) {
        jobApplicationService.processJobApplication(user.getId(), id);
        return ResponseEntity.status(200).body(new ApiResponse("Job Application status updated"));

    }

    @GetMapping("get-job-applications-by-job-seeker-id")
    public ResponseEntity GetJobApplicationsByJobSeekerId(@AuthenticationPrincipal User user){
        return ResponseEntity.status(200).body(jobApplicationService.GetJobApplicationsByJobSeekerId(user.getId()));

    }
    @GetMapping("count-job-applications-by-status-and-seeker-id/{status}")
    public ResponseEntity countJobApplicationsByStatusAndSeekerId(@PathVariable String status,@AuthenticationPrincipal User user){
        return ResponseEntity.status(200).body(jobApplicationService.countJobApplicationsByStatusAndSeekerId(status, user.getId()));

    }

    @GetMapping("getApplicationByJobID/{job_id}")
    private ResponseEntity getApplicationByJobID(@AuthenticationPrincipal User user, @PathVariable Integer job_id){
        return ResponseEntity.status(200).body(jobApplicationService.getApplicationByJobID(user.getId(),job_id));
    }

    @GetMapping("getApplicationbyUni/{uni}/{jobid}")
    private ResponseEntity getApplicationbyUni(@AuthenticationPrincipal User user,@PathVariable String uni , @PathVariable Integer jobid){
        return ResponseEntity.status(200).body(jobApplicationService.getApplicationbyUni(user.getId(), uni,  jobid));
    }



    @GetMapping("getApplicationbymajor/{jobId}/{major}")
    private ResponseEntity getApplicationbyMajor(@AuthenticationPrincipal User user ,@PathVariable String major , @PathVariable Integer jobId){
        return ResponseEntity.status(200).body(jobApplicationService.findJobApplicationByMajor(user.getId(),major,jobId));
    }


    @GetMapping("getApplicationbygpa/{gpa}/{jobId}")
    private ResponseEntity getApplicationbyGPA(@AuthenticationPrincipal User user,@PathVariable Double gpa , @PathVariable Integer jobId){
        return ResponseEntity.status(200).body(jobApplicationService.findJobApplicationByGPA(user.getId(),gpa,jobId));
    }


    @GetMapping("sortJobApplicationByGPA/{jobId}")
    private ResponseEntity sortJobApplicationByGPA(@AuthenticationPrincipal User user,@PathVariable Integer jobId){
        return ResponseEntity.status(200).body(jobApplicationService.sortJobApplicationByGPA(user.getId(),jobId));
    }


    @GetMapping("getApplicationbystatusandjobid/{jobId}/{Status}")
    private ResponseEntity getApplicationbyStatus(@AuthenticationPrincipal User user,@PathVariable String Status , @PathVariable Integer jobId){
        return ResponseEntity.status(200).body(jobApplicationService.findJobApplicationByStatusandJobid(user.getId(),Status,jobId));
    }

    @GetMapping("getApplicationbystatusandseekerid/{Status}")
    private ResponseEntity getApplicationbyStatusandseeker(@AuthenticationPrincipal User user,@PathVariable String Status){
        return ResponseEntity.status(200).body(jobApplicationService.findJobApplicationByStatusandJobSeekerid(user.getId(),Status));
    }



    @GetMapping("changeStatustoAccept/{Id}")
    private ResponseEntity ChangeStatustoAccept(@AuthenticationPrincipal User user, @PathVariable Integer Id){
        jobApplicationService.ChangeStatustoAccept(user.getId(),Id);
        return ResponseEntity.status(200).body(new ApiResponse("Job Application status changed to accept"));
    }
}
