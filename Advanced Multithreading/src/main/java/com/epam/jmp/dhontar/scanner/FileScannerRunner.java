package com.epam.jmp.dhontar.scanner;

import java.io.File;
import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;

public class FileScannerRunner {

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
        var task = new ScanTask(folder);
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

        ScanTask.Statistics result = task.join();
        System.out.println("\rScanning complete");
        System.out.println("\nFiles count: " + result.fileCount);
        System.out.println("Folders count: " + result.folderCount);
        System.out.println("Total size: " + result.fileSize + " bytes");

        scanner.close();
    }
}
