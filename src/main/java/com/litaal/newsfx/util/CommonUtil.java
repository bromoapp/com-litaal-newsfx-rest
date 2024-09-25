package com.litaal.newsfx.util;

import java.math.RoundingMode;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.litaal.newsfx.constant.ECurrency;
import com.litaal.newsfx.constant.EPair;
import com.litaal.newsfx.constant.ETimeFrame;
import com.litaal.newsfx.dto.NewsDto;
import com.litaal.newsfx.model.IndexedNews;
import com.litaal.newsfx.model.NewsDailyFx;
import com.litaal.newsfx.model.NewsDukascopy;
import com.litaal.newsfx.model.NewsFxStreet;
import com.litaal.newsfx.model.NewsMql5;
import com.litaal.newsfx.model.NewsResult;
import com.litaal.newsfx.model.PairPrice;
import com.litaal.newsfx.model.PairTick;

@Component
public class CommonUtil {

	@Value("${time.pattern}")
	private String timePattern;

	private SimpleDateFormat sdf;

	private SimpleDateFormat getFormatter() {
		if (sdf == null) {
			sdf = new SimpleDateFormat(timePattern);
		}
		return sdf;
	}

	public String dateToString(Date time) {
		return getFormatter().format(time);
	}

	public String getExpiryTime(int delay) {
		Date expiry = Date.from(ZonedDateTime.now().plusHours(delay).toInstant());
		String expiryInString = dateToString(expiry);
		return expiryInString;
	}

	public Object[] getAvailCurrencies(EPair pair) {
		List<String> currencies = new ArrayList<>();
		String one = pair.name().substring(0, 3);
		String two = pair.name().substring(3, 6);
		currencies.add(one);
		currencies.add(two);
		return currencies.toArray();
	}

	public Object[] parsePairPriceTimesFromList(List<PairPrice> list) {
		List<Object> items = new ArrayList<>();
		for (PairPrice pair : list) {
			items.add(pair.getTime());
		}
		return items.toArray();
	}

	public Object[] parsePairTickTimesFromList(List<PairTick> list) {
		List<Object> items = new ArrayList<>();
		for (PairTick pair : list) {
			items.add(pair.getTime());
		}
		return items.toArray();
	}

	public Object[] parsePricesFromList(List<PairPrice> list) {
		List<Object> items = new ArrayList<>();
		for (PairPrice pair : list) {
			List<Object> item = new ArrayList<>();
			item.add(pair.getOpen());
			item.add(pair.getClose());
			item.add(pair.getLow());
			item.add(pair.getHigh());
			items.add(item.toArray());
		}
		return items.toArray();
	}

	public Object[] parseVolumesFromList(List<PairPrice> list) {
		List<Object> items = new ArrayList<>();
		for (PairPrice pair : list) {
			items.add(pair.getVolume());
		}
		return items.toArray();
	}

	public Object[] parseAsksFromList(List<PairTick> list) {
		List<Object> items = new ArrayList<>();
		for (PairTick tick : list) {
			items.add(tick.getAskVol());
		}
		return items.toArray();
	}

	public Object[] parseBidskFromList(List<PairTick> list) {
		List<Object> items = new ArrayList<>();
		for (PairTick tick : list) {
			items.add(-1 * tick.getBidVol());
		}
		return items.toArray();
	}

	private DateTimeFormatter timeFormatter;

	private DateTimeFormatter timeFormatter() {
		if (timeFormatter == null) {
			timeFormatter = DateTimeFormatter.ofPattern(timePattern);
		}
		return timeFormatter;
	}

	private String make2Digits(int val) {
		if (String.valueOf(val).length() < 2) {
			return "0" + val;
		}
		return String.valueOf(val);
	}

