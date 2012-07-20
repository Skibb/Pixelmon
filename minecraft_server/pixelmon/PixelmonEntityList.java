package pixelmon;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityEggInfo;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EnumCreatureType;
import net.minecraft.src.ModLoader;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.World;
import net.minecraft.src.mod_Pixelmon;
import net.minecraft.src.forge.MinecraftForge;
import pixelmon.Pokemon.*;
import pixelmon.Trainers.EntityTrainerBugCatcher;
import pixelmon.Trainers.EntityTrainerYoungster;
import pixelmon.Trainers.EntityTrainerYoungster02;
import pixelmon.database.DatabaseStats;
import pixelmon.database.DatabaseTrainers;

public class PixelmonEntityList {
	/** Provides a mapping between entity classes and a string */
	private static Map<String, Class<?>> stringToClassMapping = new HashMap<String, Class<?>>();

	/** Provides a mapping between a string and an entity classes */
	private static Map<Class<?>, String> classToStringMapping = new HashMap<Class<?>, String>();

	/** provides a mapping between an entityID and an Entity Class */
	private static Map<Integer, Class<?>> IDtoClassMapping = new HashMap<Integer, Class<?>>();

	private static Map<Integer, ClassType> IDtoTypeMapping = new HashMap<Integer, ClassType>();

	/** provides a mapping between an Entity Class and an entity ID */
	private static Map<Class<?>, Integer> classToIDMapping = new HashMap<Class<?>, Integer>();

	/** Maps entity names to their numeric identifiers */
	private static Map<String, Integer> stringToIDMapping = new HashMap<String, Integer>();

	/** This is a HashMap of the Creative Entity Eggs/Spawners. */
	public static HashMap<Integer, EntityEggInfo> entityEggs = new HashMap<Integer, EntityEggInfo>();

	/**
	 * adds a mapping between Entity classes and both a string representation
	 * and an ID
	 */
	private static void addMapping(Class<?> par0Class, String par1Str, int par2, ClassType type) {
		stringToClassMapping.put(par1Str, par0Class);
		classToStringMapping.put(par0Class, par1Str);
		IDtoClassMapping.put(Integer.valueOf(par2), par0Class);
		IDtoTypeMapping.put(Integer.valueOf(par2), type);
		classToIDMapping.put(par0Class, Integer.valueOf(par2));
		stringToIDMapping.put(par1Str, Integer.valueOf(par2));
	}

	/**
	 * Create a new instance of an entity in the world by using the entity name.
	 */
	public static Entity createEntityByName(String par0Str, World par1World) {
		Entity var2 = null;

		try {
			Class<?> var3 = (Class<?>) stringToClassMapping.get(par0Str);

			if (var3 != null) {
				var2 = (Entity) var3.getConstructor(new Class[] { World.class }).newInstance(new Object[] { par1World });
			}
		} catch (Exception var4) {
			var4.printStackTrace();
		}

		return var2;
	}

	/**
	 * create a new instance of an entity from NBT store
	 */
	public static Entity createEntityFromNBT(NBTTagCompound par0NBTTagCompound, World par1World) {
		EntityLiving var2 = null;

		try {
			Class<?> var3 = (Class<?>) stringToClassMapping.get(par0NBTTagCompound.getString("id"));

			if (var3 != null) {
				var2 = (EntityLiving) var3.getConstructor(new Class[] { World.class }).newInstance(new Object[] { par1World });
			}
		} catch (Exception var4) {
			var4.printStackTrace();
		}

		if (var2 != null) {
			var2.readFromNBT(par0NBTTagCompound);
		} else {
			System.out.println("Skipping Entity with id " + par0NBTTagCompound.getString("id"));
		}

		return var2;
	}

