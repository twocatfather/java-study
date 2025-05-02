package day3.optional.학생성적관리시스템;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class GradeManagementService {
    private Map<String, Student> students = new ConcurrentHashMap<>();

    /**
     * 학생 ID 로 학생을 조회하는 메서드
     * null 일 수 있다.
     */
    public Optional<Student> findStudent(String studentId) {
        return Optional.ofNullable(students.get(studentId));
    }

    /**
     * 학생의 특정과목 성적을 조회하는 메서드
     * 매개변수로는 studentId, 과목명(subject)
     * return 성적을 담은 Optional (빈 Optional)
     */
    public Optional<Integer> getStudentGrade(String studentId, String subject) {
        return findStudent(studentId)
                .flatMap(student -> student.getGrade(subject));
    }


    /**
     * 학생의 모든 과목 성적 평균을 계산하는 메서드
     * studentId,
     * return 평균 성적을 담은 Optional ( 학생이 없거나 과목이 없으면 빈 Optional)
     */
    public Optional<Double> calculateAverageGrade(String studentId) {
        return findStudent(studentId)
                .map(student -> {
                    Map<String, Integer> grades = student.getGrades();
                    return grades.isEmpty() ?
                            Optional.<Double>empty() :
                            Optional.of(grades.values().stream()
                                    .mapToInt(Integer::intValue)
                                    .average()
                                    .orElse(0.0));
                })
                .orElse(Optional.empty());
    }

    /**
     * 학생의 전공 과목(있는 경우) 성적을 조회하는 메서드
     * studentId, majorSubject 전공과목명
     * return 성적 문자열 (학생이 없거나 전공이 없거나 성적이 없으면 기본메시지)
     */
    public String getMajorSubjectGrade(String studentId, String majorSubject) {
        return findStudent(studentId)
                .flatMap(Student::getMajor)
                .map(major -> {
                    return findStudent(studentId)
                            .flatMap(student -> student.getGrade(majorSubject))
                            .map(grade -> String.format("%s 전공 학생의 %s 성적: %d", major, majorSubject, grade))
                            .orElse(String.format("%s 전공 학생이지만 %s 과목 성적이 없습니다.", major, majorSubject));
                })
                .orElse("학생을 찾을 수 없거나 전공 정보가 없습니다.");
    }

    /**
     * 성적 우수 학생 목록을 반환하는 메서드 (특정 점수 이상)
     * @param subject 과목명
     * @param minGrade 최소 성적 기준
     * @return 성적 우수 학생 이름 목록
     */
    public List<String> getTopStudentsInSubject(String subject, int minGrade) {
        return students.values().stream()
                .filter(student -> student.getGrade(subject)
                        .filter(grade -> grade >= minGrade)
                        .isPresent())
                .map(Student::getName)
                .toList();
    }

    /**
     * 학생의 성적 요약 정보를 문자열로 반환하는 메서드
     * @param studentId 학생 ID
     * @return 성적 요약 문자열, 학생이 없으면 기본 메시지 반환
     */
    public String getGradeSummary(String studentId) {
        // ifPresentOrElse()
        return findStudent(studentId)
                .map(student -> {
                    // 평균성적
                    double average = calculateAverageGrade(studentId)
                            .orElse(0.0);

                    // 전공 정보 포함 (있는 경우)
                    String majorInfo = student.getMajor()
                            .map(major -> "전공: " + major)
                            .orElse("전공 미정");

                    return String.format("학생: %s, %s, 평균 성적: %.1f",
                            student.getName(), majorInfo, average);
                })
                .orElse("학생 정보를 찾을 수 없습니다.");
    }
}
