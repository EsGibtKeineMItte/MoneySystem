package de.leonhard.moneysystem.commands;

import de.leonhard.moneysystem.API;
import de.leonhard.moneysystem.Moneysystem;
import de.nanox.nnxcore.networking.NNXPlayer;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.scheduler.GroupedThreadFactory;
import org.bukkit.Bukkit;

import java.sql.SQLException;

public class MoneyCommand extends Command {
    //        0               1        2
//MoneyCommand <player|pay|set> <player> <amount>
    public MoneyCommand() {
        super("money");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer) sender;


            switch (args.length) {


                case 0:

                    player.sendMessage(new TextComponent("§aDu hast §6" + API.getNNXPlayer(player).getMoney() + "§a Coins."));
                    //MoneyCommand von sich sehen
                    break;
                case 1:
                    if (ProxyServer.getInstance().getPlayer(args[0]) != null) {
                        ProxiedPlayer target = (ProxiedPlayer) ProxyServer.getInstance().getPlayer(args[0]);

                        player.sendMessage(new TextComponent("§aDer Spieler: §6" + target.getName() + "§a hat §6 " + API.getNNXPlayer(target).getMoney() + "§a Coins."));
                    } else {
                        System.out.println("§cDieser Spieler ist nicht online.");
                    }
                    break;
                case 2:
                    player.sendMessage(new TextComponent("§aMoneySystem v1 by linksKeineMitte/Leonhard"));
                    player.sendMessage(new TextComponent("§cBenutzung: /money <player|pay|set> <player> <amount> "));

                //MoneyCommand von anderen sehen
                case 3:
                    if (args[0].equalsIgnoreCase("set")) {
                        //MoneyCommand set linkskeinemitte 1000
                        ProxiedPlayer target = ProxyServer.getInstance().getPlayer(args[1]);
                        NNXPlayer nnxPlayer = API.getNNXPlayer(player);
                        NNXPlayer nnxTarget = API.getNNXPlayer(target);
                        if (nnxPlayer.getGroup().equalsIgnoreCase("Admin")) {


                            int amount;
                            try {
                                amount = Integer.parseInt(args[2]);
                            } catch (NumberFormatException e) {
                                player.sendMessage(new TextComponent("§cDu kannst keine Buchstaben überweisen."));
                                amount = nnxTarget.getMoney();
                            }


                            nnxTarget.setMoney(amount);
                            try {
                                nnxTarget.save();
                            } catch (SQLException e) {
                                player.sendMessage(new TextComponent("§cHier ist ein schwerwiegender Fehler aufgetreten, bitte kontaktiere die Administration."));
                            }

                            player.sendMessage(new TextComponent("§aDu hast das Geld des Spielers : §6" + target.getName() + "§a auf §6" + amount + " §agesetzt"));


                        } else {
                            player.sendMessage(new TextComponent("§cDafür hast du nicht die benötigten Rechte."));
                        }


                    } else if (args[0].equalsIgnoreCase("pay")) {
                        ProxiedPlayer target = ProxyServer.getInstance().getPlayer(args[1]);
                        NNXPlayer nnxPlayer = API.getNNXPlayer(player);
                        NNXPlayer nnxTarget = API.getNNXPlayer(target);

                        int amount;
                        try {
                            amount = Integer.parseInt(args[2]);

                        } catch (NumberFormatException e) {
                            player.sendMessage(new TextComponent("§cDu kannst keine Buchstaben angeben."));
                            amount = 0;
                        }
                        if (nnxPlayer.getMoney() > amount) {
                            if (amount > 0) {
                                try {
                                    nnxPlayer.setMoney(nnxPlayer.getMoney() - amount);
                                    nnxPlayer.save();
                                } catch (SQLException e) {

                                }
                                try {
                                    nnxTarget.setMoney(nnxTarget.getMoney() + amount);
                                    nnxTarget.save();
                                } catch (SQLException e1) {

                                }


                                player.sendMessage(new TextComponent("§aDu hast dem Spieler: §6 " + target.getName() + " " + amount + "§a Coins überwiesen."));
                                target.sendMessage(new TextComponent("§aDir wurden von §6" + player.getName() + " " + amount + " §aCoins überwiesen."));
                            } else {
                                player.sendMessage(new TextComponent("§cDu kannst auch keine Minusbeträge überweisen.)"));
                            }
                        } else {
                            player.sendMessage(new TextComponent("§cDafür hast du nicht genügend Geld."));
                        }

                    } else {
                        player.sendMessage(new TextComponent("§aMoneySystem v1 by linksKeineMitte/Leonhard"));
                        player.sendMessage(new TextComponent("§cBenutzung: <player|pay|set> <player> <amount> "));
                    }
            }
        }
    }
}
