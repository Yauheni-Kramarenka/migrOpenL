/*
 * Copyright © 2023 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 * CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.
 */

package mixin

import com.fasterxml.jackson.databind.util.StdConverter

/**
 * Deserializer to define retiree status for {@link org.openl.generated.beans.Employee}
 *
 * @since 23.14
 * @author vkalynovskyi
 */
class IsRetireeDeserializer extends StdConverter<String, Boolean> {
    @Override
    Boolean convert(String value) {
        switch (value) {
            case "A":
            case "Active":
            case "I":
            case "Inactive": return false
            case "R":
            case "Retiree": return true
            default: throw new IllegalArgumentException("Unrecognized retiree status " + value)
        }
    }
}
