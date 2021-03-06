/*
 * Minecraft Dev for IntelliJ
 *
 * https://minecraftdev.org
 *
 * Copyright (c) 2017 minecraft-dev
 *
 * MIT License
 */

package com.demonwav.mcdev.platform.mixin.inspection.reference

import com.demonwav.mcdev.platform.mixin.reference.target.TargetReference
import com.demonwav.mcdev.platform.mixin.util.MemberReference
import com.demonwav.mcdev.platform.mixin.util.MixinConstants.Annotations.AT
import com.demonwav.mcdev.util.constantStringValue
import com.intellij.codeInspection.ProblemsHolder
import com.intellij.psi.PsiAnnotation
import com.intellij.psi.PsiAnnotationMemberValue

class UnqualifiedMemberReferenceInspection : AnnotationAttributeInspection(AT, "target") {

    override fun getStaticDescription() = "Reports unqualified member references in @At targets"

    override fun visitAnnotationAttribute(annotation: PsiAnnotation, value: PsiAnnotationMemberValue, holder: ProblemsHolder) {
        // Check if the specified target reference uses member descriptors
        if (!TargetReference.usesMemberReference(value)) {
            return
        }

        // TODO: Quick fix

        val reference = MemberReference.parse(value.constantStringValue) ?: return
        if (!reference.qualified) {
            holder.registerProblem(value, "Unqualified member reference in @At target")
            return
        }

        if (reference.descriptor == null) {
            holder.registerProblem(value, "Method/field descriptor is required for member reference in @At target")
        }
    }

}
