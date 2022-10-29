package net.unethicalite.plugins.gearsets;

import com.google.inject.Inject;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.input.KeyManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.util.HotkeyListener;
import org.pf4j.Extension;
import java.util.Arrays;
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

    List<String> gearSet1 = Arrays.stream(config.gearset1().split(","))
            .collect(Collectors.toList());

    List<String> gearSet2 = Arrays.stream(config.gearset2().split(","))
            .collect(Collectors.toList());

    @Override
    protected void startUp() {
        keyManager.registerKeyListener(hotkeyListener);
    }

    @Override
    public void shutDown() {
        keyManager.unregisterKeyListener(hotkeyListener);
    }

    private final HotkeyListener hotkeyListener = new HotkeyListener(() -> config.toggleKeyBind()) {

        @Override
        public void hotkeyPressed(){
            for (String i : gearSet1) {

            }
            }
    };

}