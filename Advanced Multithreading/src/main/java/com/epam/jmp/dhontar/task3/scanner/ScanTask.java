package com.epam.jmp.dhontar.task3.scanner;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ScanTask extends RecursiveTask<ScanTask.Statistics> {

    private final File file;
    static long fileSize = 0;
    static int fileCount = 0;
    static int folderCount = 1;

    public ScanTask(File file) {
        this.file = file;
    }

    @Override
    protected Statistics compute() {

        List<ScanTask> tasks = new ArrayList<>();

        File[] files = file.listFiles();
        if (files == null) {
            return new Statistics(fileSize, fileCount, folderCount);
        }

        for (File subfile : files) {
            if (subfile.isDirectory()) {
                folderCount++;
                tasks.add(new ScanTask(subfile));
            } else {
                fileCount++;
                fileSize += subfile.length();
            }
        }
        ForkJoinTask.invokeAll(tasks);

        return new Statistics(fileSize, fileCount, folderCount);
    }

    protected static class Statistics {

        final long fileSize;
        final int fileCount;
        final int folderCount;

        Statistics(long fileSize, int fileCount, int folderCount) {
            this.fileSize = fileSize;
            this.fileCount = fileCount;
            this.folderCount = folderCount;
        }
    }
}
