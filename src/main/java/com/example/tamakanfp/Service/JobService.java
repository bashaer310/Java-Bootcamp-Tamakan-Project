package com.example.tamakanfp.Service;

import com.example.tamakanfp.ApiResponse.ApiException;
import com.example.tamakanfp.ApiResponse.ApiResponse;
import com.example.tamakanfp.Model.*;
import com.example.tamakanfp.Repository.JobProviderRepository;
import com.example.tamakanfp.Repository.JobRepository;
import com.example.tamakanfp.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JobService {

    private final JobRepository jobRepository;
    private final JobProviderRepository jobProviderRepository;
    private final UserRepository userRepository;
    private final JobSeekerService jobSeekerService;
    public List<Job> getJobs(){
        return jobRepository.findAll();
    }
    public void addJob(Job job,Integer id){
        User user= userRepository.findUserById(id);
        JobProvider provider=jobProviderRepository.findJopProviderById(id);
       if(provider==null || user ==null)
           throw new ApiException("Provider or User not found");

       if (provider.getStatus().equals("pending"))
           throw new ApiException("Don't allow adda job");

        job.setJobProvider(provider);
        jobRepository.save(job);
        jobSeekerService.sendJobOffer(id);

    }

    public String updateJobStatus(Integer id) {
        Job job=jobRepository.findJobById(id);
        if (job==null)
            throw new ApiException("Job not found");

        if (job.getEndDate().isBefore(LocalDate.now()) && !job.getStatus().equals("unavailable")) {
            job.setStatus("unavailable");
            jobRepository.save(job);
            return "Job status updated";
        }

        return null;
    }
    public void updateJob(Job job,Integer id,Integer providerId){
        User user= userRepository.findUserById(providerId);
        JobProvider provider=jobProviderRepository.findJopProviderById(providerId);
        Job oldJob=jobRepository.findJobByIdAndJobProvider(id,provider);
        if (oldJob==null || provider ==null  || user ==null)
            throw new ApiException("Job or provider or user not found");

        oldJob.setJobName(job.getJobName());
        oldJob.setJobDescription(job.getJobDescription());
        oldJob.setCity(job.getCity());
        oldJob.setAddress(oldJob.getAddress());
        oldJob.setStartDate(job.getStartDate());
        oldJob.setEndDate(job.getEndDate());
        oldJob.setStatus(job.getStatus());
        oldJob.setSector(job.getSector());
        oldJob.setSalary(job.getSalary());
        oldJob.setWorkingDays(job.getWorkingDays());
        oldJob.setWorkingHours(job.getWorkingHours());

        jobRepository.save(oldJob);
    }

    public void deleteJob(Integer id,Integer providerId) { //deleted
        User user= userRepository.findUserById(providerId);
        JobProvider provider=jobProviderRepository.findJopProviderById(providerId);
        Job job = jobRepository.findJobByIdAndJobProvider(id,provider);
        if (job==null|| provider ==null ||user==null)
         throw new ApiException("Job or provider or user not found");

        jobRepository.delete(job);
    }

    public List<Job> getJobByJobProviderId(Integer id){
        User user= userRepository.findUserById(id);
        JobProvider provider=jobProviderRepository.findJopProviderById(id);
        List<Job> job = jobRepository.findJobByJobProvider(provider);
        if (job==null|| provider ==null||user==null)
            throw new ApiException("Job or provider or user not found");

        return  job;
    }

    public List<Job> getJobByJobStatus(String status){
        List<Job> job = jobRepository.findJobByStatus(status);
        if (job==null)
            throw new ApiException("Job not found");

        return  job;
    }

    public List<Job> getJobByJobSector(String sector){
        List<Job> job = jobRepository.findJobBySector(sector);
        if (job==null)
            throw new ApiException("Job not found");

        return  job;
    }

    public void stopReceivingApplications(Integer id,Integer providerId){
        User user=userRepository.findUserById(providerId);
        JobProvider provider=jobProviderRepository.findJopProviderById(providerId);
        Job job = jobRepository.findJobByIdAndJobProvider(id,provider);
        if (job==null|| provider ==null||user==null)
            throw new ApiException("Job or provider or user not found");

        if(job.getStatus().equals("unavailable"))
            throw new ApiException("Can't change status, it is already unavailable");

        job.setStatus("unavailable");
        jobRepository.save(job);
    }

    ///
    ////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////

    //No security because it will return jobs,the jobs are available to any one to see

    public  List<Job> getJobBySalary(Double salary){

        List<Job> job=jobRepository.findJobBySalary(salary);

        if (job.isEmpty()){
            throw new ApiException("There is no job with this salary");
        }


        return job;

    }
    //No security because it will return jobs,the jobs are available to any one to see

    public Job getJobById(Integer job_id){

        Job job=jobRepository.findJobById(job_id);

        if (job==null){
            throw new ApiException("The job id is null");
        }
        return job;

    }
    //No security because it will return jobs,the jobs are available to any one to see

    public List<Job> getJobByName(String jobName){

        List<Job> job=jobRepository.findJobByJobName(jobName);

        if (job.isEmpty()){
            throw new ApiException("There is no job with this Name");
        }
        return job;

    }
    //No security because it will return jobs,the jobs are available to any one to see

    public List<Job> getJobByCity(String city){

        List<Job> job=jobRepository.findJobByCity(city);

        if (job.isEmpty()){
            throw new ApiException("There is no job Located in this city");
        }
        return job;

    }

    //No security because it will return jobs,the jobs are available to any one to see
    public List<Job> getJobByEndDate(LocalDate endDate ){
        List<Job> job=jobRepository.findJobByEndDate(endDate);

        if (job.isEmpty()){
            throw new ApiException("There is no job End in this Date");
        }
        return job;

    }

    public Long countJobs(){

        return jobRepository.count();
    }

    public int countJobBySalary(Double salary){

        List<Job> job=jobRepository.findJobBySalary(salary);

        if (job==null)
            throw new ApiException("Jobs not found");

        return job.size();
    }

    public Integer countJobByName(String jobName){

        List<Job> job=jobRepository.findJobByJobName(jobName);

        if (job==null)
            throw new ApiException("Jobs not found");

        return job.size();
    }

    public Integer countJobByCity(String city){

        List<Job> job=jobRepository.findJobByCity(city);

        if (job==null)
            throw new ApiException("Jobs not found");
        return job.size();}

    public Integer countByJobEndDate(LocalDate endDate){


        List<Job> job=jobRepository.findJobByEndDate(endDate);
        if (job==null)
            throw new ApiException("Jobs not found");

        return job.size();}

}
