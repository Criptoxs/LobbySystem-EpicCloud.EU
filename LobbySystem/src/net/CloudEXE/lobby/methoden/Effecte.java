package net.CloudEXE.lobby.methoden;

import de.slikey.effectlib.effect.CircleEffect;
import de.slikey.effectlib.util.ParticleEffect;
import net.CloudEXE.lobby.core.Core;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public class Effecte {

    public static void Spawn(){
        CircleEffect spawneffect = new CircleEffect(Core.effectManager);
        spawneffect.setLocation(new Location(Bukkit.getWorld("world"), -1188.500, 170, 44.500));
        spawneffect.enableRotation = false;
        spawneffect.radius = .8f;
        spawneffect.particle = ParticleEffect.FIREWORKS_SPARK;
        spawneffect.iterations = 999999999 * 999999999;
        spawneffect.start();

        CircleEffect spawneffect2 = new CircleEffect(Core.effectManager);
        spawneffect2.setLocation(new Location(Bukkit.getWorld("world"), -1188.500, 168, 133.500));
        spawneffect2.enableRotation = false;
        spawneffect2.radius = .8f;
        spawneffect2.particle = ParticleEffect.FIREWORKS_SPARK;
        spawneffect2.iterations = 999999999 * 999999999;
        spawneffect2.start();
    }
}
