package me.skipjip;

import static org.bukkit.Bukkit.getLogger;

import java.util.concurrent.CopyOnWriteArrayList;

import net.minecraft.server.v1_6_R3.EntityPlayer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.util.Vector;

public class Events implements Listener {
	public final CopyOnWriteArrayList<EntityPlayer> players = new java.util.concurrent.CopyOnWriteArrayList<EntityPlayer>();

	@EventHandler
	public void onPlayerDropItem(PlayerDropItemEvent e) {
		Item item = e.getItemDrop();
		if (item.getItemStack().getType().equals(Material.EYE_OF_ENDER)) {
			e.setCancelled(true);
		}
	}
	@EventHandler
	public void onMove(PlayerMoveEvent e)
	{
		Player p = e.getPlayer();
		Location l = p.getLocation();
		Location d = new Location(l.getWorld(), l.getX(), l.getY() - 1, l.getZ());

		if(d.getBlock().getType() == Material.OBSIDIAN) {
			Location location = new Location(Bukkit.getWorld("world") , -35.5, 46, 214.5);
			p.teleport(location);
			p.sendMessage(ChatColor.DARK_GREEN + "Woops! You fell, Try again!");
		}
	}
	@EventHandler
	public void onBlock(PlayerMoveEvent e)
	{
		Player p = e.getPlayer();
		Location l = p.getLocation();
		Vector v = p.getVelocity();
		Location d = new Location(l.getWorld(), l.getX(), l.getY() - 1, l.getZ());

		if(d.getBlock().getType() == Material.SPONGE) {
			p.setVelocity(new Vector(v.getX(), 10.0D, v.getZ()));
		}
	}
	@EventHandler
	public void blockTouch(PlayerMoveEvent e)
	{
		Player p = e.getPlayer();
		Location l = p.getLocation();
		Location d = new Location(l.getWorld(), l.getX(), l.getY() - 1, l.getZ());

		if(d.getBlock().getType() == Material.STAINED_CLAY) {
			Location location = new Location(Bukkit.getWorld("world") , -35.5, 46, 214.5);
			p.teleport(location);
			p.sendMessage(ChatColor.DARK_GREEN + "Woops! You fell, Try again!");
		}
	}
	@EventHandler
	public void blockOn(PlayerMoveEvent e)
	{
		Player p = e.getPlayer();
		Location l = p.getLocation();
		Location d = new Location(l.getWorld(), l.getX(), l.getY() - 1, l.getZ());

		if(d.getBlock().getType() == Material.QUARTZ_BLOCK) {
			Location location = new Location(Bukkit.getWorld("world") , -35.5, 46, 214.5);
			p.teleport(location);
			p.sendMessage(ChatColor.DARK_GREEN + "Woops! You fell, Try again!");
		}
	}
	@EventHandler
	public void OnBlock(PlayerMoveEvent e) throws InterruptedException
	{
		Player p = e.getPlayer();
		Location l = p.getLocation();
		Location d = new Location(l.getWorld(), l.getX(), l.getY() - 1, l.getZ());

		if(d.getBlock().getType() == Material.GOLD_BLOCK) {
			Location location = new Location(Bukkit.getWorld("world") , -33.4, 67.0, 161.4);
			Player[] players = Bukkit.getServer().getOnlinePlayers();
			for (Player player : players) {
				p.getInventory().clear();
				player.teleport(location);
			}
			Bukkit.broadcastMessage(ChatColor.DARK_GREEN + p.getName() + " has won the game!");
			Thread.sleep(2000);
			Bukkit.broadcastMessage(ChatColor.DARK_GREEN + p.getName() + " has won the game!");
			Thread.sleep(2000);
			Bukkit.broadcastMessage(ChatColor.DARK_GREEN + p.getName() + " has won the game!");
			Thread.sleep(2000);
			Bukkit.broadcastMessage(ChatColor.DARK_GREEN + p.getName() + " has won the game!");
			Thread.sleep(2000);
			Bukkit.broadcastMessage(ChatColor.DARK_GREEN + p.getName() + " has won the game!");
			Thread.sleep(2000);
			Bukkit.shutdown();
		}
	}
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer(); // The player who joined
		Location location = new Location(Bukkit.getWorld("world") , 28.4, 128, 257.4);
		player.teleport(location);
		getLogger().info("ON PLAYER JOIN");
		getLogger().info(event.getPlayer().getName() + " joined the server! :D");
		PlayerInventory inventory = player.getInventory(); // The player's inventory
		ItemStack EOE = new ItemStack(Material.EYE_OF_ENDER);
		player.getInventory().clear();
		{
			if (!inventory.contains(EOE))
				inventory.addItem(EOE);
			player.sendMessage(ChatColor.DARK_GREEN + "Welcome!");
			player.sendMessage(ChatColor.DARK_AQUA + "Be sure to check the rules of the server by right clicking the Eye of Ender and selecting the Paper Icon!");

		}
	}
}