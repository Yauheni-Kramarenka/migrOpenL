/*
 * Copyright © 2023 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 * CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.
 */


import com.eisgroup.genesis.openl.integration.census.CensusResolver
import com.fasterxml.jackson.databind.node.ObjectNode
import mixin.EmployeeCoverageMixin
import mixin.EmployeeMixin

/**
 * {@link CensusResolver} implementation for GPLU project.
 *
 * @since 23.14
 * @author vkalynovskyi
 */
class GpluCensusResolver extends CensusResolver {

    /**
     * Maps census items to {@link org.openl.generated.beans.Employee}
     * @param ratingInfo
     * @return
     */
    @Override
    protected ObjectNode convertObjectNode(ObjectNode ratingInfo) {
        var employeeInfo = ratingInfo.employeeInfo
        var employeeCoverages = employeeInfo.putArray("employeeCoverages")

        COVERAGE_TYPE_TO_RATING_INFO_FIELD_MAP
                .forEach((coverageType, ratingInfoFieldName) -> {
                    var product = ratingInfo.get(ratingInfoFieldName)
                    if (product != null) {
                        ((ObjectNode) product).put("coverageId", coverageType)
                        employeeCoverages.add(product)
                    }
                })

        return employeeInfo
    }

    @Override
    protected String getTargetDatatype() {
        "Employee"
    }

    @Override
    protected Map<String, Class> getMixins() {
        return [
                "Employee"        : EmployeeMixin.class,
                "EmployeeCoverage": EmployeeCoverageMixin.class
        ]
    }

    @Override
    protected Collection<String> getFields() {
        List<String> employeeInfoFields = ["employeeInfo"]
        employeeInfoFields.addAll(COVERAGE_TYPE_TO_RATING_INFO_FIELD_MAP.values())
        return employeeInfoFields
    }

    private static final Map<String, String> COVERAGE_TYPE_TO_RATING_INFO_FIELD_MAP = [
            "WholeLife"                 : "productWholeLife",
            "SpouseTermLife"            : "productSpouseTermLife",
            "ChildTermLife"             : "productChildTermLife",
            "ADD"                       : "productADD",
            "LongTermCareAdditionalCost": "productLongTermCareAdditionalCost",
            "ExtensionOfLongTermCare"   : "productExtensionOfLongTermCare",
            "LongTermCareRestoration"   : "productLongTermCareRestoration",
            "WaiverOfPremium"           : "productWaiverOfPremium"
    ]

}
