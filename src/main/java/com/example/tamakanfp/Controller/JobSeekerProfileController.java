package com.example.tamakanfp.Controller;


import com.example.tamakanfp.ApiResponse.ApiResponse;
import com.example.tamakanfp.DTO.JobSeekerProfileDTO;
import com.example.tamakanfp.Model.JobApplication;
import com.example.tamakanfp.Model.User;
import com.example.tamakanfp.Service.JobSeekerProfileService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("api/v1/job-seeker-profile")
@RequiredArgsConstructor

public class JobSeekerProfileController {

    private final JobSeekerProfileService jobSeekerProfileService;

    @PostMapping(value = "add-job-seeker-profile")
    private ResponseEntity addJobSeekerProfile(@RequestParam("resumeFile")MultipartFile multipartFile,@RequestParam("profileDto")String profileDtoText , @AuthenticationPrincipal User user) throws IOException {

        @Valid
        JobSeekerProfileDTO profileDtoJSON =new ObjectMapper().readValue(profileDtoText,JobSeekerProfileDTO.class);

        jobSeekerProfileService.addJobSeekerProfile(multipartFile,profileDtoJSON, user.getId());
        return ResponseEntity.status(200).body(new ApiResponse("Profile added"));
    }
    @PutMapping("update-job-seeker-profile")
    private ResponseEntity updateJobSeekerProfile(@RequestParam("resumeFile")MultipartFile multipartFile, @RequestParam("profileDto")String profileDtoText, @AuthenticationPrincipal User user) throws IOException {
        @Valid
        JobSeekerProfileDTO profileDtoJSON =new ObjectMapper().readValue(profileDtoText,JobSeekerProfileDTO.class);
        jobSeekerProfileService.updateJobSeekerProfile(multipartFile,profileDtoJSON, user.getId());
       return ResponseEntity.status(200).body(new ApiResponse("Profile updated"));
    }
    @DeleteMapping("delete-job-seeker-profile")
    private ResponseEntity deleteJobSeekerProfile(@AuthenticationPrincipal User user) {
        jobSeekerProfileService.deleteJobSeekerProfile(user.getId());
        return ResponseEntity.status(200).body(new ApiResponse("Profile delete"));
    }

    @GetMapping("get-resume-file-by-id/{id}")
    private ResponseEntity getResumeFileById(@PathVariable Integer id) {
        return ResponseEntity.status(200).contentType(MediaType.valueOf(MediaType.APPLICATION_PDF_VALUE)).body(jobSeekerProfileService.getResumeFileById(id));

    }

    @GetMapping("get-job-seeker-profile-by-id/{id}")
    private ResponseEntity getJobSeekerProfileById(@PathVariable Integer id){
        return ResponseEntity.status(200).body(jobSeekerProfileService.getJobSeekerProfileById(id));
    }




}
