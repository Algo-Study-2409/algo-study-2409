import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * 정현우 : PGS 43164 여행경로
 * - 0.25 ms
 * - DFS
 * - HashMap 에 공항 이름별 공항 객체 저장
 * - 이름의 char 3 개로 정렬용 code 생성
 * - 공항별 갈 수 있는 목적지 정렬
 * - 인천에서 출발, 간선 visited 처리 하면서 DFS
 * - 결과 배열 모두 채워지면 종료
 * */
public class PGS43164_여행경로 {
    private static final Airport START = new Airport("ICN"); // 인천 공항

    private static final class Airport implements Comparable<Airport> {
        private static int len; // 결과 배열의 길이
        private static String[] ret; // 결과 배열

        int code;
        int degree;
        boolean[] visited;
        String name;
        ArrayList<Airport> to;

        Airport(String name) {
            this.name = name; // 공항 이름
            this.code = getCode(name); // 정렬용 코드
            to = new ArrayList<>(); // 목적지 목록
        }

        @Override
        public int compareTo(Airport o) {
            return code - o.code;
        }

        private static final int getCode(String name) { // 정렬용 code 생성
            return name.charAt(0) << 16 | name.charAt(1) << 8 | name.charAt(2);
        }

        private final boolean dfs(int depth) { // DFS
            int i;

            ret[depth++] = name; // 결과 배열 채우기
            if (depth == len) { // 결과 배열 모두 채워짐
                return true; // 종료
            }
            for (i = 0; i < degree; i++) { // 간선 탐색
                if (visited[i]) { // 방문한 간선
                    continue;
                }
                visited[i] = true; // 간선 방문 처리
                if (to.get(i).dfs(depth)) { // 다음 공항으로 이동
                    return true; // 해당 간선을 통해 결과 배열이 모두 채워짐
                }
                visited[i] = false; // 방문 여부 원상 복구
            }
            return false; // 현재 상태로 결과 배열 채우기 실패
        }

        final void init() { // 공항 초기화
            degree = to.size(); // 진출 차수
            visited = new boolean[degree]; // 간선 방문 여부
            Collections.sort(to); // 목적지 정렬
        }

        final String[] toStringArray(int len) { // 결과 배열 생성
            Airport.len = len; // 결과 배열의 길이
            ret = new String[len]; // 결과 배열
            dfs(0); // DFS
            return ret;
        }
    }

    public String[] solution(String[][] tickets) {
        Airport u;
        Airport v;
        HashMap<String, Airport> airports;

        airports = new HashMap<>(); // 공항 이름별 공항 객체 저장
        airports.put(START.name, START); // 인천 공항
        for (String[] ticket : tickets) { // 항공권
            if ((u = airports.get(ticket[0])) == null) { // HashMap 에 출발 공항이 없으면
                airports.put(ticket[0], u = new Airport(ticket[0])); // 공항 생성
            }
            if ((v = airports.get(ticket[1])) == null) { // HashMap 에 도착 공항이 없으면
                airports.put(ticket[1], v = new Airport(ticket[1])); // 공항 생성
            }
            u.to.add(v); // 출발 공항의 목적지 목록에 도착 공항 추가
        }
        for (Airport airport : airports.values()) { // 모든 공항
            airport.init(); // 내부 초기화
        }
        return START.toStringArray(tickets.length + 1); // 인천에서 출발, 결과 배열 생성
    }
}
