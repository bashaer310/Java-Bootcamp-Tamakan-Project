package com.example.tamakanfp.Config;



        import com.example.tamakanfp.Service.MyUserDetailsService;
        import lombok.RequiredArgsConstructor;
        import org.springframework.context.annotation.Bean;
        import org.springframework.context.annotation.Configuration;
        import org.springframework.http.HttpMethod;
        import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
        import org.springframework.security.config.annotation.web.builders.HttpSecurity;
        import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
        import org.springframework.security.config.http.SessionCreationPolicy;
        import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
        import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final MyUserDetailsService myUserDetailsService;


    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(myUserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());

        return daoAuthenticationProvider;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)throws Exception{
        httpSecurity.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authenticationProvider(daoAuthenticationProvider())
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST,"/api/v1/jobSeeker/regester","/api/v1/jobProvider/regester","api/v1/job/get-jobs","api/v1/job/get-job-by-job-status/{status}","api/v1/job/get-job-by-job-sector/{sector}","api/v1/job/get-by-salary/{salary}","api/v1/job/get-by-id/{job_id}","api/v1/job/get-by-name/{jobName}","api/v1/job/get-by-city/{city}","api/v1/job/get-by-endDate/{endDate}","api/v1/job/count-all-jobs","api/v1/job/count-job-salary/{salary}","api/v1/job/count-job-name/{name}","api/v1/job/count-job-city/{city}","api/v1/job/count-job-end-date/{endDate}","/api/v1/chatgpt/getChat/{prompt}/chat").permitAll()
                .requestMatchers("api/v1/job-seeker-profile/add-job-seeker-profile","api/v1/job-seeker-profile/update-job-seeker-profile","api/v1/job-seeker-profile/delete-job-seeker-profile","api/v1/job-application/reject-job-application-by-job-seeker/{id}","api/v1/job-application/get-job-applications-by-job-seeker-id","api/v1/job-application/count-job-applications-by-status-and-seeker-id/{status}","/api/v1/job-application/getApplicationbystatusandseekerid","api/v1/job-application/add-job-application/{jobId}","/api/v1/jobSeeker/updateJobSeeker","/api/v1/files/download-certificate/{fileName}").hasAuthority("seeker")
                .requestMatchers("api/v1/job/add-job","api/v1/job/update-job/{id}","api/v1/job/get-job-by-job-provider-id","api/v1/job/stop-receiving-applications/{id}","api/v1/job-application/reject-job-application-by-job-provider/{id}","api/v1/job-application/process-job-application/{id}","/api/v1/jobProvider/updateJobProvider","/api/v1/jobSeeker/sendEmail","/api/v1/job-application/getApplicationByJobID","/api/v1/job-application/getApplicationbyUni","/api/v1/job-application/getApplicationbymajor","/api/v1/job-application/getApplicationbygpa","/api/v1/job-application/sortJobApplicationByGPA","/api/v1/job-application/getApplicationbystatusandjobid","/api/v1/job-application/changeStatustoAccept","/api/v1/files/add-certificate/{job_application}","api/v1/job-application/add-recommendation/{jobApp_id}").hasAuthority("provider")
                .requestMatchers("/api/v1/files/get-certificate-by-jobSeekerid/{jobSeekr_id}","/api/v1/recommendation/get-all-recommendation-by-id/{jobS_id}").hasAnyAuthority("seeker","provider")
                .requestMatchers("api/v1/job-seeker-profile/get-resume-file-by-id/{id}","api/v1/job-seeker-profile/get-job-seeker-profile-by-id/{id}").hasAnyAuthority("admin","seeker","provider")
                .requestMatchers("api/v1/job/update-job-status/{id}","api/v1/job-application/get-job-applications","api/v1/job-application/update-job-application-status/{id}","/api/v1/jobProvider/verify-job-provider/{jobProviderId}").hasAuthority("admin")
                .anyRequest().authenticated()
                .and()
                .logout().logoutUrl("/api/v1/auth/logout")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .and()
                .httpBasic();
        return httpSecurity.build();
    }


}