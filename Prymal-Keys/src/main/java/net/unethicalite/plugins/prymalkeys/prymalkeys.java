package net.unethicalite.plugins.prymalkeys;

import com.google.inject.Inject;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.input.KeyManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.unethicalite.api.items.Inventory;
import org.pf4j.Extension;

import java.awt.event.KeyEvent;

@Extension
@PluginDescriptor(name = "Prymal Keys", description = "Press hotkey to do the thing", enabledByDefault = false)
@Slf4j
@Singleton

public class prymalkeys extends Plugin implements net.runelite.client.input.KeyListener
{
    @Inject
    private KeyManager keyManager;
    @Inject
    private prymalkeysconfig config;
    @Inject
    private Client client;
    @Inject
    private ClientThread clientThread;

    @Provides
    prymalkeysconfig provideConfig(ConfigManager configManager)
    {
        return configManager.getConfig(prymalkeysconfig.class);
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

        if (e.getKeyCode() == this.config.gearSet1HotKey().getKeyCode())
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

        if (e.getKeyCode() == this.config.gearSet2HotKey().getKeyCode())
        {
            clientThread.invoke(() ->
            {
                log.info("Switch items: {}", config.gearSet2());
                Inventory.getAll(config.gearSet2().split(","))
                        .stream()
                        .forEach(i -> i.interact(x -> x != null && (x.toLowerCase().contains("wear")
                                || x.toLowerCase().contains("wield")
                                || x.toLowerCase().contains("equip"))));
            });
        }
    }

    @Override
    public void keyTyped(KeyEvent e)
    {

    }

    @Override
    public void keyReleased(KeyEvent e)
    {

    }


}