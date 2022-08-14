package net.unethicalite.plugins.gearsets;

import com.google.inject.Provides;
import com.google.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.mixins.Inject;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.util.HotkeyListener;
import net.unethicalite.api.items.Bank;
import net.unethicalite.api.utils.MessageUtils;
import org.pf4j.Extension;
import net.runelite.client.input.KeyManager;

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

    @Override
    protected void startUp() { keyManager.registerKeyListener(hotkeyListener); }

    @Override
    public void shutDown() { keyManager.unregisterKeyListener(hotkeyListener); }

    private final HotkeyListener hotkeyListener = new HotkeyListener(() -> config.toggleKeyBind()) {

        @Override
        public void hotkeyPressed(){
            if (!Bank.isOpen()) { MessageUtils.addMessage("Open your bank, Then press your hotkey."); }
        }
    };

}