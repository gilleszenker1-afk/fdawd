# Kelp Tweaks 🌿

Ein Fabric-Mod-Grundgerüst für **Minecraft 26.1.2**, das über einen GitHub-Actions-Workflow
automatisch gebaut wird.

## Enthaltene Features

- **Redstone Tweaks** (`RedstoneTweaks.java`): Rechtsklick + Ducken mit einem Blaze-Rod
  auf einen Repeater springt die Verzögerung um 2 Stufen statt um 1 weiter.
- **Mob Tweaks** (`MobTweaks.java`): Zombies/Skelette, die nachts von einem Spieler
  besiegt werden, haben 15 % Chance auf einen Bonus-Smaragd.
- **Kelp-Namens-Tag** (`KelpNameTag.java`): Jeder Spieler, der den Server mit dieser Mod
  betritt, bekommt automatisch ein 🌿 an das Ende seines Namens gehängt (Tabliste,
  Nametag über dem Kopf, Chat) – so sieht jeder sofort, wer die Mod nutzt.

## So bauen

1. Dieses Verzeichnis in ein neues GitHub-Repository pushen.
2. Der Workflow unter `.github/workflows/build.yml` läuft automatisch bei jedem Push
   auf `main` (und lässt sich auch manuell über "Run workflow" starten).
3. Nach erfolgreichem Lauf lädst du den fertigen `.jar` unter dem Reiter
   **Actions → dein Lauf → Artifacts → kelptweaks-jar** herunter.
4. Den Jar zusammen mit Fabric Loader ≥ 0.19.2 und Fabric API 0.150.0+26.1.2 (oder
   kompatibel) in den `mods`-Ordner legen.

## Wichtiger Hinweis

Minecraft 26.1.2 ist eine sehr neue Version. Ich habe den Code nach bestem Wissen anhand
öffentlich bekannter Fabric-API-Klassen geschrieben, konnte ihn aber selbst nicht gegen
die echten 26.1.2-Mappings kompilieren (kein Internetzugriff in meiner Umgebung). Es kann
sein, dass der GitHub-Actions-Lauf beim ersten Mal einen kleinen Fehler zeigt (z. B. eine
umbenannte Methode wie `addScoreHolderToTeam`/`addPlayerToTeam` oder eine leicht andere
Loom-Pluginversion in `build.gradle`). Schau in dem Fall einfach in den roten Fehler im
Actions-Log – meist reicht eine Zeile Anpassung.

Weitere Tweaks lassen sich einfach in `RedstoneTweaks.java` bzw. `MobTweaks.java` ergänzen.
