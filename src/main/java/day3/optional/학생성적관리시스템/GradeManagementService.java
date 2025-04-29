package day3.optional.학생성적관리시스템;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GradeManagementService {
    private Map<String, Student> students = new ConcurrentHashMap<>();

    /**
     * 학생 ID 로 학생을 조회하는 메서드
     * null 일 수 있다.
     */

    /**
     * 학생의 특정과목을 조회하는 메서드
     * 매개변수로는 studentId, 과목명(subject)
     * return 성적을 담은 Optional (빈 Optional)
     */

    /**
     * 학생의 모든 과목 성적 평균을 계산하는 메서드
     * studentId,
     * return 평균 성적을 담은 Optional ( 학생이 없거나 과목이 없으면 빈 Optional)
     */

    /**
     * 학생의 전공 과목(있는 경우) 성적을 조회하는 메서드
     * studentId, majorSubject 전공과목명
     * return 성적 문자열 (학생이 없거나 전공이 없거나 성적이 없으면 기본메시지)
     */

    /**
     * 성적 우수 학생 목록을 반환하는 메서드 (특정 점수 이상)
     * @param subject 과목명
     * @param minGrade 최소 성적 기준
     * @return 성적 우수 학생 이름 목록
     */

    /**
     * 학생의 성적 요약 정보를 문자열로 반환하는 메서드
     * @param studentId 학생 ID
     * @return 성적 요약 문자열, 학생이 없으면 기본 메시지 반환
     */
}
