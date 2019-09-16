package org.refactoringminer;

import com.github.javaparser.ast.type.Type;

import java.util.HashSet;
import java.util.Set;

public class ClassInfo {
    public String name;
    public float methodsNumber;
    public float methodParametersSum;
    public Set<Type> set1 = new HashSet<Type>();

    public ClassInfo(){
        this.methodsNumber = 1;
        this.methodParametersSum = 1;
    }
    public float getCohesion(){
        return methodParametersSum / (methodsNumber * (set1.size()+1));
    }

}
