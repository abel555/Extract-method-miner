package org.refactoringminer;

import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.HashSet;
import java.util.Set;

public class MethodVIsitor extends VoidVisitorAdapter<ClassInfo> {

    @Override
    public void visit(final MethodDeclaration n, ClassInfo info ) {

        NodeList<Parameter> parameters = n.getParameters();
        Set<Type> distinctMethodsParams = new HashSet<Type>();
        for (Parameter par:parameters) {
            info.set1.add(par.getType());
            distinctMethodsParams.add(par.getType());
        }
        info.methodParametersSum = info.methodParametersSum + distinctMethodsParams.size();
        super.visit(n, info);
    }

}
