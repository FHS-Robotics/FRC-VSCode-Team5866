package frc.robot.utilities;

import edu.wpi.first.wpilibj.Preferences;

public final class Settings {
    public static void init() {
        get("arm_limits_enabled", true);
    }

    public static boolean get(String key, boolean defaultValue) {
        if (!Preferences.containsKey(key)) {
            Preferences.setBoolean(key, defaultValue);
        }

        return Preferences.getBoolean(key, defaultValue);
    }
}
