package org.gcube.portlets.admin.accountingmanager.server.amservice.command;

import java.util.Calendar;
import java.util.SortedMap;

import org.gcube.accounting.analytics.Info;
import org.gcube.accounting.analytics.NumberedFilter;
import org.gcube.accounting.analytics.persistence.AccountingPersistenceQuery;
import org.gcube.accounting.analytics.persistence.AccountingPersistenceQueryFactory;
import org.gcube.portlets.admin.accountingmanager.server.amservice.query.AccountingQueryTop;
import org.gcube.portlets.admin.accountingmanager.server.amservice.response.SeriesResponse4JobTop;
import org.gcube.portlets.admin.accountingmanager.server.amservice.response.SeriesResponse4PortletTop;
import org.gcube.portlets.admin.accountingmanager.server.amservice.response.SeriesResponse4ServiceTop;
import org.gcube.portlets.admin.accountingmanager.server.amservice.response.SeriesResponse4StorageTop;
import org.gcube.portlets.admin.accountingmanager.server.amservice.response.SeriesResponse4TaskTop;
import org.gcube.portlets.admin.accountingmanager.server.amservice.response.SeriesResponseBuilder;
import org.gcube.portlets.admin.accountingmanager.server.amservice.response.SeriesResponseDirector;
import org.gcube.portlets.admin.accountingmanager.shared.data.AccountingType;
import org.gcube.portlets.admin.accountingmanager.shared.data.response.SeriesResponse;
import org.gcube.portlets.admin.accountingmanager.shared.exception.AccountingManagerServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Giancarlo Panichi email: <a
 *         href="mailto:g.panichi@isti.cnr.it">g.panichi@isti.cnr.it</a>
 *
 */
public class AccountingCommandTop implements AccountingCommand<SeriesResponse> {
	private static final Logger logger = LoggerFactory
			.getLogger(AccountingCommandTop.class);

	private AccountingQueryTop accountingQueryTop;
	private AccountingType accountingType;
	

	public AccountingCommandTop(AccountingQueryTop accountingQueryTop,
			AccountingType accountingType) {
		this.accountingQueryTop = accountingQueryTop;
		this.accountingType = accountingType;
	}

	@Override
	public SeriesResponse execute() throws AccountingManagerServiceException {
		try {
			AccountingPersistenceQuery apq = AccountingPersistenceQueryFactory
					.getInstance();

			logger.debug("Qyery TopValues: "+accountingQueryTop.getFilterKey()
							.getKey());		
		
			SortedMap<NumberedFilter, SortedMap<Calendar, Info>> topSM = apq
					.getTopValues(accountingQueryTop.getType(),
							accountingQueryTop.getTemporalConstraint(),
							accountingQueryTop.getFilters(), accountingQueryTop.getFilterKey()
							.getKey(),null, true,
							accountingQueryTop.getTopNumber());

			if (topSM == null) {
				throw new AccountingManagerServiceException(
						"Error retrieving info for top: sorted map is null!");
			}
			
			logger.debug("TopSM: "+topSM);


			SeriesResponseBuilder seriesResponseBuilder = getSeriesResponseBuilder(
					accountingType, topSM);

			SeriesResponseDirector seriesResponseDirector = new SeriesResponseDirector();
			seriesResponseDirector
					.setSeriesResponseBuilder(seriesResponseBuilder);
			seriesResponseDirector.constructSeriesResponse();
			SeriesResponse seriesResponse = seriesResponseDirector
					.getSeriesResponse();

			if (seriesResponse == null) {
				throw new AccountingManagerServiceException(
						"Error creating series response!");
			}
			logger.debug("SeriesResponse Created: " + seriesResponse);
			return seriesResponse;

		} catch (Throwable e) {
			logger.error("Error in AccountingCommandTop(): "
					+ e.getLocalizedMessage());
			e.printStackTrace();
			throw new AccountingManagerServiceException("No data available!");

		}
	}

	private SeriesResponseBuilder getSeriesResponseBuilder(
			AccountingType accountingType, SortedMap<NumberedFilter, SortedMap<Calendar, Info>> topSM)
			throws AccountingManagerServiceException {
		if (accountingType == null) {
			throw new AccountingManagerServiceException(
					"Error accounting type is null");
		}

		switch (accountingType) {
		case JOB:
			return new SeriesResponse4JobTop(topSM);
		case PORTLET:
			return new SeriesResponse4PortletTop(topSM);
		case SERVICE:
			return new SeriesResponse4ServiceTop(topSM);
		case STORAGE:
			return new SeriesResponse4StorageTop(topSM);
		case TASK:
			return new SeriesResponse4TaskTop(topSM);
		default:
			throw new AccountingManagerServiceException(
					"Error request type is unknow!");

		}
	}

}
