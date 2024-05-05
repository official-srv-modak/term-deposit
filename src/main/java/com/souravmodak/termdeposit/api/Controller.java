package com.souravmodak.termdeposit.api;

import com.souravmodak.termdeposit.model.response.BaseResponse;
import com.souravmodak.termdeposit.model.response.PredictionResponse;
import com.souravmodak.termdeposit.service.TermDepositClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class Controller {

    @GetMapping("/healthcheck")
    public String healthCheck()
    {
        return "Systems working fine";
    }

    private final TermDepositClientService termDepositClientService;

    @Autowired
    public Controller(TermDepositClientService termDepositClientService) {
        this.termDepositClientService = termDepositClientService;
    }

//    @PostMapping("/train/upload")
//    public ResponseEntity<String> uploadTrainCsvFile(@RequestParam("file") MultipartFile file) {
//        if (file.isEmpty()) {
//            return new ResponseEntity<>("Please select a file!", HttpStatus.BAD_REQUEST);
//        }
//
//        try {
//            trainDataService.saveTrainDataFromCsv(file);
//            return new ResponseEntity<>("File uploaded successfully!", HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>("Failed to upload file: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @PostMapping("/test/upload")
//    public ResponseEntity<String> uploadTestCsvFile(@RequestParam("file") MultipartFile file) {
//        if (file.isEmpty()) {
//            return new ResponseEntity<>("Please select a file!", HttpStatus.BAD_REQUEST);
//        }
//
//        try {
//            testDataService.saveTrainDataFromCsv(file);
//            return new ResponseEntity<>("File uploaded successfully!", HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>("Failed to upload file: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @DeleteMapping("/train/delete-all")
//    public ResponseEntity<String> deleteAllTrainData() {
//        try {
//            trainDataService.deleteAllTrainData();
//            return new ResponseEntity<>("All records deleted successfully!", HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>("Failed to delete records: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//    @DeleteMapping("/test/delete-all")
//    public ResponseEntity<String> deleteAllTestData() {
//        try {
//            testDataService.deleteAllTrainData();
//            return new ResponseEntity<>("All records deleted successfully!", HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>("Failed to delete records: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @PostMapping("/client/get-prediction")
    public PredictionResponse getPrediction(@RequestBody Object requestData){
        PredictionResponse response = new PredictionResponse();
        response = termDepositClientService.makePredictionRequest(requestData);
        return response;
    }

    @GetMapping("/client/train-model")
    public BaseResponse trainModel()
    {
        BaseResponse response = new BaseResponse();
        response = termDepositClientService.trainModel();
        return response;
    }

    @GetMapping("/client/delete-model")
    public BaseResponse deleteModel()
    {
        BaseResponse response = new BaseResponse();
        response = termDepositClientService.deleteModel();
        return response;
    }
}