	public long getNearestTimeInUnixtime(ETimeFrame tf, long stamp) {
		String value = null;
		String monthTxt = null;
		String dayTxt = null;
		String hourTxt = null;
		String minTxt = null;

		LocalDateTime datetime = Instant.ofEpochSecond(stamp).atZone(ZoneId.of("GMT")).toLocalDateTime();
		int year = datetime.getYear();
		int month = datetime.getMonthValue();
		int day = datetime.getDayOfMonth();
		int hour = datetime.getHour();
		int min = datetime.getMinute();

		monthTxt = make2Digits(month);
		dayTxt = make2Digits(day);
		switch (tf) {
		case D1:
			value = year + "/" + monthTxt + "/" + dayTxt + " 00:00";
			break;
		case H1:
			hourTxt = make2Digits(hour);
			if (String.valueOf(hour).length() < 2) {
				hourTxt = "0" + hour;
			}
			value = year + "/" + monthTxt + "/" + dayTxt + " " + hourTxt + ":00";
			break;
		case M1:
			hourTxt = make2Digits(hour);
			minTxt = make2Digits(min);
			value = year + "/" + monthTxt + "/" + dayTxt + " " + hourTxt + ":" + minTxt;
			break;
		case M15:
			hourTxt = make2Digits(hour);
			if (min < 15) {
				min = 0;
			}
			if (15 <= min && min < 30) {
				min = 15;
			}
			if (30 <= min && min < 45) {
				min = 30;
			}
			if (45 <= min && min <= 59) {
				min = 45;
			}
			minTxt = make2Digits(min);
			value = year + "/" + monthTxt + "/" + dayTxt + " " + hourTxt + ":" + minTxt;
			break;
		case M30:
			hourTxt = make2Digits(hour);
			if (min < 30) {
				min = 0;
			}
			if (30 <= min) {
				min = 30;
			}
			minTxt = make2Digits(min);
			value = year + "/" + monthTxt + "/" + dayTxt + " " + hourTxt + ":" + minTxt;
			break;
		case M5:
			hourTxt = make2Digits(hour);
			if (min < 5) {
				min = 0;
			}
			if (5 <= min && min < 10) {
				min = 5;
			}
			if (10 <= min && min < 15) {
				min = 10;
			}
			if (15 <= min && min < 20) {
				min = 15;
			}
			if (20 <= min && min < 25) {
				min = 20;
			}
			if (25 <= min && min < 30) {
				min = 25;
			}
			if (30 <= min && min < 35) {
				min = 30;
			}
			if (35 <= min && min < 40) {
				min = 35;
			}
			if (40 <= min && min < 45) {
				min = 40;
			}
			if (45 <= min && min < 50) {
				min = 45;
			}
			if (50 <= min && min <= 59) {
				min = 50;
			}
			minTxt = make2Digits(min);
			value = year + "/" + monthTxt + "/" + dayTxt + " " + hourTxt + ":" + minTxt;
			break;
		default:
			break;
		}
		LocalDateTime time = LocalDateTime.parse(value, timeFormatter());
		long nearest = time.atZone(ZoneId.of("GMT")).toEpochSecond();
		return nearest;
	}

	public int parseYearFromUnixtime(long time) {
		LocalDateTime ldt = LocalDateTime.ofInstant(Instant.ofEpochMilli(time * 1000L), ZoneId.of("GMT"));
		return ldt.getYear();
	}

	public NewsDto toNewsDto(int id, int index, NewsDukascopy o) {
		NewsDto dto = new NewsDto();
		dto.setId(id);
		dto.setIndex(index);
		dto.setUuid(o.getUuid());
		dto.setDate(o.getDate());
		dto.setTitle(o.getTitle());
		if (o.getPeriodicity() != null) {
			if (o.getPeriodicity().trim().length() > 0) {
				dto.setTitle(dto.getTitle() + " | " + o.getPeriodicity());
			}
		}
		dto.setCountry(o.getCountry());
		dto.setCurrency(o.getCurrency());
		dto.setImpact(o.getImpact());
		dto.setActual(o.getActual());
		dto.setPrevious(o.getPrevious());
		dto.setForecast(o.getForecast());
		if (o.getDescription() != null && o.getDescription().length() > 0) {
			dto.setDescription(o.getDescription());
		}
		return dto;
	}

	public NewsDto toNewsDto(int id, int index, NewsMql5 o) {
		NewsDto dto = new NewsDto();
		dto.setId(id);
		dto.setIndex(index);
		dto.setUuid(o.getUuid());
		dto.setDate(o.getReleaseDate());
		dto.setTitle(o.getEventName());
		dto.setCountry(o.getCountry());
		dto.setCurrency(o.getCurrencyCode());
		dto.setImpact(getNewsImpactAsInt(o.getImportance()));
		dto.setActual(o.getActualValue());
		dto.setPrevious(o.getPreviousValue());
		dto.setForecast(o.getForecastValue());
		return dto;
	}

