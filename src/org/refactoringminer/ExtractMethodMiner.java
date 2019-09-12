package org.refactoringminer;
import com.github.javaparser.JavaParser;
import com.github.javaparser.StaticJavaParser;
import gr.uom.java.xmi.diff.ExtractOperationRefactoring;
import org.apache.commons.io.FilenameUtils;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jgit.lib.Repository;
import org.refactoringminer.api.*;
import org.refactoringminer.rm1.GitHistoryRefactoringMinerImpl;
import org.refactoringminer.util.GitServiceImpl;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ExtractMethodMiner {
    public static void main(String[] args) throws Exception {
        List<String> repos = readUrlRepos("E:\\projects-urls.txt");
        for (String rep:repos) {
            GitService gitService = new GitServiceImpl();
            GitHistoryRefactoringMiner miner = new GitHistoryRefactoringMinerImpl();
            Repository repo = gitService.cloneIfNotExists(
                    "tmp/" + FilenameUtils.getBaseName(rep),
                    rep);
            RefactorInfo extractMethodsInfo = new RefactorInfo();
            final String[] lastCommitId = new String[1];
            miner.detectAll(repo, "master", new RefactoringHandler() {
                @Override
                public void handle(String commitId, List<Refactoring> refactorings) {

                    for (Refactoring ref : refactorings) {
                        if(ref.getRefactoringType() == RefactoringType.EXTRACT_OPERATION) {
                            extractMethodsInfo.setCommitIdBefore(lastCommitId[0]);
                            extractMethodsInfo.setCommitIdAfter(commitId);
                            extractMethodsInfo.addClassBefore(ref.getInvolvedClassesBeforeRefactoring().get(0));
                            extractMethodsInfo.addClassAfter(ref.getInvolvedClassesAfterRefactoring().get(0));
                        }

                    }
                    lastCommitId[0] = commitId;
                }
            });
            //gitService.checkout(repo, extractMethodsInfo.getCommitIdAfter());

            //CompilationUnit cu = JavaParser.parse(repo.getDirectory());
            //cu.getClassByName(extractMethodsInfo.getClassBefore());

            //ompilationUnit cu2 = StaticJavaParser.parse(repo.getDirectory(), Charset.forName("utf-8"));

        }


    }
        public static List<String> readUrlRepos(String filePath){
            List<String> allLines = new ArrayList<>();
            try {
                allLines = Files.readAllLines(Paths.get(filePath));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return allLines;
        }
}
