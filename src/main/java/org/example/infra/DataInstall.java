package org.example.infra;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.example.controller.MovieController;

//gpt 코드
//임시 코드
public class DataInstall {
    private static final DateTimeFormatter YYYYMMDD = DateTimeFormatter.BASIC_ISO_DATE; // yyyyMMdd

    public static void main(String[] args) {
        String start = "20250630"; // 시작일(yyyyMMdd) — 원하는 값으로 바꿔도 됨
        int stepMonths = 2;        // 몇 달씩 내릴지 (요구: 2달)
        int stopYearInclusive = 2020; // 2024년까지 포함하고, 2023으로 넘어가면 종료

        for (String ymd : iterateBackByMonthEnds(start, stepMonths, stopYearInclusive)) {
            // 여기서 실제 호출
            MovieController.fetchMovieWithGenre(ymd);
            // System.out.println(ymd); // 확인용 출력이 필요하면 주석 해제
        }
    }

    /**
     * 시작일을 그 달의 말일로 보정한 뒤, 매번 stepMonths 만큼 감소시키며
     * 매 스텝의 말일(월말) yyyyMMdd 문자열을 반환한다.
     * stopYearInclusive(예: 2024)보다 작은 연도로 떨어지면 중단.
     */
    private static List<String> iterateBackByMonthEnds(String startYmd, int stepMonths, int stopYearInclusive) {
        LocalDate start = LocalDate.parse(startYmd, YYYYMMDD);
        List<String> out = new ArrayList<>();

        // 시작도 월말로 맞춤
        LocalDate current = toEndOfMonth(start);

        while (current.getYear() >= stopYearInclusive) {
            out.add(current.format(YYYYMMDD));
            // 2개월 전으로 이동 후 그 달의 말일로 보정
            current = toEndOfMonth(current.minusMonths(stepMonths));
        }
        return out;
    }

    /** 해당 날짜가 속한 달의 말일로 보정 */
    private static LocalDate toEndOfMonth(LocalDate date) {
        YearMonth ym = YearMonth.from(date);
        return ym.atEndOfMonth();
    }
}
