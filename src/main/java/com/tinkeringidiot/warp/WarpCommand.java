package com.tinkeringidiot.warp;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WarpCommand implements CommandExecutor {

    private JsonObject warpList;
    public WarpCommand(JsonObject warpList) {
        this.warpList = (JsonObject) warpList.get("warps");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length == 1) {
                Set<Map.Entry<String, JsonElement>> entries = warpList.entrySet();
                for(Map.Entry<String, JsonElement> entry: entries) {
                    if(entry.getKey().equalsIgnoreCase(args[0])) {
                        JsonObject val = entry.getValue().getAsJsonObject();
                        int x = val.get("X").getAsInt();
                        int y = val.get("Y").getAsInt();
                        int z = val.get("Z").getAsInt();

                        Location newLoc = new Location(Bukkit.getWorld("world"), x, y, z);
                        player.teleport(newLoc);
                        return true;
                    }
                }

                for(Player onPlayer : Bukkit.getOnlinePlayers()) {
                    if(onPlayer.getName().equalsIgnoreCase(args[0])) {
                        player.teleport(onPlayer.getLocation());
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
