package com.lastminute.model;

import java.util.ArrayList;
import java.util.List;

public class SearchFormatResult {

    private List<String> errorList;
    private SearchDataBean searchDataBean;
    private String command;

    public SearchFormatResult(){
        errorList = new ArrayList<String>();
    }

    public void addError(String error) {
        errorList.add(error);
    }

    public boolean isErrorEmpty() {
        if (errorList.isEmpty()) {
            return true;
        }
        return false;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public List<String> getErrorList() {
        return errorList;
    }

    public void setErrorList(List<String> errorList) {
        this.errorList = errorList;
    }

    public SearchDataBean getSearchDataBean() {
        return searchDataBean;
    }

    public void setSearchDataBean(SearchDataBean searchDataBean) {
        this.searchDataBean = searchDataBean;
    }
}