	/**
	 * Create a new instance of an entity in the world by using an entity ID.
	 */
	public static Entity createEntityByID(int par0, World par1World) {
		Entity var2 = null;

		try {
			Class<?> var3 = (Class<?>) IDtoClassMapping.get(Integer.valueOf(par0));

			if (var3 != null) {
				var2 = (Entity) var3.getConstructor(new Class[] { World.class }).newInstance(new Object[] { par1World });
			}
		} catch (Exception var4) {
			var4.printStackTrace();
		}

		if (var2 == null) {
			System.out.println("Skipping Entity with id " + par0);
		}

		return var2;
	}

	/**
	 * gets the entityID of a specific entity
	 */
	public static int getEntityID(Entity par0Entity) {
		return ((Integer) classToIDMapping.get(par0Entity.getClass())).intValue();
	}

	/**
	 * Gets the string representation of a specific entity.
	 */
	public static String getEntityString(Entity par0Entity) {
		return (String) classToStringMapping.get(par0Entity.getClass());
	}

	/**
	 * Finds the class using IDtoClassMapping and classToStringMapping
	 */
	public static String getStringFromID(int par0) {
		Class<?> var1 = (Class<?>) IDtoClassMapping.get(Integer.valueOf(par0));
		return var1 != null ? (String) classToStringMapping.get(var1) : null;
	}

	public static ClassType getClassTypeFromID(int par0) {
		return (ClassType) IDtoTypeMapping.get(Integer.valueOf(par0));
	}

