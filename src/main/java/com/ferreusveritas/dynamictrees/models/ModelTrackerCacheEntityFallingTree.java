package com.ferreusveritas.dynamictrees.models;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import com.ferreusveritas.dynamictrees.entities.EntityFallingTree;

import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelTrackerCacheEntityFallingTree {
	
	public static Map<Integer, ModelEntityFallingTree> modelMap = new ConcurrentHashMap<>();
	
	public static ModelEntityFallingTree getModel(EntityFallingTree entity) {
		return modelMap.computeIfAbsent(entity.getEntityId(), e -> new ModelEntityFallingTree(entity) );
	}
	
	public static void cleanupModels(World world, EntityFallingTree entity) {
		modelMap.remove(entity.getEntityId());
		cleanupModels(world);
	}
	
	public static void cleanupModels(World world) {
		List<Integer> removeList = new ArrayList<>();
		modelMap.forEach((k, v) -> if (world.getEntityByID(k) == null) removeList.add(k));
		removeList.forEach(k -> modelMap.remove(k));
	}
}
