package com.example.tamakanfp.Service;


import com.example.tamakanfp.ApiResponse.ApiException;
import com.example.tamakanfp.DTO.JobSeekerProfileDTO;
import com.example.tamakanfp.Model.JobSeeker;
import com.example.tamakanfp.Model.JobSeekerProfile;
import com.example.tamakanfp.Model.User;
import com.example.tamakanfp.Repository.JobSeekerProfileRepository;
import com.example.tamakanfp.Repository.JobSeekerRepository;
import com.example.tamakanfp.Repository.UserRepository;
import com.example.tamakanfp.Util.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JobSeekerProfileService {

    private final JobSeekerProfileRepository jobSeekerProfileRepository;
    private final JobSeekerRepository jobSeekerRepository;
    private final UserRepository userRepository;
    public void addJobSeekerProfile(MultipartFile multipartFile, JobSeekerProfileDTO jobSeekerProfileDTO, Integer id) throws IOException {
        User user= userRepository.findUserById(id);
        JobSeeker seeker=jobSeekerRepository.findJopSeekerById(id);

        if (seeker == null || user ==null) {
            throw new ApiException("Seeker or user not found");
        }

        JobSeekerProfile profile=new JobSeekerProfile(null, jobSeekerProfileDTO.getOut0fGPA(), jobSeekerProfileDTO.getCourses(),jobSeekerProfileDTO.getSkills(), jobSeekerProfileDTO.getGPA(),jobSeekerProfileDTO.getMajor(), jobSeekerProfileDTO.getUniversity(),jobSeekerProfileDTO.getAcademicQualification(), jobSeekerProfileDTO.getExpectedGraduationYear(), null,null,null, seeker);

        //Upload Resume file
        profile.setResumeName(multipartFile.getOriginalFilename());
        profile.setResumeType(multipartFile.getContentType());
        profile.setResumeData(FileUtils.compressFile(multipartFile.getBytes()));

        if (profile.getResumeName() == null || profile.getResumeType()==null || profile.getResumeData()==null ){
            throw new ApiException("Resume file not uploaded");
        }
        jobSeekerProfileRepository.save(profile);
    }

    public void updateJobSeekerProfile(MultipartFile multipartFile,JobSeekerProfileDTO profileDTO, Integer id) throws IOException {
        User user= userRepository.findUserById(id);
        JobSeeker seeker=jobSeekerRepository.findJopSeekerById(id);
        JobSeekerProfile profile=jobSeekerProfileRepository.findJobSeekerProfileByIdAndJobSeeker(id,seeker);
        if (profile == null || seeker ==null || user == null) {
            throw new ApiException("Profile or seeker or user not found");
        }

        profile.setOut0fGPA(profileDTO.getOut0fGPA());
        profile.setCourses(profileDTO.getCourses());
        profile.setSkills(profileDTO.getSkills());
        profile.setGPA(profileDTO.getGPA());
        profile.setMajor(profileDTO.getMajor());
        profile.setUniversity(profileDTO.getUniversity());
        profile.setAcademicQualification(profileDTO.getAcademicQualification());
        profile.setExpectedGraduationYear(profileDTO.getExpectedGraduationYear());

        //Upload Resume file
        profile.setResumeName(multipartFile.getOriginalFilename());
        profile.setResumeType(multipartFile.getContentType());
        profile.setResumeData(FileUtils.compressFile(multipartFile.getBytes()));

        if (profile.getResumeName() == null || profile.getResumeType()==null || profile.getResumeData()==null ){
            throw new ApiException("Resume file not uploaded");
        }

        jobSeekerProfileRepository.save(profile);
    }

    public void deleteJobSeekerProfile(Integer id) {
        User user= userRepository.findUserById(id);
        JobSeeker seeker=jobSeekerRepository.findJopSeekerById(id);
        JobSeekerProfile profile=jobSeekerProfileRepository.findJobSeekerProfileByIdAndJobSeeker(id,seeker);
        if (profile == null || seeker ==null || user==null) {
            throw new ApiException("Profile or seeker or user not found");
        }
        seeker.setJobSeekerProfile(null);
        jobSeekerProfileRepository.delete(profile);

    }

    public byte[] getResumeFileById(Integer id){
        JobSeeker seeker=jobSeekerRepository.findJopSeekerById(id);
        Optional<JobSeekerProfile> profile=jobSeekerProfileRepository.findJobSeekerProfileByJobSeeker(seeker);
        if (profile == null || seeker ==null) {
            throw new ApiException("Profile or seeker not found");
        }
        byte[] resumeFile= FileUtils.decompressFile(profile.get().getResumeData());
        return resumeFile;
    }

    public JobSeekerProfile getJobSeekerProfileById(Integer id){
        JobSeekerProfile profile = jobSeekerProfileRepository.findJobSeekerProfileById(id);
        if (profile == null) {
            throw new ApiException("Profile not found");
        }
        return profile;
    }


}
