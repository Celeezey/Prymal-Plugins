package net.unethicalite.plugins.prymalkeys;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.Keybind;

import java.awt.event.KeyEvent;

@ConfigGroup("gearsets")
public interface prymalkeysconfig extends Config
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
            name = "Gear set 2",
            description = "Second Set of gear",
            position = 11

    )
    default String gearSet2()
    {
        return "";
    }

    @ConfigItem(
            keyName = "gearSet1HotKey",
            name = "Gear Set 1 Hotkey",
            description = "Hotkey to switch to first gear set",
            position = 12
    )
    default Keybind gearSet1HotKey()
    {
        return new Keybind(KeyEvent.VK_X, 0);
    }

    @ConfigItem(
            keyName = "gearSet2HotKey",
            name = "Gear Set 2 Hotkey",
            description = "Hotkey to switch to first gear set",
            position = 13
    )
    default Keybind gearSet2HotKey()
    {
        return new Keybind(KeyEvent.VK_Z, 0);
    }
}