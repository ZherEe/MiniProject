package com.user.info.project;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(produces = "application/json")
public class UsersController {
    private final Logger logger = LogManager.getLogger(UsersController.class);
    @Autowired
    private UserService userService;


    @RequestMapping(value = {"/users"},method = RequestMethod.GET)
    public Object getAllUsers(@RequestParam(value="min",defaultValue="0.0",required = false) double min,
                                    @RequestParam(value="max",defaultValue ="4000.0",required = false) double max,
                                    @RequestParam(value = "offset",defaultValue = "0",required = false) int offset,
                                    @RequestParam(value = "limit",required = false) String limit,
                                    @RequestParam(value = "sort", required = false) String sort){
        UsersResults results= new UsersResults();
        try{
            results = userService.getAllUsers(min,max,offset,limit,sort);
            return results;
        }
        catch (Exception e){
            logger.error("[GET][ALLUSERS][ERROR] error retrieving user information. Error message:{}",e.getMessage());
            results.setError("Error retrieving user Information. error message:"+e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(results);
        }
    }

    @RequestMapping(value = {"/upload"},method =RequestMethod.POST)
    public Object uploadUserInfo(@RequestBody MultipartFile file){
        UploadResponse response = new UploadResponse();
        if (CSVHelper.hasCSVFormat(file)) {
            try{
                response= userService.uploadUserInfo(file);
                return  response;
            }
            catch (Exception e){
                response.setFailure(0);
                logger.error("[POST][USERSUPLOAD][ERROR] error uploading user information. Error message:{}",e.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
        }
        else{
            response.setFailure(0);
            logger.error("[POST][USERSUPLOAD][ERROR] error uploading user information. File is invalid");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

    }
}
