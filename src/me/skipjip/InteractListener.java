package me.skipjip;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public class InteractListener implements Listener {

	@EventHandler
	public void onClick(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		Action action = event.getAction();
		if (action == Action.RIGHT_CLICK_BLOCK || action == Action.RIGHT_CLICK_AIR) {

			ItemStack hand = player.getItemInHand();
			if (hand!=null && hand.getType() == Material.EYE_OF_ENDER) {

				player.sendMessage(ChatColor.GOLD + "Opening selector...");
				player.openInventory(Timer.getEye_Of_EnderInventory());
				event.setCancelled(true);

			}
			ItemStack inHand = player.getItemInHand();
			if (hand!=null && inHand.getType() == Material.MAGMA_CREAM) {

				player.setVelocity(new Vector(-4,3,0));
				event.setCancelled(true);
				Player players = event.getPlayer();
				PlayerInventory inventory = players.getInventory();
				ItemStack magma = new ItemStack(Material.MAGMA_CREAM, 1);
				inventory.remove(magma);
				player.sendMessage(ChatColor.GOLD + "WOO!");
			}
			ItemStack holding = player.getItemInHand();
			if (hand!=null && holding.getType() == Material.BLAZE_ROD) {

				event.setCancelled(true);
				Player players = event.getPlayer();
				PlayerInventory inventory = players.getInventory();
				ItemStack blaze = new ItemStack(Material.BLAZE_ROD, 1);
				inventory.remove(blaze);
				player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200, 3));
				player.sendMessage(ChatColor.AQUA + "You are super fast!");
			}
		}
	}

}
