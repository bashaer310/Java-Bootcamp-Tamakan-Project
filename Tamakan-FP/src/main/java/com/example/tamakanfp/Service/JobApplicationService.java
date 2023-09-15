package com.example.tamakanfp.Service;

import com.example.tamakanfp.ApiResponse.ApiException;
import com.example.tamakanfp.Model.*;
import com.example.tamakanfp.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobApplicationService {

    private final JobApplicationRepository jobApplicationRepository;
    private final JobRepository jobRepository;
    private final JobSeekerRepository jobSeekerRepository;
    private final UserRepository userRepository;
    private final JobProviderRepository jobProviderRepository;

    public List<JobApplication> getJobApplications(){ //deleted
        return jobApplicationRepository.findAll();
    }

    public void  addJobApplication(Integer jobId,Integer seekerId){
        User user=userRepository.findUserById(seekerId);
        Job job=jobRepository.findJobById(jobId);
        JobSeeker seeker=jobSeekerRepository.findJopSeekerById(seekerId);
        JobApplication application=jobApplicationRepository.findJobApplicationByJobSeekerAndJob(seeker,job);
        if (job==null ||seeker==null ||user==null)
            throw new ApiException("Job or seeker or user not found");

        if (application!=null || seeker.getJobSeekerProfile() ==null || job.getStatus().equals("unavailable"))
            throw new ApiException("Can't apply the job");

        JobApplication jobApplication=new JobApplication(null,"in-progress",job,seeker,null,null);

        List<JobApplication> seekerJobApplication=jobApplicationRepository.findJobApplicationByJobSeeker(jobApplication.getJobSeeker());
        for (JobApplication app:seekerJobApplication) {
            if (jobApplication.getJob().getStartDate().isAfter(app.getJob().getEndDate())&&app.getStatus().equals("accepted"))
                throw new ApiException("Can't apply the job");

        }

        jobApplicationRepository.save(jobApplication);
    }

    public String updateJobApplicationStatus(Integer id){

        JobApplication jobApplication=jobApplicationRepository.findJobApplicationById(id);
        if (jobApplication==null)
            throw new ApiException("Job application id not found");

        if (jobApplication.getJob().getStatus().equals("unavailable") && (!jobApplication.getStatus().equals("accepted") || !jobApplication.getStatus().equals("rejected")))
        {jobApplication.setStatus("rejected");
        jobApplicationRepository.save(jobApplication);
        return "Job application status updated";}

        return "Can't change job application status";
    }

    public void rejectJobApplicationByJobProvider(Integer jobProviderId,Integer jobApplicationId){
        User user=userRepository.findUserById(jobProviderId);
        JobProvider provider=jobProviderRepository.findJopProviderById(jobProviderId);
        JobApplication application=jobApplicationRepository.getJobApplicationByIdAndByJobProviderId(jobApplicationId,jobProviderId);
        if (application==null || user ==null || provider ==null)
            throw new ApiException("Job application or user or provider not found");

        if (application.getStatus().equals("rejected")|| application.getStatus().equals("accepted")||application.getJob().getStatus().equals("unavailable"))
            throw new ApiException("Can't change job application status to rejected");

        //if in-progress or hiring-process
        application.setStatus("rejected");
        jobApplicationRepository.save(application);
    }
    public void rejectJobApplicationByJobSeeker(Integer jobSeekerId,Integer jobApplicationId){
        User user=userRepository.findUserById(jobSeekerId);
        JobSeeker seeker=jobSeekerRepository.findJopSeekerById(jobSeekerId);
        JobApplication application=jobApplicationRepository.findJobApplicationByIdAndJobSeeker(jobApplicationId,seeker);

        if (application==null || seeker ==null || user ==null)
            throw new ApiException("Job application or job seeker or user not found");
        if (application.getStatus().equals("rejected") || application.getStatus().equals("accepted")||application.getStatus().equals("hiring-process")|| application.getJob().getStatus().equals("unavailable"))
            throw new ApiException("Can't rejected of the job");

        //if in-progress

        application.setStatus("rejected");
        jobApplicationRepository.save(application);
    }

    public void processJobApplication(Integer jobProviderId,Integer jobApplicationId){
        User user=userRepository.findUserById(jobProviderId);
        JobProvider provider=jobProviderRepository.findJopProviderById(jobProviderId);
        JobApplication application=jobApplicationRepository.getJobApplicationByIdAndByJobProviderId(jobApplicationId,jobProviderId);
        if (application==null || user ==null|| provider ==null)
            throw new ApiException("Job application or provider or user not found");

        if (application.getStatus().equals("accepted")||application.getStatus().equals("hiring-process")||application.getStatus().equals("rejected") ||application.getJob().getStatus().equals("unavailable"))
            throw new ApiException("Can't change job application status to hiring-process");

        //if in-progress
        application.setStatus("hiring-process");
        jobApplicationRepository.save(application);

    }

    public List<JobApplication> GetJobApplicationsByJobSeekerId(Integer seekerId){
        User user=userRepository.findUserById(seekerId);
        JobSeeker seeker=jobSeekerRepository.findJopSeekerById(seekerId);
        List<JobApplication> jobApplications=jobApplicationRepository.findJobApplicationByJobSeeker(seeker);

        if (seeker==null || jobApplications== null || user == null)
            throw new ApiException("Job seeker or job applications or user not found");


        return  jobApplications;
    }
    public Integer countJobApplicationsByStatusAndSeekerId(String status,Integer seekerId){
        User user=userRepository.findUserById(seekerId);
        JobSeeker seeker=jobSeekerRepository.findJopSeekerById(seekerId);
        List<JobApplication> jobApplications=jobApplicationRepository.findJobApplicationByJobSeekerAndStatus(seeker,status);

        if (seeker==null || jobApplications== null || user == null)
            throw new ApiException("Job seeker or job applications or user not found");


        return  jobApplications.size();
    }

    public List<JobApplication> getApplicationByJobID(Integer user_id,Integer job_Id) {
        User user = userRepository.findUserById(user_id);
        if (user == null) {
            throw new ApiException("User id not found");
        }

        JobProvider jobProvider = jobProviderRepository.findJopProviderById(user_id);
        if (jobProvider == null) {
            throw new ApiException("User not allowed");
        }

        Job job = jobRepository.findJobById(job_Id);
        if (job == null) {
            throw new ApiException("job id not found");
        }
        List<JobApplication> jobApplications = jobApplicationRepository.findJobApplicationByJob(job);
        if (jobApplications.isEmpty()) {
            throw new ApiException("No job application yet");
        }
        return jobApplications;
    }

    public List<JobApplication> getApplicationbyUni(Integer user_id,String uni ,Integer jobid) {
        User user = userRepository.findUserById(user_id);
        if (user == null) {
            throw new ApiException("User id not found");
        }
        JobProvider jobProvider = jobProviderRepository.findJopProviderById(user_id);
        if (jobProvider == null) {
            throw new ApiException("User not allowed");
        }

        List<JobApplication> jobApplications = jobApplicationRepository.findJobApplicationByJobSeeker_JobSeekerProfile_UniversityAndJob_Id(uni,jobid);
        if (jobApplications.isEmpty()) {
            throw new ApiException("There are no applicants from this university ");
        }
        return jobApplications;
    }

    public List<JobApplication> findJobApplicationByMajor(Integer user_id,String major, Integer jobId) {
        User user = userRepository.findUserById(user_id);
        if (user == null) {
            throw new ApiException("User id not found");
        }
        JobProvider jobProvider = jobProviderRepository.findJopProviderById(user_id);
        if (jobProvider == null) {
            throw new ApiException("User not allowed");
        }

        List<JobApplication> jobApplications = jobApplicationRepository.findJobApplicationByJobSeeker_JobSeekerProfile_MajorAndJob_Id(major, jobId);
        if (jobApplications.isEmpty()) {
            throw new ApiException("There are no applicants from this major ");
        }
        return jobApplications;
    }


    public List<JobApplication> findJobApplicationByGPA(Integer user_id,Double gpa, Integer jobId) {
        User user = userRepository.findUserById(user_id);
        if (user == null) {
            throw new ApiException("User id not found");
        }
        JobProvider jobProvider = jobProviderRepository.findJopProviderById(user_id);
        if (jobProvider == null) {
            throw new ApiException("User not allowed");
        }

        List<JobApplication> jobApplications = jobApplicationRepository.findJobApplicationByJobSeeker_JobSeekerProfileGPAAndJob_Id(gpa, jobId);
        if (jobApplications.isEmpty()) {
            throw new ApiException("There are no applicants with this GPA");
        }
        return jobApplications;
    }


    public List<JobApplication> sortJobApplicationByGPA(Integer user_id,Integer jobId) {
        User user = userRepository.findUserById(user_id);
        if (user == null) {
            throw new ApiException("User id not found");
        }
        JobProvider jobProvider = jobProviderRepository.findJopProviderById(user_id);
        if (jobProvider == null) {
            throw new ApiException("User not allowed");
        }

        List<JobApplication> jobApplications = jobApplicationRepository.findJobApplicationByJob_Id(jobId);
        if (jobApplications.isEmpty()) {
            throw new ApiException("No job Application");
        }
        return jobApplications;
    }


    public List<JobApplication> findJobApplicationByStatusandJobid(Integer user_id,String Status, Integer jobId) {
        User user = userRepository.findUserById(user_id);
        if (user == null) {
            throw new ApiException("User id not found");
        }
        JobProvider jobProvider = jobProviderRepository.findJopProviderById(user_id);
        if (jobProvider == null) {
            throw new ApiException("User not allowed");
        }

        List<JobApplication> jobApplications = jobApplicationRepository.findJobApplicationByStatusAndJob_Id(Status, jobId);
        if (jobApplications.isEmpty()) {
            throw new ApiException("There are no applicants with this Status");
        }
        return jobApplications;
    }


    public List<JobApplication> findJobApplicationByStatusandJobSeekerid(Integer user_id,String Status) {
        User user = userRepository.findUserById(user_id);
        if (user == null) {
            throw new ApiException("User id not found");
        }
        JobSeeker jobSeeker = jobSeekerRepository.findJopSeekerById(user_id);
        if (jobSeeker == null) {
            throw new ApiException("User not  allowed");
        }
        List<JobApplication> jobApplications = jobApplicationRepository.findJobApplicationByStatusAndJobSeeker_Id(Status, user_id);
        if (jobApplications.isEmpty()) {
            throw new ApiException("You do not have job application yet");
        }
        return jobApplications;
    }


    public void ChangeStatustoAccept(Integer user_id, Integer jobApplication_Id) {
        User user = userRepository.findUserById(user_id);
        if (user == null) {
            throw new ApiException("User id not found");
        }
        JobProvider jobProvider = jobProviderRepository.findJopProviderById(user_id);
        if (jobProvider == null) {
            throw new ApiException("User not allowed");
        }
        JobApplication jobApplication = jobApplicationRepository.findJobApplicationById(jobApplication_Id);
        if (jobApplication == null)
            throw new ApiException("Job application not found");



        if (jobApplication.getStatus().equals("rejected") || jobApplication.getStatus().equals("accepted") || jobApplication.getJob().getStatus().equals("unavailable"))
            throw new ApiException("Can't accepted of the job");


        jobApplication.setStatus("accepted");
        jobApplicationRepository.save(jobApplication);
    }



}
