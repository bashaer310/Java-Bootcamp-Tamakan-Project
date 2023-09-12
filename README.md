# Java-Bootcamp-Tamakan

Tamakan is an extensive platform developed specifically to assist students get real-world work experience while they're still their academic journey. Tamakan serves as a centralized hub where students can explore and obtain part-time job opportunities that align with their interests, abilities, and career goals. Tamakan can help them to build relationships that can be beneficial to their careers. include that Tamakan can help them to gain valuable work experience that they can use to boost their resumes and make them more competitive in the job market.

##### The standout features of this system are:
* Handling the hiring process: The system handles the hiring process, which is carried out by the students and companies. This frees up time for both parties and makes it easier to find a mutually beneficial match.
* Reviews feature: The system incorporates a reviews feature where employers can offer feedback and reviews for job seekers/students upon the successful completion of their part-time engagements. This helps students to get valuable feedback on their work and to improve their skills and knowledge.
* AI-powered resources: The system incorporates a comprehensive suite of AI-powered resources to assist students in crafting compelling and professional resumes. This helps students to create resumes that are tailored to their specific skills and experience and that will help them to stand out to potential employers.
* Experience certificate: The job provider can add an experience certificate after the student is done with their training. This can be a valuable addition to the student's resume and can help them to get a job after graduation.
* Job alerts: Tamakan will send job alerts to the students' email addresses who are matched with the job criteria. This ensures that students are always up-to-date on the latest job openings and that they have the opportunity to apply for jobs that are a good fit for their skills and interests.

## Class Digram
![PTJMS (5)](https://github.com/bashaer310/Java-Bootcamp-Tamakan/assets/110439978/e84a5228-1761-4d35-8ebf-c9694ecd3c9e)

## Use Case Digrams
*  Job seeker
![PTJMS (8)](https://github.com/bashaer310/Java-Bootcamp-Tamakan/assets/110439978/c4125f6d-d33a-451b-98a3-9151f501b291)

* Job provider
![PTJMS (7)](https://github.com/bashaer310/Java-Bootcamp-Tamakan/assets/110439978/d8e159f5-4d44-4ed4-aaeb-e22cf052397f)

## API Documentation
![267152126-5029d072-9ff3-4e4d-b089-483f3b1cd603](https://github.com/bashaer310/Java-Bootcamp-Tamakan/assets/110439978/f74b1914-2ea1-4acc-9bca-66b164aebb82)
> 
URL: [Post man](https://documenter.getpostman.com/view/28987531/2s9YC2zDDy)

## Figma
Job provider: [figma-job provider](https://www.figma.com/file/kKWzfN1RqmZEZRwGyUsSwm/Tamakan-Job-Provider?type=design&node-id=0%3A1&mode=design&t=tlKUFYIySKYEsyFf-1)
> 
Job seeker: [figma-job seeker](https://www.figma.com/file/bSVDkRqbryoEFuZGzGXG8v/Tamakan-Job-Seeker?type=design&node-id=0%3A1&mode=design&t=aG1xdCNqiJm80ZfP-1)

## Presentation
URL: [canva link](https://www.canva.com/design/DAFuIerps88/OU0CPTI1rU1SGJOjQjHFrA/edit?utm_content=DAFuIerps88&utm_campaign=designshare&utm_medium=link2&utm_source=sharebutton)

## My Role
*Functions:
•	get-job-by-salary
•	get-job-by-job-name
•	get-job-by-job-id
•	get-jobs-by-city
•	get-job-by-end-date
•	count-Jobs
•	count-job-by-salary
•	count-job-by-name
•	count-job-by-city
•	count-job-by-endDate
•	getChatMessage
•	add-Recommendation
•	get-recommendations
•	upload-certificates
•	get-certificate
•	download-Certificates

*Modls:
•	ChatMessagePrompot
•	 FileUtils
•	Certificates
•	Recommendations
•	RecommendationsDTO

*Relations
one to one (certificate and job Application)
one to one (Recommendations and job Application)

*Repository
•	Certificates Repository
•	Recommendation Repository

*Job Repository:
•	findJobBySalary 
•	getJobByJobId
•	findJobByJobName   
•	findJobByCity 
•	 findJobByEndDate
•	long count();

*Test:
•	JobApplicationServiceTest
•	JobServiceTest
•	RecommendationServiceTest

*Security

•	Applied Security Authority and Authentication for all my functions.






