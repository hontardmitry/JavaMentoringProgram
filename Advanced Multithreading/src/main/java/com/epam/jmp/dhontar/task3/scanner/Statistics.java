package com.epam.jmp.dhontar.task3.scanner;

public class Statistics {

    private long fileSize;
    private int fileCount;
    private int folderCount;

    public Statistics() {
    }

    public long getFileSize() {
        return fileSize;
    }

    public int getFileCount() {
        return fileCount;
    }

    public int getFolderCount() {
        return folderCount;
    }

    public void increaseFileSizeBy(long additionalSize) {
        this.fileSize += additionalSize;
    }

    public void incrementFileCount() {
        this.fileCount++;
    }

    public void incrementFolderCount() {
        this.folderCount++;
    }

    public void updateStats(Statistics stats) {
        this.fileSize += stats.fileSize;
        this.fileCount += stats.fileCount;
        this.folderCount += stats.folderCount;
    }
}
