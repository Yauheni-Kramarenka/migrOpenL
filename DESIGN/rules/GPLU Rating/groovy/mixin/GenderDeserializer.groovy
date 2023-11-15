/*
 * Copyright © 2023 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 * CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.
 */

package mixin

import com.fasterxml.jackson.databind.util.StdConverter

/**
 * Deserializer for conversing gender from census items to {@link org.openl.generated.beans.Employee}
 *
 * @since 23.14
 * @author vkalynovskyi
 */
class GenderDeserializer extends StdConverter<String, String> {
    @Override
    String convert(String value) {
        switch (value) {
            case "Male":
            case "male":
            case "M": return "Male"
            case "Female":
            case "female":
            case "F": return "Female"
            case "Not Specified":
            case "Non-Conforming": return null
            default: throw new IllegalArgumentException("Unrecognized gender " + value)
        }
    }
}
