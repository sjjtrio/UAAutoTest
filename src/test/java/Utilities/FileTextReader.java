package Utilities;

import java.io.*;

public final class FileTextReader {

    private FileTextReader(){

    }

    private static volatile FileTextReader instance = null;

    public static FileTextReader getInstance(){
        if (instance == null) {
            synchronized (FileTextReader.class) {
                if (instance == null) {
                    instance = new FileTextReader();
                }
            }
        }

        return instance;
    }

    private String foundFolderPath = "";

    public String FindFullPathByFileName(final String fileName) throws IOException {
        String folder = System.getProperty("user.dir");
        File parent = new File(folder);
        foundFolderPath = "";
        GetFilePathByName(parent, fileName);
        return foundFolderPath;
    }

    public String FindFullPathByFileName(final String fileName, final String folderPath) throws IOException {
        String folder = folderPath;
        File parent = new File(folder);
        foundFolderPath = "";
        GetFilePathByName(parent, fileName);
        return foundFolderPath;
    }

    private void GetFilePathByName(final File folder, String fileName) {
        if(!foundFolderPath.equals("")) return;
        for (final File fileEntry : folder.listFiles()) {
            if(fileEntry.getName().toLowerCase().contains(fileName.toLowerCase()))
            {
                foundFolderPath = fileEntry.getAbsolutePath();
                break;
            }
            if (fileEntry.isDirectory()) {
                GetFilePathByName(fileEntry, fileName);
            }
        }
    }

    public String ReadFile(String filePath)
    {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = br.readLine()) != null) {
                if (sb.length() > 0) {
                    sb.append("\n");
                }
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        String contents = sb.toString();
        //System.out.println(contents);
        return contents;
    }
}
