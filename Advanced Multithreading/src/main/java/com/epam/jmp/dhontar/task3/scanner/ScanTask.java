package com.epam.jmp.dhontar.task3.scanner;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ScanTask extends RecursiveTask<Statistics> {

    private final File file;
    public ScanTask(File file) {
        this.file = file;
    }

    @Override
    public Statistics compute() {
        var tasks = new ArrayList<ForkJoinTask<Statistics>>();
        var stats = new Statistics();

        File[] files = file.listFiles();
        if (files == null) {
            return stats;
        }

        for (File subfile : files) {
            if (subfile.isDirectory()) {
                stats.incrementFolderCount();
                tasks.add(new ScanTask(subfile).fork());
            } else {
                stats.incrementFileCount();
                stats.increaseFileSizeBy(subfile.length());
            }
        }
        for (var i = tasks.size() - 1; i >= 0; i--){
            stats.updateStats(tasks.get(i).join());
        }
        return stats;
    }
}