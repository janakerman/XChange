package org.knowm.xchange.kucoin.service.marketdata;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.kucoin.KucoinExchange;
import org.knowm.xchange.kucoin.service.KucoinMarketDataService;
import org.knowm.xchange.service.marketdata.MarketDataService;

public class TickerFetchIntegration {

  @Test
  public void fetch_ticker_test() throws IOException {
    Exchange exchange = ExchangeFactory.INSTANCE.createExchange(KucoinExchange.class);
    MarketDataService marketDataService = exchange.getMarketDataService();

    Ticker ticker = marketDataService.getTicker(CurrencyPair.LTC_BTC);

    assertThat(ticker.getBid()).isGreaterThan(BigDecimal.ZERO);
    assertThat(ticker.getAsk()).isGreaterThan(BigDecimal.ZERO);
    assertThat(ticker.getHigh()).isGreaterThan(BigDecimal.ZERO);
    assertThat(ticker.getLast()).isGreaterThan(BigDecimal.ZERO);
    assertThat(ticker.getVolume()).isGreaterThan(BigDecimal.ZERO);
    assertThat(ticker.getCurrencyPair()).isEqualTo(CurrencyPair.LTC_BTC);
    assertThat(ticker.getTimestamp()).isNotNull();
  }

  @Test
  public void fetch_tickers_test() throws IOException {
    Exchange exchange = ExchangeFactory.INSTANCE.createExchange(KucoinExchange.class);
    KucoinMarketDataService marketDataService = (KucoinMarketDataService)exchange.getMarketDataService();

    List<Ticker> tickers = marketDataService.getTickers();

    Ticker ticker;

    ticker = tickers.stream().filter(ticker1 -> ticker1.getCurrencyPair().equals(CurrencyPair.BTC_USDT))
        .findFirst()
        .get();
    assertThat(ticker.getBid()).isGreaterThan(BigDecimal.ZERO);
    assertThat(ticker.getAsk()).isGreaterThan(BigDecimal.ZERO);
    assertThat(ticker.getHigh()).isGreaterThan(BigDecimal.ZERO);
    assertThat(ticker.getLast()).isGreaterThan(BigDecimal.ZERO);
    assertThat(ticker.getVolume()).isGreaterThan(BigDecimal.ZERO);
    assertThat(ticker.getTimestamp()).isNotNull();

    ticker = tickers.stream().filter(ticker1 -> ticker1.getCurrencyPair().equals(CurrencyPair.BCH_BTC))
        .findFirst()
        .get();
    assertThat(ticker.getBid()).isGreaterThan(BigDecimal.ZERO);
    assertThat(ticker.getAsk()).isGreaterThan(BigDecimal.ZERO);
    assertThat(ticker.getHigh()).isGreaterThan(BigDecimal.ZERO);
    assertThat(ticker.getLast()).isGreaterThan(BigDecimal.ZERO);
    assertThat(ticker.getVolume()).isGreaterThan(BigDecimal.ZERO);
    assertThat(ticker.getTimestamp()).isNotNull();
  }


}