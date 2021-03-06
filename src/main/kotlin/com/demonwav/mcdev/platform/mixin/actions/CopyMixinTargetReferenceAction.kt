/*
 * Minecraft Dev for IntelliJ
 *
 * https://minecraftdev.org
 *
 * Copyright (c) 2017 minecraft-dev
 *
 * MIT License
 */

package com.demonwav.mcdev.platform.mixin.actions

import com.demonwav.mcdev.platform.mixin.util.qualifiedMemberReference
import com.demonwav.mcdev.util.findReferencedMember
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys.CARET
import com.intellij.openapi.actionSystem.CommonDataKeys.PSI_FILE
import com.intellij.openapi.ide.CopyPasteManager
import com.intellij.openapi.wm.WindowManager
import com.intellij.psi.PsiField
import com.intellij.psi.PsiMethod
import java.awt.datatransfer.StringSelection

class CopyMixinTargetReferenceAction : AnAction() {

    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project ?: return
        val file = e.getData(PSI_FILE) ?: return
        val caret = e.getData(CARET) ?: return

        val element = file.findElementAt(caret.offset) ?: return
        val member = findReferencedMember(element) ?: return

        val targetReference = when (member) {
            is PsiMethod -> member.qualifiedMemberReference
            is PsiField -> member.qualifiedMemberReference
            else -> return
        }

        CopyPasteManager.getInstance().setContents(StringSelection(targetReference.toString()))
        WindowManager.getInstance().getStatusBar(project).info = "Mixin target reference has been copied."
    }

}
