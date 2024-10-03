# Java-Bootcamp-Tamakan

Tamakan is an extensive platform developed specifically to assist students get real-world work experience while they're still their academic journey. Tamakan serves as a centralized hub where students can explore and obtain part-time job opportunities that align with their interests, abilities, and career goals. Tamakan can help them to build relationships that can be beneficial to their careers. include that Tamakan can help them to gain valuable work experience that they can use to boost their resumes and make them more competitive in the job market.

##### The standout features of this system are:
* Handling the hiring process: The system handles the hiring process, which is carried out by the students and companies. This frees up time for both parties and makes it easier to find a mutually beneficial match.
* Reviews feature: The system incorporates a reviews feature where employers can offer feedback and reviews for job seekers/students upon the successful completion of their part-time engagements. This helps students to get valuable feedback on their work and to improve their skills and knowledge.
* AI-powered resources: The system incorporates a comprehensive suite of AI-powered resources to assist students in crafting compelling and professional resumes. This helps students to create resumes that are tailored to their specific skills and experience and that will help them to stand out to potential employers.
* Experience certificate: The job provider can add an experience certificate after the student is done with their training. This can be a valuable addition to the student's resume and can help them to get a job after graduation.
* Job alerts: Tamakan will send job alerts to the students' email addresses who are matched with the job criteria. This ensures that students are always up-to-date on the latest job openings and that they have the opportunity to apply for jobs that are a good fit for their skills and interests.

## Class Digram
![268192415-ee77ecb8-3bc6-436f-968a-f6d5fced2074](https://github.com/user-attachments/assets/0db2e32d-ed0e-4dfc-8f79-8160ecc77038)

## Use Case Digrams
*  Job seeker
![268192343-6eeef3e9-5a5c-49d1-89e2-099c5a948437](https://github.com/user-attachments/assets/a664274d-c61d-4886-8b3d-b523d83315bd)
> 
* Job provider
![268192254-c9c4c428-6cab-4ca8-808e-59327ef494f3](https://github.com/user-attachments/assets/84328e4f-5405-4f09-aa27-199525fc799c)

## API Documentation
![268192200-99b077ca-d70a-41ae-9d6f-9585476d0602](https://github.com/user-attachments/assets/743d9be0-adbb-4ac2-9e8d-1a20fb972903)
> 
URL: [Post man](https://documenter.getpostman.com/view/28987531/2s9YC2zDDy)

## Figma
Job provider: [figma-job provider](https://www.figma.com/file/kKWzfN1RqmZEZRwGyUsSwm/Tamakan-Job-Provider?type=design&node-id=0%3A1&mode=design&t=tlKUFYIySKYEsyFf-1)
> 
Job seeker: [figma-job seeker](https://www.figma.com/file/bSVDkRqbryoEFuZGzGXG8v/Tamakan-Job-Seeker?type=design&node-id=0%3A1&mode=design&t=aG1xdCNqiJm80ZfP-1)


## My Role
* Service & Controller
  * Job sereker profile class
     *  addJobProfile
     * updateJobProfile
     * getProfileById
     * getReusmeById
  * Job Application class
     * addJobApplication
     * getJobApplication
     * rejectApplicationsByJobProvider
     * rejectApplicationsByJobSeeker
     * processApplications
     * GetApplicationsBySeekerId
     * countApplicationsByStatusAndSeekerId
     * updateJobApplicationStatus
  * Job class
     * addJob
     * getJobs
     * updateJobs
     * StopReceivingApplications
     * getJobByStatus
     * getJobByProviderID
     * getJobBySector
     * updateJobStatus
* Model
     * Job Seeker Profile
     * Job Application 
     * Job
* Relationships
     * one to one (job seeker and job seeker profile)
     * one to many (job seeker and job application)
     * one to many (job and job application)
     * one to many (job provider and job)
* Security
     * Applied Security Authority for all my functions
* Test
     * JobControllerTest


