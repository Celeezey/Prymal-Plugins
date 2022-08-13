package net.unethicalite.plugins.gearsets;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.Keybind;

@ConfigGroup("gearsets")
public interface gearsetsconfig extends Config {

    @ConfigItem(
            keyName = "gearlist",
            name = "Custom Gear",
            description = "Withdraw the specified gear",
            position = 10

    )
    default String gearlist() {
        return "Example item, Example item, Example item";
    }


    @ConfigItem(
            keyName = "toggleKeyBind",
            name = "Start/Stop hotkey",
            description = "Hotkey to start/stop the explorer",
            position = 24
    )
    default Keybind toggleKeyBind() {
        return Keybind.NOT_SET;
    }
}

