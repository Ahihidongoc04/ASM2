package nghianghia;

import java.util.ArrayList;
import java.util.List;

public class StudentInformationSystem {
    private List<Student> students;

    public StudentInformationSystem() {
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void editStudent(String id, String name, double marks) {
        students.stream()
            .filter(s -> s.getId().equals(id))
            .findFirst()
            .ifPresent(student -> {
                student.setName(name);
                student.setMarks(marks);
            });
    }

    public boolean deleteStudent(String id) {
        return students.removeIf(student -> student.getId().equals(id));
    }

    public void sortStudents() {
        if (students == null || students.size() <= 1) {
            return; // No need to sort if the list is null or has only one element
        }
        students = mergeSort(students);
    }

    private List<Student> mergeSort(List<Student> studentList) {
        if (studentList.size() <= 1) {
            return studentList;
        }

        int mid = studentList.size() / 2;
        List<Student> left = mergeSort(new ArrayList<>(studentList.subList(0, mid)));
        List<Student> right = mergeSort(new ArrayList<>(studentList.subList(mid, studentList.size())));

        return merge(left, right);
    }

    private List<Student> merge(List<Student> left, List<Student> right) {
        List<Student> merged = new ArrayList<>();
        int i = 0, j = 0;

        while (i < left.size() && j < right.size()) {
            if (left.get(i).getMarks() >= right.get(j).getMarks()) {
                merged.add(left.get(i));
                i++;
            } else {
                merged.add(right.get(j));
                j++;
            }
        }

        while (i < left.size()) {
            merged.add(left.get(i));
            i++;
        }

        while (j < right.size()) {
            merged.add(right.get(j));
            j++;
        }

        return merged;
    }

    public Student searchStudentById(String id) {
        return students.stream()
            .filter(student -> student.getId().equals(id))
            .findFirst()
            .orElse(null);
    }

    public List<Student> getStudents() {
        return students;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Student Information System:\n");
        for (Student student : students) {
            sb.append(student.toString()).append("\n");
        }
        return sb.toString();
    }
}
