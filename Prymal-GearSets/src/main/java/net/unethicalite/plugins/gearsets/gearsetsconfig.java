package net.unethicalite.plugins.gearsets;

import net.runelite.api.Item;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.Keybind;

@ConfigGroup("gearsets")
public interface gearsetsconfig extends Config {

    @ConfigItem(
            keyName = "gearSet1",
            name = "Gear set 1",
            description = "First Set",
            position = 10

    )
    default String gearSet1()
    {
        return "";
    }

    @ConfigItem(
            keyName = "gearSet2",
            name = "Second Set",
            description = "Second Set",
            position = 11

    )
    default String gearSet2()
    {
        return "";
    }


    @ConfigItem(
            keyName = "toggleKeyBind",
            name = "Switch Gear Hotkey",
            description = "Hotkey to switch between gear sets",
            position = 12
    )
    default Keybind toggleKeyBind()
    {
        return Keybind.NOT_SET;
    }
}

