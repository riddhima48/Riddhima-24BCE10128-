package edu.ccrm.io;

import edu.ccrm.config.AppConfig;
import java.nio.file.*;
import java.io.IOException;
import java.util.stream.Stream;

public class BackupService {
    private final Path base = AppConfig.getInstance().getDataFolder();

    public Path backup(Path... files) throws IOException {
        String ts = AppConfig.getInstance().timestamp();
        Path dest = base.resolve("backup_" + ts);
        Files.createDirectories(dest);
        for(Path p: files){
            if(Files.exists(p)) Files.copy(p, dest.resolve(p.getFileName()), StandardCopyOption.REPLACE_EXISTING);
        }
        return dest;
    }

    public long recursiveSize(Path p) throws IOException {
        try(Stream<Path> stream = Files.walk(p)){
            return stream.filter(Files::isRegularFile).mapToLong(pp->{
                try { return Files.size(pp); } catch(Exception e){ return 0L; }
            }).sum();
        }
    }
}
