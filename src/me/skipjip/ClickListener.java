package me.skipjip;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
//import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class ClickListener implements Listener {

	@EventHandler
	public void onClick(InventoryClickEvent event) {
		HumanEntity entity = event.getWhoClicked();
		if ((entity instanceof Player)){
			Player player = (Player)entity;

			if (event.getInventory().getName().equals(Timer.getEye_Of_EnderInventory().getName())) {

				event.setCancelled(true);

				ItemStack clicked = event.getCurrentItem();
				if (clicked!=null) {

					if (clicked.getType() == Material.BOOK) {

						player.closeInventory();
						player.sendMessage(ChatColor.DARK_GREEN + "Use your ender pearls to teleport to the finish. First one to the finsih wins!");

					}

					else if (clicked.getType() == Material.PAPER) {

						player.closeInventory();
						player.sendMessage(ChatColor.RED + "No hacking, Cheating, or Being Rude of any Kind. Also, Have Fun! :)");
					}
					else if (clicked.getType() == Material.FEATHER) {

						player.closeInventory();
						player.sendMessage(ChatColor.AQUA + "And i'm freeeeeee! Free Fallin'!" + ChatColor.RED + " Oh... erm, nevermind.");
						player.setVelocity(new Vector(0, 1, 0));
					}
					else if (clicked.getType() == Material.ENDER_PEARL) {

						player.closeInventory();
						player.sendMessage(ChatColor.AQUA + "");
						Location location = new Location(Bukkit.getWorld("world") , 71.4, 82.0, 257.4);
						player.teleport(location);
					}
					else if (clicked.getType() == Material.REDSTONE_TORCH_ON) {

						player.closeInventory();
						player.sendMessage(ChatColor.AQUA + "Teleporting to spawn...");
						Location location = new Location(Bukkit.getWorld("world") , 28.4, 128.0, 257.4);
						player.teleport(location);
					}

				}

			}

		}

	}

}
