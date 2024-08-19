package com.example.tamakanfp.Controller;



import com.example.tamakanfp.ApiResponse.ApiResponse;
import com.example.tamakanfp.DTO.JobProviderDTO;
import com.example.tamakanfp.DTO.JobSeekerDTO;
import com.example.tamakanfp.Model.User;
import com.example.tamakanfp.Service.JobProviderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/jobProvider")
public class JobProviderController {

       private final JobProviderService jobProviderService;


    @PostMapping("/regester")
    public ResponseEntity Regester(@RequestBody @Valid JobProviderDTO jobProviderDTO) {
        jobProviderService.regester(jobProviderDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Job Provider added"));

    }

    @PutMapping("/updateJobProvider")
    public  ResponseEntity updateJobProvider (@AuthenticationPrincipal User user, @RequestBody @Valid JobProviderDTO jobProviderDTO ){
        jobProviderService.updateJobProvider(user.getId(),jobProviderDTO);
        return ResponseEntity.status(200).body(new ApiResponse("JobProvider updated successfully"));
    }
    @GetMapping("/logout")
    public ResponseEntity Logoout() {
        return ResponseEntity.status(200).body(new ApiResponse("Log out successfully"));

    }

    @PutMapping("verify-job-provider/{jobProviderId}")
    public ResponseEntity verifyJobProvider(@AuthenticationPrincipal User user,@PathVariable Integer jobProviderId)
    {
        jobProviderService.verifyJobProvider(jobProviderId, user.getId());
        return ResponseEntity.status(200).body(new ApiResponse("JobProvider updated successfully"));

    }
}
