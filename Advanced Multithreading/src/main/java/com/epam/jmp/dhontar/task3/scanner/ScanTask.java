package com.epam.jmp.dhontar.task3.scanner;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ScanTask extends RecursiveTask<Statistics> {

    private final File file;
    public ScanTask(File file) {
        this.file = file;
    }

    @Override
    protected Statistics compute() {
        List<ScanTask> tasks = new ArrayList<>();
        var stats = new Statistics();

        File[] files = file.listFiles();
        if (files == null) {
            return stats;
        }

        for (File subfile : files) {
            if (subfile.isDirectory()) {
                stats.incrementFolderCount();
                tasks.add(new ScanTask(subfile));
            } else {
                stats.incrementFileCount();
                stats.increaseFileSizeBy(subfile.length());
            }
        }
        ForkJoinTask.invokeAll(tasks);
        tasks.forEach(task -> stats.updateStats(task.join()));
        return stats;
    }
}