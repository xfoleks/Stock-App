package edu.xfolex.stockapp.presentation.company_info.state

import edu.xfolex.stockapp.domain.model.CompanyInfo
import edu.xfolex.stockapp.domain.model.IntradayInfo

data class CompanyInfoState(
    val stockInfos: List<IntradayInfo> = emptyList(),
    val company: CompanyInfo? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
