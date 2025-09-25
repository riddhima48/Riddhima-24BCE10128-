package edu.ccrm.io;

import edu.ccrm.config.AppConfig;
import java.nio.file.Path;

public class AppPaths {
    public static Path dataFolder(){ return AppConfig.getInstance().getDataFolder(); }
}
