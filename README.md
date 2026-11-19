# Astryx Addon (Meteor addon for Minecraft 1.21.11)

An orange-and-gray starter addon. It adds:
- Watermark HUD: your client name + version
- Coords HUD: your XYZ
- "hello" module: prints a chat message when switched on

## Build it
1. Install JDK 21 (e.g. Adoptium Temurin 21) and IntelliJ IDEA Community.
2. IntelliJ: File > Open, pick this folder, let Gradle finish syncing (first time takes a few minutes).
3. Open the Gradle tab > Tasks > build > build (or run `./gradlew build`, `gradlew.bat build` on Windows).
4. Your jar is in `build/libs/astryx-addon-0.1.0.jar`.
5. Put it in your Fabric `mods` folder next to Meteor Client 1.21.11 and Fabric API.

## Use it
- Right Shift opens the Meteor GUI; your "Astryx" tab has the hello module.
- Open the HUD editor from the GUI to place "watermark" and "coords".

## Rename it
- Astryx.java: change NAME.
- To change the package/folder names too, rename them together (Astryx.java, fabric.mod.json "entrypoints", and getPackage()).

## Build without installing anything (GitHub does it for you)
1. Make a free account at github.com and create a new repository (public is fine).
2. Click "uploading an existing file" and drag in everything from inside the astryx-addon folder
   (including the hidden-looking .github folder), then Commit.
3. Open the repo's Actions tab. A "Build jar" run starts by itself and takes about 3-6 minutes.
4. Click the finished run, and download "astryx-addon-jar" at the bottom. Unzip it.
   Use the jar that does NOT end in "-sources" (astryx-addon-0.1.0.jar).
