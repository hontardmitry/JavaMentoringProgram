package com.epam.jmp.dhontar.task3.scanner;

import java.io.File;
import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;

public class FileScannerRunner {

    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        System.out.print("Enter folder path: ");
        var folderPath = scanner.nextLine();
        var folder = new File(folderPath);

        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println("Invalid folder path.");
            return;
        }

        var pool = new ForkJoinPool();
        var task = new ScanTask(folder);
        pool.execute(task);

        System.out.println("\nScanning folder ");

        var barSize = 40;
        var x = 0;
        var bgndChar = "░";
        var caret = "████";
        var background = bgndChar.repeat(barSize);
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

        var result = task.join();
        System.out.println("\rScanning complete");
        System.out.println("\nFiles count: " + result.getFileCount());
        System.out.println("Folders count: " + result.getFolderCount());
        System.out.println("Total size: " + result.getFileSize() + " bytes");

        scanner.close();
    }
}
