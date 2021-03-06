/*
 * Minecraft Dev for IntelliJ
 *
 * https://minecraftdev.org
 *
 * Copyright (c) 2017 minecraft-dev
 *
 * MIT License
 */

package com.demonwav.mcdev.platform.mixin.reference

import com.intellij.psi.PsiElement

internal interface MixinReference {

    val description: String

    fun isUnresolved(context: PsiElement): Boolean

    fun isValidAnnotation(name: String): Boolean

}
