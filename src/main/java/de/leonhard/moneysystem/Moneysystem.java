package de.leonhard.moneysystem;

import de.leonhard.moneysystem.commands.MoneyCommand;
import net.md_5.bungee.api.plugin.Plugin;

public final class Moneysystem extends Plugin {
    /*
       <MoneySystem an addon for NNXCore by Exceptionflug.>
       Copyright (C) <2018>  <Leonhard aka linksKeineMitte>

       This program is free software: you can redistribute it and/or modify
       it under the terms of the GNU General Public License as published by
       the Free Software Foundation, either version 3 of the License, or
               (at your option) any later version.

       This program is distributed in the hope that it will be useful,
       but WITHOUT ANY WARRANTY; without even the implied warranty of
       MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
       GNU General Public License for more details.

       You should have received a copy of the GNU General Public License
       along with this program.  If not, see <https://www.gnu.org/licenses/>.
      */
    @Override
    public void onEnable() {
        this.getProxy().getPluginManager().registerCommand(this, new MoneyCommand());
        System.out.println("§aMoney-System von linksKeineMitte/leonhard erfolgreich geladen");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
