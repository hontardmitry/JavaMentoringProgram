package com.epam.jmp.dhontar.scanner;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class FileScanner {

    public static class ScanTask extends RecursiveTask<FileScanner.Statistics>{
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
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter folder path: ");
        String folderPath = scanner.nextLine();
        File folder = new File(folderPath);

        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println("Invalid folder path.");
            return;
        }

        ForkJoinPool pool = new ForkJoinPool();
        ScanTask task = new ScanTask(folder);
        pool.execute(task);

        System.out.println("\nScanning folder ");

        String bgndChar = "░";
        int barSize = 40;
        String background = bgndChar.repeat(barSize);
        String caret = "████";
        int x = 0;
        while (!task.isDone()) {
            if (x < barSize) {
                System.out.print("\r"
                        + background.substring(0, x++ % barSize)
                        + caret + background.substring(0, (barSize - x) % barSize));
                try {
                    Thread.sleep(30);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                x = 0;
            }
        }

        Statistics result = task.join();
        System.out.println("\rScanning complete");
        System.out.println("\nFiles count: " + result.fileCount);
        System.out.println("Folders count: " + result.folderCount);
        System.out.println("Total size: " + result.fileSize + " bytes");
        System.out.println("Files: " + ScanTask.fileCount);
        System.out.println("Folders: " + ScanTask.folderCount);

        scanner.close();
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