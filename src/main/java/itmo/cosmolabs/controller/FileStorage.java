package itmo.cosmolabs.controller;

import com.azure.storage.file.share.*;

public class FileStorage {
    public static final String connectStr = "****";
    public static final String shareName = "cosmolab";
    public static final String dirName = "/";

    public static Boolean uploadFile(String fileName)
    {
        try
        {
            ShareDirectoryClient dirClient = new ShareFileClientBuilder()
                    .connectionString(connectStr).shareName(shareName)
                    .resourcePath(dirName)
                    .buildDirectoryClient();

            ShareFileClient fileClient = dirClient.getFileClient(fileName);
            fileClient.create(1024);
            fileClient.uploadFromFile(fileName);
            return true;
        }
        catch (Exception e)
        {
            System.out.println("uploadFile exception: " + e.getMessage());
            return false;
        }
    }

    public static Boolean downloadFile(String destDir,
                                       String fileName)
    {
        try
        {
            ShareDirectoryClient dirClient = new ShareFileClientBuilder()
                    .connectionString(connectStr).shareName(shareName)
                    .resourcePath(dirName)
                    .buildDirectoryClient();

            ShareFileClient fileClient = dirClient.getFileClient(fileName);

            // Create a unique file name
            String date = new java.text.SimpleDateFormat("yyyyMMdd-HHmmss").format(new java.util.Date());
            String destPath = destDir + "/"+ date + "_" + fileName;

            fileClient.downloadToFile(destPath);
            return true;
        }
        catch (Exception e)
        {
            System.out.println("downloadFile exception: " + e.getMessage());
            return false;
        }
    }
}