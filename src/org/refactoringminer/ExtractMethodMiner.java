package org.refactoringminer;
import org.apache.commons.io.FilenameUtils;
import org.eclipse.jgit.lib.Repository;
import org.refactoringminer.api.*;
import org.refactoringminer.rm1.GitHistoryRefactoringMinerImpl;
import org.refactoringminer.util.GitServiceImpl;

import java.io.IOException;
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
            miner.detectAll(repo, "master", new RefactoringHandler() {
                @Override
                public void handle(String commitId, List<Refactoring> refactorings) {
                    for (Refactoring ref : refactorings) {
                        if(ref.getRefactoringType() == RefactoringType.EXTRACT_OPERATION) {
                            System.out.println("Refactorings at " + commitId);
                            System.out.println(ref.toString());
                            System.out.println(repo);

                        }

                    }
                }
            });
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
