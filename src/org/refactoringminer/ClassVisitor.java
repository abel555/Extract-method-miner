package org.refactoringminer;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class ClassVisitor extends VoidVisitorAdapter<ClassInfo> {
    @Override
    public void visit(final ClassOrInterfaceDeclaration c, ClassInfo info){
        MethodVIsitor methodVIsitor = new MethodVIsitor();

        info.name = c.getNameAsString();
        info.methodsNumber = c.getMethods().size() + 1;
        c.accept(methodVIsitor, info);
        super.visit(c,info);
        System.out.println(c.getName());
        System.out.println(c.getMethods());
    }

}
