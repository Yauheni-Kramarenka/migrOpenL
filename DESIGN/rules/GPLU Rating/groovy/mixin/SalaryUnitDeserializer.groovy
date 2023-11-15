/*
 * Copyright © 2023 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 * CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.
 */

package mixin

import com.fasterxml.jackson.databind.util.StdConverter

/**
 * Deserializer for conversing census items 'earningsMode' to {@link org.openl.generated.beans.Employee} 'salaryUnit'
 *
 * @since 23.14
 * @author vkalynovskyi
 */
class SalaryUnitDeserializer extends StdConverter<String, String> {
    @Override
    String convert(String value) {
        switch (value) {
            case "A":
            case "Annual": return "Annual"
            case "M":
            case "Monthly": return "Monthly"
            case "H":
            case "Hourly": return "Hourly"
            case "W":
            case "Weekly": return "Weekly"
            case "B":
            case "Bi-weekly": return "BiWeekly"
            case "S":
            case "Semi-monthly": return "SemiMonthly"
            case "Salaried": return "Salaried"
            default: throw new IllegalArgumentException("Unrecognized Salary unit " + value)
        }
    }
}
