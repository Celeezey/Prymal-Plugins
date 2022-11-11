package net.unethicalite.plugins.gearsets;

import com.google.inject.Inject;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Item;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.input.KeyManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.util.HotkeyListener;
import net.unethicalite.api.commons.Time;
import net.unethicalite.api.items.Inventory;
import net.unethicalite.api.utils.MessageUtils;
import org.pf4j.Extension;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Extension
@PluginDescriptor(name = "Prymal GearSets", description = "Press your configured hotkeys to withdraw your sets of gear.", enabledByDefault = false)
@Slf4j
@Singleton

public class gearsets extends Plugin
{
    @Provides
    gearsetsconfig getConfig(ConfigManager configManager)
    {
        return configManager.getConfig(gearsetsconfig.class);
    }
    @Inject
    private KeyManager keyManager;
    @Inject
    private gearsetsconfig config;

    Map<String, Item> gearMap1;



    @Override
    public void startUp()
    {
        keyManager.registerKeyListener(hotkeyListener);

        String gearList1 = config.gearSet1();

        for (String item : gearList1.split(",")) {
            gearMap1.put(item, Inventory.getFirst(item));
        }

    }

    @Override
    public void shutDown()
    {
        keyManager.unregisterKeyListener(hotkeyListener);
    }

    public final HotkeyListener hotkeyListener = new HotkeyListener(() -> config.toggleKeyBind()) {

        @Override
        public void hotkeyPressed()
        {
            for (Item i : gearMap1.values()) {
                MessageUtils.addMessage(i.toString());
                i.interact(x -> x != null && (x.contains("Wear")
                        || x.contains("Wield")
                        || x.contains("Equip")));
                Time.sleep(45, 65);
                return;

            }
        }
    };

}