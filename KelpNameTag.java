package net.kelptweaks;

import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.scoreboard.ScoreboardTeam;
import net.minecraft.scoreboard.Team;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

/**
 * Fügt jedem Spieler, der diese Mod installiert hat, automatisch ein Kelp-Symbol
 * am Ende seines Namens hinzu (Nametag über dem Kopf, Tabliste, Chat).
 * Läuft über ein Scoreboard-Team mit Suffix, das serverseitig bei jedem
 * Join gesetzt wird — kein Client-Mixin nötig.
 */
public class KelpNameTag {

    private static final String TEAM_NAME = "kelptweaks_users";
    private static final String SUFFIX = " \uD83C\uDF3F"; // 🌿

    public static void init() {
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            ServerPlayerEntity player = handler.getPlayer();
            var scoreboard = server.getScoreboard();

            Team team = scoreboard.getTeam(TEAM_NAME);
            if (team == null) {
                team = scoreboard.addTeam(TEAM_NAME);
                team.setSuffix(Text.literal(SUFFIX));
                team.setColor(net.minecraft.util.Formatting.GREEN);
            }

            // Hinweis für 26.1.2: Falls addScoreHolderToTeam beim Bauen nicht
            // gefunden wird, hieß die Methode in älteren Mappings addPlayerToTeam.
            scoreboard.addScoreHolderToTeam(player.getGameProfile().getName(), team);

            KelpTweaks.LOGGER.info("{} nutzt Kelp Tweaks – Namens-Tag gesetzt.", player.getGameProfile().getName());
        });
    }
}
