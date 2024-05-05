package com.souravmodak.termdeposit.model.response;
import org.json.simple.JSONObject;

public class PredictionResponse extends BaseResponse{
    JSONObject clientData;
    int decision;

    public PredictionResponse() {
    }

    public JSONObject getJsonData() {
        return clientData;
    }

    public void setJsonData(JSONObject clientData) {
        this.clientData = clientData;
    }

    public int getDecision() {
        return decision;
    }

    public void setDecision(int decision) {
        this.decision = decision;
    }
}
