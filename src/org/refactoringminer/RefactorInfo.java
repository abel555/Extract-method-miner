package org.refactoringminer;

import java.util.ArrayList;
import java.util.List;

public class RefactorInfo {

    public String getCommitIdBefore() {
        return commitIdBefore;
    }

    public void setCommitIdBefore(String commitIdBefore) {
        this.commitIdBefore = commitIdBefore;
    }

    public String getCommitIdAfter() {
        return commitIdAfter;
    }

    public void setCommitIdAfter(String commitIdAfter) {
        this.commitIdAfter = commitIdAfter;
    }

    public List<String> getClassBefore() {
        return classBefore;
    }

    public void setClassBefore(List<String> classBefore) {
        this.classBefore = classBefore;
    }

    public List<String> getClassAfter() {
        return classAfter;
    }

    public void setClassAfter(List<String> classAfter) {
        this.classAfter = classAfter;
    }

    public void addClassBefore(String classToAdd) {
        classBefore.add(classToAdd);
    }
    public void addClassAfter(String classToAdd) {
        classAfter.add(classToAdd);
    }

    String commitIdBefore;
    String commitIdAfter;
    List<String> classBefore;
    List<String> classAfter;

    public RefactorInfo(String commitIdBefore, String commitIdAfter, List<String> classBefore, List<String> classAfter) {
        this.commitIdBefore = commitIdBefore;
        this.commitIdAfter = commitIdAfter;
        this.classBefore = classBefore;
        this.classAfter = classAfter;
    }




    public RefactorInfo(){
        classBefore = new ArrayList<>();
        classAfter = new ArrayList<>();
    }
}
