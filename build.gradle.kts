plugins {
    java
    alias(libs.plugins.runpaper)
}

group = "net.diecode"
version = "5.0.0"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://jitpack.io") {
        content { includeModule("com.github.MilkBowl", "VaultAPI") }
    }
    ivy("https://github.com/garbagemule/MobArena/releases/download") {
        patternLayout { artifact("[revision]/[artifact]-[revision].[ext]") }
        metadataSources { artifact() }
        content { includeModule("com.garbagemule", "MobArena") }
    }
}

dependencies {
    compileOnly(libs.paperapi)
    compileOnly(libs.vaultapi) { isTransitive = false }
    compileOnly(libs.mobarena)
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(25))
}

sourceSets.main {
    java.setSrcDirs(listOf("src"))
    resources.setSrcDirs(listOf("src"))
    resources.include("plugin.yml", "resources/**")
}

tasks {
    processResources {
        val pluginVersion = project.version.toString()
        inputs.property("pluginVersion", pluginVersion)
        filesMatching("plugin.yml") {
            expand("version" to pluginVersion)
        }
    }

    withType<JavaCompile>().configureEach {
        options.encoding = "UTF-8"
    }

    runServer {
        minecraftVersion(libs.versions.minecraft.get())
        javaLauncher.set(project.javaToolchains.launcherFor(java.toolchain))
        jvmArguments.add("-Dcom.mojang.eula.agree=true")
        systemProperty("terminal.jline", false)
        systemProperty("terminal.ansi", true)
    }
}
