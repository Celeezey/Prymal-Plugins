package net.unethicalite.plugins.gearsets;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

import java.awt.event.KeyEvent;

@ConfigGroup("gearsets")
public interface gearsetsconfig extends Config
{

    @ConfigItem(
            keyName = "gearSet1",
            name = "Gear set 1",
            description = "First Set of gear",
            position = 10

    )
    default String gearSet1()
    {
        return "";
    }

    @ConfigItem(
            keyName = "gearSet2",
            name = "Second Set",
            description = "Second Set of gear",
            position = 11

    )
    default String gearSet2()
    {
        return "";
    }


    @ConfigItem(
            keyName = "toggleHotKey",
            name = "Switch Gear Hotkey",
            description = "Hotkey to switch between gear sets",
            position = 12
    )
    default String toggleHotKey()
    {
        return KeyEvent.getKeyText(KeyEvent.VK_X);
    }
}