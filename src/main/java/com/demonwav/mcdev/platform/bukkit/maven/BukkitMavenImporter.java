/*
 * Minecraft Dev for IntelliJ
 *
 * https://minecraftdev.org
 *
 * Copyright (c) 2017 minecraft-dev
 *
 * MIT License
 */

package com.demonwav.mcdev.platform.bukkit.maven;

import com.demonwav.mcdev.buildsystem.maven.AbstractMavenImporter;
import com.demonwav.mcdev.platform.bukkit.BukkitModuleType;

import com.intellij.openapi.module.JavaModuleType;
import com.intellij.openapi.module.ModuleType;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public class BukkitMavenImporter extends AbstractMavenImporter {

    public BukkitMavenImporter() {
        super(BukkitModuleType.getInstance());
    }

    public BukkitMavenImporter(@NotNull final BukkitModuleType type) {
        super(type);
    }

    @NotNull
    @Override
    public ModuleType getModuleType() {
        return JavaModuleType.getModuleType();
    }
}
