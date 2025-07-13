package com.swaglabs.utils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;



public class FilesUtil {

    // Private constructor to prevent instantiation
    private FilesUtil() {
    }

    // Returns the latest file in the specified directory based on last modified time.
    public static File getLatestFile(String folderPath) {
        File folder = new File(folderPath);
        File[] files = folder.listFiles();
        if (files == null || files.length == 0) {
            LogsUtil.warn("No files found in directory: " + folderPath);
            return null;
        }
        File latestFile = files[0];
        for (File file : files) {
            if (file.lastModified() > latestFile.lastModified()) {
                latestFile = file;
            }
        }
        return latestFile;
    }


    // Deletes all files in the specified directory and its subdirectories.
    public static void deleteFiles(File dirPath) {
        if(dirPath == null || !dirPath.exists()) {
            LogsUtil.warn("Directory does not exist or is null: " + dirPath);
        }

        assert dirPath != null;
        File[] filesList = dirPath.listFiles();

        if (filesList == null) {
            LogsUtil.warn("No files found in directory: " + dirPath);

        }

        assert filesList != null;
        for (File file : filesList) {
            if (file.isDirectory()) {
                deleteFiles(file);
            } else {
                try {
                    Files.delete(file.toPath());

                }catch (IOException e) {
                    LogsUtil.error("Failed to delete file: " + file);
                }
            }
        }
    }

    // Cleans the specified directory by deleting all files and subdirectories.
    public static void cleanDirectory(File file){
        try {
            FileUtils.deleteQuietly(file);
        } catch (Exception e) {
            LogsUtil.error(e.getMessage());
        }

    }

    // Renames a file by copying it to a new name and deleting the old file.
    public static void renameFile(File oldName, File newName) {
        try {
            File targetFile = oldName.getParentFile().getAbsoluteFile();
            String targetDirectory = targetFile + File.separator + newName;
            FileUtils.copyFile(oldName, new File(targetDirectory));
            FileUtils.deleteQuietly(oldName);
            LogsUtil.info("File renamed successfully: " + oldName.getName() + " to " + newName.getName());
        } catch (Exception e) {
            LogsUtil.error("Failed to rename file: " + oldName.getName() + " to " + newName.getName() + " - " + e.getMessage());
        }

    }
}
