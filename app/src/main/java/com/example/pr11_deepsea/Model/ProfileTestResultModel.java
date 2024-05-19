package com.example.pr11_deepsea.Model;

public class ProfileTestResultModel {
    private long testDate;
    private String testName, testLevel, testId;
    private int testScore;

    public ProfileTestResultModel() {
    }

    public String getTestId() {
        return testId;
    }

    public ProfileTestResultModel(long testDate, String testName, String testLevel, int testScore) {
        this.testDate = testDate;
        this.testName = testName;
        this.testLevel = testLevel;
        this.testScore = testScore;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    public long getTestDate() {
        return testDate;
    }

    public void setTestDate(long testDate) {
        this.testDate = testDate;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getTestLevel() {
        return testLevel;
    }

    public void setTestLevel(String testLevel) {
        this.testLevel = testLevel;
    }

    public int getTestScore() {
        return testScore;
    }

    public void setTestScore(int testScore) {
        this.testScore = testScore;
    }
}
