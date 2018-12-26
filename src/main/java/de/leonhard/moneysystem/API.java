package de.leonhard.moneysystem;

import de.nanox.nnxcore.networking.NNXPlayer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.sql.SQLException;

public class API {
    /*
    -.-
     */

    public static NNXPlayer getNNXPlayer(ProxiedPlayer player){
        try{
            NNXPlayer nnxPlayer = NNXPlayer.getPlayer(player.getUniqueId());
            return nnxPlayer;
        }catch(SQLException e){

        }
     return null;
    }
}
