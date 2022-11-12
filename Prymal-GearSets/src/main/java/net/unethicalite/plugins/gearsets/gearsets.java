package net.unethicalite.plugins.gearsets;

import com.google.inject.Inject;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.Item;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.input.KeyManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.util.HotkeyListener;
import net.unethicalite.api.commons.Time;
import net.unethicalite.api.items.Inventory;
import net.unethicalite.api.utils.MessageUtils;
import org.pf4j.Extension;

import java.awt.event.KeyEvent;
import java.util.Map;

@Extension
@PluginDescriptor(name = "Prymal GearSets", description = "Press your configured hotkeys to withdraw your sets of gear.", enabledByDefault = false)
@Slf4j
@Singleton

public class gearsets extends Plugin implements net.runelite.client.input.KeyListener
{
    @Inject
    private KeyManager keyManager;
    @Inject
    private gearsetsconfig config;
    @Inject
    private Client client;
    @Inject
    private ClientThread clientThread;

    @Provides
    gearsetsconfig provideConfig(ConfigManager configManager)
    {
        return configManager.getConfig(gearsetsconfig.class);
    }

    @Override
    public void startUp()
    {
        keyManager.registerKeyListener(this);
    }

    @Override
    public void shutDown()
    {
        keyManager.unregisterKeyListener(this);
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        if (client.getGameState() != GameState.LOGGED_IN)
        {
            return;
        }

        if (KeyEvent.getKeyText(e.getKeyCode()).equalsIgnoreCase(config.toggleHotKey()))
        {
            clientThread.invoke(() ->
            {
                log.info("Switch items: {}", config.gearSet1());
                Inventory.getAll(config.gearSet1().split(","))
                        .stream()
                        .forEach(i -> i.interact(x -> x != null && (x.toLowerCase().contains("wear")
                                || x.toLowerCase().contains("wield")
                                || x.toLowerCase().contains("equip"))));
            });
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }


}

