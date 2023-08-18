package com.tinkeringidiot.warp;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URISyntaxException;

public class Warp extends JavaPlugin {

    private String warpFile = "\\warps.json";
    private JsonObject warpList;

    @Override
    public void onEnable() {
        getLogger().info("Warp is online");

        try {
            String parentPath = new File(Warp.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile().toString();
            warpList = (JsonObject) new JsonParser().parse(new FileReader(parentPath + warpFile));

            this.getCommand("warp").setExecutor(new WarpCommand(warpList));

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
