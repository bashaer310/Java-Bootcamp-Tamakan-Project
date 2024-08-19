package com.example.tamakanfp.Controller;


import com.example.tamakanfp.ApiResponse.ApiResponse;
import com.example.tamakanfp.DTO.JobProviderDTO;
import com.example.tamakanfp.DTO.JobSeekerDTO;
import com.example.tamakanfp.Model.User;
import com.example.tamakanfp.Service.JobProviderService;
import com.example.tamakanfp.Service.JobSeekerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/jobSeeker")
public class JobSeekerController {

    private final JobSeekerService jobSeekerService;


    @PostMapping("/regester")
    public ResponseEntity Regester(@RequestBody @Valid JobSeekerDTO jobSeekerDTO) {
        jobSeekerService.regester(jobSeekerDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Job Seeker added"));

    }

    @PutMapping("/updateJobSeeker")
    public  ResponseEntity updateJobSeeker (@AuthenticationPrincipal User user , @RequestBody @Valid JobSeekerDTO jobSeekerDTO ){
        jobSeekerService.updateJobSeeker(user.getId(), jobSeekerDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Job Seeker updated successfully"));
    }
    @PutMapping("/logout")
    public ResponseEntity Logoout() {
        return ResponseEntity.status(200).body(new ApiResponse("Log out successfully"));

    }
    @GetMapping("/sendEmail")
    public ResponseEntity sendJobOffer(@AuthenticationPrincipal User user){
        return ResponseEntity.status(200).body(jobSeekerService.sendJobOffer(user.getId()));
    }
}
