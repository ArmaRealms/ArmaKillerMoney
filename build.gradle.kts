import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    alias(libs.plugins.java)
    alias(libs.plugins.shadow)
    alias(libs.plugins.runpaper)
}

group = "net.diecode"
version = "2.1.4"

repositories {
    // Vault, TriumphGUI & ShopGUIPlus
    maven("https://jitpack.io")
    // TriumphCMD
    maven("https://repo.triumphteam.dev/snapshots/")
    // PaperMC
    maven("https://repo.papermc.io/repository/maven-public/")
    // EssentialsX
    maven("https://repo.essentialsx.net/releases/")
    // PlaceholderAPI
    maven("https://repo.extendedclip.com/content/repositories/placeholderapi/")
    // PlayerPoints
    maven("https://repo.rosewooddev.io/repository/public/")
    // CrazyCrates
    maven("https://repo.crazycrew.us/releases")
    // PlayerWarps & PlayerAuctions
    maven("https://repo.olziedev.com/")
    // Oraxen
    maven("https://repo.oraxen.com/releases")
    // Floodgate
    maven("https://repo.opencollab.dev/main/")
    mavenCentral()
    flatDir { dirs("libs") }
}

dependencies {
    implementation(libs.triumphgui)
    implementation(libs.triumphcmd)
    implementation(libs.jdwebhooks)
    compileOnly(libs.paperapi)
    compileOnly(libs.hikaricp)
    compileOnly(libs.luckpermsapi)
    compileOnly(libs.placeholderapi)
    compileOnly(libs.playerpoints)
    compileOnly(libs.crazycratesapi)
    compileOnly(libs.playerwarpsapi)
    compileOnly(libs.playerauctionsapi)
    compileOnly(libs.oraxenapi)
    compileOnly(libs.floodgateapi)
    compileOnly(libs.shopguiapi) { exclude("org.spigotmc") }
    compileOnly(libs.essentialsx) { exclude("org.spigotmc") }
    compileOnly(fileTree("libs"))
    testImplementation(libs.junit.jupiter)
    testRuntimeOnly(libs.junit.platform)
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}

tasks {
    withType<ShadowJar> {
        archiveClassifier.set("")
    }

    withType<ProcessResources> {
        filesMatching("plugin.yml") {
            expand("version" to project.version, "hikari" to libs.versions.hikaricp.get())
        }
    }

    withType<JavaCompile> {
        options.encoding = "UTF-8"
        options.compilerArgs.add("-parameters")
    }

    build {
        dependsOn("apiJar")
        dependsOn(shadowJar)
    }

    runServer {
        minecraftVersion("1.21.8")
        jvmArguments.add("-Dcom.mojang.eula.agree=true")
        jvmArguments.add("-Dnet.kyori.ansi.colorLevel=truecolor")
        jvmArguments.add("-Dfile.encoding=UTF8")
        systemProperty("terminal.jline", false)
        systemProperty("terminal.ansi", true)
    }

    test {
        useJUnitPlatform()
    }
}