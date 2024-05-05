package com.souravmodak.termdeposit.service;

import com.souravmodak.termdeposit.api.TermDepositModelClient;
import com.souravmodak.termdeposit.model.response.BaseResponse;
import com.souravmodak.termdeposit.model.response.PredictionResponse;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class TermDepositClientService {

    private final TermDepositModelClient termDepositModelClient;

    @Autowired
    public TermDepositClientService(TermDepositModelClient termDepositModelClient) {
        this.termDepositModelClient = termDepositModelClient;
    }

    public PredictionResponse makePredictionRequest(Object requestData) {
        PredictionResponse baseResponse = new PredictionResponse();
        try {
            Object prediction = termDepositModelClient.makePrediction(requestData);
            JSONObject predObj = convertToJSONObject((LinkedHashMap<String, Object>) prediction);
            baseResponse.setJsonData(convertToJSONObject((LinkedHashMap<String, Object>) requestData));
            baseResponse.setMessage("Request Successful");
            baseResponse.setStatus(HttpStatus.OK);
            baseResponse.setDecision((Integer) predObj.get("decision"));

        } catch (Exception e) {
            baseResponse.setMessage("Failed to request " + e.getMessage());
            baseResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        finally {
            return baseResponse;
        }
    }

    public BaseResponse trainModel() {
        BaseResponse baseResponse = new BaseResponse();
        try {
            Object prediction = termDepositModelClient.trainModel();
            JSONObject predObj = convertToJSONObject((LinkedHashMap<String, Object>) prediction);
            baseResponse.setMessage((String) predObj.get("message"));
            baseResponse.setStatus(HttpStatus.OK);

        } catch (Exception e) {
            baseResponse.setMessage("Failed to request " + e.getMessage());
            baseResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        finally {
            return baseResponse;
        }
    }

    public BaseResponse deleteModel() {
        BaseResponse baseResponse = new BaseResponse();
        try {
            Object prediction = termDepositModelClient.deleteModel();
            JSONObject predObj = convertToJSONObject((LinkedHashMap<String, Object>) prediction);
            baseResponse.setMessage((String) predObj.get("message"));
            baseResponse.setStatus(HttpStatus.OK);

        } catch (Exception e) {
            baseResponse.setMessage("Failed to request " + e.getMessage());
            baseResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        finally {
            return baseResponse;
        }
    }

        public static JSONObject convertToJSONObject(LinkedHashMap<String, Object> map) {
        JSONObject jsonObject = new JSONObject();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            jsonObject.put(entry.getKey(), entry.getValue());
        }
        return jsonObject;
    }
}