	static {
		addMapping(EntityAbra.class, "Abra", IDListPixelmon.abraId, ClassType.Pixelmon);
		addMapping(EntityArbok.class, "Arbok", IDListPixelmon.arbokId, ClassType.Pixelmon);
		addMapping(EntityBlastoise.class, "Blastoise", IDListPixelmon.blastoiseId, ClassType.Pixelmon);
		addMapping(EntityBulbasaur.class, "Bulbasaur", IDListPixelmon.bulbasaurId, ClassType.Pixelmon);
		addMapping(EntityButterfree.class, "Butterfree", IDListPixelmon.butterfreeId, ClassType.Pixelmon);
		addMapping(EntityCaterpie.class, "Caterpie", IDListPixelmon.caterpieId, ClassType.Pixelmon);
		addMapping(EntityCharizard.class, "Charizard", IDListPixelmon.charizardId, ClassType.Pixelmon);
		addMapping(EntityCharmander.class, "Charmander", IDListPixelmon.charmanderId, ClassType.Pixelmon);
		addMapping(EntityCharmeleon.class, "Charmeleon", IDListPixelmon.charmeleonId, ClassType.Pixelmon);
		addMapping(EntityCubone.class, "Cubone", IDListPixelmon.cuboneId, ClassType.Pixelmon);
		addMapping(EntityDiglett.class, "Diglett", IDListPixelmon.diglettId, ClassType.Pixelmon);
		addMapping(EntityDugtrio.class, "Dugtrio", IDListPixelmon.dugtrioId, ClassType.Pixelmon);
		addMapping(EntityEevee.class, "Eevee", IDListPixelmon.eeveeId, ClassType.Pixelmon);
		addMapping(EntityEkans.class, "Ekans", IDListPixelmon.ekansId, ClassType.Pixelmon);
		addMapping(EntityElectrode.class, "Electrode", IDListPixelmon.electrodeId, ClassType.Pixelmon);
		addMapping(EntityFlareon.class, "Flareon", IDListPixelmon.flareonId, ClassType.Pixelmon);
		addMapping(EntityGastly.class, "Gastly", IDListPixelmon.gastlyId, ClassType.Pixelmon);
		addMapping(EntityGeodude.class, "Geodude", IDListPixelmon.geodudeId, ClassType.Pixelmon);
		addMapping(EntityGoldeen.class, "Goldeen", IDListPixelmon.goldeenId, ClassType.Pixelmon);
		addMapping(EntityGrowlithe.class, "Growlithe", IDListPixelmon.growlitheId, ClassType.Pixelmon);
		addMapping(EntityGyarados.class, "Gyarados", IDListPixelmon.gyaradosId, ClassType.Pixelmon);
		addMapping(EntityHorsea.class, "Horsea", IDListPixelmon.horseaId, ClassType.Pixelmon);
		addMapping(EntityIvysaur.class, "Ivysaur", IDListPixelmon.ivysaurId, ClassType.Pixelmon);
		addMapping(EntityJolteon.class, "Jolteon", IDListPixelmon.jolteonId, ClassType.Pixelmon);
		addMapping(EntityJigglypuff.class, "Jigglypuff", IDListPixelmon.jigglypuffId, ClassType.Pixelmon);
		addMapping(EntityMagikarp.class, "Magikarp", IDListPixelmon.magikarpId, ClassType.Pixelmon);
		addMapping(EntityMagnemite.class, "Magnemite", IDListPixelmon.magnemiteId, ClassType.Pixelmon);
		addMapping(EntityMankey.class, "Mankey", IDListPixelmon.mankeyId, ClassType.Pixelmon);
		addMapping(EntityMetapod.class, "Metapod", IDListPixelmon.metapodId, ClassType.Pixelmon);
		addMapping(EntityMew.class, "Mew", IDListPixelmon.mewId, ClassType.Pixelmon);
		addMapping(EntitySandile.class, "Sandile", IDListPixelmon.sandileId, ClassType.Pixelmon);
		addMapping(EntityKrokorok.class, "Krokorok", IDListPixelmon.krokorokId, ClassType.Pixelmon);
		addMapping(EntityMiltank.class, "Miltank", IDListPixelmon.miltankId, ClassType.Pixelmon);
		addMapping(EntityPidgey.class, "Pidgey", IDListPixelmon.pidgeyId, ClassType.Pixelmon);
		addMapping(EntityPikachu.class, "Pikachu", IDListPixelmon.pikachuId, ClassType.Pixelmon);
		addMapping(EntityPidgeotto.class, "Pidgeotto", IDListPixelmon.pidgeottoId, ClassType.Pixelmon);
		addMapping(EntityPidgeot.class, "Pidgeot", IDListPixelmon.pidgeotId, ClassType.Pixelmon);
		addMapping(EntityNinetales.class, "Ninetales", IDListPixelmon.ninetalesId, ClassType.Pixelmon);
		addMapping(EntityOddish.class, "Oddish", IDListPixelmon.oddishId, ClassType.Pixelmon);
		addMapping(EntityOmanyte.class, "Omanyte", IDListPixelmon.omanyteId, ClassType.Pixelmon);
		addMapping(EntityOmastar.class, "Omastar", IDListPixelmon.omastarId, ClassType.Pixelmon);
		addMapping(EntityPsyduck.class, "Psyduck", IDListPixelmon.psyduckId, ClassType.Pixelmon);
		addMapping(EntityRattata.class, "Rattata", IDListPixelmon.rattataId, ClassType.Pixelmon);
		addMapping(EntitySeaking.class, "Seaking", IDListPixelmon.seakingId, ClassType.Pixelmon);
		addMapping(EntityShellder.class, "Shellder", IDListPixelmon.shellderId, ClassType.Pixelmon);
		addMapping(EntitySnorlax.class, "Snorlax", IDListPixelmon.snorlaxId, ClassType.Pixelmon);
		addMapping(EntitySquirtle.class, "Squirtle", IDListPixelmon.squirtleId, ClassType.Pixelmon);
		addMapping(EntityStaryu.class, "Staryu", IDListPixelmon.staryuId, ClassType.Pixelmon);
		addMapping(EntityStarmie.class, "Starmie", IDListPixelmon.starmieId, ClassType.Pixelmon);
		addMapping(EntityTrapinch.class, "Trapinch", IDListPixelmon.trapinchId, ClassType.Pixelmon);
		addMapping(EntityVenusaur.class, "Venusaur", IDListPixelmon.venusaurId, ClassType.Pixelmon);
		addMapping(EntityVoltorb.class, "Voltorb", IDListPixelmon.voltorbId, ClassType.Pixelmon);
		addMapping(EntityVulpix.class, "Vulpix", IDListPixelmon.vulpixId, ClassType.Pixelmon);
		addMapping(EntityWartortle.class, "Wartortle", IDListPixelmon.wartortleId, ClassType.Pixelmon);
		addMapping(EntityWigglytuff.class, "Wigglytuff", IDListPixelmon.wigglytuffId, ClassType.Pixelmon);
		addMapping(EntityZubat.class, "Zubat", IDListPixelmon.zubatId, ClassType.Pixelmon);
		addMapping(EntityMagneton.class, "Magneton", IDListPixelmon.magnetonId, ClassType.Pixelmon);
		addMapping(EntityVibrava.class, "Vibrava", IDListPixelmon.vibravaId, ClassType.Pixelmon);
		addMapping(EntityMareep.class, "Mareep", IDListPixelmon.mareepId, ClassType.Pixelmon);
		addMapping(EntityDratini.class, "Dratini", IDListPixelmon.dratiniId, ClassType.Pixelmon);
		addMapping(EntityTentacool.class, "Tentacool", IDListPixelmon.tentacoolId, ClassType.Pixelmon);
		addMapping(EntitySolrock.class, "Solrock", IDListPixelmon.solrockId, ClassType.Pixelmon);
		addMapping(EntityLunatone.class, "Lunatone", IDListPixelmon.lunatoneId, ClassType.Pixelmon);
		addMapping(EntityNidoranMale.class, "NidoranMale", IDListPixelmon.nidoranMaleId, ClassType.Pixelmon);
		addMapping(EntityVaporeon.class, "Vaporeon", IDListPixelmon.vaporeonId, ClassType.Pixelmon);
		addMapping(EntityGloom.class, "Gloom", IDListPixelmon.gloomId, ClassType.Pixelmon);
		
		addMapping(EntityTrainerYoungster.class, "Youngster", IDListTrainer.trainerYoungsterId, ClassType.Trainer);
		addMapping(EntityTrainerYoungster02.class, "Youngster2", IDListTrainer.trainerYoungster2Id, ClassType.Trainer);
		addMapping(EntityTrainerBugCatcher.class, "BugCatcher", IDListTrainer.trainerBugCatcherId, ClassType.Trainer);
	}

