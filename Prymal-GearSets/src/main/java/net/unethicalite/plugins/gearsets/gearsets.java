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
import net.unethicalite.api.items.Inventory;
import org.pf4j.Extension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Extension
@PluginDescriptor(name = "Prymal GearSets", description = "Press your configured hotkeys to withdraw your sets of gear.", enabledByDefault = false)
@Slf4j
@Singleton

public class gearsets extends Plugin
{
    @Provides
    gearsetsconfig getConfig(ConfigManager configManager){
        return configManager.getConfig(gearsetsconfig.class);
    }
    @Inject
    private KeyManager keyManager;
    @Inject
    private gearsetsconfig config;

    //List<String> gearList1 = new ArrayList<>();

    List<String> gearList1 = Arrays.stream(config.gearList1().split(","))
            .collect(Collectors.toList());

    //List<String> gearList2 = Arrays.stream(config.gearList2().split(","))
            //.collect(Collectors.toList());

    HashMap<String, Item> gearMap1 = new HashMap<String, Item>();

    @Override
    protected void startUp()
    {
        keyManager.registerKeyListener(hotkeyListener);
        for (String i : gearList1) {
            gearMap1.put(i, Inventory.getFirst(i));
        }
    }

    @Override
    public void shutDown()
    {
        keyManager.unregisterKeyListener(hotkeyListener);
    }

    private final HotkeyListener hotkeyListener = new HotkeyListener(() -> config.toggleKeyBind()) {

        @Override
        public void hotkeyPressed()
        {
            for (Item i : gearMap1.values()) {
                i.interact(x -> x != null && (x.toLowerCase().contains("wear")
                        || x.toLowerCase().contains("wield")
                        || x.toLowerCase().contains("equip")));

            }
        }
    };

}