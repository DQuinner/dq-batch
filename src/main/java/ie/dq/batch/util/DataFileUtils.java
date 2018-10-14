package ie.dq.batch.util;

import ie.dq.batch.domain.FileRow;
import ie.dq.batch.domain.SampleData;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class DataFileUtils {

    public static FileRow convertToFileRow(SampleData sampleData){
        FileRow fileRow = new FileRow();
        fileRow.setPosition1(sampleData.getId());
        fileRow.setPosition2(sampleData.getDescription());
        fileRow.setPosition3(sampleData.getAmount());
        return fileRow;
    }

    public static Path copyFile(String from, String to) throws IOException {
        File original = new File(from);
        Path copied = Paths.get(to);
        Path originalPath = original.toPath();
        return Files.copy(originalPath, copied, StandardCopyOption.REPLACE_EXISTING);
    }

    public static boolean deleteFile(String fileName) throws IOException {
        return Files.deleteIfExists(Paths.get(fileName));
    }

    public static boolean fileExists(String fileName) throws IOException {
        File file = new File(fileName);
        return file.exists();
    }

}
