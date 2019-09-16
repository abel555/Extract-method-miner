package org.refactoringminer;

public class ExtractMethodMiner {
    public static void main(String[] args) throws Exception {
            ExtractMethodProcessor extractMethodProcessor = new ExtractMethodProcessor("E:\\\\projects-urls.txt");
            extractMethodProcessor.abcd(extractMethodProcessor.repos.get(0));
            //gitService.checkout(repo, extractMethodsInfo.getCommitIdAfter());

            //CompilationUnit cu = JavaParser.parse(repo.getDirectory());
            //cu.getClassByName(extractMethodsInfo.getClassBefore());

            //ompilationUnit cu2 = StaticJavaParser.parse(repo.getDirectory(), Charset.forName("utf-8"));





    }

}
