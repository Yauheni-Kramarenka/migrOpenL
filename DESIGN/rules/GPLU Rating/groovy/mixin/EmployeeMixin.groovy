/*
 * Copyright © 2023 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 * CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.
 */
package mixin

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import org.openl.rules.ruleservice.databinding.annotation.MixInClass


/**
 * Mixin for {@link org.openl.generated.beans.Employee}
 *
 * @author vkalynovskyi
 * @since 23.14
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@MixInClass("org.openl.generated.beans.Employee")
class EmployeeMixin {

    @JsonProperty("idNumber")
    String employeeID
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date dob
    @JsonDeserialize(converter = GenderDeserializer.class)
    String gender
    @JsonProperty("disabilityEarnings")
    Double salaryAmt
    @JsonProperty("earningsMode")
    @JsonDeserialize(converter = SalaryUnitDeserializer.class)
    String salaryUnit
    @JsonDeserialize(converter = IsRetireeDeserializer.class)
    @JsonProperty("type")
    Boolean isRetiree
    @JsonProperty("residentialZipCode")
    String zip
    @JsonProperty("stateOfResidence")
    String state
}
