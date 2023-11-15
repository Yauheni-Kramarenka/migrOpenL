/* Copyright © 2021 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/

import com.eisgroup.genesis.openl.integration.rating.details.store.*
import org.openl.rules.calc.SpreadsheetResult
import org.openl.rules.ruleservice.core.annotations.ExternalParam
import org.openl.rules.ruleservice.core.interceptors.RulesType
import org.openl.rules.ruleservice.core.interceptors.annotations.ServiceCallBeforeInterceptor
import org.openl.rules.ruleservice.storelogdata.annotation.PrepareStoreLogData
import org.openl.rules.ruleservice.storelogdata.cassandra.annotation.StoreLogDataToCassandra
import org.openl.rules.ruleservice.storelogdata.db.annotation.StoreLogDataToDB

import javax.ws.rs.core.Context
import javax.servlet.ServletContext

interface RatingService extends RatingDetailsService {

    @StoreLogDataToDB(value = RatingDetailsEntity.class, sync = true)
    @StoreLogDataToCassandra(value = RatingDetails.class, sync = true)
    // {@code bindToServiceMethodAdvice = GpluCensusResolver.class} is workaround to execute prepare store log data
    // advice before service call advice. It is needed to prepare rating details before service call to prevent
    // save error in case of service call failure.
    @PrepareStoreLogData(value = RatingDetailsCollectBefore.class, before = true, bindToServiceMethodAdvice = GpluCensusResolver.class)
    @PrepareStoreLogData(RatingDetailsCollectorAfter.class)
    @ServiceCallBeforeInterceptor(GpluCensusResolver.class)
    SpreadsheetResult PolicyCalculation(@RulesType("Policy") Object policy, @ExternalParam @Context ServletContext sc)

}
