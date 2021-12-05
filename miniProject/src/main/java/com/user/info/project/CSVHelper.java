package com.user.info.project;


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CSVHelper {
    public static String TYPE = "text/csv";


    public static boolean hasCSVFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }

    public static List<UserInfo> csvToUserInfo(InputStream is) throws Exception {
        try{
            BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
            List<UserInfo> recordsToUpload = new ArrayList<>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for(CSVRecord csvRecord : csvRecords){
                UserInfo user = new UserInfo();
                if(csvRecord.get("name") !=null && !csvRecord.get("name").isEmpty()){
                    user.setName(csvRecord.get("name"));
                }
                else{
                    throw new Exception("data is invalid");
                }
                if(csvRecord.get("salary") !=null && !csvRecord.get("salary").isEmpty()){
                    double salary = Double.parseDouble(csvRecord.get("salary"));
                    if(salary<0.0){
                        continue;
                    }
                    user.setSalary(salary);
                }
                else{
                    throw new Exception("data is invalid");
                }
                recordsToUpload.add(user);

            }
            return recordsToUpload;

        }
        catch(IOException e){
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
        catch (Exception e){
            throw new Exception("data is invalid");
        }
    }

}
