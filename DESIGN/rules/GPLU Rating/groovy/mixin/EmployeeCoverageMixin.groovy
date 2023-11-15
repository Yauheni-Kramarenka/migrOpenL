/*
 * Copyright © 2023 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 * CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.
 */
package mixin

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import org.openl.rules.ruleservice.databinding.annotation.MixInClass

/**
 * Mixin for {@link org.openl.generated.beans.EmployeeCoverage}
 *
 * @author vkalynovskyi
 * @since 23.14
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@MixInClass("org.openl.generated.beans.EmployeeCoverage")
abstract class EmployeeCoverageMixin {

    @JsonProperty("ratingClass")
    String classNumber
}