	public static void registerEntities() {
		Iterator i = IDtoClassMapping.entrySet().iterator();
		while (i.hasNext()) {
			Map.Entry entry = (Map.Entry) i.next();
			ModLoader.registerEntityID((Class) entry.getValue(), getStringFromID((Integer) entry.getKey()), (Integer) entry.getKey());
			MinecraftForge.registerEntity((Class)entry.getValue(), mod_Pixelmon.instance, (Integer)entry.getKey(), 50, 1, true);
		}
	}

	public static void addSpawns() {
		Iterator i = IDtoClassMapping.entrySet().iterator();
		while (i.hasNext()) {
			Map.Entry entry = (Map.Entry) i.next();
			String name = getStringFromID((Integer) entry.getKey());
			ClassType type = getClassTypeFromID((Integer) entry.getKey());
			BiomeGenBase[] biomes;
			if (type == ClassType.Pixelmon) {
				biomes = DatabaseStats.GetSpawnBiomes(name);
				int rarity = DatabaseStats.GetRarity(name);
				int groupMin = DatabaseStats.GetMinGroupSize(name);
				int groupMax = DatabaseStats.GetMaxGroupSize(name);
				EnumCreatureType creaturetype = DatabaseStats.GetCreatureType(name);
				if (rarity != -1) {
					ModLoader.addSpawn((Class)entry.getValue(), rarity, groupMin, groupMax, creaturetype);
				}
			}else if (type == ClassType.Trainer){
				biomes = DatabaseTrainers.GetSpawnBiomes(name);
				ModLoader.addSpawn((Class)entry.getValue(), 10, 1, 1, EnumCreatureType.creature);
			}
		}
	}

	public enum ClassType {
		Trainer, Pixelmon
	}
}
