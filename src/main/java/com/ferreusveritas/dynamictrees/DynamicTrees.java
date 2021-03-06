package com.ferreusveritas.dynamictrees;

import com.ferreusveritas.dynamictrees.command.DTCommand;
import com.ferreusveritas.dynamictrees.compat.CompatHandler;
import com.ferreusveritas.dynamictrees.proxy.CommonProxy;
import com.ferreusveritas.dynamictrees.worldgen.WorldGeneratorTrees;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppedEvent;

/**
* 2016-2018 Ferreusveritas
*/
@Mod(modid = ModConstants.MODID, name = ModConstants.NAME, version=ModConstants.VERSION, dependencies=ModConstants.DEPENDENCIES)
public class DynamicTrees {
	
	@Mod.Instance(ModConstants.MODID)
	public static DynamicTrees instance;
	
	@SidedProxy(clientSide = "com.ferreusveritas.dynamictrees.proxy.ClientProxy", serverSide = "com.ferreusveritas.dynamictrees.proxy.CommonProxy")
	public static CommonProxy proxy;
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		proxy.preInit(event);
		CompatHandler.HandleCompat();
	}
	
	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init(event);
	}
	
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit();
		proxy.cleanUp();
	}
	
	@Mod.EventHandler
	public static void serverStarting(FMLServerStartingEvent event) {
		event.registerServerCommand(new DTCommand());
	}
	
	@Mod.EventHandler
	public static void serverStopped(FMLServerStoppedEvent event) {
		WorldGeneratorTrees.clearFlatWorldCache();
	}
	
}