package edu.xfolex.stockapp.data.mappers

import edu.xfolex.stockapp.data.local.entities.CompanyListingEntity
import edu.xfolex.stockapp.data.remote.dto.CompanyInfoDto
import edu.xfolex.stockapp.domain.model.CompanyInfo
import edu.xfolex.stockapp.domain.model.CompanyListing

fun CompanyListingEntity.toCompanyListing(): CompanyListing = CompanyListing(
    name = name,
    symbol = symbol,
    exchange = exchange
)

fun CompanyListing.toCompanyListingEntity(): CompanyListingEntity = CompanyListingEntity(
    name = name,
    symbol = symbol,
    exchange = exchange
)

fun CompanyInfoDto.toCompanyInfo(): CompanyInfo = CompanyInfo(
    symbol = symbol ?: "",
    description = description ?: "",
    name = name ?: "",
    country = country ?: "",
    industry = industry ?: ""
)