	public NewsDto toNewsDto(int id, int index, NewsFxStreet o) {
		NewsDto dto = new NewsDto();
		dto.setId(id);
		dto.setIndex(index);
		dto.setUuid(o.getUuid());
		dto.setDate(o.getDate());
		dto.setTitle(o.getName());
		dto.setCountry(o.getCountryCode());
		dto.setCurrency(o.getCurrencyCode());
		dto.setImpact(getNewsImpactAsInt(o.getVolatility()));
		if (o.getActual() != null) {
			dto.setActual(o.getActual() + "");
			if (o.getUnit() != null) {
				if (o.getUnit().equalsIgnoreCase("%")) {
					dto.setActual(dto.getActual() + o.getUnit());
				} else {
					dto.setActual(o.getUnit() + " " + dto.getActual());
					if (o.getPotency() != null) {
						dto.setActual(dto.getActual() + " " + o.getPotency());
					}
				}
			}
		}
		if (o.getPrevious() != null) {
			dto.setPrevious(o.getPrevious() + "");
			if (o.getUnit() != null) {
				if (o.getUnit().equalsIgnoreCase("%")) {
					dto.setPrevious(dto.getPrevious() + o.getUnit());
				} else {
					dto.setPrevious(o.getUnit() + " " + dto.getPrevious());
					if (o.getPotency() != null) {
						dto.setPrevious(dto.getPrevious() + " " + o.getPotency());
					}
				}
			}
		}
		if (o.getConsensus() != null) {
			dto.setForecast(o.getConsensus() + "");
			if (o.getUnit() != null) {
				if (o.getUnit().equalsIgnoreCase("%")) {
					dto.setForecast(dto.getForecast() + o.getUnit());
				} else {
					dto.setForecast(o.getUnit() + " " + dto.getForecast());
					if (o.getPotency() != null) {
						dto.setForecast(dto.getForecast() + " " + o.getPotency());
					}
				}
			}
		}

		return dto;
	}

	public NewsDto toNewsDto(int id, int index, NewsDailyFx o) {
		NewsDto dto = new NewsDto();
		dto.setId(id);
		dto.setIndex(index);
		dto.setUuid(o.getUuid());
		dto.setDate(o.getDate());
		dto.setTitle(o.getTitle());
		dto.setCountry(o.getCountry());
		dto.setCurrency(o.getCurrency());
		dto.setImpact(getNewsImpactAsInt(o.getImportance()));
		dto.setActual(o.getActual());
		dto.setPrevious(o.getPrevious());
		dto.setForecast(o.getForecast());
		if (o.getDescription() != null && o.getDescription().length() > 0) {
			dto.setDescription(o.getDescription());
		}
		return dto;
	}

	public NewsDto toNewsDto(int id, IndexedNews o) {
		NewsDto dto = new NewsDto();
		dto.setId(id);
		dto.setTitle(o.getTitle());
		dto.setCurrency(o.getCurrency().name());
		dto.setImpact(o.getImpact());
		dto.setCountry(o.getCountry());
		dto.setProvider(o.getProvider().toLowerCase());
		return dto;
	}

	public void injectNewsResult(NewsDto dto, NewsResult o) {
		dto.setAvp(o.getAvp());
		dto.setAvpDiff(o.getAvpDiff());
		dto.setAvf(o.getAvf());
		dto.setAvfDiff(o.getAvfDiff());
		dto.setFvp(o.getFvp());
		dto.setFvpDiff(o.getFvpDiff());
		dto.setEffect(o.getEffect());
	}

	private int getNewsImpactAsInt(String impact) {
		if (impact.trim().equalsIgnoreCase("none")) {
			return -1;
		}
		if (impact.trim().equalsIgnoreCase("low")) {
			return 0;
		}
		if (impact.trim().equalsIgnoreCase("medium")) {
			return 1;
		}
		if (impact.trim().equalsIgnoreCase("high")) {
			return 2;
		}
		return 0;
	}

	public String getMql5CountryCodeWhenNull(String currency) {
		String code = "";
		ECurrency curr = ECurrency.valueOf(currency.toUpperCase());
		switch (curr) {
		case AUD:
			code = "AU";
		case CAD:
			code = "CA";
		case CHF:
			code = "CH";
		case EUR:
			code = "EU";
		case GBP:
			code = "GB";
		case JPY:
			code = "JP";
		case NZD:
			code = "NZ";
		case USD:
			code = "US";
		}
		return code;
	}

	public long getThisMonthEndDate() {
		Calendar cal = Calendar.getInstance();
		int eom = cal.getActualMaximum(Calendar.DATE);
		cal.set(Calendar.DAY_OF_MONTH, eom);
		Date date = cal.getTime();
		return date.getTime();
	}

	public double roundUp(double value) {
		DecimalFormat df = new DecimalFormat("#");
		df.setRoundingMode(RoundingMode.CEILING);
		return Double.parseDouble(df.format(value));
	}

	public int getOffsetRecord(int page, int index, int max) {
		int offset = 0;
		if (page > 1) {
			offset = max + index;
		}
		return offset;
	}

	public String formatArray(Object[] objs) {
		String res = "";
		for (Object obj : objs) {
			if (obj instanceof String) {
				res += "'" + obj + "',";
			} else {
				res += obj + ",";
			}
		}
		res = res.substring(0, res.length() - 1);
		return res;
	}

	public String newRandomPassword() {
		SecureRandom random = new SecureRandom();
		return String.format("%08d", random.nextInt(100000000));
	}

}
