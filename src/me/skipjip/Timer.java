package me.skipjip;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import net.minecraft.server.v1_6_R3.EntityPlayer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.skipjip.Events;

public class Timer extends JavaPlugin {
	private final Events listeners = new Events();
	private Map<String, Long> lastUsage = new HashMap<String, Long>();
	public final int cdtime = 60;
	private boolean running = false;
	private int ticks = 0;
	public final CopyOnWriteArrayList<EntityPlayer> players = new java.util.concurrent.CopyOnWriteArrayList<EntityPlayer>();
	private static Location targetPoint = null;
	public static ArrayList<String> isPlaying = new ArrayList<String>();
	public static List<String> Players = new ArrayList<String>();

	@Override
	public void onEnable() {
		// Register our events
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(listeners, this);
		getServer().getPluginManager().registerEvents(new InteractListener(), this);
		getServer().getPluginManager().registerEvents(new ClickListener(), this);
		tick();
		running = true;
		ticks = 120;
	}

	@Override
	public void onDisable(){
	}

	public void tick() {
		Bukkit.getServer().getScheduler().runTaskTimer(this, new Runnable() {
			public void run() {

				if (ticks == 120)
					Bukkit.broadcastMessage(ChatColor.DARK_RED + "Game starting in 2 minutes!");
				if (ticks == 60)
					Bukkit.broadcastMessage(ChatColor.DARK_RED + "Game starting in 1 minute!");
				if (ticks == 30)
					Bukkit.broadcastMessage(ChatColor.DARK_RED + "Game starting in " + ticks  + " seconds!");
				if (ticks == 15)
					Bukkit.broadcastMessage(ChatColor.DARK_RED + "Game starting in " + ticks  + " seconds!");
				if (ticks == 10)
					Bukkit.broadcastMessage(ChatColor.DARK_RED + "Game starting in " + ticks  + " seconds!");
				if (ticks == 5)
					Bukkit.broadcastMessage(ChatColor.DARK_RED + "Game starting in " + ticks  + " seconds!");
				if (ticks == 4)
					Bukkit.broadcastMessage(ChatColor.DARK_RED + "Game starting in " + ticks  + " seconds!");
				if (ticks == 3)
					Bukkit.broadcastMessage(ChatColor.DARK_RED + "Game starting in " + ticks  + " seconds!");
				if (ticks == 2)
					Bukkit.broadcastMessage(ChatColor.DARK_RED + "Game starting in " + ticks  + " seconds!");
				if (ticks == 1)
					Bukkit.broadcastMessage(ChatColor.DARK_RED + "Game starting in " + ticks  + " second!");

				if (running) {
					ticks--;
					if (ticks == 0) {
						int onlinePlayers = Bukkit.getOnlinePlayers().length;
						if (onlinePlayers < 2 && ticks == 0) {
							Bukkit.broadcastMessage(ChatColor.DARK_GREEN + "There is not enough people to start the game! There needs to be at least 2!");
							ticks = 30;
						}
						if (onlinePlayers >= 1 && ticks == 0) {
							Bukkit.broadcastMessage(ChatColor.DARK_PURPLE + "Let the games begin!");
							Location location = new Location(Bukkit.getWorld("world") , -35.5, 46.0, 214.5);
							Player[] players = getServer().getOnlinePlayers();
							for (Player player : players) {
								getLogger().info(player.getName());
								player.teleport(location);
								PlayerInventory inventory = player.getInventory();
								ItemStack EOE = new ItemStack(Material.EYE_OF_ENDER, 1);
								ItemStack pearl = new ItemStack(Material.ENDER_PEARL, 64);
								player.getInventory().clear();
								inventory.addItem(pearl);
								inventory.remove(EOE);
							}
							if (onlinePlayers >= 20) {
								Bukkit.broadcastMessage(ChatColor.BLUE + "Wow, there is a lot of you guys. Shortening the time to " + ticks + " seconds!");
								ticks = 30;
							}
						}
					}
				}
			}
		}, 20L, 20L);

	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (command.getName().equalsIgnoreCase("mytimer")) {
			if (!running) {
				running=true;
				sender.sendMessage(ChatColor.GREEN + "Clock unpaused.");
				ticks = 60;
			}
			else {
				running=false;
				sender.sendMessage(ChatColor.RED + "Clock stopped.");
			}
			return true;
		}		
		else if (command.getName().equalsIgnoreCase("cooldown")) {
			long lastUsed = 0;
			if (lastUsage.containsKey(sender.getName())) {
				lastUsed = lastUsage.get(sender.getName());

			}

			int cdmillis = cdtime * 1000;

			if (System.currentTimeMillis()-lastUsed>=cdmillis) {

				sender.sendMessage(ChatColor.AQUA + "Okay! Command run!");
				lastUsage.put(sender.getName(), System.currentTimeMillis());

			}
			else {
				int timeLeft = (int) (cdtime-((System.currentTimeMillis()-lastUsed)/1000));
				sender.sendMessage(ChatColor.DARK_GRAY + "This command is on cooldown for another "+ timeLeft + " seconds.");

			}
			return true;
		}

		else if (command.getName().equalsIgnoreCase("startgame")) {
			if (!running)
				running = true;
			ticks = 10;
			Bukkit.broadcastMessage(ChatColor.DARK_PURPLE + "The time was just changed by an Admin to " + ticks + " seconds!");

			return true;
		}
		return false;
	}
	public static Inventory getEye_Of_EnderInventory(){
        // inventory asdf
		Inventory inv = Bukkit.createInventory(null, 9, "Click on Item!");

		{
			ItemStack item = new ItemStack(Material.PAPER);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(ChatColor.WHITE + "�2Rules");
			List<String> lore = new ArrayList<String>();
			lore.add("�7Tells you the rules of the server");
			meta.setLore(lore);
			item.setItemMeta(meta);
			inv.addItem(item);
		}
		{
			ItemStack item = new ItemStack(Material.BOOK);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(ChatColor.WHITE + "�2Help");
			List<String> lore = new ArrayList<String>();
			lore.add("�7Gives you information about the game");
			meta.setLore(lore);
			item.setItemMeta(meta);
			inv.addItem(item);
		}
		{
			ItemStack item = new ItemStack(Material.FEATHER);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(ChatColor.WHITE + "�2Skydive");
			List<String> lore = new ArrayList<String>();
			lore.add("�7Click me to go skydiving!");
			meta.setLore(lore);
			item.setItemMeta(meta);
			inv.addItem(item);
		}
		{
			ItemStack item = new ItemStack(Material.ENDER_PEARL);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(ChatColor.WHITE + "�2Parkour");
			List<String> lore = new ArrayList<String>();
			lore.add("�7Click me to teleport to parkour!");
			meta.setLore(lore);
			item.setItemMeta(meta);
			inv.addItem(item);
		}
		{
			ItemStack item = new ItemStack(Material.REDSTONE_TORCH_ON);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(ChatColor.WHITE + "�2Spawn");
			List<String> lore = new ArrayList<String>();
			lore.add("�7Click me to teleport to spawn!");
			meta.setLore(lore);
			item.setItemMeta(meta);
			inv.addItem(item);
		}

		return inv;

	}
	public static Location getTarget() {
		return targetPoint;
	}

	public static void setTarget(Location loc) {
		Timer.targetPoint = loc;

	}
	public void onPlayerJoin(PlayerJoinEvent event) {
		running = true;
	}

}
