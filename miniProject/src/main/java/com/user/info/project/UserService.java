package com.user.info.project;


import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.LockModeType;
import java.util.List;

@Service
public class UserService {
    private final Logger logger = LogManager.getLogger(UserService.class);

    @Autowired
    private UserInfoRepo userInfoRepo;


    public UsersResults getAllUsers(double min, double max, int offset, String limit, String sort) throws Exception {
        logger.info("[GET][ALLUSERS][START] retrieving users information");
        UsersResults results = new UsersResults();

        if(sort!=null){
            Integer sortNumber;
            if(sort!=null && sort.equalsIgnoreCase("name")){
                sortNumber=1;
            }
            else if(sort!=null && sort.equalsIgnoreCase("salary")){
                sortNumber=2;
            }
            else{
                throw new Exception("Invalid sort parameter");
            }
            results.setResults(userInfoRepo.getUserInfo(min,max,offset,limit,sortNumber));
            logger.info("[GET][ALLUSERS][END] user retrieved");
            return results;
        }

        results.setResults(userInfoRepo.getUserInfo(min,max,offset,limit));
        logger.info("[GET][ALLUSERS][END] user retrieved");

        return results;
    }

    @Transactional
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    public UploadResponse uploadUserInfo(MultipartFile file) throws Exception{
        logger.info("[USERSUPLOAD][START] Uploading users information");
        UploadResponse response = new UploadResponse();
        List<UserInfo> userToUpload = CSVHelper.csvToUserInfo(file.getInputStream());
        userInfoRepo.saveAll(userToUpload);
        response.setSuccess(1);
        logger.info("[USERSUPLOAD][END] Uploading users information");
        return response;
    }
}
