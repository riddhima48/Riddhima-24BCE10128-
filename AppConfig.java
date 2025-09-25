package edu.ccrm.config;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public final class AppConfig {
    private static final AppConfig INSTANCE = new AppConfig();
    private final Path dataFolder;
    private final DateTimeFormatter backupFormatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");

    private AppConfig() {
        this.dataFolder = Paths.get(System.getProperty("user.dir"), "data");
    }

    public static AppConfig getInstance() { return INSTANCE; }

    public Path getDataFolder() { return dataFolder; }

    public String timestamp() { return LocalDateTime.now().format(backupFormatter); }
}
